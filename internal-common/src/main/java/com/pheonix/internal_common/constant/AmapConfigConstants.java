package com.pheonix.internal_common.constant;

public class AmapConfigConstants {

    //路径规划地址
    public static final String DIRECTION_URL = "https://restapi.amap.com/v3/direction/driving";

    //行政区域信息地址
    public static final String DISTRICT_URL = "https://restapi.amap.com/v3/config/district";

    //路径规划出参的json 属性值
    public static final String STATUS = "status";   //0：请求失败；1：请求成功

    public static final String ROUTE = "route";     //驾车路径规划信息列表

    public static final String PATHS = "paths";     //驾车换乘信息，详看高德地图api文档https://lbs.amap.com/api/webservice/guide/api/direction#driving

    public static final String DISTANCE = "distance";

    public static final String DURATION = "duration";

}
