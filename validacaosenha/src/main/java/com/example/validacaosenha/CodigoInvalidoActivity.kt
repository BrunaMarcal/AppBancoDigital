package com.example.validacaosenha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.validacaosenha.databinding.ActivityCodigoInvalidoBinding
import com.example.validacaosenha.databinding.ActivityValidaSmsBinding

class CodigoInvalidoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCodigoInvalidoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCodigoInvalidoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTentarNovamente.setOnClickListener{
            onBackPressed()
        }
    }
}