package com.aldemir.androidchips

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.aldemir.androidchips.databinding.ActivityMainBinding
import com.google.android.material.chip.Chip

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.chip1.setOnClickListener {
            Toast.makeText(this, binding.chip1.text, Toast.LENGTH_SHORT).show()

        }
        entryChip()

        choiceChips()

        filterChips()
    }

    private fun filterChips() {
        binding.chipFilterGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip: Chip? = group.findViewById(checkedId)
            if (chip?.isChecked == true) {
                Toast.makeText(this, chip.text, Toast.LENGTH_SHORT).show()
                chip.chipBackgroundColor = getColorStateList(
                    R.color.purple_200
                )
            } 
        }
    }

    private fun choiceChips() {
        binding.choiceGroupChips.setOnCheckedChangeListener { group, checkedId ->
            val chip: Chip? = group.findViewById(checkedId)
            chip?.let {
                Toast.makeText(this, it.text, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun entryChip() {
        binding.etName.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && 
                event.action == KeyEvent.ACTION_UP) {

                binding.apply {
                    val name = etName.text.toString()
                    createChips(name)
                    etName.text.clear()
                }
                return@setOnKeyListener true
            }
            false
        }
    }

    private fun createChips(name: String) {
        val chip = Chip(this)
        chip.apply {
            text = name
            chipIcon = ContextCompat.getDrawable(
                this@MainActivity, R.drawable.ic_cancel)
            isChipIconVisible = false
            isCloseIconVisible = true
            isClickable = true
            isCheckable = true

            binding.apply {
                chipEntryGroup.addView(chip as View)
                chip.setOnCloseIconClickListener {
                    chipEntryGroup.removeView(chip as View)
                }
            }
        }
    }
}