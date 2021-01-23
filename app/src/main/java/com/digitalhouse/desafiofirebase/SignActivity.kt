package com.digitalhouse.desafiofirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.digitalhouse.desafiofirebase.databinding.ActivitySignBinding

class SignActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}