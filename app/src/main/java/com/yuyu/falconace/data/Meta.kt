package com.yuyu.falconace.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meta(
    val category: List<String>?,
    val section: String? = ""
) : Parcelable