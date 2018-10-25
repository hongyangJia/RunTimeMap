package com.mm.user.amap

import android.graphics.Bitmap
import com.amap.api.location.AMapLocation
import com.amap.api.maps.AMap
import com.amap.api.maps.CameraUpdateFactory
import com.amap.api.maps.LocationSource
import com.amap.api.maps.MapFragment
import com.amap.api.maps.model.BitmapDescriptorFactory
import com.amap.api.maps.model.LatLng
import com.amap.api.maps.model.MyLocationStyle
import com.mm.user.amap.conversion.AMapToMessage
import com.mm.user.amap.location.AMapListener
import com.mm.user.amap.location.AMapLocations
import com.mm.user.amap.polyline.AMapPolylines
import com.mm.user.fastmap.info.MapMessage

/**
 * created by hongyang on 18-9-19
 */
 open class AMapFragment : MapFragment(), MapStandard {


    var onLocationListener: LocationSource.OnLocationChangedListener? = null
    val myLocationStyle = MyLocationStyle()
    var isLocationRoute=false
    /**
     * 读取配置信息,定制配置任务
     */
    override fun configure(mapOptions: MapOptions) {

        //地图当前缩放级别
        if (mapOptions.zoomTo != 0f) {
            map.moveCamera(CameraUpdateFactory.zoomTo(mapOptions.zoomTo))
        }
        //地图当前图标样式
        if (mapOptions.isLocationEnabled) {
            //定义 小蓝点样式
            myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.gps_point))
            //定义 跟随模式
            myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);
            map.myLocationStyle = myLocationStyle
        }

        if (mapOptions.isLocationRoute){
            isLocationRoute=true
        }
        //无效图标
        invalidSettings()
    }

    /**
     * 开始绘制小蓝点
     */
    override fun startLocation() {
        //注册定位接口
        AMapLocations.register(activity)
        map.setLocationSource(mapLocationSource)
        map.isMyLocationEnabled = true
    }

    /**
     * 设置无效功能
     */
    private fun invalidSettings() {
        //放大缩小图标隐藏
        map.uiSettings.isZoomControlsEnabled = false
        //高德log隐藏
        map.uiSettings.setLogoBottomMargin(-100)
    }

    /**
     * 单点绘制多彩轨迹
     */
    override fun addPolygon(mapMessage: MapMessage) {
        AMapPolylines.startDraw(map, mapMessage)
    }

    /**
     * 多点绘制多彩轨迹
     */
    override fun addPolygon(mapMessages: ArrayList<com.mm.user.fastmap.info.LatLng>) {
        AMapPolylines.startDraw(map, AMapToMessage.convertAMapLatLng(mapMessages))
    }

    /**
     * 销毁相关代码
     */
    override fun destroy() {
        mapLocationSource.deactivate()
    }

    /**
     * 重新开始
     */
    override fun restartLocation() {
        AMapLocations.startLocation(locationListener)
    }

    /**
     * 停止定位
     */
    override fun stopLocation() {
        AMapLocations.pauseLocation(locationListener)
    }

    /**
     * 截图并且回调bitmap
     */
    override fun screenShot(mapScreenShot: MapStandard.MapScreenShotListener) {
        map.getMapScreenShot(object : AMap.OnMapScreenShotListener {
            override fun onMapScreenShot(p0: Bitmap?, p1: Int) {
                mapScreenShot.onMapScreenShot(p0!!, p1)
            }

            override fun onMapScreenShot(p0: Bitmap?) {

            }
        })
    }

    /**
     * 移动摄像头
     */
    override fun moveCamera(latLng: com.mm.user.fastmap.info.LatLng) {
        map.moveCamera(CameraUpdateFactory.newLatLng(LatLng(latLng.latitude, latLng.longitude)))
    }

    /**
     * 结束跑步 返回数据
     */
    override fun endLocation(): ArrayList<MapMessage> {
        destroy()
        return AMapPolylines.latLngs
    }

    /**
     * 系统小蓝点处理
     */
    private var mapLocationSource: LocationSource = object : LocationSource {
        override fun activate(onLocationChangedListener: LocationSource.OnLocationChangedListener) {
            onLocationListener = onLocationChangedListener
            //开始定位
            AMapLocations.startLocation(locationListener)
        }

        override fun deactivate() {
            //结束定位并且注销接口
            stopLocation()
            onLocationListener = null
        }
    }

    /**
     * 定位回调
     */
    private var locationListener = object : AMapListener.LocationListener {
        override fun onLocation(aMapLocation: AMapLocation) {
            if (onLocationListener != null) {
                //定位图标跟随高精度定位
                onLocationListener!!.onLocationChanged(aMapLocation)
                if (!isLocationRoute)return
                val latLng = AMapToMessage.convertMessage(aMapLocation)
                if (AMapPolylines.isDraw(latLng)) {
                    addPolygon(latLng)
                }
            }
        }
    }
}