package com.th.offsettool.util

import java.io.File
import javax.swing.filechooser.FileFilter

const val EXTENSION_DOT = ".json"

const val DEFAULT_FILE_NAME = "offsets.json"

val FILE_FILTER = object: FileFilter() {
    override fun accept(f: File?): Boolean {
        return f != null && f.isFile && f.endsWith(EXTENSION_DOT)
    }

    override fun getDescription(): String {
        return "the json file"
    }
}
