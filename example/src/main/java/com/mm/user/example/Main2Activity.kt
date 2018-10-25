package com.mm.user.example

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import com.mm.user.amap.MapOptions
import com.mm.user.amap.MapStandard
import com.mm.user.amap.location.AMapLocation
import com.mm.user.amap.location.AMapLocations
import com.mm.user.fastmap.info.MapMessage
import com.mm.user.fastmap.location.MapLocations
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
        val v =MapLocations(AMapLocation.newLocation())
        v.register(this)
        v.startLocation(object :MapStandard.LocationListener{
            override fun onLocation(mapMessage: MapMessage) {
                 Log.e("mapMessage",mapMessage.address);
            }
        })
    }

    fun init() {

        pause.setOnClickListener {

        }

        end.setOnClickListener {
            DataUtils.endLocation = map!!.endLocation()
            val v = Intent(this, EndActivity1::class.java)
            startActivity(v)
        }

    }
}
