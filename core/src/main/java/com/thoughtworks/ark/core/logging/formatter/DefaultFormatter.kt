package com.thoughtworks.ark.core.logging.formatter

class DefaultFormatter : Formatter {

    override fun format(message: String, vararg args: Any?): String {
        return message.format(*args)
    }
}
