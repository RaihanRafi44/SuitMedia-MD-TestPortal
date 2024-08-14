package com.raihan.testportal.presentation.firstscreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.raihan.testportal.databinding.ActivityFirstScreenBinding
import com.raihan.testportal.presentation.secondscreen.SecondScreenActivity

class FirstScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCheck.setOnClickListener {
            val inputText = binding.etPalindrome.text.toString()
            if (inputText.isEmpty()) {
                showAlertDialog("Warning", "Please input text")
            } else if (isPalindrome(inputText)) {
                showAlertDialog("Palindrome", "isPalindrome")
            } else {
                showAlertDialog("Not Palindrome", "Not Palindrome")
            }
        }

        binding.btnNext.setOnClickListener {
            if (binding.etName.text.toString().isEmpty()) {
                showAlertDialog("Warning", "Please input your name")
            } else {
                val intent = Intent(this, SecondScreenActivity::class.java)
                intent.putExtra("name", binding.etName.text.toString())
                startActivity(intent)
            }
        }
    }

    private fun isPalindrome(text: String): Boolean {
        val cleanedText = text.replace(Regex("[^A-Za-z]"), "").lowercase()
        return cleanedText == cleanedText.reversed()
    }

    private fun showAlertDialog(
        title: String,
        message: String,
    ) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}
