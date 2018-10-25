package com.mm.user.example

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import com.mm.user.amap.MapOptions
import com.mm.user.amap.MapStandard
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {
    var map: MapStandard? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.initMap()
        this.init()
    }
    fun initMap() {

        map = fragmentManager.findFragmentById(R.id.ff) as MapStandard
        map!!.configure(MapOptions.Builder.zoomTo(16f).isLocationEnabled(true).build())
        map!!.startLocation()

    }

    fun init() {

        pause.setOnClickListener {

        }

        end.setOnClickListener {
            DataUtils.endLocation = map!!.endLocation()
            Log.e("setOnClickListener", DataUtils.endLocation!!.toString())
            val v = Intent(this, EndActivity1::class.java)
            startActivity(v)
        }

    }
}
