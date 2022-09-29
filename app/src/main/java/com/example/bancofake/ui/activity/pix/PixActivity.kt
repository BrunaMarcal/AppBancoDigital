package com.example.bancofake.ui.activity.pix

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.PatternsCompat
import androidx.core.view.isVisible
import com.example.bancofake.Repository
import com.example.bancofake.databinding.ActivityPixBinding
import com.example.bancofake.ui.activity.pix.viewmodel.PixViewModel
import com.example.bancofake.ui.activity.pix_valor.PixValorActivity
import kotlinx.android.synthetic.main.activity_pix.*
import kotlinx.coroutines.Dispatchers


class PixActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPixBinding
    lateinit var viewModel: PixViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPixBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttonEmail = imgEmailPix
        val buttonTelefone = imgTelefonePix
        val inputEmail = edtEmail
        val inputTelefone = edtTelefone

        val repository = Repository(this)
        viewModel =
            PixViewModel.PixViewModelProviderFactory(application, repository, Dispatchers.IO)
                .create(PixViewModel::class.java)

        buttonEmail.setOnClickListener{
            inputEmail.visibility = View.VISIBLE
        }

        buttonTelefone.setOnClickListener{
            inputTelefone.visibility = View.VISIBLE
        }

        btnAvancarPix.setOnClickListener{
            if (edtEmail.isVisible) {
                validaEmail()
            } else if (edtTelefone.isVisible) {
                validaTelefone()
            } else {
                Toast.makeText(this, "Selecione um tipo de entrada", LENGTH_SHORT).show()
            }
        }
    }

    private fun validaEmail () : Boolean {
        val inputEmail = binding.edtEmail.text.toString()
        return if (inputEmail.isEmpty()) {
            binding.edtEmail.error = "O campo de email não pode estar vazio."
            false
        } else if (!PatternsCompat.EMAIL_ADDRESS.matcher(inputEmail).matches()) {
            binding.edtEmail.error = "Por favor digite um email válido."
            false
        } else {
            binding.edtEmail.error = null
            startActivity(Intent(this@PixActivity, PixValorActivity::class.java))
            true
        }
    }

    private fun validaTelefone() : Boolean {
        val inputTelefone = binding.edtTelefone.text.toString()
        val telefone = inputTelefone.replace("\\D", "")
        return if (telefone.isEmpty()) {
            binding.edtTelefone.error = "O campo telefone não pode estar vazio."
            false
        } else if (!(telefone.length in 10..11)) {
            binding.edtTelefone.error = "Por favor digite um telefone válido. Exemplo: 00000-0000"
            false
        } else {
            binding.edtTelefone.error = null
            startActivity(Intent(this@PixActivity, PixValorActivity::class.java))
            true
        }
    }
}
