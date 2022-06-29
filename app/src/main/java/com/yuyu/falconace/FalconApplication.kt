package com.yuyu.falconace

import android.app.Application
import com.yuyu.falconace.data.source.NewsRepository
import com.yuyu.falconace.data.source.remote.NewsRemoteDataSource
import kotlin.properties.Delegates

class FalconApplication : Application() {

    val repository = NewsRepository(NewsRemoteDataSource(this))

    companion object {
        var instance: FalconApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}