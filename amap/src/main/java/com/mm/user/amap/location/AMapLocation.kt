package com.mm.user.amap.location

import android.annotation.SuppressLint
import android.content.Context
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.mm.user.amap.MapStandard
import com.mm.user.fastmap.info.LatLng
import com.mm.user.fastmap.info.MapMessage
import com.mm.user.fastmap.location.LocationSource
import java.util.*

/**
 * created by hongyang on 18-9-19
 */
object AMapLocation:LocationSource {

    @SuppressLint("StaticFieldLeak")
    var mLocationClient: AMapLocationClient? = null
    var locationListener = ArrayList<MapStandard.LocationListener>()
    var mLocationOption: AMapLocationClientOption? = null
    /**
     * 定位间隔时间五秒
     */
    val INTERVAL_TIME = 5000

    fun newLocation():LocationSource{
        return this
    }
    /**
     * 配置基本定位信息
     */
    override fun register(mContext: Context) {
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
    override fun startLocation(locationListener: MapStandard.LocationListener) {
        if (!this.locationListener.contains(locationListener)) {
            this.locationListener.add(locationListener)
        }
        //如果当前没有启动定位功能,现在就开始执行定位
        if (!mLocationClient!!.isStarted) {
            mLocationClient!!.startLocation()
        }
    }

    /**
     * 移除注册接口
     */
    override fun pauseLocation(locationListener: MapStandard.LocationListener) {
        if (this.locationListener.contains(locationListener)) {
            this.locationListener.remove(locationListener)
        }
        //如果当前没有注册接口,需要停止定位信息
        if (this.locationListener.size == 0) {
            stopLocation()
        }
    }

    /**
     * 停止定位并且清除注册接口
     */
    override fun stopLocation() {
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
                    val latlngs = LatLng(aMapLocation.latitude,aMapLocation.longitude)
                    listener.onLocation(MapMessage(latlngs,aMapLocation.bearing,aMapLocation.speed,aMapLocation.time,aMapLocation.address))
                }
            }
        }
    }
}