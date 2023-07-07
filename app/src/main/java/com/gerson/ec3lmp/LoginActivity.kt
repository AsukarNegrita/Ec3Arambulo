package com.gerson.ec3lmp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Patterns
import android.widget.Toast
import androidx.core.view.get
import androidx.core.widget.addTextChangedListener
import com.gerson.ec3lmp.databinding.ActivityLoginBinding

class   LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        fun validaremailpssw(email: String, password: String):Boolean{
            val valemail= email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
            val valpssw=password.isNotEmpty() && password.length>=6
            return valemail && valpssw
        }

        binding.etEmail.editText?.addTextChangedListener { text->
            binding.btnLogin.isEnabled= validaremailpssw(text.toString(),binding.etPassword.editText?.text.toString())
        }
        binding.etPassword.editText?.addTextChangedListener{text ->
            binding.btnLogin.isEnabled= validaremailpssw(binding.etEmail.editText?.text.toString(),text.toString())
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.editText?.text.toString()
            val password = binding.etPassword.editText?.text.toString()

            if (email == "ejemplo@idat.edu.pe" && password == "123456") {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, "Correo o contraseña incorrectos",Toast.LENGTH_SHORT).show()
            }
        }


    }
}