package com.example.validacaosenha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.validacaosenha.databinding.ActivityValidaSenhaBinding

class ValidaSenhaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityValidaSenhaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityValidaSenhaBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}