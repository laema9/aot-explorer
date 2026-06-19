package com.example.aot_api.navigation

object Routes {
    const val CHARACTER_LIST = "character_list"
    const val CHARACTER_DETAIL = "character_detail/{characterId}"
    fun characterDetail(id: Int) = "character_detail/$id"
}
