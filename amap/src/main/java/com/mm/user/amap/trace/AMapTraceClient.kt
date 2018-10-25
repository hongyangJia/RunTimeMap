package com.mm.user.amap.trace

import android.content.Context
import com.amap.api.maps.model.LatLng
import com.amap.api.trace.LBSTraceClient
import com.amap.api.trace.TraceListener
import com.amap.api.trace.TraceLocation
import com.mm.user.amap.location.AMapListener
import com.mm.user.fastmap.info.MapMessage

/**
 * created by hongyang on 18-9-26
 */
object AMapTraceClient : TraceListener {

    private var mTraceList = ArrayList<TraceLocation>()
    private var mTraceClient: LBSTraceClient? = null
    private var mSequenceLineID = 1000
    private var mCoordinateType = LBSTraceClient.TYPE_AMAP
    private var traceListener: AMapListener.TraceListener? = null


    /**
     * 纠正偏移失败
     */
    override fun onRequestFailed(p0: Int, p1: String?) {
        if (traceListener != null) {
            traceListener!!.onError(p1)
        }
    }

    /**
     * 纠正偏移过程
     */
    override fun onTraceProcessing(p0: Int, p1: Int, p2: MutableList<LatLng>?) {

    }

    /**
     * 阶段性完成纠正偏移
     */
    override fun onFinished(p0: Int, p1: MutableList<LatLng>?, p2: Int, p3: Int) {
        if (traceListener != null && p1 != null) {
            //转换数据类型
            val latlngs = ArrayList<com.mm.user.fastmap.info.LatLng>()
            for (i in p1) {
                latlngs.add(com.mm.user.fastmap.info.LatLng(i.latitude, i.longitude))
            }
            //完成回调
            traceListener!!.onTrace(latlngs)
        }
    }

    /**
     * 创建 LBSTraceClient
     */
    fun register(context: Context) {
        if (mTraceClient == null) {
            mTraceClient = LBSTraceClient.getInstance(context)
        }
    }

    /**
     * 开始获取所有定位信息
     * 开始调用纠偏接口
     */
    fun startTrace(MapMessage: java.util.ArrayList<MapMessage>, traceListener: AMapListener.TraceListener) {
        this.traceListener = traceListener
        mTraceList.clear()
        for (message in MapMessage) {
            val traceLocation = TraceLocation()
            traceLocation.latitude = message.latLng.latitude
            traceLocation.longitude = message.latLng.longitude
            traceLocation.bearing = message.bearing
            traceLocation.time = message.time
            traceLocation.speed = message.speed
            mTraceList.add(traceLocation)
        }
        //开始纠正偏移
        mTraceClient!!.queryProcessedTrace(mSequenceLineID, mTraceList, mCoordinateType, this)
    }

}