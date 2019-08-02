package com.th.offsettool.mpv.main

import com.th.offsettool.bean.DataMain
import java.io.File

interface ContactMain {
    interface Presenter {
        fun setView(view: View);
        fun addBase(base: String)
        fun addOffset(offset: String)
        fun saveFile(file: File?);
        fun loadFile(file: File?);
    }

    interface View {
        fun updateUI(dataMain: DataMain)
    }
}