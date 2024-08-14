package com.raihan.testportal.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var id: Int,
    var email: String,
    var firstName: String,
    var lastName: String,
    var avatar: String,
) : Parcelable
