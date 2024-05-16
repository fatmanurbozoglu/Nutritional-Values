package com.example.filmlist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//    Entity
// bir sınıfı bir veritabanı satırı olarak işaretler.
// Her biri için Entity, öğeleri tutmak üzere bir veritabanı tablosu oluşturulur.
// Dizide Entity sınıfına başvurulmalıdır
@Entity
data class Food(
    // ColumnInfo -> sutunlari belirler
    @ColumnInfo(name = "isim")
    @SerializedName("isim")
    val foodName: String?,
    @ColumnInfo(name = "kalori")
    @SerializedName("kalori")
    val foodCalorie: String?,
    @ColumnInfo(name = "karbonhidrat")
    @SerializedName("karbonhidrat")
    val foodCarbohydrate: String?,
    @ColumnInfo(name = "protein")
    @SerializedName("protein")
    val foodProtein:String?,
    @ColumnInfo(name = "yag")
    @SerializedName("yag")
    val foodOil: String?,
    @ColumnInfo(name = "gorsel")
    @SerializedName("gorsel")
    val image :String?)
{
    // otomatık olarak olusturulacak Id eklendı
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}

