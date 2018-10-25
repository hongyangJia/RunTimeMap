package com.mm.user.fastmap.location

import android.content.Context
import com.mm.user.amap.MapStandard

/**
 * created by hongyang on 18-9-27
 */
interface LocationSource {

      fun register(mContext: Context)
      fun startLocation(locationListener: MapStandard.LocationListener)
      fun pauseLocation(locationListener: MapStandard.LocationListener)
      fun stopLocation()

}