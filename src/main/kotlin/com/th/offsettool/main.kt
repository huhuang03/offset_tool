package com.th.offsettool

import com.th.offsettool.mpv.main.PresenterMain
import com.th.offsettool.mpv.main.UIMain


fun main() {
    val presenter = PresenterMain()
    UIMain(presenter)

    presenter.apply {
        this.mView.updateUI(this.mData)
    }

}
