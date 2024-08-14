package com.raihan.testportal.data.source.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Support(
    @SerializedName("url")
    val url: String,
    @SerializedName("text")
    val text: String,
)
