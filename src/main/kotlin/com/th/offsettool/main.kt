package com.th.offsettool

import com.th.offsettool.bean.Value
import com.th.offsettool.main.PresenterMain
import com.th.offsettool.ui.UIMain


fun main() {
    val presenter = PresenterMain()
    UIMain(presenter)

    presenter.apply {
        this.mData.mBases.add(Value(0x10000))
        this.mData.mOffsets.add(Value(0x2))
        this.mView.updateUI(this.mData)
    }

}
