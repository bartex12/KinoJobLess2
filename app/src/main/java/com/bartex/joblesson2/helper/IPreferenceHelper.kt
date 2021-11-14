package com.bartex.joblesson2.helper

interface IPreferenceHelper {
    fun savePosition(position:Int)
    fun getPosition(): Int
    fun savePage(page:Int)
    fun getPage(): Int

}