package com.example.bookstoreapp.Fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.bookstoreapp.Database.Book
import com.example.bookstoreapp.Database.MainDb
import com.example.bookstoreapp.R

class BookDetailsFragment(book: Book, role: String) : Fragment() {

    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var cost: TextView
    private lateinit var btnDelete: Button
    private lateinit var btnBack: Button
    private lateinit var btnSave: Button
    private lateinit var actionTxt: Toast
    private lateinit var db: MainDb
    private val curBook = book
    private val role = role

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (role == "admin"){
            btnDelete.visibility = View.VISIBLE
            btnSave.visibility = View.VISIBLE
        }
        else{
            btnDelete.visibility = View.GONE
            btnSave.visibility = View.GONE
        }

        title.text = curBook.title
        description.text = curBook.desc
        cost.text = curBook.cost.toString()

        setOnClick()
        setBookInfo()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_book_details, container, false)

        title = rootView.findViewById(R.id.titleTxt)
        description = rootView.findViewById(R.id.descriptionTxt)
        cost = rootView.findViewById(R.id.costTxt)
        btnDelete = rootView.findViewById(R.id.buttonDelete)
        btnBack = rootView.findViewById(R.id.buttonBack)
        btnSave = rootView.findViewById(R.id.buttonSave)

        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance(book: Book, role: String) = BookDetailsFragment(book, role)
    }

    fun setBookInfo(){

        title.setOnClickListener(){
            val alertdialog = AlertDialog.Builder(requireContext())
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.edit_text, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.editText)
            editText.setText(curBook.title)
            with(alertdialog){
                setTitle("Enter text")
                setPositiveButton("OK"){ dialog, which ->
                    title.text = editText.text.toString()
                }
                setView(dialogLayout)
                show()
            }
        }

        description.setOnClickListener(){
            val alertdialog = AlertDialog.Builder(requireContext())
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.edit_text, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.editText)
            editText.setText(curBook.desc)
            with(alertdialog){
                setTitle("Enter text")
                setPositiveButton("OK"){ dialog, which ->
                    description.text = editText.text.toString()
                }
                setView(dialogLayout)
                show()
            }
        }

        cost.setOnClickListener(){
            val alertdialog = AlertDialog.Builder(requireContext())
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.edit_text, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.editText)
            editText.setText(curBook.cost.toString())
            with(alertdialog){
                setTitle("Enter text")
                setPositiveButton("OK"){ dialog, which ->
                    cost.text = editText.text.toString()
                }
                setView(dialogLayout)
                show()
            }
        }
    }

    fun setOnClick(){

        btnDelete.setOnClickListener(){
            Thread{
                db = MainDb.getDb(requireContext())
                if (curBook.id != null){
                    db.getDao().deleteBookByID(curBook.id!!)
                }

                requireActivity().runOnUiThread(){
                    actionTxt = Toast.makeText(requireContext(),"Successfully Deleted",Toast.LENGTH_SHORT)
                    actionTxt.show()
                }

            }.start()
        }

        btnBack.setOnClickListener(){
            requireFragmentManager().beginTransaction()
                .replace(R.id.home_placeholder, BookListFragment.newInstance(0,"", 0, role))
                .commit()
        }

        btnSave.setOnClickListener(){
            Thread{
                db = MainDb.getDb(requireContext())
                if (curBook.id!=null){
                    db.getDao().updateBook(
                        curBook.id!!,
                        title.text.toString(),
                        description.text.toString(),
                        cost.text.toString().toInt()
                    )
                    requireActivity().runOnUiThread(){
                        actionTxt = Toast.makeText(requireContext(), "Changes Saved", Toast.LENGTH_SHORT)
                        actionTxt.show()
                    }
                }
            }.start()
        }
    }
}