package com.example.gitaarstemmer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.gitaarstemmer.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var menuBarToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        createFragment()
        createMenu()

        setContentView(binding.root)
    }

    // begint de eerste fragment
    fun createFragment() {
        val welcomeScreenFragment = WelcomeScreenFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, welcomeScreenFragment)
            commit()
        }
    }
    fun createMenu(){
        menuBarToggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.menu_open, R.string.menu_close)
        binding.drawerLayout.addDrawerListener(menuBarToggle)
        menuBarToggle.syncState()

        // when the menu drawer opens, the toggle button moves to a "back" button and it will close again.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menuPony -> doStuff()
                // ...
            }
            true
        }

    }

    // opent het menu wanneer op de knop wordt gedrukt
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // we need to do this to respond correctly to clicks on menu items, otherwise it won't be caught
        if(menuBarToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    //FIXME: delete
    fun doStuff(){}
}