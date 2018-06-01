package com.example.bohdanrybak.slicestest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ChangeTempBroadcastReceiver : BroadcastReceiver() {
    companion object {
        const val ACTION_MINUS_BEDROOM_TEMPERATURE = "minus_bedroom_temp"
        const val ACTION_PLUS_BEDROOM_TEMPERATURE = "plus_bedroom_temp"
        const val ACTION_MINUS_KITCHEN_TEMPERATURE = "minus_kitchen_temp"
        const val ACTION_PLUS_KITCHEN_TEMPERATURE = "plus_kitchen_temp"
    }

    override fun onReceive(ctx: Context, intent: Intent) {
        val action = intent.action

        when (action) {
            ACTION_PLUS_BEDROOM_TEMPERATURE ->
                MainActivity.changeBedroomTemperature(ctx, MainActivity.bedroomTemperature + 1)
            ACTION_MINUS_BEDROOM_TEMPERATURE ->
                MainActivity.changeBedroomTemperature(ctx, MainActivity.bedroomTemperature - 1)
            ACTION_PLUS_KITCHEN_TEMPERATURE ->
                    MainActivity.changeKitchenTemperature(ctx, MainActivity.kitchenTemperature + 1)
            ACTION_MINUS_KITCHEN_TEMPERATURE ->
                    MainActivity.changeKitchenTemperature(ctx, MainActivity.kitchenTemperature - 1)
        }
    }
}