package com.yuyu.falconace.data.source

import com.yuyu.falconace.data.News
import com.yuyu.falconace.data.Result

interface INewsRepository{

    suspend fun getNewsResult(): Result<News>
}