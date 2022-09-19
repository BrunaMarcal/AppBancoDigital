package com.example.bancofake.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bancofake.R
import com.example.bancofake.databinding.ActivityPixBinding

class PixActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPixBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPixBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun verificaChave (){
        binding.imbEmailPix
    }
}