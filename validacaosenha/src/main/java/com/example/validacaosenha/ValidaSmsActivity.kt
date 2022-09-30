package com.example.validacaosenha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.validacaosenha.databinding.ActivityValidaSmsBinding

class ValidaSmsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityValidaSmsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityValidaSmsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageBtnComeBackSms.setOnClickListener {
            onBackPressed()
        }

        binding.btnValidarSms.setOnClickListener {
            val inputValorCampo1: String = binding.editText1.text.toString()
            val inputValorCampo2: String = binding.editText2.text.toString()
            val inputValorCampo3: String = binding.editText3.text.toString()

            if (inputValorCampo1.isEmpty() || inputValorCampo2.isEmpty() || inputValorCampo3.isEmpty()) {
                Toast.makeText(this, "O campo não pode estar vazio.", Toast.LENGTH_SHORT).show()
            } else if (inputValorCampo1 == "9" && inputValorCampo2 == "9" && inputValorCampo3 == "9") {
                startActivity(Intent(this, CodigoInvalidoActivity::class.java))
            } else {
                Toast.makeText(this, "Sucesso.", Toast.LENGTH_SHORT).show()
//                startActivity(Intent(this, HomeActivity::class.java))
            }
        }
    }
}
