package com.example.bancofake.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "usuario")
data class Usuario(

    @ColumnInfo(name = "nome")
    val nome: String,
    @ColumnInfo(name = "sobrenome")
    val sobrenome: String,
    @ColumnInfo(name = "cpf")
    val cpf: String,
    @ColumnInfo(name = "senha")
    val senha: String

): Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}
