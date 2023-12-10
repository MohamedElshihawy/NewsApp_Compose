package com.example.newsgate.news.utils

//
// @RequiresApi(Build.VERSION_CODES.O)
// fun formatTimeAgo(dateString: String): String {
//    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
//    val parsedDate: LocalDateTime = LocalDateTime.parse(dateString, formatter)
//    val now = LocalDateTime.now()
//
//    val diffInSeconds = Duration.between(parsedDate, now).toSeconds()
//
//    return when {
//        diffInSeconds < 60 -> "$diffInSeconds seconds ago"
//        diffInSeconds < 3600 -> "${diffInSeconds / 60} minutes ago"
//        else -> "${diffInSeconds / 3600} hours ago"
//    }
// }
