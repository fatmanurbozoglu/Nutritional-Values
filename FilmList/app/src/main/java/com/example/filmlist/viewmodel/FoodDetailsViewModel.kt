package com.example.filmlist.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.filmlist.model.Food
import com.example.filmlist.servis.FoodDatabase
import kotlinx.coroutines.launch

class FoodDetailsViewModel(application: Application): BaseViewModel(application) {

    val foodLiveData = MutableLiveData<Food>()

    fun getRoomData(uuid : Int){
        launch {
            val dao = FoodDatabase(getApplication()).foodDao()
            val food = dao.getFood(uuid)
            foodLiveData.value = food
        }
    }
}