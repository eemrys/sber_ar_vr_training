package com.example.android.devbyteviewer.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface DevbyteService {
    @GET("devbytes.json")
    fun getPlaylistAsync(): Deferred<NetworkVideoContainer>
}