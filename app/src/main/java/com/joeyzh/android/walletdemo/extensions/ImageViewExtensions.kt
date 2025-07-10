package com.joeyzh.android.walletdemo.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide

/**
 * @Date 2025/7/10
 * @Create Joey 周晔
 * @Description ImageView的扩展类
 */

fun ImageView.setUrl(url: String?,res:Int){
    Glide.with(this.context)
        .load(url)
        .circleCrop()
        .error(res)
        .into(this)
}