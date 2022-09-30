package com.example.validacaosenha

import android.content.Context

interface ValidacaoSenha {

    fun callValidacaoSenha(context: Context, funcSuccess: ()-> Unit, funcError: ()-> Unit)

}