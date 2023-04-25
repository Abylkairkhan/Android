package com.example.bookstoreapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstoreapp.Adapters.BookActionListener
import com.example.bookstoreapp.Adapters.BookAdapter
import com.example.bookstoreapp.Database.Book
import com.example.bookstoreapp.Database.MainDb
import com.example.bookstoreapp.R

class BookListFragment(num: Int, pattern: String, id: Int, role: String) : Fragment() {

    private lateinit var actionTxt: Toast
    private lateinit var db: MainDb
    private val typeOfSort = num
    private val pattern = pattern
    private val role = role
    private val ID = id

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_book_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Thread{
            db = MainDb.getDb(requireContext())
            lateinit var bookList: List<Book>

            if(typeOfSort == 0){
                bookList = db.getDao().getAllBook()
            }
            else if(typeOfSort == 1){
                bookList = db.getDao().getAllBookASC()
            }
            else if(typeOfSort == 2){
                bookList = db.getDao().getAllBookDESC()
            }
            else if(typeOfSort == 3){
                bookList = db.getDao().getBookByID(ID)
            }
            else if(typeOfSort == -1){
                bookList = db.getDao().getBookByPattern(pattern)
            }

            requireActivity().runOnUiThread(){
                val layoutManager = LinearLayoutManager(context)
                var recyclerView = view.findViewById<RecyclerView>(R.id.recView)
                recyclerView.layoutManager = layoutManager
                recyclerView.setHasFixedSize(true)
                recyclerView.adapter = BookAdapter(bookList, object: BookActionListener{
                    override fun onBookDetails(book: Book) {
//                        actionTxt = Toast.makeText(requireContext(), book.title.toString(), Toast.LENGTH_SHORT)
//                        actionTxt.show()
                        requireFragmentManager().beginTransaction()
                            .replace(R.id.home_placeholder, BookDetailsFragment.newInstance(book, role))
                            .commit()
                    }
                })
            }
        }.start()
    }

    companion object {
        @JvmStatic
        fun newInstance(num: Int, pattern: String, id: Int, role: String) = BookListFragment(num, pattern, id, role)
    }
}