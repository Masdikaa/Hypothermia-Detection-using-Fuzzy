package com.masdika.hypothermiadetectionusingfuzzy

class CalculateHypothermia(
    private val bodyTemperature: Double,
    private val heartRate: Int,
    private val oxygenSaturation: Int
) {
    fun detectHypothermia(): String {
        // Fuzzyfikasi
        val bodyTemperatureFuzzy = fuzzifyBodyTemperature(bodyTemperature)
        val heartRateFuzzy = fuzzifyHeartRate(heartRate)
        val oxygenSaturationFuzzy = fuzzifyOxygenSaturation(oxygenSaturation)

        // Inferensi
        val resultInference = inference(bodyTemperatureFuzzy, heartRateFuzzy, oxygenSaturationFuzzy)

        // Return Defuzzyfikasi
        return defuzzify(resultInference)
    }

    private fun fuzzifyBodyTemperature(bodyTemperature: Double): String {
        return if (bodyTemperature < 28) {
            "Extreme Low"
        } else if (bodyTemperature < 32) {
            "Low"
        } else if (bodyTemperature < 35) {
            "Medium"
        } else {
            "Normal"
        }
    }

    private fun fuzzifyHeartRate(heartRate: Int): String {
        return when (heartRate) {
            in 70..120 -> {
                "Normal"
            }

            in 50..69 -> {
                "Medium"
            }

            else -> {
                "Low"
            }
        }
    }


    private fun fuzzifyOxygenSaturation(oxygenSaturation: Int): String {
        return if (oxygenSaturation > 90) {
            "Normal"
        } else if (oxygenSaturation in 80..89) {
            "Medium"
        } else {
            "Low"
        }
    }

    // Fungsi inferensi
    private fun inference(
        bodyTemperatureFuzzy: String,
        heartRateFuzzy: String,
        oxygenSaturationFuzzy: String
    ): String {
        return when {
            // Rule 1: Mild Hypothermia
            (bodyTemperatureFuzzy == "Low" || bodyTemperatureFuzzy == "Extreme Low") && heartRateFuzzy == "Normal" -> "Mild Hypothermia"

            // Rule 2: Moderate Hypothermia
            (bodyTemperatureFuzzy == "Low" || bodyTemperatureFuzzy == "Extreme Low") && heartRateFuzzy == "Medium" -> "Moderate Hypothermia"

            // Rule 3: Severe Hypothermia
            (bodyTemperatureFuzzy == "Low" || bodyTemperatureFuzzy == "Extreme Low") && heartRateFuzzy == "Low" && oxygenSaturationFuzzy == "Low" -> "Severe Hypothermia"

            else -> "Normal"
        }
    }

    // Fungsi defuzzyfikasi
    private fun defuzzify(resultInference: String): String {
        return resultInference
    }
}