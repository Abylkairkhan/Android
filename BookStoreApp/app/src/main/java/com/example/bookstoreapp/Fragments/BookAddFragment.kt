package com.example.bookstoreapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.bookstoreapp.Database.Book
import com.example.bookstoreapp.Database.MainDb
import com.example.bookstoreapp.R

class BookAddFragment : Fragment() {

    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var cost: TextView
    private lateinit var addBtn: Button
    private lateinit var db: MainDb

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_book_add, container, false)

        title = rootView.findViewById(R.id.titleText)
        description = rootView.findViewById(R.id.descripText)
        cost = rootView.findViewById(R.id.costText)
        addBtn = rootView.findViewById(R.id.AddBookButton)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addBtn.setOnClickListener() {
            Thread {
                if (!title.text.isNullOrEmpty() && !description.text.isNullOrEmpty() && !cost.text.isNullOrEmpty()) {
                    val book: Book = Book(
                        null,
                        title.text.toString(),
                        description.text.toString(),
                        cost.text.toString().toInt()
                    )
                    db = MainDb.getDb(requireContext())
                    db.getDao().insertBook(book)
                    requireActivity().runOnUiThread() {
                        var actionTxt: Toast = Toast.makeText(
                            requireContext(),
                            "Book Successfully Added",
                            Toast.LENGTH_SHORT
                        )
                        actionTxt.show()
                    }
                } else {
                    requireActivity().runOnUiThread() {
                        var actionTxt: Toast = Toast.makeText(
                            requireContext(),
                            "Fill the each Field",
                            Toast.LENGTH_SHORT
                        )
                        actionTxt.show()
                    }
                }
            }.start()
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() = BookAddFragment()
    }
}