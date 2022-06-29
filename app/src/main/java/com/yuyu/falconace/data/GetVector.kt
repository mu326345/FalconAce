package com.yuyu.falconace.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetVector(
    val items: List<Item>?
) : Parcelable