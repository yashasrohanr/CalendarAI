package com.example.magellancalendar

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.magellancalendar.Helpers.Day
import java.util.Collections
@RequiresApi(Build.VERSION_CODES.O)
class SharedViewModel : ViewModel() {
    var daysWithCoordinates: ArrayList<Day> = arrayListOf()
    val LOGTAG = "SharedViewModel"
    companion object {
        private var instance: SharedViewModel? = null
        fun getInstance(): SharedViewModel {
            return instance ?: synchronized(this) {
                instance ?: SharedViewModel().also { instance = it }
            }
        }
    }
    fun addDayWithCoordinates(day: Day) {
        if (daysWithCoordinates.isNotEmpty() && daysWithCoordinates.contains(day)) return
        daysWithCoordinates.add(day)
        Log.d(LOGTAG, "addDayWithCoordinates: added day = ${day.date.dayOfMonth} to viewmodel")
    }
    fun getDaysWithCoordinates(): List<Day> {
        Log.d(LOGTAG, "getDaysWithCoordinates: when called listSize = ${daysWithCoordinates.size}")
        return Collections.unmodifiableList(daysWithCoordinates)
    }
}
