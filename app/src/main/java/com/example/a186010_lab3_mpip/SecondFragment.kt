package com.example.a186010_lab3_mpip

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.a186010_lab3_mpip.models.Data
import com.example.a186010_lab3_mpip.viewmodels.SecondFragmentViewModel

class SecondFragment : Fragment() {

    private lateinit var secondFragmentViewModel: SecondFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_second, container, false)

        val title: String = SecondFragmentArgs.fromBundle(requireArguments()).title

        secondFragmentViewModel = ViewModelProvider(this).get(SecondFragmentViewModel::class.java)


        secondFragmentViewModel.getMovieMutableLiveData().observe(viewLifecycleOwner,
            { t -> displayData(t!!, view) })

        secondFragmentViewModel.findMovieByTitle(title)

        return view
    }

    private fun displayData(movie: Data, view: View){
        val movieTitle = view.findViewById<TextView>(R.id.movieDetailsTitle)
        val moviePicture = view.findViewById<ImageView>(R.id.moviePicture)
        val movieYear = view.findViewById<TextView>(R.id.movieDetailsYear)

        movieTitle.text = movie.Title
        movieYear.text = movie.Year

        Glide.with(view.context).load(movie.Poster).into(moviePicture)
    }

}