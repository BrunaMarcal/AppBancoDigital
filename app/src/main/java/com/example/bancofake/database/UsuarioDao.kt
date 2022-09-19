package com.example.bancofake.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun inserirUsuario(usuario: Usuario)

    @Query("SELECT * FROM usuario WHERE cpf = :cpf AND senha = :senha")
    fun selecionarUsuario (cpf: String, senha: String): LiveData<Usuario>

    @Query("SELECT * FROM usuario WHERE id = :id")
    fun selecionarId(id: Long): LiveData<Usuario>
}