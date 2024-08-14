package com.raihan.testportal.data.mapper

import com.raihan.testportal.data.model.User
import com.raihan.testportal.data.source.network.model.Data

fun Data?.toUser() =
    User(
        id = this?.id ?: 0,
        email = this?.email.orEmpty(),
        firstName = this?.firstName.orEmpty(),
        lastName = this?.lastName.orEmpty(),
        avatar = this?.avatar.orEmpty(),
    )

fun Collection<Data>?.toUserList() =
    this?.map {
        it.toUser()
    } ?: listOf()
