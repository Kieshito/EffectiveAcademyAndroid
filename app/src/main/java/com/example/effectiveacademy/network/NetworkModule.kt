package com.example.effectiveacademy.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.security.MessageDigest

object NetworkModule {
    private const val BASE_URL = "https://gateway.marvel.com/"
    private const val PUBLIC_KEY: String = "b599b2cea5c8b794ba83cadf41a70e67"
    private const val PRIVATE_KEY: String = "768333402f2317458d0ead51c300c9f3947a0b64"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val marvelApi: MarvelApi = retrofit.create(MarvelApi::class.java)

    //???
    fun generateHash(timestamp: String): String {
        val input = timestamp + PRIVATE_KEY + PUBLIC_KEY
        val md = MessageDigest.getInstance("MD5")
        val digest = md.digest(input.toByteArray())
        return digest.joinToString("") {"%02x".format(it)}
    }

    fun getApiKey(): String{
        return PUBLIC_KEY
    }
}