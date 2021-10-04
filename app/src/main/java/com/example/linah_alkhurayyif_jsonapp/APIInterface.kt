package com.example.linah_alkhurayyif_jsonapp

import retrofit2.http.GET
import retrofit2.Call

interface APIInterface {
    @GET("eur.json")
    fun getCurrency(): Call<CurrencyDetails>?
    }