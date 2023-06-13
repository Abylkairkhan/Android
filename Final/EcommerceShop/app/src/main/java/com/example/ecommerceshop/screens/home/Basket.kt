package com.example.ecommerceshop.screens.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceshop.R
import com.example.ecommerceshop.adapters.BookAdapter
import com.example.ecommerceshop.adapters.GenreAdapter
import com.example.ecommerceshop.databinding.FragmentBasketBinding
import com.example.ecommerceshop.models.Book
import com.example.ecommerceshop.models.Genre

class Basket : Fragment(), GenreAdapter.Listener, BookAdapter.Listener {

    private lateinit var binding: FragmentBasketBinding
    private var vm = HomeViewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        vm.getBook2()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBasketBinding.inflate(inflater,  container, false)
        initRecView()
        return binding.root
    }

    private fun initRecView() {
        var layoutManager = LinearLayoutManager(activity)
        var adapter = BookAdapter(this@Basket)
        vm.getBookMutable2().observe(viewLifecycleOwner){
            adapter.list = it
            binding.basketRecView.adapter = adapter
            binding.basketRecView.layoutManager = layoutManager
        }
    }

    override fun onClick(book: Book) {
        TODO("Not yet implemented")
    }

    override fun onClick(genre: Genre) {
        TODO("Not yet implemented")
    }


}