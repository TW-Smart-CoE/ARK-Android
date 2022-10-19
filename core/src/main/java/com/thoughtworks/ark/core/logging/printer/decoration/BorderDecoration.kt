package com.thoughtworks.ark.core.logging.printer.decoration

class BorderDecoration: Decoration {

    private val header = "╔═════════════════════════════════"
    private val start =  "║ "
    private val footer = "╚═════════════════════════════════"

    override fun header() = header
    override fun footer() = footer
    override fun start() = start
}
