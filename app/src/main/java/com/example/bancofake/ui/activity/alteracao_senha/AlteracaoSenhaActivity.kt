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
import com.example.bancofake.ui.activity.home.viewmodel.HomeViewModel
import com.example.bancofake.ui.activity.login.LoginActivity
import com.example.bancofake.util.SharedPreference
import kotlinx.android.synthetic.main.activity_alteracao_senha.*
import kotlinx.android.synthetic.main.activity_home.*
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

//        viewModel.getUsuario(getUsuarioId).observe(this) {
//            it?.let { usuario ->
//                mUsuario = usuario
//                edtSenhaAtual.text = usuario.senha


        binding.btnCancelarAlteracaoSenha.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        btnSalvarAlteracaoSenha.setOnClickListener {
            validaSenha()
        }
    }

    fun updateSenha(id: Long) {
        viewModel.atualizarSenha(id, edtSenhaNova.text.toString())
        finish()
    }

    //Minha senha do usuario vem nula porque ate entao nao tem valor passado pra ela ali
    //Como eu pego o valor após o cadastro ?
    fun validaSenha() {
        var senhaAtual = binding.edtSenhaAtual.text.toString()
        var senhaNova = binding.edtSenhaNova.text.toString()
        var senhaUsuario = mUsuario.senha

        if (senhaAtual != senhaUsuario) {
            Toast.makeText(this, "Senha atual que você digitou está incorreta.", Toast.LENGTH_SHORT)
                .show()
        } else if (senhaAtual.isEmpty()) {
            Toast.makeText(this, "O campo senha atual não pode ficar vazio.", Toast.LENGTH_SHORT)
                .show()
        } else if (senhaNova.isEmpty()) {
            Toast.makeText(this, "O campo senha nova não pode ficar vazio.", Toast.LENGTH_SHORT)
                .show()
        } else if (senhaAtual == mUsuario.senha || senhaNova != mUsuario.senha) {
            mUsuario.senha = senhaNova
            Toast.makeText(this, "Senha alterada com sucesso.", Toast.LENGTH_SHORT).show()
            if (getUsuarioId != 0L) updateSenha(getUsuarioId)
        }
    }
}

//    fun validacaoSenha() {
//        btnSalvarAlteracaoSenha.setOnClickListener {
//            when (viewModel.senhavalida(edtSenhaAtual, edtSenhaNova, mUser.senha)) {
//                0 -> Toast.makeText(
//                    this@AlteracaoSenhaActivity,
//                    "A senha atual nao pode ficar vazia", Toast.LENGTH_SHORT
//                ).show()
//                1 -> Toast.makeText(
//                    this@AlteracaoSenhaActivity,
//                    "Preencha todos os campos", Toast.LENGTH_SHORT
//                ).show()
//                2 -> Toast.makeText(
//                    this@AlteracaoSenhaActivity,
//                    "A nova senha nao pode ficar vazia", Toast.LENGTH_SHORT
//                ).show()
//                3 -> Toast.makeText(
//                    this@AlteracaoSenhaActivity,
//                    "Senha inválida", Toast.LENGTH_SHORT
//                ).show()
//                else -> {
//                }
//            }
//        }
//    }



