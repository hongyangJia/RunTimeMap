package com.mm.user.example

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.mm.user.amap.MapOptions
import com.mm.user.amap.MapStandard
import kotlinx.android.synthetic.main.activity_end.*

/**
 * created by hongyang on 18-9-27
 */
class EndActivity1: AppCompatActivity() {

    var map: MapStandard? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_end)
        this.initMap()
        this.initScreenShot()
    }

    /**
     * 移动视图
     */
    fun initMap() {
        map = fragmentManager.findFragmentById(R.id.end) as MapStandard
        map!!.configure(MapOptions.Builder.zoomTo(16f).isLocationEnabled(false).build())
      /*AMapTraceClient.register(this)
        AMapTraceClient.startTrace(DataUtils.endLocation!!, object : AMapListener.TraceListener {
            override fun onTrace(latLng: ArrayList<LatLng>) {
                map!!.addPolygon(latLng)
                map!!.moveCamera(latLng.get(0))
            }
            override fun onError(errorInfo: String?) {
            }
        })*/

    }

    /**
     * 截图
     */
    fun initScreenShot() {
        screenShot.setOnClickListener {
            map!!.screenShot(object : MapStandard.MapScreenShotListener {
                override fun onMapScreenShot(bitmap: Bitmap, state: Int) {
                    if (screenBitmap.visibility == ViewGroup.GONE) {
                        screenBitmap.visibility = ViewGroup.VISIBLE
                        screenBitmap.setImageBitmap(bitmap)
                        screenShot.setText("取消")
                    } else {
                        screenBitmap.visibility = ViewGroup.GONE
                        screenShot.setText("拍照")
                    }
                }
            })
        }
    }
}