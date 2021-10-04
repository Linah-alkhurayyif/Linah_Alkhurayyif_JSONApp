package com.example.linah_alkhurayyif_jsonapp

import com.google.gson.annotations.SerializedName

class CurrencyDetails {
    @SerializedName("date")
    var date: String? = null

    @SerializedName("eur")
    var eur: Currency? = null

    class Currency {

        @SerializedName("inr")
        var inr: String? = null

        @SerializedName("usd")
        var usd: String? = null

        @SerializedName("aud")
        var aud: String? = null

        @SerializedName("sar")
        var sar: String? = null

        @SerializedName("cny")
        var cny: String? = null

        @SerializedName("jpy")
        var jpy: String? = null
    }
}