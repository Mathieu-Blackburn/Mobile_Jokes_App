package com.nomdelacompagnie.tp2_mathieu_blackburn_rogers

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import com.nomdelacompagnie.tp2_mathieu_blackburn_rogers.databinding.ActivityChangepwdBinding
import java.io.*

class ChangePwdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityChangepwdBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val localStorage = getSharedPreferences("login", Context.MODE_PRIVATE)
        val FileLogin = File(this.filesDir, "logins.dta")
        FileLogin.createNewFile()

        binding.btnRegister.setOnClickListener(){
            if(binding.edUsername.text.isEmpty() ||
                binding.edPassword.text.isEmpty()){
                val snack = Snackbar.make(binding.root,
                    "Please enter the passwords", Snackbar.LENGTH_LONG).show()
            }
            else{
                val stream: InputStream = File(this.filesDir, "logins.dta").inputStream()
                val lineList = mutableListOf<String>()
                var loginList = listOf<String>()
                var ok = false
                var line = ""

                stream.bufferedReader().forEachLine {

                    loginList = it.split(";")
                    if(loginList[0] == intent.getStringExtra("username") &&
                            loginList[1] == binding.edUsername.text.toString()){
                        ok = true
                        line = loginList[0] + ";" + binding.edPassword.text
                    }
                    else{
                        line = it
                    }
                    lineList.add(line)
                }
//                finish()
                if(ok){
                    FileLogin.delete()
                    FileLogin.createNewFile()

                    for (i in lineList){
                        FileLogin.appendText(i + "\n")
                    }

//                    FileLogin.appendText("" + binding.edUsername.text + ";" + binding.edPassword.text + "\n")
//                    val intent = Intent(this, JokesActivity :: class.java)
//
//                    startActivity(intent)
                    finish()
                }
                else{
                    val snack = Snackbar.make(binding.root,
                        "Old password does not match", Snackbar.LENGTH_LONG).show()
                }
            }




        }
    }
}