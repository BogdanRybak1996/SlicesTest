package com.example.bohdanrybak.slicestest

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        var bedroomTemperature = 15
        var kitchenTemperature = 15

        fun getBedroomTemperatureString(context: Context): String{
            return context.getString(R.string.bedroom_temperature_string, bedroomTemperature)
        }

        fun getKitchenTemperatureString(context: Context): String{
            return context.getString(R.string.kitchen_temperature_string, kitchenTemperature)
        }

        fun changeBedroomTemperature(context: Context, newTemperature: Int){
            bedroomTemperature = newTemperature
            updateSlice(context)
        }

        fun changeKitchenTemperature(context: Context, newTemperature: Int){
            kitchenTemperature = newTemperature
            updateSlice(context)
        }

        private fun updateSlice(context: Context){
            val uri = Uri.parse("content://com.example.bohdanrybak.slicestest/temperature")
            context.contentResolver.notifyChange(uri, null)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        tv_bedroom_temperature.text =
                getString(R.string.bedroom_temperature_string, bedroomTemperature)
        tv_kitchen_temperature.text =
                getString(R.string.kitchen_temperature_string, kitchenTemperature)

        iv_plus_bedroom_temp.setOnClickListener {
            bedroomTemperature++
            tv_bedroom_temperature.text =
                    getString(R.string.bedroom_temperature_string, bedroomTemperature)
        }
        iv_minus_bedroom_temp.setOnClickListener {
            bedroomTemperature--
            tv_bedroom_temperature.text =
                    getString(R.string.bedroom_temperature_string, bedroomTemperature)
        }
        iv_plus_kitchen_temp.setOnClickListener {
            kitchenTemperature++
            tv_kitchen_temperature.text =
                    getString(R.string.kitchen_temperature_string, kitchenTemperature)
        }
        iv_minus_kitchen_temp.setOnClickListener {
            kitchenTemperature--
            tv_kitchen_temperature.text =
                    getString(R.string.kitchen_temperature_string, kitchenTemperature)
        }
        super.onResume()
    }
}
