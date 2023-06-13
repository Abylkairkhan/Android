package com.example.ecommerceshop.screens.home



import androidx.core.view.ContentInfoCompat.Flags
import com.example.ecommerceshop.models.Raiting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerceshop.Credentials
import com.example.ecommerceshop.Room.MainDB
import com.example.ecommerceshop.models.Book
import com.example.ecommerceshop.models.Genre
import com.example.ecommerceshop.requests.Service
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private val service = Service()
    private lateinit var db: MainDB
    companion object{
        var genres: MutableLiveData<List<Genre>> = MutableLiveData<List<Genre>>()
        var books: MutableLiveData<List<Book>> = MutableLiveData<List<Book>>()
        var books2: MutableLiveData<List<Book>> = MutableLiveData<List<Book>>()
        var rating: MutableLiveData<List<Raiting>> = MutableLiveData<List<Raiting>>()
    }

    fun getBooks(){
        CoroutineScope(Dispatchers.IO).launch {
            val data = service.getBookSaved(Credentials.USER_ID.toString())
        }
    }

    fun saveBook(book_id:String){
        CoroutineScope(Dispatchers.IO).launch {
            service.addBook( Credentials.USER_ID.toString(),book_id)
        }
    }

    fun getRatingMutable():MutableLiveData< List<Raiting> >{
        return rating
    }

    fun getGenreMutable(): MutableLiveData< List<Genre> >{
        return genres
    }

    fun getBookMutable(): MutableLiveData< List<Book> >{
        return books
    }

    fun getBookMutable2(): MutableLiveData< List<Book> >{
        return books2
    }

    fun getGenre(){
        CoroutineScope(Dispatchers.IO).launch {
            val data_genres = service.getGenre()
            genres.postValue(data_genres)
        }
    }

    fun getBook(){
        CoroutineScope(Dispatchers.IO).launch {
            val data_books = service.getBook()
            books.postValue(data_books)
        }
    }

    fun getBook2(){
        CoroutineScope(Dispatchers.IO).launch {
            val data_books = service.getBook2()
            books2.postValue(data_books)
        }
    }

    fun filterName(type: Int){
        CoroutineScope(Dispatchers.IO).launch {
            var data_books = listOf<Book>()
            if (type==1) {
                data_books = service.getBookByName()
            }
            if (type==2) {
                data_books = service.getBookByPage()
            }
            if (type==3) {
                data_books = service.getBookByYear()
            }
            if (type==4) {
                data_books = service.getBookByRating()
            }
            books.postValue(data_books)
        }
    }

    fun searchByPattern(pattern: String){
        CoroutineScope(Dispatchers.IO).launch {
            val data_books = service.getBookByPattern(pattern)
            books.postValue(data_books)
        }
    }

    fun getAllRatings(){
        CoroutineScope(Dispatchers.IO).launch {
            val data_rating = service.getAllRatings()
            rating.postValue(data_rating)
        }
    }

    fun putRating(x: Float, book_id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            var rating = Raiting(x, book_id, Credentials.USER_ID)
            service.putRating(rating)
        }
    }

}