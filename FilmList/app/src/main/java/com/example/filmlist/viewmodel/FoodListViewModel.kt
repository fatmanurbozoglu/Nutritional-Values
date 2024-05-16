package com.example.filmlist.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.filmlist.model.Food
import com.example.filmlist.servis.FoodAPIServis
import com.example.filmlist.servis.FoodDatabase
import com.example.filmlist.utils.PrivateSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FoodListViewModel(application: Application): BaseViewModel(application) {
    // MutableLiveData değiştirilebilir
    val foods = MutableLiveData<List<Food>>()
    val errorMessage = MutableLiveData<Boolean>()
    val foodLoading = MutableLiveData<Boolean>()
    private val foodApiServis = FoodAPIServis()
    private val disposable = CompositeDisposable()
    // disposable -> istekleri kullandıktan sonra disposable i cagırıp onlardan kurtulabiliriz
    private val privateSharedPreferences = PrivateSharedPreferences( getApplication())
    private var updateTime = 10*60*1000*1000*1000L // dakika nanotime cevrilir
    fun refreshData(){
        val saveTime = privateSharedPreferences.getTime() // kayıt edilme zamanı
        if (saveTime != null && saveTime != 0L && System.nanoTime()-saveTime < updateTime){
            getDataFromSqlite()
        }else{
            getDataFromInternet()
        }
    }
    fun refreshFromInternet(){
        getDataFromInternet()
    }
    private fun getDataFromSqlite(){
        foodLoading.value = true
        launch {
           val foodList= FoodDatabase(getApplication()).foodDao().getAllFood()
            showFoods(foodList)
            Toast.makeText(getApplication(), "besinler room'dan alındı", Toast.LENGTH_LONG).show()
        }
    }
    private fun getDataFromInternet(){
        foodLoading.value = true
        disposable.add(
            foodApiServis.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object:DisposableSingleObserver<List<Food>>(){
                    override fun onSuccess(t: List<Food>) {
                        // basarılı olursa
                        hideToSqlite(t)
                        Toast.makeText(getApplication(), "besinler internetten alındı", Toast.LENGTH_LONG).show()
                    }
                    override fun onError(e: Throwable) {
                        // hata alırsak
                        errorMessage.value = true
                        foodLoading.value = false
                        e.printStackTrace()
                    }
                })
        )
    }
    private fun showFoods(foodList: List<Food>){
        foods.value = foodList
        errorMessage.value = false
        foodLoading.value = false
    }
    private fun hideToSqlite(foodList: List<Food>){ // sqlite'a  sakla
        launch {
            val dao = FoodDatabase(getApplication()).foodDao()
            dao.deleteAllFood() // besin listesinde onceden bısey varsa onu temızler
            val uuidList = dao.insertAll(*foodList.toTypedArray()) // *foodList.toTypedArray() -> listeyi tekil halde gösterir
            var i = 0
            while (i < foodList.size){
                foodList[i].uuid = uuidList[i].toInt()
                i += 1
            }
            showFoods(foodList)
        }
        privateSharedPreferences.saveTime(System.nanoTime())
    }
}