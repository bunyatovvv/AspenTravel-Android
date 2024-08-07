package com.behmennnn.aspentravel.common.util

import android.animation.ValueAnimator
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.behmennnn.aspentravel.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.setImageURL(url: String, context: Context) {
    val options = RequestOptions.placeholderOf(placeHolder(context))
        .error(R.drawable.ic_launcher_foreground)
    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)
}

private fun placeHolder(context: Context): CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 8f
    circularProgressDrawable.centerRadius = 40f
    circularProgressDrawable.start()
    return circularProgressDrawable
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun TextView.animateCharacterByCharacter(text: String, delay: Long = 15L) {

    if (text.isEmpty()) return

    val charAnimation = ValueAnimator.ofInt(0, text.length)

    charAnimation.apply {
        this.duration = delay * text.length.toLong()
        this.repeatCount = 0
        addUpdateListener {
            val charCount = it.animatedValue as Int
            val animatedText = text.substring(0, charCount)
            this@animateCharacterByCharacter.text = animatedText
        }
    }
    charAnimation.start()
}