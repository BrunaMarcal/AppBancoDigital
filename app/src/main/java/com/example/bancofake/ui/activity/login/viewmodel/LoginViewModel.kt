package com.example.bancofake.ui.activity.login.viewmodel

import android.app.Application
import android.text.TextUtils
import android.widget.EditText
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.bancofake.Repository
import com.example.bancofake.database.Usuario
import kotlinx.coroutines.CoroutineDispatcher
import java.lang.IllegalArgumentException

class LoginViewModel (application: Application, private val repository: Repository, private val dispatcher: CoroutineDispatcher): AndroidViewModel(application) {

    fun getUsuario (cpf: String, senha: String): LiveData<Usuario> =
        repository.getUsuario(cpf, senha)

    fun isValid(edtNome: EditText, edtSenha: EditText): Int{
        return if (TextUtils.isEmpty(edtNome.text.toString()) && TextUtils.isEmpty(edtSenha.text.toString())) {
            return  0
        } else if (TextUtils.isEmpty(edtSenha.text.toString())) {
            return  1
        } else -1
    }

    class LoginViewModelProviderFactory(private val application: Application, private val repository: Repository, private val dispatcher: CoroutineDispatcher): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
                return LoginViewModel(application, repository, dispatcher) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}