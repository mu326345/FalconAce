package com.yuyu.falconace.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemX(
    val _meta: Meta?,
    val appearance: Appearance?,
    val extra: Extra?,
    val ref: String?,
    val source: String?,
    val type: String?
) : Parcelable