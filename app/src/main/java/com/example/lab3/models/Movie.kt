package com.example.lab3.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(@PrimaryKey(autoGenerate = true) val id: Long = 0,
                val Title: String = "",
                val Year: String = "",
                val Poster: String = "")
