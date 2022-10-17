package com.example.bancofake.ui.activity.alteracao_senha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bancofake.Repository
import com.example.bancofake.database.Usuario
import com.example.bancofake.databinding.ActivityAlteracaoSenhaBinding
import com.example.bancofake.ui.activity.alteracao_senha.viewmodel.AlteracaoSenhaViewModel
import com.example.bancofake.ui.activity.home.HomeActivity
import com.example.bancofake.ui.activity.login.LoginActivity
import com.example.bancofake.util.SharedPreference
import com.example.validacaosenha.ValidaSenhaActivity
import com.example.validacaosenha.ValidaSmsActivity
import kotlinx.android.synthetic.main.activity_alteracao_senha.*
import kotlinx.coroutines.Dispatchers

class AlteracaoSenhaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlteracaoSenhaBinding
    lateinit var viewModel: AlteracaoSenhaViewModel
    private var getUsuarioId: Long = 0
    lateinit var mUsuario: Usuario
    lateinit var sharedPreference: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlteracaoSenhaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository(this)
        viewModel = AlteracaoSenhaViewModel.AlteracaoSenhaViewModelProviderFactory(application, repository, Dispatchers.IO).create(
            AlteracaoSenhaViewModel::class.java)

        sharedPreference = SharedPreference(this)
        sharedPreference.getDado(LoginActivity.USUARIO)?.let {
            getUsuarioId = it
        }

        binding.btnCancelarAlteracaoSenha.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        btnSalvarAlteracaoSenha.setOnClickListener {
            validaSenha()
        }

        viewModel.uiState.observe(this){ state->
            when(state) {
                AlteracaoSenhaViewModel.UiState.Error -> Toast.makeText(this, "Senha atual incorreta", Toast.LENGTH_SHORT).show()
                AlteracaoSenhaViewModel.UiState.Success ->  {
                    telaRandomica ()
                }
            }
        }

    }

    fun updateSenha(id: Long, senhaAtual: String, senhaNova: String) {
        try {
            viewModel.atualizarSenha(id, senhaAtual, senhaNova)
        } catch (error:AlteracaoSenhaException){
            Toast.makeText(this, "Senha atual incorreta", Toast.LENGTH_SHORT).show()
            binding.edtSenhaAtual.setText("")
        } catch (error:Exception) {
            Toast.makeText(this, "Erro ao atualizar senha", Toast.LENGTH_SHORT).show()
        }
    }

    //Minha senha do usuario vem nula porque ate entao nao tem valor passado pra ela ali
    //Como eu pego o valor após o cadastro ?
    fun validaSenha() {
        var senhaAtual = binding.edtSenhaAtual.text.toString()
        var senhaNova = binding.edtSenhaNova.text.toString()

        if (senhaAtual.isEmpty()) {
            Toast.makeText(this, "O campo senha atual não pode ficar vazio.", Toast.LENGTH_SHORT)
                .show()
        } else if (senhaNova.isEmpty()) {
            Toast.makeText(this, "O campo senha nova não pode ficar vazio.", Toast.LENGTH_SHORT)
                .show()
        } else {
            if (getUsuarioId != 0L) updateSenha(getUsuarioId, senhaAtual, senhaNova)
        }
    }

    fun telaRandomica () {
        val numero = (0..1).random()

        if (numero == 0) {
            startActivity(Intent(this, ValidaSenhaActivity::class.java))
        } else if (numero == 1) {
            startActivity(Intent(this, ValidaSmsActivity::class.java))
        }
    }
}



