package com.example.kotlin_layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.kotlin_layout.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityMainBinding.inflate(layoutInflater) //Xml baglayici ile bu sayfaya cagirmis oluyoruz.
        setContentView(binding.root)

        binding.ButtonHesapla.setOnClickListener {
            bahsisHesapla()
        }

    }

    fun bahsisHesapla() {
        val hizmetBedeliString = binding.editTextHizmetBedeli.text.toString()

        val tutar = hizmetBedeliString.toDoubleOrNull()

        if(tutar == null){
            binding.textViewBahsisTutari.text = ""
            return
        }

        val secilenRadioButton = binding.radioGroupBahsisSecenekleri.checkedRadioButtonId

        val bahsisYuzdesi = when (secilenRadioButton) {
            R.id.radioButton_YuzdeYirmi -> 0.20
            R.id.radioButton_YuzdeOnksekiz -> 0.18
            else -> 0.15
        }

        var bahsis = bahsisYuzdesi * tutar

        val yukariYuvarla = binding.SwitchBahsisYukariYuvarla.isChecked

        if (yukariYuvarla)
            bahsis = kotlin.math.ceil(bahsis)

        val formatlananBahsis = NumberFormat.getCurrencyInstance(Locale("tr","TR")).format(bahsis)
        binding.textViewBahsisTutari.text = getString(R.string.bahsis_tutari,formatlananBahsis)
    }
}