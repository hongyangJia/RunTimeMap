package com.mm.user.amap.polyline

import android.graphics.Color
import com.amap.api.maps.AMap
import com.amap.api.maps.model.LatLng
import com.amap.api.maps.model.PolylineOptions
import com.mm.user.fastmap.info.MapMessage

/**
 * created by hongyang on 18-9-19
 */
object AMapPolylines {

    val options = PolylineOptions()
    val colorList = java.util.ArrayList<Int>()
    var latLngs = ArrayList<MapMessage>()

    init {
        colorList.add(Color.GREEN)
        defultOptions()
    }

    fun startDraw(aMap: AMap, latLng: MapMessage) {
        this.latLngs.add(latLng)
        options.add(LatLng(latLng.latLng.latitude, latLng.latLng.longitude))
        aMap.addPolyline(options)
    }

    fun startDraw(aMap: AMap, latLng: ArrayList<LatLng>) {
        options.addAll(latLng)
        aMap.addPolyline(options)
    }

    fun defultOptions() {
        options.colorValues(colorList)
        options.useGradient(true)
        options.width(16f)
    }

    fun endColor(aMap: AMap) {
        colorList.clear()
        colorList.add(Color.YELLOW)
        defultOptions()
    }

    fun isDraw(latLng: MapMessage): Boolean {
        if (latLngs.size == 0) {
            return true
        }
        val latLngAll = latLngs.get(latLngs.size - 1)
        if (latLngAll.latLng.latitude != latLng.latLng.latitude
                || latLngAll.latLng.longitude != latLngAll.latLng.longitude) {
            return true
        }
        return false
    }

}