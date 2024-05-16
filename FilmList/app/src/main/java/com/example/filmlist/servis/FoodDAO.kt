package com.example.filmlist.servis

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.filmlist.model.Food

// verileri eklediğimiz, verileri cektigımız seyleri buraya ekleyecegız

@Dao
interface FoodDAO {
    //  DAO (Data Access Object)

    // Insert -> Room'dan gelıyor ve sqlite'da insert into işlemini yapmak için
    @Insert
    // Insert'ın altına fonksıyonu ve ne yapacagını yaz

    // suspend fonksiyon -> bu fonksıyon coroutine scope içerisinde olabılıyor. bu fonk. coroutine içerisinde durdurulup devam ettirilebiliyor
    suspend fun insertAll(vararg food: Food): List<Long>
        // vararg -> birden fazla ve istediğimiz sayıda olusturulabılıyor
        // List<Long> -> long dondurmesinin sebebi id'ler


    // veri cekme islemi

    @Query("SELECT * FROM food")
    suspend fun getAllFood(): List<Food>

    @Query("SELECT * FROM food WHERE uuid = :foodId")
    suspend fun getFood(foodId: Int): Food

    @Query("DELETE FROM food")
    suspend fun deleteAllFood()

}