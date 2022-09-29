package com.example.bancofake.ui.activity.dados_pessoais

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bancofake.R
import com.example.bancofake.Repository
import com.example.bancofake.database.Usuario
import com.example.bancofake.databinding.ActivityDadosPessoaisBinding
import com.example.bancofake.databinding.ActivityLoginBinding
import com.example.bancofake.ui.activity.dados_pessoais.viewmodel.DadosPessoaisViewModel
import com.example.bancofake.ui.activity.home.HomeActivity
import com.example.bancofake.ui.activity.home.viewmodel.HomeViewModel
import com.example.bancofake.ui.activity.login.LoginActivity
import com.example.bancofake.util.SharedPreference
import kotlinx.android.synthetic.main.activity_dados_pessoais.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.Dispatchers

class DadosPessoaisActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDadosPessoaisBinding
    lateinit var viewModel: DadosPessoaisViewModel
    private var getUsuarioId: Long = 0
    lateinit var mUsuario: Usuario
    lateinit var sharedPreference: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDadosPessoaisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository(this)
        viewModel = DadosPessoaisViewModel.DadosPessoaisViewModelProviderFactory(application, repository, Dispatchers.IO).create(DadosPessoaisViewModel::class.java)

        sharedPreference = SharedPreference(this)
        sharedPreference.getDado(LoginActivity.USUARIO)?.let {
            getUsuarioId = it
        }
        viewModel.getUsuario(getUsuarioId).observe(this) {
            it?.let { usuario ->
                mUsuario = usuario
                txtNomeDadosPessoais.text = usuario.nome
                txtSobrenomeDadosPessoais.text = usuario.sobrenome
                txtCpfDadosPessoais.text = usuario.cpf
            }
        }
    }
}