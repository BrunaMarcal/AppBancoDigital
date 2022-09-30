package com.example.validacaosenha

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity

class ValidacaoSenhaImpl : ValidacaoSenha {

    override fun callValidacaoSenha(
        context: Context,
        funcSuccess: () -> Unit,
        funcError: () -> Unit
    ) {
        val numero = (0..1).random()

        if (numero == 0) {
            val intent = Intent(context, ValidaSenhaActivity::class.java)
            context.startActivity(Intent(context, ValidaSenhaActivity::class.java))
        } else if (numero == 1) {
            context.startActivity(Intent(context, ValidaSmsActivity::class.java))
        }
    }
}