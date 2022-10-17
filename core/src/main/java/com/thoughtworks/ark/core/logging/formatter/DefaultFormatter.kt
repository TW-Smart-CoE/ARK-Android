package com.thoughtworks.ark.core.logging.formatter

class DefaultFormatter: Formatter {

    override fun format(message: String, args: Array<out Any?>): String {
        return message.format(*args)
    }
}
