package com.djevannn.core.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SearchUtils {

    fun getSortedQuery(query: String): SimpleSQLiteQuery {
        return SimpleSQLiteQuery("SELECT * FROM food_entities where title LIKE '%$query%'")
    }

}