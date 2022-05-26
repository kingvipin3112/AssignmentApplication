package com.example.myapplication.viemodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    val data = repository.getDetails()
}