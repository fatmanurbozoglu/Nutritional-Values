package com.example.filmlist.servis

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.filmlist.model.Food

@Database(entities = arrayOf(Food::class), version = 1)
abstract class FoodDatabase: RoomDatabase(){
    abstract fun foodDao(): FoodDAO

    // Singleton -> farklı threadlerde aynı anda tek bir obje oluşturabildiğimiz sınıflardır.
    // Singleton kullanarak yapıyoruz

    companion object{
        // Volatile -> buradaki instance'yı diger threadlerde de görünür yapıyor
        @Volatile private var instance: FoodDatabase? = null

        private val lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context)= Room.databaseBuilder(
            context.applicationContext,
            FoodDatabase::class.java, "fooddatabase").build()
    }


}