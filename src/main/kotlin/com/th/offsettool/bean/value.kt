package com.th.offsettool.bean

import java.util.regex.Pattern


class Value(val value: Long) {
    companion object {
        private fun dealValueText(valueText: String): String {
            var processedText = valueText.trim()
            if (processedText.startsWith("0x", ignoreCase = true)) {
                processedText = processedText.substring(2)
            }
            processedText = processedText.trim('0')
            if (processedText.endsWith('h', ignoreCase = true)) {
                processedText = processedText.substring(0, processedText.length - 1)
            }

            return processedText
        }

        fun validate(valueText: String): Boolean {
            val processedText = dealValueText(valueText)
            return Pattern.compile("[0-9a-fA-F]{1,8}").matcher(processedText).find()
        }

        /**
         * get a value from text
         */
        fun getValue(valueText: String): Value? {
            if (!validate(valueText)) {
                return null
            }
            return Value(java.lang.Long.parseLong(dealValueText(valueText)))
        }
    }

    fun show(len: Int = 8): String {
        if (len <= 0) {
            return "0x%x".format(value)
        }
        return "0x%0${len}x".format(value)
    }

    fun add(value: Value): Value {
        return Value(this.value + value.value)
    }
}

class DataMain {
    var mBases: MutableList<Value> = mutableListOf()
    var mOffsets: MutableList<Value> = mutableListOf()

    fun getValue(baseIndex: Int, offsetIndex: Int): Value {
        return mBases[baseIndex].add(mOffsets[offsetIndex])
    }

    override fun toString(): String {
        return "DataMain(mBases=$mBases, mOffsets=$mOffsets)"
    }


}