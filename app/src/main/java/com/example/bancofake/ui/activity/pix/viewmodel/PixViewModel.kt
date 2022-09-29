package com.example.bancofake.ui.activity.pix.viewmodel

import android.app.Application
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bancofake.Repository
import com.example.bancofake.database.Usuario
import kotlinx.coroutines.CoroutineDispatcher

class PixViewModel(application: Application, private val repository: Repository, private val dispatcher: CoroutineDispatcher) : AndroidViewModel(application) {

    class PixViewModelProviderFactory(private val application: Application, private val repository: Repository, private val dispatcher: CoroutineDispatcher) : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(PixViewModel::class.java)) {
                    return PixViewModel(application, repository, dispatcher) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
    }
}