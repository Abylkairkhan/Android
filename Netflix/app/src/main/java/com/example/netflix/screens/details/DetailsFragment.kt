package com.example.netflix.screens.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.netflix.databinding.FragmentDetailsBinding
import com.example.netflix.databinding.PartResultBinding
import com.example.netflix.utils.ErrorResult
import com.example.netflix.utils.PendingResult
import com.example.netflix.utils.SuccessResult
import com.example.netflix.utils.takeSuccess
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var resultBinding: PartResultBinding
    private val vm: DetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        resultBinding = PartResultBinding.bind(binding.root)
        init()
        return binding.root
    }

    private fun init(){
        with(binding){
            vm.getMovie().observe(viewLifecycleOwner){ result ->
                when(result){
                    is SuccessResult -> {
                        Picasso.get().load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + result.takeSuccess()?.backdrop_path).into(img)
                        titleDetail.text = result.takeSuccess()?.title
                        movieAdult.text = result.takeSuccess()?.adult.toString()
                        movieVote.text = result.takeSuccess()?.vote_average.toString()
                        overview.text = result.takeSuccess()?.overview
                        date.text = result.takeSuccess()?.release_date
                        movieRuntime.text = result.takeSuccess()?.runtime.toString() + "min"
                        movieStatus.text = result.takeSuccess()?.status

                        resultBinding.errorContainer.visibility = View.GONE
                        resultBinding.progressBar.visibility = View.GONE
                    }
                    is PendingResult ->{
                        binding.cardView.visibility = View.GONE
                        binding.cardView2.visibility = View.GONE
                        resultBinding.errorContainer.visibility = View.VISIBLE
                        resultBinding.progressBar.visibility = View.VISIBLE
                    }
                    is ErrorResult -> {
                        binding.cardView.visibility = View.GONE
                        binding.cardView2.visibility = View.GONE
                        resultBinding.errorContainer.visibility = View.VISIBLE
                        resultBinding.tryAgainBtn.visibility = View.VISIBLE
                    }
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
