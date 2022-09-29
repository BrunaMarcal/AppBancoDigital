package com.example.bancofake.ui.activity.pix_valor

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.bancofake.R
import com.example.bancofake.Repository
import com.example.bancofake.database.Usuario
import com.example.bancofake.databinding.ActivityPixValorBinding
import com.example.bancofake.ui.activity.home.HomeActivity
import com.example.bancofake.ui.activity.pix_valor.viewmodel.PixValorViewModel
import com.example.bancofake.util.SharedPreference
import com.example.validacaosenha.ValidaSenhaActivity
import kotlinx.android.synthetic.main.activity_pix.*
import kotlinx.android.synthetic.main.activity_pix_valor.*
import kotlinx.coroutines.Dispatchers


class PixValorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPixValorBinding
    lateinit var viewModel: PixValorViewModel
    private var getUsuarioId: Long = 0
    lateinit var sUsuario: Usuario
    lateinit var sharedPreference: SharedPreference
    lateinit var alert: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPixValorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository(this)
        viewModel = PixValorViewModel.PixValorViewModelProviderFactory(application, repository, Dispatchers.IO).create(PixValorViewModel::class.java)

        sharedPreference = SharedPreference(this)
        sharedPreference.getDado(HomeActivity.USUARIO).let {
            getUsuarioId = it
        }

        viewModel.getUsuario(getUsuarioId).observe(this) {
            it?.let { usuario ->
                sUsuario = usuario
                txtSaldoDaContaPix.text = usuario.saldo.toString()
            }
        }

        btnConcluirPixValor.setOnClickListener{
            validacaoPix()
        }
    }
    private fun validacaoPix() {
        var convertEdtValorPix = edtValorPix
        val inputValorPix: Double = convertEdtValorPix.text.toString().toDoubleOrNull() ?: 0.0
        var valorCaixa = sUsuario.saldo

        if (inputValorPix <= 0.0) {
            chamarAlertDialog()
        } else if (inputValorPix > valorCaixa) {
                chamarAlertDialog()
        } else if (inputValorPix == valorCaixa) {
            val resultadoPix = valorCaixa - inputValorPix
            valorCaixa = resultadoPix
            sUsuario.saldo = valorCaixa
        } else if (inputValorPix in 1.0..100.0) {
            val resultadoPix = valorCaixa - inputValorPix
            valorCaixa = resultadoPix
            sUsuario.saldo = valorCaixa
            startActivity(Intent(this@PixValorActivity, ValidaSenhaActivity::class.java))
        }
    }

    fun chamarAlertDialog() {
        val builder = AlertDialog.Builder(this)
        val view: View = layoutInflater.inflate(R.layout.custom_dialog_igual, null)
        val btnFechar = view.findViewById(R.id.btnVoltarIgual) as Button

        btnFechar.setOnClickListener {
            alert.dismiss()
        }
        builder.setView(view)
        alert = builder.create()
        alert.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alert.show()
    }
}


