package com.example.bancofake.ui.activity.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bancofake.Repository
import com.example.bancofake.database.Usuario
import com.example.bancofake.databinding.ActivityHomeBinding
import com.example.bancofake.ui.activity.alteracao_senha.AlteracaoSenhaActivity
import com.example.bancofake.ui.activity.dados_pessoais.DadosPessoaisActivity
import com.example.bancofake.ui.activity.pix.PixActivity
import com.example.bancofake.ui.activity.login.LoginActivity
import com.example.bancofake.ui.activity.home.viewmodel.HomeViewModel
import com.example.bancofake.util.SharedPreference
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.Dispatchers

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    lateinit var viewModel: HomeViewModel
    private var getUsuarioId: Long = 0
    lateinit var mUsuario: Usuario
    lateinit var sharedPreference: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository(this)
        viewModel = HomeViewModel.HomeViewModelProviderFactory(application, repository, Dispatchers.IO).create(HomeViewModel::class.java)

        sharedPreference = SharedPreference(this)
        sharedPreference.getDado(LoginActivity.USUARIO)?.let {
            getUsuarioId = it
        }

        viewModel.getUsuario(getUsuarioId).observe(this) {
            it?.let { usuario ->
                mUsuario = usuario
                txtNomeHome.text = usuario.nome
                txtCpfHome.text = usuario.cpf
                txtSobrenomeHome.text = usuario.sobrenome
                txtSaldoDaContaHome.text = usuario.saldo.toString()
            }
        }

        binding.btnDadosPessoais.setOnClickListener{
            val intent = Intent(this, DadosPessoaisActivity::class.java)
            startActivity(intent)
        }


        binding.btnPix.setOnClickListener{
            val intent = Intent(this, PixActivity::class.java)
            startActivity(intent)

        }

        binding.btnAlterarSenha.setOnClickListener {
            val intent = Intent(this, AlteracaoSenhaActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        const val USUARIO = "USUARIO"
    }
}
