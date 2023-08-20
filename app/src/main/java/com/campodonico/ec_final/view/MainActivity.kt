package com.campodonico.ec_final.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.campodonico.ec_final.R
import com.campodonico.ec_final.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_michi) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnvMenu.setupWithNavController(navController)

        /*
        binding.bnvMenu.setOnItemSelectedListener { item ->

            when(item.itemId){
                R.id.item_michi_list ->{
                    binding.txtHello.text = "Michi list"
                    true
                }
                R.id.item_michi_favorite ->{
                    binding.txtHello.text = "Michi favorite"
                    true
                }
                R.id.item_michi_info ->{
                    binding.txtHello.text = "Michi info"
                    true
                }
                else ->{
                    binding.txtHello.text = "error"
                    false
                }
            }


        }*/


    }
}