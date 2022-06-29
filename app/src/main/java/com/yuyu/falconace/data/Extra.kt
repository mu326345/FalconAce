package com.yuyu.falconace.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Extra(
    val created: Int?,
    val description: String? = ""
) : Parcelable