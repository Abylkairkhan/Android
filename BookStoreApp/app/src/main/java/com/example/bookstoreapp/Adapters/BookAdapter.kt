package com.example.bookstoreapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstoreapp.Database.Book
import com.example.bookstoreapp.R

interface BookActionListener{

//    fun onBookDelete(book: Book)
    fun onBookDetails(book: Book)

}

class BookAdapter(private var bookList: List<Book>,
                  private val bookAction: BookActionListener): RecyclerView.Adapter<BookAdapter.BookViewHolder>(),
                                                               View.OnClickListener{

    override fun onClick(v: View) {
        val book = v.tag as Book
        bookAction.onBookDetails(book)
    }

    class BookViewHolder(view: View): RecyclerView.ViewHolder(view){
        var bookName: TextView = itemView.findViewById(R.id.bookTitle)
        var bookPrice: TextView = itemView.findViewById(R.id.bookPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_item, parent, false)

        inflater.setOnClickListener(this)

        return BookViewHolder(inflater)
    }

    override fun getItemCount(): Int = bookList.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val curItem = bookList[position]
        holder.bookName.text = curItem.title
        holder.bookPrice.text = curItem.cost.toString()
        holder.itemView.tag = curItem

    }
}