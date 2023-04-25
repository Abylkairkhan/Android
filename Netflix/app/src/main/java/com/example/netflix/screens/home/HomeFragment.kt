package com.example.netflix.screens.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.netflix.R
import com.example.netflix.databinding.FragmentHomeBinding
import com.example.netflix.models.Movie
import com.example.netflix.screens.details.DetailsViewModel

class HomeFragment : Fragment(), MovieAdapter.Listener {

    private lateinit var binding: FragmentHomeBinding
    private var vm = HomeViewModel()
    private var dvm = DetailsViewModel()
    private var page: Int = 1
    private var lastScrollTime = 0L

    override fun onAttach(context: Context) {
        super.onAttach(context)
        vm.getMovies(page)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        initRecView()
        return binding.root
    }

    private fun initRecView(){
        var layoutManager = LinearLayoutManager(activity)
        var adapter = MovieAdapter(this@HomeFragment)

        binding.apply {
            vm.movies.observe(viewLifecycleOwner){
                adapter.list = it
                RecView.adapter = adapter
            }

            vm.loading.observe(viewLifecycleOwner){
                if (it) {
                    binding.textView.visibility = View.VISIBLE
                } else {
                    binding.textView.visibility = View.GONE
                }
            }

            RecView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val visibleItemCount: Int = layoutManager.childCount
                    val pastVisibleItem: Int = layoutManager.findFirstCompletelyVisibleItemPosition()
                    val total = adapter.itemCount
                    val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                    val currentTime = System.currentTimeMillis()
                    if(currentTime - lastScrollTime >= 100){
                        lastScrollTime = currentTime
                        if (visibleItemCount + pastVisibleItem >= total) {
                            vm.updateMovies(++page)
                            layoutManager.scrollToPosition(lastVisibleItemPosition)
                        }
                    }
                    super.onScrolled(recyclerView, dx, dy)
                }
            })

            RecView.layoutManager = layoutManager
        }
    }

    override fun onClick(movie: Movie) {
        dvm.passMovie(movie.id!!)
        dvm.passType(true)
        findNavController().navigate(R.id.action_id_home_fragment_to_id_details_fragment)
    }
}