package com.mm.user.amap.conversion

import com.amap.api.location.AMapLocation
import com.mm.user.fastmap.info.LatLng
import com.mm.user.fastmap.info.MapMessage

/**
 * created by hongyang on 18-9-26
 */
object AMapToMessage {

    /**
     * AMapLocation 转换 MapMessage
     */
    fun convertMessage(aMapLocation: AMapLocation): MapMessage {
        val location = com.mm.user.fastmap.info.LatLng(aMapLocation.latitude, aMapLocation.longitude)
        return MapMessage(location, aMapLocation.bearing, aMapLocation.speed, aMapLocation.time,aMapLocation.address)
    }

    /**
     * AMapLocation latLng 转换 MapMessage latLng
     */
    fun convertAMapLatLng(latLngs: ArrayList<LatLng>): ArrayList<com.amap.api.maps.model.LatLng> {
        val aMapLatLngs = ArrayList<com.amap.api.maps.model.LatLng>()
        for (latlng in latLngs) {
            aMapLatLngs.add(com.amap.api.maps.model.LatLng(latlng.latitude, latlng.longitude))
        }
        return aMapLatLngs;
    }

}