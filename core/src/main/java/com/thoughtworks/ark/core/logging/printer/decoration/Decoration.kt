package com.thoughtworks.ark.core.logging.printer.decoration

/**
 * Add extra info for each basic call
 */
interface Decoration {

    /**
     * Add a line before log call
     */
    fun header(): String? = null
    /**
     * Add extra info before log line
     */
    fun start(): String? = null
    /**
     * Add a line after log call
     */
    fun footer(): String? = null
}
