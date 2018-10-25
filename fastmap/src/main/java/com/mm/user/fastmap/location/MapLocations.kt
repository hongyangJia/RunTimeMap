package com.mm.user.fastmap.location

import android.content.Context
import com.mm.user.amap.MapStandard

/**
 * created by hongyang on 18-9-27
 */
class MapLocations : LocationSource {

    private var source: LocationSource? = null

    constructor(source: LocationSource) {
        this.source = source
    }

    override fun register(mContext: Context) {
        if (source != null) {
            source!!.register(mContext)
        }
    }

    override fun startLocation(locationListener: MapStandard.LocationListener) {
        if (source != null) {
            source!!.startLocation(locationListener)
        }
    }

    override fun pauseLocation(locationListener: MapStandard.LocationListener) {
        if (source != null) {
            source!!.pauseLocation(locationListener)
        }
    }

    override fun stopLocation() {
        if (source != null) {
            source!!.stopLocation()
        }
    }

}