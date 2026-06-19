package com.example.aot_api.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val info: PaginationInfo,
    val results: List<T>
)

@Serializable
data class PaginationInfo(
    val count: Int,       // Nombre total
    val pages: Int,       // Nombre de pages
    val next: String? = null,  // URL de la page suivante
    val prev: String? = null   // URL de la page précédente
)
