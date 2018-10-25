package com.mm.user.amap.location

import com.amap.api.location.AMapLocation
import java.util.*

/**
 * created by hongyang on 18-9-19
 */
interface AMapListener {
    interface LocationListener {
        fun onLocation(aMapLocation: AMapLocation)
    }

    interface TraceListener {
        fun onTrace(latLng: ArrayList<com.mm.user.fastmap.info.LatLng>)
        fun onError(errorInfo: String?)
    }
}
