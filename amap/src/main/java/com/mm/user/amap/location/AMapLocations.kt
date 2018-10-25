package com.mm.user.amap.location

import android.annotation.SuppressLint
import android.content.Context
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.mm.user.fastmap.location.LocationSource
import java.util.*

/**
 * created by hongyang on 18-9-19
 */
object AMapLocations {

    @SuppressLint("StaticFieldLeak")
    var mLocationClient: AMapLocationClient? = null
    var locationListener = ArrayList<AMapListener.LocationListener>()
    var mLocationOption: AMapLocationClientOption? = null
    /**
     * 定位间隔时间五秒
     */
    const val INTERVAL_TIME = 5000

    /**
     * 配置基本定位信息
     */
    fun register(mContext: Context) {
        mLocationClient = AMapLocationClient(mContext)
        mLocationOption = AMapLocationClientOption()
        mLocationOption!!.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy)
        mLocationOption!!.setInterval(INTERVAL_TIME.toLong())
        mLocationClient!!.setLocationOption(mLocationOption)
        mLocationClient!!.setLocationListener(mLocationListener)
    }

    /**
     * 开始注册接口
     */
    fun startLocation(locationListener: AMapListener.LocationListener) {
        if (!AMapLocations.locationListener.contains(locationListener)) {
            AMapLocations.locationListener.add(locationListener)
        }
        //如果当前没有启动定位功能,现在就开始执行定位
        if (!mLocationClient!!.isStarted) {
            mLocationClient!!.startLocation()
        }
    }

    /**
     * 移除注册接口
     */
    fun pauseLocation(locationListener: AMapListener.LocationListener) {
        if (AMapLocations.locationListener.contains(locationListener)) {
            AMapLocations.locationListener.remove(locationListener)
        }
        //如果当前没有注册接口,需要停止定位信息
        if (AMapLocations.locationListener.size == 0) {
            stopLocation()
        }
    }

    /**
     * 停止定位并且清除注册接口
     */
    fun stopLocation() {
        if (mLocationClient!!.isStarted) {
            mLocationClient!!.stopLocation()
        }
        locationListener.clear()
    }

    /**
     * 所有注册接口回调
     */
    var mLocationListener: AMapLocationListener = AMapLocationListener { aMapLocation ->
        if (aMapLocation != null) {
            if (aMapLocation.errorCode == 0) {
                for (listener in locationListener) {
                    listener.onLocation(aMapLocation)
                }
            }
        }
    }
}