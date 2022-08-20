package core

fun Int.secondsInMillis() = 1000L * this

fun Int.minutesInMillis() = 60L * this.secondsInMillis()

fun Int.hoursInMillis() = 60L * this.minutesInMillis()

@Suppress("unused")
fun Int.daysInMillis() = 24L * this.hoursInMillis()