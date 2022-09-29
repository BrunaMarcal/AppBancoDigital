package com.example.bancofake.ui.activity.pix_valor.viewmodel

import android.app.Application
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bancofake.Repository
import com.example.bancofake.database.Usuario
import kotlinx.coroutines.CoroutineDispatcher
import org.w3c.dom.Text

class PixValorViewModel(application: Application, private val repository: Repository, private val dispatcher: CoroutineDispatcher) : AndroidViewModel(application) {

    fun getUsuario(id: Long): LiveData<Usuario> = repository.getUsuarioId(id)

    class PixValorViewModelProviderFactory(
        private val application: Application,
        private val repository: Repository,
        private val dispatcher: CoroutineDispatcher
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PixValorViewModel::class.java)) {
                return PixValorViewModel(application, repository, dispatcher) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
