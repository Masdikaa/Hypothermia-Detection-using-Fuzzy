package com.masdika.hypothermiadetectionusingfuzzy

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.masdika.hypothermiadetectionusingfuzzy.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var resultDetection: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnSubmit.setOnClickListener {
            val bodyTemper: String = binding.bodyTemperature.text.toString()
            val hearRate: String = binding.heartRate.text.toString()
            val bloodOxygen: String = binding.bloodSaturate.text.toString()

            if (bodyTemper.isEmpty()) {
                binding.bodyTemperature.error = "Field must be filled"
                binding.bodyTemperature.requestFocus()
            } else if (hearRate.isEmpty()) {
                binding.heartRate.error = "Field must be filled"
                binding.heartRate.requestFocus()
            } else if (bloodOxygen.isEmpty()) {
                binding.bloodSaturate.error = "Field must be filled"
                binding.bloodSaturate.requestFocus()
            } else {
                calculateHypothermia(bodyTemper.toDouble(), hearRate.toInt(), bloodOxygen.toInt())
                Toast.makeText(this, resultDetection, Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun calculateHypothermia(bodyTemper: Double, hearRate: Int, bloodOxygen: Int) {
        val calculateHypothermia =
            CalculateHypothermia(
                bodyTemper,
                hearRate,
                bloodOxygen
            )
        resultDetection = calculateHypothermia.detectHypothermia()
    }
}