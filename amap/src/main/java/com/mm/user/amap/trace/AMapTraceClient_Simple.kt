package com.mm.user.amap.trace

import android.content.Context
import android.util.Log
import com.amap.api.maps.model.LatLng
import com.amap.api.trace.LBSTraceClient
import com.amap.api.trace.TraceLocation
import com.amap.api.trace.TraceStatusListener
import com.mm.user.amap.location.AMapListener
import java.util.*

/**
 * created by hongyang on 18-9-19
 */
object AMapTraceClient_Simple : TraceStatusListener {
    override fun onTraceStatus(locations: MutableList<TraceLocation>?, rectifications: MutableList<LatLng>?, errorInfo: String?) {
        Log.e("onTraceStatus", errorInfo)
        if (LBSTraceClient.TRACE_SUCCESS != errorInfo) {
            if (locations != null) {
                val stringBuffer = StringBuffer()
                for (location in locations) {
                    stringBuffer.append("{")
                    stringBuffer.append("\"lon\":").append(location.longitude).append(",")
                    stringBuffer.append("\"lat\":").append(location.latitude).append(",")
                    stringBuffer.append("\"loctime\":").append(location.time).append(",")
                    stringBuffer.append("\"speed\":").append(location.speed).append(",")
                    stringBuffer.append("\"bearing\":").append(location.bearing)
                    stringBuffer.append("}")
                    stringBuffer.append("\n")
                }
                Log.i("amap", "轨迹纠偏失败，请先检查以下几点:\n" +
                        "定位是否成功\n" +
                        "onTraceStatus第一个参数中 经纬度、时间、速度和角度信息是否为空\n" +
                        "若仍不能解决问题，请将以下内容通过官网(lbs.amap.com)工单系统反馈给我们 \n" + errorInfo + " \n "
                        + stringBuffer.toString())
            }
            return
        }

        if (locationListener != null) {
     /*       locationListener!!.onTrace(rectifications)*/
        }
    }

    var mTraceClient: LBSTraceClient? = null

    var latLngs = ArrayList<LatLng>()

    var locationListener: AMapListener.TraceListener? = null


    fun register(mContext: Context) {
        mTraceClient = LBSTraceClient.getInstance(mContext)
    }

    fun stopTrace() {
        if (mTraceClient != null) {
            mTraceClient!!.stopTrace()
        }
        locationListener = null
    }

    fun startTrace(locationListener: AMapListener.TraceListener) {
        if (this.locationListener == null) {
            this.locationListener = locationListener
        }
            mTraceClient!!.startTrace(this)
           Log.e("startTrace","startTrace");
    }

}