package com.example.bancofake

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bancofake.databinding.ActivityAlteracaoSenhaBinding
import com.example.bancofake.databinding.ActivityHomeBinding

class AlteracaoSenhaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlteracaoSenhaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlteracaoSenhaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnCancelarAlteracaoSenha.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}