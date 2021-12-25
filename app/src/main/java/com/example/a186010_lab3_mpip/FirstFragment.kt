package com.example.a186010_lab3_mpip

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a186010_lab3_mpip.adapters.MovieRecyclerViewAdapter
import com.example.a186010_lab3_mpip.api.OMDbApi
import com.example.a186010_lab3_mpip.api.OMDbApiClient
import com.example.a186010_lab3_mpip.models.Data
import com.example.a186010_lab3_mpip.models.MovieList
import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.a186010_lab3_mpip.viewmodels.FirstFragmentViewModel


class FirstFragment : Fragment() {
    private lateinit var omdbApiClient: OMDbApi
    private lateinit var recyclerViewAdapter: MovieRecyclerViewAdapter
    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var firstFragmentViewModel: FirstFragmentViewModel
    private lateinit var movieList: MutableList<Data>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstFragmentViewModel = ViewModelProvider(this).get(FirstFragmentViewModel::class.java)

        firstFragmentViewModel.getMovieListMutableLiveData().observe(viewLifecycleOwner,
            { t -> displayData(t!!) })

        omdbApiClient = OMDbApiClient.getOMDbApi()!!

        movieRecyclerView = view.findViewById(R.id.movieRecyclerViewId)

        movieRecyclerView.layoutManager = LinearLayoutManager(activity)

        recyclerViewAdapter = MovieRecyclerViewAdapter(view.context ,mutableListOf<Data>()){ position ->
            onMovieClick(
                position
            )
        }

        movieRecyclerView.adapter = recyclerViewAdapter

        (movieRecyclerView.getLayoutManager() as LinearLayoutManager).stackFromEnd = true

        val searchBar: EditText = view.findViewById(R.id.searchBarId)

        searchBar.setOnEditorActionListener{ v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT){
                val title: String = searchBar.text.toString()
                hideKeyboard()
                firstFragmentViewModel.searchMoviesByTitle(title)
                movieRecyclerView.scrollToPosition(0)
                true
            }
            else {
                Toast.makeText(activity, "Error!", Toast.LENGTH_LONG).show()
                false
            }
        }
    }

    private fun displayData(data: MovieList) {
        movieList = data.Search
        recyclerViewAdapter.updateData(movieList)
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