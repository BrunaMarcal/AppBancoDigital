package com.example.bancofake.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bancofake.R
import com.example.bancofake.databinding.ActivityDadosPessoaisBinding
import com.example.bancofake.databinding.ActivityLoginBinding

class DadosPessoaisActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDadosPessoaisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDadosPessoaisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nome = intent.getStringExtra("nome")
        binding.txtNomeDadosPessoais.setText(nome)

        val sobrenome = intent.getStringExtra("sobrenome")
        binding.txtSobrenomeDadosPessoais.setText(sobrenome)

        val cpf = intent.getStringExtra("cpf")
        binding.txtCpfDadosPessoais.setText(cpf)
    }
}