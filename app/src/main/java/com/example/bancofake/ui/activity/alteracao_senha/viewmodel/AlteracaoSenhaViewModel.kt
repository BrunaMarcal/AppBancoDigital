package com.example.bancofake.ui.activity.alteracao_senha.viewmodel

import android.app.Application
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import androidx.lifecycle.*
import com.example.bancofake.Repository
import com.example.bancofake.database.Usuario
import com.example.bancofake.ui.activity.home.viewmodel.HomeViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class AlteracaoSenhaViewModel (application: Application, private val repository: Repository, private val dispatcher: CoroutineDispatcher): AndroidViewModel(application){

    fun getUsuario(id: Long): LiveData<Usuario> = repository.getUsuarioId(id)

    fun atualizarSenha (id: Long, senha: String) = viewModelScope.launch {
        repository.updateSenha(id, senha)
    }

    fun senhavalida (senhaAntiga: EditText, senhaNova: EditText, senhaUsuario: String): Int {
        return if (TextUtils.isEmpty(senhaAntiga.text.toString())) {
            return 0
        } else if (TextUtils.isEmpty(senhaAntiga.text.toString()) && TextUtils.isEmpty(senhaNova.text.toString())) {
            return 1
        } else if (TextUtils.isEmpty(senhaNova.text.toString())) {
            return 2
        } else if (senhaAntiga.text.toString() != senhaUsuario) {
            return 3
        } else -1
    }

    class AlteracaoSenhaViewModelProviderFactory(private val application: Application, private val repository: Repository, private val dispatcher: CoroutineDispatcher) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlteracaoSenhaViewModel::class.java)) {
                return AlteracaoSenhaViewModel(application, repository, dispatcher) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")

        }
    }
}