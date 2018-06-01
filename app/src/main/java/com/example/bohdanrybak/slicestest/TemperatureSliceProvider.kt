package com.example.bohdanrybak.slicestest

import android.net.Uri
import androidx.core.graphics.drawable.IconCompat
import androidx.slice.Slice
import androidx.slice.SliceProvider
import androidx.slice.builders.ListBuilder
import androidx.slice.builders.ListBuilder.INFINITY
import android.app.PendingIntent
import android.content.Intent
import androidx.slice.builders.SliceAction
import com.example.bohdanrybak.slicestest.ChangeTempBroadcastReceiver.Companion.ACTION_PLUS_BEDROOM_TEMPERATURE
import com.example.bohdanrybak.slicestest.ChangeTempBroadcastReceiver.Companion.ACTION_MINUS_BEDROOM_TEMPERATURE
import com.example.bohdanrybak.slicestest.ChangeTempBroadcastReceiver.Companion.ACTION_MINUS_KITCHEN_TEMPERATURE
import com.example.bohdanrybak.slicestest.ChangeTempBroadcastReceiver.Companion.ACTION_PLUS_KITCHEN_TEMPERATURE


class TemperatureSliceProvider: SliceProvider() {

    private val sReqCode = 1

    override fun onCreateSliceProvider(): Boolean {
        return true
    }

    override fun onBindSlice(sliceUri: Uri?): Slice? {
        when(sliceUri?.path){
            "/temperature" -> return createTemperatureSlice(sliceUri)
        }
        return null
    }

    private fun createTemperatureSlice(sliceUri: Uri?): Slice{

        //last parameter - max count of rows
        val sliceListBuilder = ListBuilder(context, sliceUri!!, INFINITY)

        val headerBuilder = ListBuilder.HeaderBuilder(sliceListBuilder).apply {
            setTitle("Temperature")
            setSubtitle("Subtitle")
        }
        sliceListBuilder.setHeader(headerBuilder)


        val plusBedroomTempAction = SliceAction(
               getChangeTempIntent(ACTION_PLUS_BEDROOM_TEMPERATURE),
                IconCompat.createWithResource(context, R.drawable.ic_plus),
                "Plus bedroom temp"
        )

        val minusBedroomTempAction = SliceAction(
                getChangeTempIntent(ACTION_MINUS_BEDROOM_TEMPERATURE),
                IconCompat.createWithResource(context, R.drawable.ic_minus),
                "Minus bedroom temp"
        )

        val bedroomRow = ListBuilder.RowBuilder(sliceListBuilder).apply {
            setTitle(MainActivity.getBedroomTemperatureString(context))
            addEndItem(plusBedroomTempAction)
            addEndItem(minusBedroomTempAction)
        }
        sliceListBuilder.addRow(bedroomRow)


        val plusKitchenTempAction = SliceAction(
                getChangeTempIntent(ACTION_PLUS_KITCHEN_TEMPERATURE),
                IconCompat.createWithResource(context, R.drawable.ic_plus),
                "Plus kitchen temp"
        )

        val minusKitchenTempAction = SliceAction(
                getChangeTempIntent(ACTION_MINUS_KITCHEN_TEMPERATURE),
                IconCompat.createWithResource(context, R.drawable.ic_minus),
                "Minus kitchen temp"
        )

        val kitchenRow = ListBuilder.RowBuilder(sliceListBuilder).apply{
            setTitle(MainActivity.getKitchenTemperatureString(context))
            addEndItem(plusKitchenTempAction)
            addEndItem(minusKitchenTempAction)
        }
        sliceListBuilder.addRow(kitchenRow)

        return sliceListBuilder.build()
    }

    private fun getChangeTempIntent(action: String): PendingIntent {
        val intent = Intent(action)
        intent.setClass(context!!, ChangeTempBroadcastReceiver::class.java)
        return PendingIntent.getBroadcast(context, sReqCode, intent,
                PendingIntent.FLAG_UPDATE_CURRENT)
    }
}