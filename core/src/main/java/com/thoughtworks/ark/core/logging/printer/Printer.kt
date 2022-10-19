package com.thoughtworks.ark.core.logging.printer

import android.util.Log
import com.thoughtworks.ark.core.logging.printer.decoration.Decoration
import kotlin.math.min

/**
 * An output stream to print formatted log
 */
abstract class Printer(private val decorations: List<Decoration>) {

    /**
     * Each log call will call this method once to print log
     */
    open fun printLog(priority: Int, tag: String?, message: String) {
        val starts = decorations.map { it.start().orEmpty() }
        printHeaders(starts, priority, tag)
        spiltToLines(message).forEach {
            printLine(priority, tag, "${starts.joinToString("")}$it")
        }
        printFooters(starts, priority, tag)
    }

    /**
     * Each log line will call this method once
     */
    protected abstract fun printLine(priority: Int, tag: String?, message: String)

    private fun printHeaders(starts: List<String>, priority: Int, tag: String?) {
        decorations.forEachIndexed { index, decoration ->
            decoration.header()?.let { header ->
                printLine(priority, tag, "${starts.subList(0, index).joinToString("")}$header")
            }
        }
    }

    private fun printFooters(starts: List<String>, priority: Int, tag: String?) {
        decorations.reversed().forEachIndexed { index, decoration ->
            decoration.footer()?.let { footer ->
                printLine(priority, tag, "${starts.subList(0, starts.size - index - 1).joinToString("")}$footer")
            }
        }
    }

    companion object {
        private const val MAX_LOG_LENGTH = 4000

        fun spiltToLines(message: String): List<String> {
            val rawLines = message.split('\n')
            if (rawLines.all { it.length < MAX_LOG_LENGTH}) {
                return rawLines
            }

            val lines = mutableListOf<String>()
            rawLines.forEach {
                val length = it.length
                if (length < MAX_LOG_LENGTH) {
                    lines.add(it)
                } else {
                    var i = 0
                    while (i < length) {
                        val end = min(length, i + MAX_LOG_LENGTH)
                        lines.add(message.substring(i, end))
                        i = end
                    }
                }
            }
            return lines.toList()
        }

        fun logLevel(priority: Int): String {
            return when (priority) {
                Log.VERBOSE -> "V"
                Log.DEBUG -> "D"
                Log.WARN -> "W"
                Log.INFO -> "I"
                Log.ERROR -> "E"
                Log.ASSERT -> "F"
                else -> ""
            }
        }
    }
}
