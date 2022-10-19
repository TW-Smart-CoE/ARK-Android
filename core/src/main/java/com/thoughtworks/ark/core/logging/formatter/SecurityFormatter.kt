package com.thoughtworks.ark.core.logging.formatter

class SecurityFormatter : Formatter {
    private val formatSpecifierRegex = "%(\\d+\\$)?([-#+ 0,(<]*)?(\\d+)?(\\.\\d+)?([tT])?([a-zA-Z%])".toRegex()

    override fun format(message: String, vararg args: Any?): String {
        return message.replace(formatSpecifierRegex, "****")
    }
}
