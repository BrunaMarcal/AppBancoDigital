package com.example.bancofake.ui.activity.cadastro
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bancofake.Repository
import com.example.bancofake.database.Usuario
import com.example.bancofake.databinding.ActivityCadastroBinding
import com.example.bancofake.ui.activity.home.HomeActivity
import com.example.bancofake.ui.activity.cadastro.viewmodel.CadastroViewModel
import com.example.bancofake.ui.activity.login.LoginActivity
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.coroutines.Dispatchers

class CadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding
    lateinit var viewModel: CadastroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository(this)
        viewModel = CadastroViewModel.CadastroViewModelProviderFactory(application, repository, Dispatchers.IO).create(CadastroViewModel::class.java)

        binding.btnSalvarNovoCadastro.setOnClickListener {
            inserirUsuario()
        }

        binding.btnCancelarCadastro.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        cadastro()
    }
        fun cadastro() {
            btnSalvarNovoCadastro.setOnClickListener {
                when (viewModel.cadastroValido(
                    edtNomeCadastro,
                    edtSobrenomeCadastro,
                    edtCpfCadastro,
                    edtSenhaCadastro
                )) {
                    0 -> Toast.makeText(
                        this@CadastroActivity,
                        "O nome de usuário não pode ficar vazio",
                        Toast.LENGTH_SHORT
                    ).show()
                    1 -> Toast.makeText(
                        this@CadastroActivity,
                        "Preencha todos os campos",
                        Toast.LENGTH_SHORT
                    ).show()
                    2 -> Toast.makeText(this@CadastroActivity,
                        "Preencha o cpf", Toast.LENGTH_SHORT)
                        .show()
                    3 -> Toast.makeText(
                        this@CadastroActivity,
                        "Preencha a senha",
                        Toast.LENGTH_SHORT
                    ).show()
                    else -> {
                        inserirUsuario()
                    }
                }
            }
        }

    private fun inserirUsuario() {
        viewModel.inserirUsuario(Usuario(edtNomeCadastro.text.toString(), edtSobrenomeCadastro.text.toString(), edtCpfCadastro.text.toString(), edtSenhaCadastro.text.toString()))
        Toast.makeText(this@CadastroActivity, "Cadastro efetuado com sucesso", Toast.LENGTH_SHORT).show()
//        startActivity(Intent(this@CadastroActivity, HomeActivity::class.java))
        finish()
    }
}
