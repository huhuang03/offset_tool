package com.th.offsettool.main

import com.th.offsettool.bean.DataMain

interface ContactMain {
    interface Presenter {
        fun setView(view: View);
        fun addBase(base: String)
        fun addOffset(offset: String)
    }

    interface View {
        fun updateUI(dataMain: DataMain)
    }
}