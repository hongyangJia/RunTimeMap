package com.mm.user.amap

import android.graphics.Bitmap
import com.mm.user.fastmap.info.LatLng
import com.mm.user.fastmap.info.MapMessage
import java.util.*
import kotlin.collections.ArrayList


/**
 * created by hongyang on 18-9-19
 */
interface MapStandard {

    /**
     * 初始化配置欣喜
     * 具体参考 MapOptions.kt
     */
    fun configure(mapOptions: MapOptions)

    /**
     * 添加绘制轨迹
     */
    fun addPolygon(mapMessage: MapMessage)

    /**
     * 纠偏绘制所有点
     */
    fun addPolygon(mapMessages: ArrayList<LatLng>)

    /**
     * 开始跑步
     */
    fun startLocation()

    /**
     * 重新开始跑步
     */
    fun restartLocation()

    /**
     * 暂停跑步
     */
    fun stopLocation()

    /**
     * 结束跑步并且返回位置信息
     */
    fun endLocation(): ArrayList<MapMessage>

    /**
     * 截图功能
     */
    fun screenShot(mapScreenShot: MapScreenShotListener)

    /**
     * 移动摄像机
     */
    fun moveCamera(latLng: LatLng)

    /**
     * 销毁所有地图代码
     */
    fun destroy()

    /**
     * 截图功能
     */
    interface MapScreenShotListener {
        fun onMapScreenShot(bitmap: Bitmap, state: Int)
    }

    interface LocationListener {
        fun onLocation(mapMessage: MapMessage)
    }
}