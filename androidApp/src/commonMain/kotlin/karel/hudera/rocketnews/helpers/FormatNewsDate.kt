package karel.hudera.rocketnews.helpers

fun formatNewsDate(date: String): String {

    val parts = date.split("-")
    val year = parts[0]
    val month = parts[1]
    val day = parts[2]

    return "$year $month. $day."
}