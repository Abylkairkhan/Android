package com.example.ecommerceshop.screens.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceshop.R
import com.example.ecommerceshop.adapters.BookAdapter
import com.example.ecommerceshop.adapters.GenreAdapter
import com.example.ecommerceshop.databinding.FragmentHomeBinding
import com.example.ecommerceshop.models.Book
import com.example.ecommerceshop.models.Genre
import com.example.ecommerceshop.screens.items.BookItem
import com.example.ecommerceshop.screens.items.GenreItem
import com.google.android.material.bottomnavigation.BottomNavigationView

class Home : Fragment(), GenreAdapter.Listener, BookAdapter.Listener {

    private lateinit var binding: FragmentHomeBinding
    private var vm = HomeViewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        vm.getGenre()
        vm.getBook()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        initHorRecView()
        initVerRecView()
        initFilter()
        initPattern()
        return binding.root
    }

    private fun initPattern(){
        binding.searchImg.setOnClickListener(){
            vm.searchByPattern(binding.searchEt.text.toString())
        }
    }

    private fun initFilter(){
        binding.sortName.setOnClickListener(){
            vm.filterName(1)
        }
        binding.sortPage.setOnClickListener(){
            vm.filterName(2)
        }
        binding.sortYear.setOnClickListener(){
            vm.filterName(3)
        }
        binding.sortRating.setOnClickListener(){
            vm.filterName(4)
        }
    }

    private fun initVerRecView(){
        var layoutManager = LinearLayoutManager(activity)
        var adapter = BookAdapter(this@Home)
        vm.getBookMutable().observe(viewLifecycleOwner){
            adapter.list = it
            binding.bookRecView.adapter = adapter
            binding.bookRecView.layoutManager = layoutManager
        }
    }

    private fun initHorRecView(){
        var layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
        var adapter = GenreAdapter(this@Home)
        vm.getGenreMutable().observe(viewLifecycleOwner){
            adapter.list = it
            binding.genreRecView.adapter = adapter
            binding.genreRecView.layoutManager = layoutManager
        }
    }

    override fun onClick(genre: Genre) {
        var frag = GenreItem()
        frag.pasGenre(genre)
        findNavController().navigate(R.id.action_home3_to_genreItem2)
    }

    override fun onClick(book: Book) {
        var frag = BookItem()
        frag.pasBook(book)
        findNavController().navigate(R.id.action_home3_to_bookItem)
    }
}