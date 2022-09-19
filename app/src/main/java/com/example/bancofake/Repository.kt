package com.example.bancofake

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.bancofake.database.AppDatabase
import com.example.bancofake.database.Usuario
import com.example.bancofake.database.UsuarioDao

class Repository (context: Context) {

    private val usuarioDao: UsuarioDao by lazy {
        AppDatabase.getDataBase(context).usuarioDao()
    }

    fun inserirUsuario(usuario: Usuario) {
        usuarioDao.inserirUsuario(usuario)
    }

    fun getUsuario (cpf: String, senha: String): LiveData<Usuario> =
        usuarioDao.selecionarUsuario(cpf, senha)

    fun getUsuarioId(id: Long): LiveData<Usuario> = usuarioDao.selecionarId(id)
}