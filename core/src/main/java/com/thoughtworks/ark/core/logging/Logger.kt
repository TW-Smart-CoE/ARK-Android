package com.thoughtworks.ark.core.logging

import com.thoughtworks.ark.core.logging.formatter.DefaultFormatter
import com.thoughtworks.ark.core.logging.formatter.Formatter
import com.thoughtworks.ark.core.logging.formatter.SecurityFormatter
import com.thoughtworks.ark.core.logging.interceptor.Interceptor
import com.thoughtworks.ark.core.logging.interceptor.LogLevelInterceptor
import com.thoughtworks.ark.core.logging.printer.AndroidPrinter
import com.thoughtworks.ark.core.logging.printer.Printer
import com.thoughtworks.ark.core.utils.isDevEnvironment

class Logger private constructor() {
    data class Strategy(
        val globalTag: String? = null,
        val interceptors: List<Interceptor> = emptyList(),
        val dataFormatter: Formatter = if (isDevEnvironment()) DefaultFormatter() else SecurityFormatter(),
        val printers: List<Printer> = emptyList()
    )

    class StrategyGenerator {
        private var globalTag: String? = null
        private var interceptors: MutableList<Interceptor> = mutableListOf()
        private var dataFormatter: Formatter = if (isDevEnvironment()) DefaultFormatter() else SecurityFormatter()
        private var printers: MutableList<Printer> = mutableListOf()

        fun setLogLevel(level: Int) {
            if (level > 0) {
                interceptors.add(LogLevelInterceptor(logLevel = level))
            }
        }

        fun setTag(tag: String?) {
            globalTag = tag
        }

        fun addInterceptor(interceptor: Interceptor) {
            interceptors.add(interceptor)
        }

        fun setFormatter(formatter: Formatter) {
            dataFormatter = formatter
        }

        fun addPrinter(printer: Printer) {
            printers.add(printer)
        }

        internal fun generate(configuration: (StrategyGenerator) -> Unit): Strategy {
            configuration(this)
            return Strategy(
                globalTag,
                interceptors,
                dataFormatter,
                printers
            )
        }
    }

    companion object Default : Logging() {
        private val logcatStrategy = Strategy(
            interceptors = listOf(LogLevelInterceptor(0)),
            printers = listOf(AndroidPrinter())
        )

        private lateinit var strategy: Strategy

        override val globalTag: String?
            get() = strategy.globalTag

        @Synchronized
        fun setUp(loggerStrategy: Strategy = logcatStrategy) {
            require(loggerStrategy.printers.isNotEmpty()) { "There has to be a printer" }
            if (!this::strategy.isInitialized) {
                strategy = loggerStrategy
            }
        }

        @Synchronized
        fun setUp(configure: (StrategyGenerator) -> Unit) {
            val loggerStrategy = StrategyGenerator().generate(configure)
            require(loggerStrategy.printers.isNotEmpty()) { "There has to be a printer" }
            if (!this::strategy.isInitialized) {
                strategy = loggerStrategy
            }
        }

        /**
         * Set tag for next log call, will be clear after the call
         */
        fun onceTag(tag: String): Logging {
            userTag.set(tag)
            return this
        }

        override fun printLog(priority: Int, tag: String?, message: String) {
            require(this::strategy.isInitialized) { "Logger is not setup" }
            strategy.printers.forEach { it.printLog(priority, tag, message) }
        }

        override fun isLoggable(tag: String?, priority: Int): Boolean {
            require(this::strategy.isInitialized) { "Logger is not setup" }
            return strategy.interceptors.any { it.intercept(tag, priority) }.not()
        }

        override fun formatMessage(message: String, vararg args: Any?): String {
            require(this::strategy.isInitialized) { "Logger is not setup" }
            return strategy.dataFormatter.format(message, *args)
        }
    }
}
