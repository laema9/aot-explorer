package com.example.aot_api.network

import com.example.aot_api.model.ApiResponse
import com.example.aot_api.model.Character
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
interface ApiService {
    @GET("characters")
    suspend fun getCharacters(
        @Query("page") page: Int = 1
    ): ApiResponse<Character>
    @GET("characters/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): Character
}
