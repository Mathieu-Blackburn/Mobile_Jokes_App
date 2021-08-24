package com.nomdelacompagnie.tp2_mathieu_blackburn_rogers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nomdelacompagnie.tp2_mathieu_blackburn_rogers.databinding.ActivityJokesBinding
import com.nomdelacompagnie.tp2_mathieu_blackburn_rogers.databinding.ActivityLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class JokesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityJokesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvWelcome.text = "Welcome, " + intent.getStringExtra("username")

        val endPointJoke10 = "https://official-joke-api.appspot.com/jokes/ten"

        val objectType = object : TypeToken<ArrayList<JokeData?>?>(){}.type

        var myJokes = mutableListOf<JokeData>()

        lifecycleScope.launch(Dispatchers.Default){
            val stringJoke = URL(endPointJoke10).readText()

            myJokes = Gson().fromJson(stringJoke, objectType)

            this@JokesActivity.runOnUiThread {
                val adapter = RvAdapter(myJokes)
                binding.rvJokes.adapter = adapter
                binding.rvJokes.layoutManager = LinearLayoutManager(this@JokesActivity)
            }
        }

        binding.btnResetPwd.setOnClickListener(){
            val intentPwd = Intent(this, ChangePwdActivity::class.java)
            intentPwd.putExtra("username", intent.getStringExtra("username"))
            startActivity(intentPwd)
        }
    }
}