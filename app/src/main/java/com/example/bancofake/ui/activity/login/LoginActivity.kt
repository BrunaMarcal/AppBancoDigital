package com.example.bancofake.ui.activity.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bancofake.Repository
import com.example.bancofake.databinding.ActivityLoginBinding
import com.example.bancofake.ui.activity.home.HomeActivity
import com.example.bancofake.ui.activity.cadastro.CadastroActivity
import com.example.bancofake.ui.activity.login.viewmodel.LoginViewModel
import com.example.bancofake.util.SharedPreference
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.Dispatchers

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository(this)
        viewModel = LoginViewModel.LoginViewModelProviderFactory(application, repository, Dispatchers.IO).create(LoginViewModel::class.java)

        val sharedPreference = SharedPreference(this)

        login(sharedPreference)

        binding.txtCadastro.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }

    fun login(sharedPreference: SharedPreference){
        btnAcessar.setOnClickListener {
            when(viewModel.isValid(edtCpfLogin, edtSenhaLogin)){
                0 -> Toast.makeText(this@LoginActivity, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                1 -> Toast.makeText(this@LoginActivity, "Preencha a senha", Toast.LENGTH_SHORT).show()
                else -> {
                    viewModel.getUsuario(edtCpfLogin.text.toString(), edtSenhaLogin.text.toString()).observe(this
                    ) {
                        it?.let {
                            sharedPreference.salvarDados(USUARIO, it.id)
                            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                            finish()
                        } ?: kotlin.run {
                            Toast.makeText(this, "Cpf ou senha invalido", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val USUARIO = "USUARIO"
    }
}