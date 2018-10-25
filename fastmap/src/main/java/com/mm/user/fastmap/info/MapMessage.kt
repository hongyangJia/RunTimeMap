package com.mm.user.fastmap.info

import java.io.Serializable

/**
 * created by hongyang on 18-9-26
 */
class MapMessage : Serializable {

    var latLng: LatLng = LatLng(0.0, 0.0)
    var bearing: Float = 0.0f
    var speed: Float = 0.0f
    var time: Long = 0

    constructor(latLng: LatLng, bearing: Float, speed: Float, time: Long) {
        this.latLng = latLng
        this.bearing = bearing
        this.speed = speed
        this.time = time
    }
}