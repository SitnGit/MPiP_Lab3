package com.example.lab3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.lab3.R
import com.example.lab3.models.Movie
import com.example.lab3.viewmodels.SecondViewModel

class SecondFragment : Fragment() {

    private lateinit var secondViewModel: SecondViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_second, container, false)

        val title: String = SecondFragmentArgs.fromBundle(requireArguments()).title

        secondViewModel = ViewModelProvider(this).get(SecondViewModel::class.java)
        secondViewModel.getMovieMutableLiveData().observe(viewLifecycleOwner,
            { m -> showMovie(m!!, view) })
        secondViewModel.findMovieByTitle(title)

        return view
    }

    private fun showMovie(movie: Movie, view: View){
        val title = view.findViewById<TextView>(R.id.Title)
        val image = view.findViewById<ImageView>(R.id.Image)
        val year = view.findViewById<TextView>(R.id.Year)
        title.text = movie.Title
        year.text = movie.Year

        Glide.with(view.context).load(movie.Poster).into(image)
    }

}