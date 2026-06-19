package com.example.aot_api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Group(
    val name: String? = null
)

@Serializable
data class Character(
    val id: Int,
    val name: String,
    @SerialName("img") val img: String? = null,
    val alias: List<String> = emptyList(),
    val species: List<String> = emptyList(),
    val gender: String? = null,
    val age: String? = null,
    val height: String? = null,
    @SerialName("birthplace") val birthplace: String? = null,
    val residence: String? = null,
    val status: String? = null,
    val occupation: String? = null,
    @SerialName("groups") val groups: List<Group> = emptyList(),
    val roles: List<String> = emptyList(),
    val episodes: List<String> = emptyList())