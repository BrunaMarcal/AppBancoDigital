package com.example.validacaosenha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.validacaosenha.databinding.ActivityValidaSenhaBinding

class ValidaSenhaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityValidaSenhaBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityValidaSenhaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageBtnComeBackSenha.setOnClickListener {
            onBackPressed()
        }


        binding.btnValidarSenha.setOnClickListener {
            val inputValorCampo1 = binding.editText1.text.toString()
            val inputValorCampo2 = binding.editText2.text.toString()
            val inputValorCampo3 = binding.editText3.text.toString()

            if (inputValorCampo1.isEmpty() && inputValorCampo2.isEmpty() && inputValorCampo3.isEmpty()) {
                Toast.makeText(this, "O campo não pode estar vazio.", Toast.LENGTH_SHORT).show()
            } else if (inputValorCampo1 == "9" && inputValorCampo2 == "9" && inputValorCampo3 == "9") {
                startActivity(Intent(this, CodigoInvalidoActivity::class.java))
            } else {
                Toast.makeText(this, "Sucesso.", Toast.LENGTH_SHORT).show()
//                startActivity(Intent(this, HomeActivity::class.java))
            }
        }
    }

//    companion object{
//        fun newInstance(): {
//            val intent = Intent(this, ValidaSenhaActivity::class.java)
//            context.startActivity(Intent(context, ValidaSenhaActivity::class.java))
//            val args = Bundle()
//
//            val fragment = ()
//            fragment.arguments = args
//            return fragment
//        }}

}