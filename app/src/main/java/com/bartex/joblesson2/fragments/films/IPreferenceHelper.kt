package com.bartex.joblesson2.fragments.films

interface IPreferenceHelper {
    fun savePosition(position:Int)
    fun getPosition(): Int
    fun savePage(page:Int)
    fun getPage(): Int

}