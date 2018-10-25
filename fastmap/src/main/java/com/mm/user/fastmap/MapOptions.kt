package com.mm.user.amap

/**
 * created by hongyang on 18-9-19
 */
class MapOptions {

    var isLocationEnabled: Boolean = true
    var isLocationIcon: Boolean = true
    var isLocationRoute:Boolean=false
    var zoomTo: Float = 0f

    private constructor() {

    }

    constructor(builder: Builder) {
        isLocationEnabled = builder.isLocationEnabled
        isLocationIcon = builder.isLocationIcon
        isLocationRoute = builder.isLocationRoute
        zoomTo = builder.zoomTo
    }

    object Builder {

        var isLocationEnabled: Boolean = true
        var isLocationIcon: Boolean = true
        var isLocationRoute:Boolean=false
        var zoomTo: Float = 0f
        /**
         * 设置显示定位图片
         */
        fun isLocationEnabled(isLocationEnabled: Boolean): MapOptions.Builder {
            this.isLocationEnabled = isLocationEnabled
            return this
        }

        /**
         * 定位图标样式
         */
        fun isLocationIcon(isLocationIcon: Boolean): MapOptions.Builder {
            this.isLocationIcon = isLocationIcon
            return this
        }

        /**
         * 定位绘制路线
         */
        fun isLocationRoute(isLocationRoute: Boolean): MapOptions.Builder {
            this.isLocationRoute = isLocationRoute
            return this
        }

        /**
         * 地图图层级别
         */
        fun zoomTo(zoomTo: Float): MapOptions.Builder {
            this.zoomTo = zoomTo
            return this
        }

        /**
         * 创建配置信息实体类
         */
        fun build(): MapOptions {
            var build = MapOptions(this)
            return build
        }

    }
}