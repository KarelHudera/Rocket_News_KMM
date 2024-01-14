package com.example.rocketnews.helpers

fun formatRocketsDate(date: String): String {

    val parts = date.split("T", "-", ":", ".", "Z")
    val year = parts[0]
    val month = parts[1]
    val day = parts[2]
    val hour = parts[3]
    val minute = parts[4]

    return "$year $month. $day. $hour:$minute"
}