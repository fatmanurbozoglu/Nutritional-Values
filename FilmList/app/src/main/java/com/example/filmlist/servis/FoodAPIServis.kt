package com.example.filmlist.servis

import com.example.filmlist.model.Food
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class FoodAPIServis {
    // https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json
    // BASE_URL -> https://raw.githubusercontent.com
    // atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json
    // Api yi aldıgımız linkin bas degismeyen kısmını base url icerisine ekleriz
    private val BASE_URL = "https://raw.githubusercontent.com"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()) // addConverterFactory -> json formatını modele ceviricez
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // RxJava kullanacagımızı belirtiyoruz
        .build()
        .create(FoodAPI ::class.java)

    fun getData(): Single<List<Food>>{
        return api.getFood()
    }
}
