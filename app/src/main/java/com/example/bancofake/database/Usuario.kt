package com.example.bancofake.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.math.BigDecimal

@Entity(tableName = "usuario")
data class Usuario(

    @ColumnInfo(name = "nome")
    val nome: String,
    @ColumnInfo(name = "sobrenome")
    val sobrenome: String,
    @ColumnInfo(name = "cpf")
    val cpf: String,
    @ColumnInfo(name = "senha")
    var senha: String,
    @ColumnInfo(name = "saldo")
    var saldo: Double = 100.0

) : Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}
