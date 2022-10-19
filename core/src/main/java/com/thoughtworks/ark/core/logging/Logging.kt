package com.thoughtworks.ark.core.logging

import android.util.Log
import java.io.PrintWriter
import java.io.StringWriter
import java.util.regex.Pattern

@Suppress("TooManyFunctions", "ThrowingExceptionsWithoutMessageOrCause")
abstract class Logging {
    protected open val globalTag: String? = null
    protected val userTag = ThreadLocal<String>()
    private val tag: String?
        get() {
            val tag = userTag.get()
            if (tag != null) {
                userTag.remove()
            }
            return tag ?: globalTag ?: Throwable().stackTrace
                .first { it.className !in classesIgnore }
                .let(::createStackElementTag)
        }

    private val classesIgnore = listOf(
        Logger::class.java.name,
        Logger.Default::class.java.name,
        Logging::class.java.name
    )

    /**
     * Extract the tag from the element.
     *
     * This will not be called if a [tag] was specified.
     */
    protected open fun createStackElementTag(element: StackTraceElement): String? {
        var tag = element.className.substringAfterLast('.')
        val matcher = ANONYMOUS_CLASS.matcher(tag)
        if (matcher.find()) {
            tag = matcher.replaceAll("")
        }
        return if (tag.length <= MAX_TAG_LENGTH) {
            tag
        } else {
            tag.substring(0, MAX_TAG_LENGTH)
        }
    }
    /** Log a verbose message */
    open fun v(message: String?, vararg args: Any?) {
        prepareAndPrint(Log.VERBOSE, null, message, *args)
    }

    /** Log a verbose exception and a message */
    open fun v(throwable: Throwable?, message: String?, vararg args: Any?) {
        prepareAndPrint(Log.VERBOSE, throwable, message, *args)
    }

    /** Log a verbose exception. */
    open fun v(throwable: Throwable?) {
        prepareAndPrint(Log.VERBOSE, throwable, null)
    }

    /** Log a debug message */
    open fun d(message: String?, vararg args: Any?) {
        prepareAndPrint(Log.DEBUG, null, message, *args)
    }

    /** Log a debug exception and a message */
    open fun d(throwable: Throwable?, message: String?, vararg args: Any?) {
        prepareAndPrint(Log.DEBUG, throwable, message, *args)
    }

    /** Log a debug exception. */
    open fun d(throwable: Throwable?) {
        prepareAndPrint(Log.DEBUG, throwable, null)
    }

    /** Log an info message */
    open fun i(message: String?, vararg args: Any?) {
        prepareAndPrint(Log.INFO, null, message, *args)
    }

    /** Log an info exception and a message */
    open fun i(throwable: Throwable?, message: String?, vararg args: Any?) {
        prepareAndPrint(Log.INFO, throwable, message, *args)
    }

    /** Log an info exception. */
    open fun i(throwable: Throwable?) {
        prepareAndPrint(Log.INFO, throwable, null)
    }

    /** Log a warning message */
    open fun w(message: String?, vararg args: Any?) {
        prepareAndPrint(Log.WARN, null, message, *args)
    }

    /** Log a warning exception and a message */
    open fun w(throwable: Throwable?, message: String?, vararg args: Any?) {
        prepareAndPrint(Log.WARN, throwable, message, *args)
    }

    /** Log a warning exception. */
    open fun w(throwable: Throwable?) {
        prepareAndPrint(Log.WARN, throwable, null)
    }

    /** Log an error message */
    open fun e(message: String?, vararg args: Any?) {
        prepareAndPrint(Log.ERROR, null, message, *args)
    }

    /** Log an error exception and a message */
    open fun e(throwable: Throwable?, message: String?, vararg args: Any?) {
        prepareAndPrint(Log.ERROR, throwable, message, *args)
    }

    /** Log an error exception. */
    open fun e(throwable: Throwable?) {
        prepareAndPrint(Log.ERROR, throwable, null)
    }

    /** Log an assert message */
    open fun wtf(message: String?, vararg args: Any?) {
        prepareAndPrint(Log.ASSERT, null, message, *args)
    }

    /** Log an assert exception and a message */
    open fun wtf(throwable: Throwable?, message: String?, vararg args: Any?) {
        prepareAndPrint(Log.ASSERT, throwable, message, *args)
    }

    /** Log an assert exception. */
    open fun wtf(throwable: Throwable?) {
        prepareAndPrint(Log.ASSERT, throwable, null)
    }

    private fun prepareAndPrint(priority: Int, throwable: Throwable?, message: String?, vararg args: Any?) {
        // Consume tag even not loggable, make sure next message is correctly tagged.
        val tag = tag
        if (!isLoggable(tag, priority)) {
            return
        }

        var msg = message
        if (msg.isNullOrEmpty()) {
            if (throwable == null) {
                return // Swallow message if it's null and there's no throwable.
            }
            msg = getStackTraceString(throwable)
        } else {
            if (args.isNotEmpty()) {
                msg = formatMessage(msg, args)
            }
            if (throwable != null) {
                msg += "\n" + getStackTraceString(throwable)
            }
        }

        printLog(priority, tag, msg)
    }

    private fun getStackTraceString(throwable: Throwable): String {
        val sw = StringWriter(MAX_STACK_TRACK_SIZE)
        val pw = PrintWriter(sw, false)
        throwable.printStackTrace(pw)
        pw.flush()
        return sw.toString()
    }

    /**
     * For interceptors
     */
    protected abstract fun isLoggable(tag: String?, priority: Int): Boolean

    /**
     * For formatters
     */
    protected abstract fun formatMessage(message: String, args: Array<out Any?>): String

    /**
     * For printers
     * Write log message to its destination.
     *
     * @param priority Log level. See [Log] for constants.
     * @param tag Explicit or inferred tag. May be `null`.
     * @param message Formatted log message.
     */
    protected abstract fun printLog(priority: Int, tag: String?, message: String)

    companion object {
        private const val MAX_TAG_LENGTH = 23
        private const val MAX_STACK_TRACK_SIZE = 256
        private val ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$")
    }
}
