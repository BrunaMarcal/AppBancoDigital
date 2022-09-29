package com.example.bancofake.ui.activity.cadastro.viewmodel

import android.app.Application
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.bancofake.Repository
import com.example.bancofake.database.Usuario
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CadastroViewModel (application: Application, private val repository: Repository, private val dispatcher: CoroutineDispatcher) : AndroidViewModel(application) {

    fun inserirUsuario(usuario: Usuario) = viewModelScope.launch(Dispatchers.IO) {
        repository.inserirUsuario(usuario)
    }

    fun cadastroValido(
        edtRegisterName: EditText,
        edtSobrenome: EditText,
        edtCpf: EditText,
        edtSenha: EditText
    ): Int {
        return if (TextUtils.isEmpty(edtRegisterName.text.toString())) {
            return 0
        } else if (TextUtils.isEmpty(edtSobrenome.text.toString())) {
            return 1
        } else if (TextUtils.isEmpty(edtCpf.text.toString())) {
            return 2
        } else if (TextUtils.isEmpty(edtSenha.text.toString())) {
            return 3
        } else -1
    }

    class CadastroViewModelProviderFactory(
        private val application: Application,
        private val repository: Repository,
        private val dispatcher: CoroutineDispatcher
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CadastroViewModel::class.java)) {
                return CadastroViewModel(application, repository, dispatcher) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}