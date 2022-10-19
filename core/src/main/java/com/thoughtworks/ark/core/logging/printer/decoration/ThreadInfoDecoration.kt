package com.thoughtworks.ark.core.logging.printer.decoration

class ThreadInfoDecoration : Decoration {

    override fun header(): String {
        return "Thread: ${Thread.currentThread().name}"
    }
}
