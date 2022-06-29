package com.yuyu.falconace

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.text.SimpleDateFormat

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imageUrl = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imageUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_launcher_foreground))
            .into(imgView)
    }
}

@BindingAdapter("createTime")
fun timeFormat(textView: TextView, time: Int) {
    val format = SimpleDateFormat("MMM dd, yyyy - hh:mm a")
    val currentDate = format.format(time)

    textView.text = currentDate
}

@BindingAdapter("refString")
fun refString(textView: TextView, ref: String) {
    textView.text = ref.split("/")[2]
}
