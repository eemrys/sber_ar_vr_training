package com.example.android.gdgfinder.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface GdgApiService {
    @GET("directory.json")
    fun getChaptersAsync():
            Deferred<GdgResponse>
}