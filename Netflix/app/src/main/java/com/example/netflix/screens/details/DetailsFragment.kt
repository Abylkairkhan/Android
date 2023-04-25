package com.example.netflix.screens.details

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.netflix.R
import com.example.netflix.Room.MainDB
import com.example.netflix.databinding.FragmentDetailsBinding
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val vm = DetailsViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init(){
        with(binding){
            vm.getMovie().observe(viewLifecycleOwner){
                Picasso.get().load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + it.backdrop_path).into(img)
                titleDetail.text = it.title
                movieAdult.text = it.adult.toString()
                movieVote.text = it.vote_average.toString()
                overview.text = it.overview
                date.text = it.release_date
                movieRuntime.text = it.runtime.toString() + "min"
                movieStatus.text = it.status
            }

            vm.getLoading().observe(viewLifecycleOwner){
                if (it) {
                    binding.loading.visibility = View.VISIBLE
                } else {
                    binding.loading.visibility = View.GONE
                }
            }

            vm.getType().observe(viewLifecycleOwner){
                if (!it){
                    binding.saveBtn.text = "Delete"
                    delete()
                }
                else save()
            }
        }
    }

    private fun delete(){
        binding.saveBtn.setOnClickListener(){
            vm.deleteMovie(requireContext())
            val toast = Toast.makeText(requireContext(), "Movie Deleted", Toast.LENGTH_LONG)
            toast.show()
        }
    }

    private fun save(){
        binding.saveBtn.setOnClickListener(){
            vm.saveMovie(requireContext())
            val toast = Toast.makeText(requireContext(), "Movie Saved", Toast.LENGTH_LONG)
            toast.show()
        }
    }
}
