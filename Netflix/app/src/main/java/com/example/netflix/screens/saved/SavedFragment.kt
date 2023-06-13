package com.example.netflix.screens.saved

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.netflix.R
import com.example.netflix.databinding.FragmentSavedBinding
import com.example.netflix.models.Movie
import com.example.netflix.screens.details.DetailsViewModel
import com.example.netflix.adapters.MovieAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SavedFragment: Fragment(), MovieAdapter.Listener {

    private lateinit var binding: FragmentSavedBinding
    private val vm: SavedViewModel by viewModel()
    private val dvm: DetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init(){
        with(binding){
            var adapter = MovieAdapter(this@SavedFragment)
            adapter.list = vm.getSavedMovie(requireContext())
            RecViewSaved.adapter = adapter
            RecViewSaved.layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onClick(movie: Movie) {
        dvm.passMovie(movie)
        dvm.passType(false)
        findNavController().navigate(R.id.action_id_saved_fragment_to_id_details_fragment)
    }
}