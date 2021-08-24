package com.nomdelacompagnie.tp2_mathieu_blackburn_rogers

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import com.nomdelacompagnie.tp2_mathieu_blackburn_rogers.databinding.ActivityLoginBinding
import java.io.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val localStorage = getSharedPreferences("login", Context.MODE_PRIVATE)
        val FileLogin = File(this.filesDir, "logins.dta")
        FileLogin.createNewFile()
        var UserName = ""

        binding.btnLogin.setOnClickListener(){
            if(binding.edUsername.text.isEmpty() ||
                binding.edPassword.text.isEmpty()){
                val snack = Snackbar.make(binding.root,
                    "Please enter a Username and a Password", Snackbar.LENGTH_LONG).show()
            }
            else{
                val stream: InputStream = File(this.filesDir, "logins.dta").inputStream()
                val lineList = mutableListOf<String>()
                var loginList = listOf<String>()
                var ok = false


                stream.bufferedReader().forEachLine {
                    loginList = it.split(";")
                    loginList[1].replace("\n", "")
                    if(loginList[0] == binding.edUsername.text.toString() &&
                        loginList[1] == binding.edPassword.text.toString()){
                        ok = true
                        UserName = loginList[0]
                    }
                }

                if(ok){
                    val intent = Intent(this, JokesActivity :: class.java)
                    intent.putExtra("username", binding.edUsername.text.toString())

                    startActivity(intent)
                }
                else{
                    val snack = Snackbar.make(binding.root,
                        "Wrong Credentials.", Snackbar.LENGTH_LONG).show()

                    UserName = ""
                }
            }

        }

        binding.btnRegister.setOnClickListener(){
            val intent = Intent(this, RegisterActivity:: class.java)
            intent.putExtra("username", UserName)

            startActivity(intent)
        }
    }
}