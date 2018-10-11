package com.liviet.hoo.liviet.utils

import java.util.*


@Suppress("unused")
class Utils {
    companion object {
        fun makeCalendarToDate(calendar: Calendar): Date{
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)
            return calendar.time
        }
    }
}