package com.yuyu.falconace.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val _meta: Meta?,
    val appearance: Appearance?,
    val extra: Extra?,
    val items: List<ItemX>?,
    val ref: String?,
    val source: String?,
    val style: String?,
    val title: String?,
    val type: String?
) : Parcelable