package com.example.ecommerceshop.screens.items


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ecommerceshop.databinding.FragmentBookItemBinding
import com.example.ecommerceshop.models.Book
import com.example.ecommerceshop.requests.Service
import com.example.ecommerceshop.screens.home.HomeViewModel
import com.squareup.picasso.Picasso

class BookItem : Fragment() {

    private lateinit var binding: FragmentBookItemBinding
    private var vm = HomeViewModel()
    companion object{
        lateinit var book: Book
    }

    fun pasBook(book_: Book){
        book = book_
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookItemBinding.inflate(inflater, container, false)
        init()
        initStars()
//        vm.getAllRatings()
        saveBtn()
        return binding.root
    }

    private fun saveBtn(){
        vm.saveBook(book.id.toString())
    }


    private fun initStars(){
        val rate = binding.movieRating
        binding.starRating.setOnClickListener(){
            val x = rate.rating
            vm.putRating(x, book.id)
            Toast.makeText(requireContext(), "$x", Toast.LENGTH_SHORT).show()
        }
    }

    private fun init(){
        with(binding){
            Picasso.get().load(book.image).into(iamgeTV)
            titleBook.text = book.title
            yearBook.text = book.year_of_publication.toString()
            pageBook.text = book.num_of_pages.toString()
            ratingCntBook.text = book.rating_count.toString()
            ratingValueBook.text = book.rating_value.toString()
        }
    }
}