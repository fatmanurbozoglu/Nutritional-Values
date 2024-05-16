package com.example.filmlist.utils

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.filmlist.R

fun ImageView.downloadImage(url: String, placeHolder: CircularProgressDrawable){
    // Glide kullanımı
    // picasso gibi bu sekilde de kullanabiliriz ->
    // Glide.with(this).load("https://goo.gl/gEgYUd").into(imageView)
    // Glide
    //    .with(myFragment)  -> nereye yüklenecek
    //    .load(url)        -> url si ne
    //    .centerCrop()     -> merkezi olarak kesme islemleri
    //    .placeholder(R.drawable.loading_spinner) -> yuklenmeden önce gösterilecek bir sey var mı
    //    .into(myImageView);

   // Glide.with(context).load(url).into(this)

    val options = RequestOptions().placeholder(placeHolder).error(R.mipmap.ic_launcher_round)
    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)

}
fun makePlaceHolder(context: Context): CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        // strokeWidth -> yukleniyor isaretının kalınlıgını gösterir
        // centerRadius -> yarıcapı
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}

// data binding kısmı icin
@BindingAdapter("android:downloadImage")
fun downloadImageDatabinding(view: ImageView, url: String){
    view.downloadImage(url, makePlaceHolder(view.context))
}