package com.kijlee.android.demo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kijlee.android.demo.R
import com.kijlee.android.demo.databinding.FgGreenDaoBinding
import com.kijlee.android.demo.entity.map.gaode.DemoDetails
import com.kijlee.android.demo.ui.map.gaode.GaodeMenuAdapter
import com.orhanobut.logger.Logger

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.map.gaode
 * @ClassName:      FgGaoDe
 * @Author:     kij
 * @Description:  高德地图
 * @Date:    2022/3/10 9:15 下午
 * @Version:    1.0
 */
class FgGaode : Fragment() {

    var _layoutBind: FgGreenDaoBinding? = null
    var gaodeAdapter: GaodeMenuAdapter? = null
    var item = ""
    private val binding get() = _layoutBind!!

    override fun onDestroy() {
        super.onDestroy()
        _layoutBind = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gaodeAdapter = GaodeMenuAdapter(demos.toMutableList())
        arguments?.let {
            if (it.containsKey(FgMain.Item_Id)) {
                // Load the placeholder content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = it.getString(FgMain.Item_Id).toString()
                Logger.e(item)
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _layoutBind = FgGreenDaoBinding.inflate(layoutInflater)

        val root: View = binding.root
        binding.gaodeRecyclerView.adapter = gaodeAdapter
        return root
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val Tab_Name = "tab_name"
    }

    private val demos: Array<DemoDetails> =
        arrayOf<DemoDetails>(
            // 创建地图
            DemoDetails(R.string.map_create, R.string.blank, null),
            // 显示地图
            DemoDetails(R.string.basic_map, R.string.basic_description, MainActivity::class.java),// BasicMapActivity::class.java
            // 显示地图
            DemoDetails(R.string.list_map, R.string.basic_description, MainActivity::class.java),// MapListViewActivity::class.java
            // 显示地图
            DemoDetails(R.string.recycle_map, R.string.basic_description, MainActivity::class.java),// RecycleViewActivity::class.java
            // 6种实现地图方式
            DemoDetails(R.string.basic_map_6, R.string.basic_description_temp, MainActivity::class.java),// MapImpMethodActivity::class.java
            // Fragment创建地图
            //			new DemoDetails(R.string.base_fragment_map, R.string.base_fragment_description,
            //					BaseMapSupportFragmentActivity.class),
            //			new DemoDetails(R.string.basic_texturemapview, R.string.basic_texturemapview_description,
            //					TextureMapViewActivity.class),
            DemoDetails(R.string.viewpager_map, R.string.viewpger_map_description, MainActivity::class.java),// ViewPagerWithMapActivity::class.java
            //			地图多实例
            DemoDetails(R.string.multi_inst, R.string.blank, MainActivity::class.java),// TwoMapActivity::class.java
            //		           室内地图
            DemoDetails(R.string.indoormap_demo, R.string.indoormap_description, MainActivity::class.java),// IndoorMapActivity::class.java
            //		    amapoptions实现地图
            DemoDetails(R.string.mapOption_demo, R.string.mapOption_description, MainActivity::class.java),// MapOptionActivity::class.java
            //-----------与地图交互-----------------------------------------------------------------------------------------------
            DemoDetails(R.string.map_interactive, R.string.blank, null),
            // 缩放控件、定位按钮、指南针、比例尺等的添加
            DemoDetails(R.string.uisettings_demo, R.string.uisettings_description, MainActivity::class.java),// UiSettingsActivity::class.java
            // 地图logo位置改变
            DemoDetails(R.string.logo, R.string.uisettings_description, MainActivity::class.java),// LogoSettingsActivity::class.java
            // 地图图层
            DemoDetails(R.string.layers_demo, R.string.layers_description, MainActivity::class.java),// LayersActivity::class.java
            // 缩放、旋转、拖拽和改变仰角操作地图
            DemoDetails(R.string.gesture, R.string.uisettings_description, MainActivity::class.java),// GestureSettingsActivity::class.java
            // 监听点击、长按、拖拽地图等事件
            DemoDetails(R.string.events_demo, R.string.events_description, MainActivity::class.java),// EventsActivity::class.java
            // 地图POI点击
            DemoDetails(R.string.poiclick_demo, R.string.poiclick_description, MainActivity::class.java),// PoiClickActivity::class.java
            // 改变地图中心点
            DemoDetails(R.string.camera_demo, R.string.camera_description, MainActivity::class.java),// CameraActivity::class.java
            // 地图动画效果
            DemoDetails(R.string.animate_demo, R.string.animate_description, MainActivity::class.java),// Animate_CameraActivity::class.java
            // 改变缩放级别
            DemoDetails(R.string.map_zoom, R.string.blank, MainActivity::class.java),// ZoomActivity::class.java
            // 地图截屏
            DemoDetails(R.string.screenshot_demo, R.string.screenshot_description, MainActivity::class.java),// ScreenShotActivity::class.java
            // 自定义最小最大缩放级别
            DemoDetails(R.string.set_min_max_zoomlevel, R.string.set_min_max_zoomlevel_description, MainActivity::class.java),// MinMaxZoomLevelActivity::class.java
            // 自定义地图显示区域
            DemoDetails(R.string.limit_bounds, R.string.limit_bounds_description, MainActivity::class.java),// LimitBoundsActivity::class.java
            //----------------------------------------------------------------------------------------------------------------------------------------
            // 在地图上绘制
            DemoDetails(R.string.map_overlay, R.string.blank, null),
            // 绘制点
            DemoDetails(R.string.marker_demo, R.string.marker_description, MainActivity::class.java),// MarkerActivity::class.java
            // marker点击回调
            DemoDetails(R.string.marker_click, R.string.marker_click, MainActivity::class.java),// MarkerClickActivity::class.java
            // Marker 动画功能
            DemoDetails(R.string.marker_animation_demo, R.string.marker_animation_description, MainActivity::class.java),// MarkerAnimationActivity::class.java
            // 绘制地图上的信息窗口
            DemoDetails(R.string.infowindow_demo, R.string.infowindow_demo, MainActivity::class.java),// InfoWindowActivity::class.java
            // 绘制自定义点
            DemoDetails(R.string.custommarker_demo, R.string.blank, MainActivity::class.java),// CustomMarkerActivity::class.java
            // 绘制默认定位小蓝点
            DemoDetails(R.string.locationmodesource_demo_old, R.string.locationmodesource_description, MainActivity::class.java),// LocationModeSourceActivity_Old::class.java
            DemoDetails(R.string.locationmodesource_demo, R.string.locationmodesource_description, MainActivity::class.java),// LocationModeSourceActivity::class.java
            // 绘制自定义定位小蓝点图标
            DemoDetails(R.string.customlocation_demo, R.string.customlocation_demo, MainActivity::class.java),// CustomLocationActivity::class.java
            DemoDetails(R.string.customlocationmode_demo, R.string.customlocationmode_demo, MainActivity::class.java),// CustomLocationModeActivity::class.java
            // 绘制实线、虚线
            DemoDetails(R.string.polyline_demo, R.string.polyline_description, MainActivity::class.java),// PolylineActivity::class.java
            // 多彩线
            DemoDetails(R.string.colourline_demo, R.string.colourline_description, MainActivity::class.java),// ColourfulPolylineActivity::class.java
            // 大地曲线
            DemoDetails(R.string.geodesic_demo, R.string.geodesic_description, MainActivity::class.java),// GeodesicActivity::class.java
            //			绘制弧线
            DemoDetails(R.string.arc_demo, R.string.arc_description, MainActivity::class.java),// ArcActivity::class.java
            // 绘制带导航箭头的线
            DemoDetails(R.string.navigatearrow_demo, R.string.navigatearrow_description, MainActivity::class.java),// NavigateArrowOverlayActivity::class.java
            // 绘制圆
            DemoDetails(R.string.circle_demo, R.string.circle_description, MainActivity::class.java),// CircleActivity::class.java
            // 矩形、多边形
            DemoDetails(R.string.polygon_demo, R.string.polygon_description, MainActivity::class.java),// PolygonActivity::class.java
            // 绘制热力图
            DemoDetails(R.string.heatmap_demo, R.string.heatmap_description, MainActivity::class.java),// HeatMapActivity::class.java
            // 绘制groundoverlay
            DemoDetails(R.string.groundoverlay_demo, R.string.groundoverlay_description, MainActivity::class.java),// GroundOverlayActivity::class.java
            // 绘制opengl
            DemoDetails(R.string.opengl_demo, R.string.opengl_description, MainActivity::class.java),// OpenglActivity::class.java
            // 绘制自定义建筑物
            DemoDetails(R.string.buildingoverlay, R.string.tileoverlay_demo, MainActivity::class.java),// BuildingOverlayActivity::class.java
            DemoDetails(R.string.multipoint_demo, R.string.multipoint_description, MainActivity::class.java),// MultiPointOverlayActivity::class.java
            DemoDetails(R.string.hole_demo, R.string.hole_decription, MainActivity::class.java),// HoleActivity::class.java
            DemoDetails(R.string.province_hole_demo, R.string.province_hole_decription, MainActivity::class.java),// ProvinceHoleActivity::class.java
            DemoDetails(R.string.particle_demo, R.string.particle_decription, MainActivity::class.java),// ParticleMapActivity::class.java
            DemoDetails(R.string.particle_weather_demo, R.string.particle_weather_decription, MainActivity::class.java),// ParticleWeatherMapActivity::class.java
            DemoDetails(R.string.show_honeycomb_heat_map_demo, R.string.show_honeycomb_heat_map_decription, MainActivity::class.java),// HoneycombHeatMapActivity::class.java
            //-----------------------------------------------------------------------------------------------------------------------------------------------------
            // 获取地图数据
            DemoDetails(R.string.search_data, R.string.blank, null),
            // 关键字检索
            DemoDetails(R.string.poikeywordsearch_demo, R.string.poikeywordsearch_description, MainActivity::class.java),// PoiKeywordSearchActivity::class.java
            // 周边搜索
            DemoDetails(R.string.poiaroundsearch_demo, R.string.poiaroundsearch_description, MainActivity::class.java),// PoiAroundSearchActivity::class.java
            // ID检索
            DemoDetails(R.string.poiidsearch_demo, R.string.poiidsearch_demo, MainActivity::class.java),// PoiIDSearchActivity::class.java
            // 沿途搜索
            DemoDetails(R.string.routepoisearch_demo, R.string.routepoisearch_demo, MainActivity::class.java),// RoutePOIActivity::class.java
            // 输入提示查询
            DemoDetails(R.string.inputtips_demo, R.string.inputtips_description, MainActivity::class.java),// InputtipsActivity::class.java
            // POI父子关系
            DemoDetails(R.string.subpoi_demo, R.string.subpoi_description, MainActivity::class.java),// SubPoiSearchActivity::class.java
            // 天气查询
            DemoDetails(R.string.weather_demo, R.string.weather_description, MainActivity::class.java),// WeatherSearchActivity::class.java
            // 地理编码
            DemoDetails(R.string.geocoder_demo, R.string.geocoder_description, MainActivity::class.java),// GeocoderActivity::class.java
            // 逆地理编码
            DemoDetails(R.string.regeocoder_demo, R.string.regeocoder_description, MainActivity::class.java),// ReGeocoderActivity::class.java
            // 行政区划查询
            DemoDetails(R.string.district_demo, R.string.district_description, MainActivity::class.java),// DistrictActivity::class.java
            // 行政区边界查询
            DemoDetails(R.string.district_boundary_demo, R.string.district_boundary_description, MainActivity::class.java),// DistrictWithBoundaryActivity::class.java
            // 公交路线查询
            DemoDetails(R.string.busline_demo, R.string.busline_description, MainActivity::class.java),// BuslineActivity::class.java
            // 公交站点查询
            DemoDetails(R.string.busstation_demo, R.string.blank, MainActivity::class.java),// BusStationActivity::class.java
            // 云图
            DemoDetails(R.string.cloud_demo, R.string.cloud_description, MainActivity::class.java),// CloudActivity::class.java
            // 出行路线规划
            DemoDetails(R.string.search_route, R.string.blank, null),
            // 驾车出行路线规划
            DemoDetails(R.string.route_drive, R.string.blank, MainActivity::class.java),// DriveRouteActivity::class.java
            // 驾车未来出行路线规划
            DemoDetails(R.string.route_plan_drive, R.string.blank, MainActivity::class.java),// DriveRoutePlanActivity::class.java
            // 步行出行路线规划
            DemoDetails(R.string.route_walk, R.string.blank, MainActivity::class.java),// WalkRouteActivity::class.java
            // 公交出行路线规划
            DemoDetails(R.string.route_bus, R.string.blank, MainActivity::class.java),// BusRouteActivity::class.java
            // 骑行出行路线规划
            DemoDetails(R.string.route_ride, R.string.blank, MainActivity::class.java),// RideRouteActivity::class.java
            DemoDetails(R.string.route_truck, R.string.blank, MainActivity::class.java),// TruckRouteActivity::class.java
            DemoDetails(R.string.route_distance, R.string.blank, MainActivity::class.java),// RouteDistanceActivity::class.java
            // route综合demo
            DemoDetails(R.string.route_demo, R.string.route_description, MainActivity::class.java),// RouteActivity::class.java
            // 短串分享
            DemoDetails(R.string.search_share, R.string.blank, null),
            DemoDetails(R.string.share_demo, R.string.share_description, MainActivity::class.java),// ShareActivity::class.java
            // 离线地图
            DemoDetails(R.string.map_offline, R.string.blank, null),
            DemoDetails(R.string.offlinemap_demo, R.string.offlinemap_description, MainActivity::class.java),// OfflineMapActivity_Old::class.java
            DemoDetails(R.string.offlinemap_ui_demo, R.string.offlinemap_ui_description, MainActivity::class.java),// OfflineMapActivity::class.java
            // 地图计算工具
            DemoDetails(R.string.map_tools, R.string.blank, null),
            // 其他坐标系转换为高德坐标系
            DemoDetails(R.string.coordconvert_demo, R.string.coordconvert_demo, MainActivity::class.java),// CoordConverActivity::class.java
            // 地理坐标和屏幕像素坐标转换
            DemoDetails(R.string.convertgeo2point_demo, R.string.convertgeo2point_demo, MainActivity::class.java),// GeoToScreenActivity::class.java
            // 两点间距离计算
            DemoDetails(R.string.calculateLineDistance, R.string.calculateLineDistance, MainActivity::class.java),// CalculateDistanceActivity::class.java
            // 判断点是否在多边形内
            DemoDetails(R.string.contains_demo, R.string.contains_demo, MainActivity::class.java),// ContainsActivity::class.java
            // 地图计算工具
            DemoDetails(R.string.map_expand, R.string.blank, null),
            // 轨迹纠偏
            DemoDetails(R.string.trace_demo, R.string.trace_description, MainActivity::class.java),// TraceActivity::class.java
            DemoDetails(R.string.trace_demo_simple, R.string.trace_description_simple, MainActivity::class.java),// TraceActivity_Simple::class.java
            // 平滑移动
            DemoDetails(R.string.smooth_move_demo, R.string.smooth_move_description, MainActivity::class.java)// SmoothMoveActivity::class.java
        )

}