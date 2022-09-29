package com.example.bancofake.ui.activity.dados_pessoais.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bancofake.Repository
import com.example.bancofake.database.Usuario
import com.example.bancofake.ui.activity.home.viewmodel.HomeViewModel
import kotlinx.coroutines.CoroutineDispatcher

class DadosPessoaisViewModel(application: Application, private val repository: Repository, private val dispatcher: CoroutineDispatcher): AndroidViewModel(application) {

    fun getUsuario(id: Long): LiveData<Usuario> = repository.getUsuarioId(id)

    class DadosPessoaisViewModelProviderFactory(
        private val application: Application,
        private val repository: Repository,
        private val dispatcher: CoroutineDispatcher
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DadosPessoaisViewModel::class.java)) {
                return DadosPessoaisViewModel(application, repository, dispatcher) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")

        }
    }
}