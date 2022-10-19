package com.thoughtworks.ark.core.logging.printer

import android.util.Log
import com.thoughtworks.ark.core.logging.printer.decoration.Decoration

class AndroidPrinter(decorations: List<Decoration> = emptyList()) : Printer(decorations) {

    override fun printLog(priority: Int, tag: String?, message: String) {
        if (priority == Log.ASSERT) {
            Log.wtf(tag, message)
        } else {
            super.printLog(priority, tag, message)
        }
    }

    override fun printLine(priority: Int, tag: String?, message: String) {
        Log.println(priority, tag, message)
    }
}
