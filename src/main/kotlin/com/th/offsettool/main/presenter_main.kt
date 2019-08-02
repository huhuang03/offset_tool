package com.th.offsettool.main

import com.th.offsettool.bean.DataMain
import com.th.offsettool.bean.Value
import com.th.offsettool.ui.UIMain

class PresenterMain(): ContactMain.Presenter {

    var mData: DataMain = DataMain()
    lateinit var mView: ContactMain.View

    override fun setView(view: ContactMain.View) {
        this.mView = view
    }

    override fun addBase(base: String) {
        if (!Value.validate(base)) {
            println("base is note validate")
            return
        }
        mData.mBases.add(Value.getValue(base)!!)
        mView.updateUI(mData)
    }

    override fun addOffset(offset: String) {
        if (!Value.validate(offset)) {
            println("offset is note validate")
            return
        }
        mData.mOffsets.add(Value.getValue(offset)!!)
        mView.updateUI(mData)
    }

}