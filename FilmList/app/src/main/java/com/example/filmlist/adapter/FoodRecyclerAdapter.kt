package com.example.filmlist.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.filmlist.R
import com.example.filmlist.databinding.RecyclerviewFilmListBinding
import com.example.filmlist.model.Food
import com.example.filmlist.utils.downloadImage
import com.example.filmlist.utils.makePlaceHolder
import com.example.filmlist.view.FoodListFragmentDirections

class FoodRecyclerAdapter(val foodList: ArrayList<Food>): RecyclerView.Adapter<FoodRecyclerAdapter.FoodHolder>(), FoodClickListener{
    private lateinit var binding: RecyclerviewFilmListBinding

    class FoodHolder(val binding: RecyclerviewFilmListBinding): RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = RecyclerviewFilmListBinding.inflate(inflater,parent,false)

        val view = DataBindingUtil.inflate<RecyclerviewFilmListBinding>(inflater, R.layout.recyclerview_film_list,parent,false)
       // val view = inflater.inflate(R.layout.recyclerview_film_list, parent, false)
        return FoodHolder(view)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        //holder.binding.foodNameRecyclerview.text = foodList[position].foodName
       // holder.binding.foodCalorieRecyclerview.text = foodList[position].foodCalorie
        // gorseli ekledik
        holder.binding.imageView.downloadImage(foodList[position].image!!, makePlaceHolder(holder.itemView.context))

        val foodId = foodList[position].uuid
        holder.itemView.setOnClickListener {
            // arguments eklemek için set ile almamız gerekiyor setFoodId ile aldık
            val action = FoodListFragmentDirections.actionFilmListFragmentToFilmDetailsFragment().setFoodId(foodId)
            Navigation.findNavController(it).navigate(action)
        }

// üstte ve altta yapılan işlemlerin ikisinde de aynı sonuc veriliyor bırı data bındıng kullanarak cok daha az satırla halledildi
        // Data Binding i kullanmak icin layout kısmında xml i düzenledık (recyclerview_film_list.xml)

        // Data Binding kullanarak
        holder.binding.food = foodList[position]
       // holder.binding.listener = this
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateFilmList(newFoodList: List<Food>){
        foodList.clear()
        foodList.addAll(newFoodList)
        notifyDataSetChanged()
    }

    override fun foodClicked(view: View) {
        // besinlere tıklanınca ne yapılacagı

      /*  val uuid = binding.foodUuid.text.toString().toIntOrNull()

        uuid?.let {
            val action = FoodListFragmentDirections.actionFilmListFragmentToFilmDetailsFragment().setFoodId(it)
            Navigation.findNavController(view).navigate(action)
        }*/
    }
}

