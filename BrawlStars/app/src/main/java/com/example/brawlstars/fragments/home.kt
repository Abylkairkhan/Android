package com.example.brawlstars.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brawlstars.R
import com.example.brawlstars.adapter.MovieAdapter
import com.example.brawlstars.databinding.FragmentHomeBinding
import com.example.brawlstars.models.Movies
import com.example.brawlstars.request.service
import com.example.brawlstars.room.maindb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class home : Fragment(), MovieAdapter.Listener {

    private lateinit var binding: FragmentHomeBinding
    private var adapter: MovieAdapter = MovieAdapter(this@home)
    private var page = 1
    private val service = service()
    private val item = item()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setPage()
        initRecView(page)
        return binding.root
    }

    private fun setPage(){
        binding.nextButton.setOnClickListener(){
            page+=1
            initRecView(page)
        }
        binding.backButton.setOnClickListener(){
            if(page==1){
                Toast.makeText(requireContext(), "That's 1 page",Toast.LENGTH_LONG).show()
            }
            else {
                page -= 1
                initRecView(page)
            }
        }
    }

    private fun initRecView(page: Int){
        CoroutineScope(Dispatchers.IO).launch {
            adapter.list = service.getData(page)
            withContext(Dispatchers.Main){
                binding.RecView.adapter = adapter
t                binding.RecView.layoutManager = LinearLayoutManager(requireContext())
                binding.textView.text = page.toString()
            }
        }
    }

    override fun onClick(movies: Movies) {
        item.setType(true)
        item.setID(movies.id!!)
        findNavController().navigate(R.id.action_homeFragment_to_item)
    }
}