package com.mm.user.fastmap.info

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

/**
 * created by hongyang on 18-9-20
 */
class LatLng(latitude: Double,longitude: Double) : Serializable{
    var latitude: Double = 0.0
    var longitude: Double = 0.0

    init {
        this.latitude=latitude
        this.longitude=longitude
    }

}