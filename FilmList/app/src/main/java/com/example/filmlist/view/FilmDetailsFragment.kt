package com.example.filmlist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.filmlist.databinding.FragmentFilmDetailsBinding
import com.example.filmlist.utils.downloadImage
import com.example.filmlist.utils.makePlaceHolder
import com.example.filmlist.viewmodel.FoodDetailsViewModel

class FilmDetailsFragment : Fragment() {
    private lateinit var binding: FragmentFilmDetailsBinding
    private lateinit var viewModel: FoodDetailsViewModel
    private var foodId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_film_details, container, false)

        binding = FragmentFilmDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let{
           foodId = FilmDetailsFragmentArgs.fromBundle(it).foodId
            println(foodId)
        }
        viewModel = ViewModelProviders.of(this)[FoodDetailsViewModel::class.java]
        viewModel.getRoomData(foodId)

        observeLiveData()
    }
    fun observeLiveData(){
        viewModel.foodLiveData.observe(viewLifecycleOwner, Observer {food->
            food?.let {
                binding.foodName.text = it.foodName
                binding.foodCalorie.text = it.foodCalorie
                binding.foodCarbohydrate.text = it.foodCarbohydrate
                binding.foodOil.text = it.foodOil
                binding.foodProtein.text = it.foodProtein

                // glide i ekledikten sonra gorselı cekmek icin
                context?.let {
                    binding.filmImageView.downloadImage(food.image!!, makePlaceHolder(it) )
                }
            }
        })
    }
}