package com.example.gitaarstemmer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.gitaarstemmer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var menuBarToggle: ActionBarDrawerToggle

    // fragments
    private var stemmerFragment = StemmerFragment()
    private var opgeslagenStemmingenFragment = OpgeslagenStemmingenFragment()
    private var maakStemmingenFragment = MaakStemmingenFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        supportFragmentManager.beginTransaction().apply {
            //kan niet met openFragment() door de terug knop
            replace(R.id.fragmentContainer, stemmerFragment)
            commit()
        }
        createMenu()

        setContentView(binding.root)
    }

    private fun createMenu() {
        menuBarToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            R.string.menu_open,
            R.string.menu_close
        )
        binding.drawerLayout.addDrawerListener(menuBarToggle)
        menuBarToggle.syncState()

        // when the menu drawer opens, the toggle button moves to a "back" button and it will close again.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // als er word geklikt op een menu item
        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menuTuner -> openFragment(stemmerFragment)
                R.id.menuOpgeslagen -> openFragment(opgeslagenStemmingenFragment)
                R.id.menuAanmaken -> openFragment(maakStemmingenFragment)
                // ...
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START, true) // sluit het menu
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (menuBarToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    // verandert naar een ander fragment
    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment)
            addToBackStack(null) // terug knop
            commit()
        }
    }
}