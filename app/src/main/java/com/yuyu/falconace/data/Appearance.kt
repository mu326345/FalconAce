package com.yuyu.falconace.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Appearance(
    val mainTitle: String?,
    val subTitle: String?,
    val subscript: String?,
    val thumbnail: String?
) : Parcelable