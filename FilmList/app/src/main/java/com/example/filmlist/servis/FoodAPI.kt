package com.example.filmlist.servis

import com.example.filmlist.model.Food
import io.reactivex.Single
import retrofit2.http.GET

interface FoodAPI {

    // GET(verileri cekmek için)
    // POST(sunucuya veri gonderilecekse)

    // https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json
    // BASE_URL -> https://raw.githubusercontent.com
    // atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json

    // rxJava da Single türünü kullanıcaz
    // Single<T> -> Tek bir öğe veya bir hata olayı yayar.

    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    fun getFood(): Single<List<Food>>
}