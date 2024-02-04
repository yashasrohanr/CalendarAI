package com.example.magellancalendar.Helpers

import java.time.LocalDate

data class Day(
    val date : LocalDate,
    var isSelected :Boolean,
    var event1StartX :Int,
    var event1StartY : Int,
    var event1EndX : Int,
    var event1EndY : Int)
