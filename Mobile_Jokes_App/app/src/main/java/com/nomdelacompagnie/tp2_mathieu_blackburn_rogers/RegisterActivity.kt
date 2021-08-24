package com.nomdelacompagnie.tp2_mathieu_blackburn_rogers

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import com.nomdelacompagnie.tp2_mathieu_blackburn_rogers.databinding.ActivityLoginBinding
import com.nomdelacompagnie.tp2_mathieu_blackburn_rogers.databinding.ActivityRegisterBinding
import java.io.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val localStorage = getSharedPreferences("login", Context.MODE_PRIVATE)
        val FileLogin = File(this.filesDir, "logins.dta")
        FileLogin.createNewFile()

        binding.btnRegister.setOnClickListener(){
            if(binding.edUsername.text.isEmpty() ||
                binding.edPassword.text.isEmpty()){
                val snack = Snackbar.make(binding.root,
                    "Please enter a Username and a Password", Snackbar.LENGTH_LONG).show()
            }
            else{
                val stream: InputStream = File(this.filesDir, "logins.dta").inputStream()
                val lineList = mutableListOf<String>()
                var loginList = listOf<String>()
                var ok = true

                stream.bufferedReader().forEachLine {
//                lineList.add(it)
                    loginList = it.split(";")
                    if(loginList[0] == binding.edUsername.text.toString()){
                        ok = false
                    }
                }

                if(ok){
                    FileLogin.appendText("" + binding.edUsername.text + ";" + binding.edPassword.text + "\n")
//                    val intent = Intent(this, JokesActivity :: class.java)
//
//                    startActivity(intent)
                    finish()
                }
                else{
                    val snack = Snackbar.make(binding.root,
                        "Username unavailable.", Snackbar.LENGTH_LONG).show()
                }
            }




        }
    }
}