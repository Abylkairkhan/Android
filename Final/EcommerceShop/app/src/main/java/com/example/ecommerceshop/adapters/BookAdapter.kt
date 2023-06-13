package com.example.ecommerceshop.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceshop.R
import com.example.ecommerceshop.databinding.BookItemBinding
import com.example.ecommerceshop.models.Book
import com.squareup.picasso.Picasso

class BookAdapter(val listener: Listener): RecyclerView.Adapter<BookAdapter.BookHolder>() {

    var list: List<Book> = emptyList()

    class BookHolder(item: View): RecyclerView.ViewHolder(item) {
        var binding = BookItemBinding.bind(item)
        fun bind(book: Book, listener: Listener) = with(binding){
            Picasso.get().load(book.image).into(imageBook)
            titleBook.text = book.title
            yearBook.text = book.year_of_publication.toString()
            pageBook.text = book.num_of_pages.toString()
            bookCardView.setOnClickListener(){
                listener.onClick(book)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_item, parent, false)
        return BookHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    interface Listener {
        fun onClick(book: Book)
    }
}