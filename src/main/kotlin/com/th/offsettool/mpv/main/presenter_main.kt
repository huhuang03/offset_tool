package com.th.offsettool.mpv.main

import com.google.gson.Gson
import com.th.offsettool.bean.DataMain
import com.th.offsettool.bean.Value
import com.th.offsettool.util.logerr
import java.io.File
import java.lang.Exception

class PresenterMain : ContactMain.Presenter {
    override fun saveFile(file: File?) {
        if (file == null) return
        file.printWriter().use {
            print(Gson().toJson(this.mData))
        }
    }

    override fun loadFile(file: File?) {
        if (file == null) return
        try {
            mData = Gson().fromJson(file.readText(), DataMain::class.java)
            mView.updateUI(mData)
        } catch (e: Exception) {
            logerr(e)
        }
    }

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