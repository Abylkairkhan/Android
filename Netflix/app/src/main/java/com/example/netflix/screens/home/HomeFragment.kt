package com.example.netflix.screens.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.netflix.R
import com.example.netflix.adapters.MovieAdapter
import com.example.netflix.adapters.MoviePagedAd
import com.example.netflix.adapters.MoviePagedAdapter
import com.example.netflix.databinding.FragmentHomeBinding
import com.example.netflix.models.Movie
import com.example.netflix.screens.details.DetailsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: Fragment(), MovieAdapter.Listener, MoviePagedAdapter.Listener, MoviePagedAd.Listener {

    private lateinit var binding: FragmentHomeBinding
    private val vm: HomeViewModel by viewModel()
    private val dvm: DetailsViewModel by viewModel()
    private var movieAdapter: MoviePagedAd? = null
    private var page: Int = 1
    private var lastScrollTime = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
//        initRecView()
        initRV()
        observeMovieFlow()
        return binding.root
    }

    private fun observeMovieFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            vm.movieFlow.collectLatest { pagingData ->
                movieAdapter?.submitData(pagingData)
            }
        }
    }

    private fun initRV(){
        movieAdapter = MoviePagedAd(this@HomeFragment)
        binding.RecView.adapter = movieAdapter
        binding.RecView.layoutManager = LinearLayoutManager(activity)
    }

//    private fun initRecView(){
//        val layoutManager = LinearLayoutManager(activity)
//        val adapter = MovieAdapter(this@HomeFragment)
//
//        binding.apply {
//            vm.movies.observe(viewLifecycleOwner){
//                adapter.list = it
//                RecView.adapter = adapter
//            }
//
//            vm.loading.observe(viewLifecycleOwner){
//                if (it) {
//                    binding.textView.visibility = View.VISIBLE
//                } else {
//                    binding.textView.visibility = View.GONE
//                }
//            }
//
//            RecView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
//                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                    val visibleItemCount: Int = layoutManager.childCount
//                    val pastVisibleItem: Int = layoutManager.findFirstCompletelyVisibleItemPosition()
//                    val total = adapter.itemCount
//                    val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
//
//                    val currentTime = System.currentTimeMillis()
//                    if(currentTime - lastScrollTime >= 100){
//                        lastScrollTime = currentTime
//                        if (visibleItemCount + pastVisibleItem >= total) {
//                            vm.updateMovies(++page)
//                            layoutManager.scrollToPosition(lastVisibleItemPosition)
//                        }
//                    }
//                    super.onScrolled(recyclerView, dx, dy)
//                }
//            })
//
//            RecView.layoutManager = layoutManager
//        }
//    }

    override fun onClick(movie: Movie) {
        dvm.passMovie(movie.id!!)
        dvm.passType(true)
        findNavController().navigate(R.id.action_id_home_fragment_to_id_details_fragment)
    }
}