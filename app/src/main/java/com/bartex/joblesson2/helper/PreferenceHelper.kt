package com.bartex.joblesson2.helper


import androidx.preference.PreferenceManager
import com.bartex.joblesson2.App
import com.bartex.joblesson2.entity.Constants

class PreferenceHelper(val app: App): IPreferenceHelper {

    override fun savePosition(position: Int) {
        PreferenceManager.getDefaultSharedPreferences(app)
                .edit()
                .putInt(Constants.FIRST_POSITION_STATE, position)
                .apply()
    }

    override fun getPosition(): Int {
        return PreferenceManager.getDefaultSharedPreferences(app)
                .getInt(Constants.FIRST_POSITION_STATE, 0)
    }

    override fun savePage(page: Int) {
        PreferenceManager.getDefaultSharedPreferences(app)
                .edit()
                .putInt(Constants.PAGE_POSITION_STATE, page)
                .apply()
    }

    override fun getPage(): Int {
        return PreferenceManager.getDefaultSharedPreferences(app)
                .getInt(Constants.PAGE_POSITION_STATE, 1)
    }
}