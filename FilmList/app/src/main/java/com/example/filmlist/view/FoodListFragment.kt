package com.example.filmlist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmlist.adapter.FoodRecyclerAdapter
import com.example.filmlist.databinding.FragmentFilmListBinding
import com.example.filmlist.viewmodel.FoodListViewModel

class FoodListFragment : Fragment() {
    private lateinit var binding: FragmentFilmListBinding

    private lateinit var viewModel: FoodListViewModel
    private val foodRecyclerAdapter = FoodRecyclerAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_film_list, container, false)
       binding = FragmentFilmListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewModel in tanımlamasını bitirmek için
        viewModel = ViewModelProviders.of(this)[FoodListViewModel::class.java]
        viewModel.refreshData()

        // recyclerView i tanımlama
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = foodRecyclerAdapter

        // kullanıcı asagı dogru cektıgınde yapılacak islemler
        binding.swiperefreshlayout.setOnRefreshListener {
            binding.errorMessageTextView.visibility = View.GONE
            binding.recyclerView.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            binding.swiperefreshlayout.isRefreshing = false

            viewModel.refreshFromInternet()
        }
        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.foods.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.recyclerView.visibility = View.VISIBLE
                foodRecyclerAdapter.updateFilmList(it)
            }
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    binding.errorMessageTextView.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }else{
                    binding.errorMessageTextView.visibility = View.GONE
                }

            }
        })

        // owner icerisine this veya viewLifecycleOwner ı kullanabiliriz
        viewModel.foodLoading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                    binding.errorMessageTextView.visibility = View.GONE
                }else{
                    binding.progressBar.visibility = View.GONE
                }
            }
        })
    }
}