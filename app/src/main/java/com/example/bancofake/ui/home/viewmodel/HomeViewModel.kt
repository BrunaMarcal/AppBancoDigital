package com.example.bancofake.ui.home.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.bancofake.Repository
import com.example.bancofake.database.Usuario
import kotlinx.coroutines.CoroutineDispatcher

class HomeViewModel (application: Application, private val repository: Repository, private val dispatcher: CoroutineDispatcher): AndroidViewModel(application) {

    fun getUsuario(id: Long): LiveData<Usuario> = repository.getUsuarioId(id)

    class HomeViewModelProviderFactory(private val application: Application, private val repository: Repository, private val dispatcher: CoroutineDispatcher) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                return HomeViewModel(application, repository, dispatcher) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")

        }
    }
}