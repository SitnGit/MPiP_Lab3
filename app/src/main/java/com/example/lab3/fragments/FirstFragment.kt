package com.example.lab3.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3.adapters.MovieRecyclerViewAdapter
import com.example.lab3.api.OMDbApi
import com.example.lab3.api.OMDbApiClient
import com.example.lab3.models.MovieList
import android.app.Activity
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lab3.R
import com.example.lab3.models.Movie
import com.example.lab3.viewmodels.FirstViewModel


class FirstFragment : Fragment() {
    private lateinit var firstViewModel: FirstViewModel
    private lateinit var OMDbAPIClient: OMDbApi
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: MovieRecyclerViewAdapter
    private lateinit var movieList: MutableList<Movie>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstViewModel = ViewModelProvider(this).get(FirstViewModel::class.java)
        firstViewModel.getMovieList().observe(viewLifecycleOwner,
            { m -> showMovies(m!!) })

        OMDbAPIClient = OMDbApiClient.getOMDbApi()!!
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        recyclerViewAdapter = MovieRecyclerViewAdapter(view.context ,mutableListOf<Movie>()){ position ->
            onMovieClick(
                position
            )
        }

        recyclerView.adapter = recyclerViewAdapter

        (recyclerView.getLayoutManager() as LinearLayoutManager).stackFromEnd = true

        val searchBar: EditText = view.findViewById(R.id.search)
        val button: Button = view.findViewById(R.id.button)
        button.setOnClickListener{
            val title: String = searchBar.text.toString()
            hideKeyboard()
            firstViewModel.findMoviesByTitle(title)
            recyclerView.scrollToPosition(0)
        }
    }

    private fun showMovies(data: MovieList) {
        movieList = data.Search
        recyclerViewAdapter.updateMovies(movieList)
    }

    private fun onMovieClick(position: Int){
        val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(movieList[position].Title)
        findNavController().navigate(action)
        Log.i("CLICK", "$position clicked!")
        Toast.makeText(activity, "$position clicked!", Toast.LENGTH_SHORT).show()
    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}