package com.example.measuredata

import android.hardware.*
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity(), SensorEventListener{

    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    private lateinit var sensorManager: SensorManager
    private var ppgdataList = mutableListOf<PPGData>()
    private var db :PPGDataDao? = null

    //The sensor id. May differ from device to device.
    private val PPG_SENSOR = 65572

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = PPGDb.getDatabase(this,lifecycleScope).ppgDao()
        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
                when (result) {
                    true -> {
                        Log.i("IDK", "Body sensors permission granted")
                    }
                    false -> Log.i("IDK", "Body sensors permission not granted")
                }
            }

    }

    override fun onStart() {
        super.onStart()
        permissionLauncher.launch(android.Manifest.permission.BODY_SENSORS)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        val sensorList: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)
        for (currentSensor in sensorList) {
            Log.d(
                "Device List",
                "Name: " + currentSensor.name + " /Type_String: " + currentSensor.stringType + " /Type_number: " + currentSensor.type
            )
        }
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(PPG_SENSOR), SensorManager.SENSOR_DELAY_FASTEST)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event!!.sensor.type == PPG_SENSOR){
            Log.d("PPG", "${event.values[0]} ${event.values[1]} ${event.timestamp}")
            // Log.d("PPG", "${event.values[0]} ${event.values[1]} ${event.timestamp}")
            // Log.d("PPG", "${event.values.map{x-> x.toString()}.reduce{x, y -> x + " " + y}} ${event.timestamp}")
            ppgdataList.add(PPGData(channel1 = event.values[0], channel2 = event.values[1], timestamp = event.timestamp))
            if(ppgdataList.size >= 1000){
                lifecycleScope.launch {
                    db!!.insertAll(ppgdataList)
                    ppgdataList = mutableListOf()
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

}
