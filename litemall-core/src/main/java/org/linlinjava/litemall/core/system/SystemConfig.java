package org.linlinjava.litemall.core.system;

import org.linlinjava.litemall.core.util.DateTimeUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统设置
 */
public class SystemConfig {
    // 小程序相关配置
    public final static String LITEMALL_WX_INDEX_NEW = "litemall_wx_index_new";
    public final static String LITEMALL_WX_INDEX_HOT = "litemall_wx_index_hot";
    public final static String LITEMALL_WX_INDEX_BRAND = "litemall_wx_index_brand";
    public final static String LITEMALL_WX_INDEX_TOPIC = "litemall_wx_index_topic";
    public final static String LITEMALL_WX_INDEX_CATLOG_LIST = "litemall_wx_catlog_list";
    public final static String LITEMALL_WX_INDEX_CATLOG_GOODS = "litemall_wx_catlog_goods";
    public final static String LITEMALL_WX_SHARE = "litemall_wx_share";
    // 运费相关配置
    public final static String LITEMALL_EXPRESS_FREIGHT_VALUE = "litemall_express_freight_value";
    public final static String LITEMALL_EXPRESS_FREIGHT_MIN = "litemall_express_freight_min";
    // 订单相关配置
    public final static String LITEMALL_ORDER_UNPAID = "litemall_order_unpaid";
    public final static String LITEMALL_ORDER_UNCONFIRM = "litemall_order_unconfirm";
    public final static String LITEMALL_ORDER_COMMENT = "litemall_order_comment";
    // 商场相关配置
    public final static String LITEMALL_MALL_NAME = "litemall_mall_name";
    public final static String LITEMALL_MALL_ADDRESS = "litemall_mall_address";
    public final static String LITEMALL_MALL_PHONE = "litemall_mall_phone";
    public final static String LITEMALL_MALL_QQ = "litemall_mall_qq";
    // 课程计划批量添加配置
    public final static String TIANYU_ADMIN_STARTTIME = "tianyu_admin_starttime";
    public final static String TIANYU_ADMIN_ENDTIME = "tianyu_admin_endtime";
    public final static String TIANYU_ADMIN_COURSEPLAN = "tianyu_admin_courseplan";
    // 微信小程序首页配置
    public final static String TIANYU_WX_HOME_TITLE = "tianyu_wx_home_title";
    public final static String TIANYU_WX_HOME_INFO = "tianyu_wx_home_info";
    // 微信小程序关于页面配置
    public final static String TIANYU_WX_ABOUT_NAME = "tianyu_wx_about_name";
    public final static String TIANYU_WX_ABOUT_DESC = "tianyu_wx_about_desc";
    public final static String TIANYU_WX_ABOUT_ADDRESS = "tianyu_wx_about_address";
    public final static String TIANYU_WX_ABOUT_PHONE = "tianyu_wx_about_phone";
    public final static String TIANYU_WX_ABOUT_QQNUMBER = "tianyu_wx_about_qqnumber";
    public final static String TIANYU_WX_ABOUT_LATITUDE = "tianyu_wx_about_latitude";
    public final static String TIANYU_WX_ABOUT_LONGITUDE = "tianyu_wx_about_longitude";
    public final static String TIANYU_WX_ABOUT_SCALE = "tianyu_wx_about_scale";
    public final static String TIANYU_WX_ABOUT_VERSION = "tianyu_wx_about_version";

    //所有的配置均保存在该 HashMap 中
    private static Map<String, String> SYSTEM_CONFIGS = new HashMap<>();

    private static String getConfig(String keyName) {
        return SYSTEM_CONFIGS.get(keyName);
    }

    private static Integer getConfigInt(String keyName) {
        return Integer.parseInt(SYSTEM_CONFIGS.get(keyName));
    }

    private static Double getConfigDouble(String keyName) {
        return Double.parseDouble(SYSTEM_CONFIGS.get(keyName));
    }

    private static Boolean getConfigBoolean(String keyName) {
        return Boolean.valueOf(SYSTEM_CONFIGS.get(keyName));
    }

    private static BigDecimal getConfigBigDec(String keyName) {
        return new BigDecimal(SYSTEM_CONFIGS.get(keyName));
    }

    private static LocalTime getConfigLocalTime(String keyName) {
        return DateTimeUtil.stringToLocalTimeShort(SYSTEM_CONFIGS.get(keyName));
    }

    private static Integer [] getConfigInts(String keyName) {
        String [] strs = SYSTEM_CONFIGS.get(keyName).split(",");
        Integer [] ints = new Integer[strs.length];
        for(int i=0; i<strs.length; i++){
            ints[i] = Integer.parseInt(strs[i]);
        }
        return ints;
    }

    public static Integer getNewLimit() {
        return getConfigInt(LITEMALL_WX_INDEX_NEW);
    }

    public static Integer getHotLimit() {
        return getConfigInt(LITEMALL_WX_INDEX_HOT);
    }

    public static Integer getBrandLimit() {
        return getConfigInt(LITEMALL_WX_INDEX_BRAND);
    }

    public static Integer getTopicLimit() {
        return getConfigInt(LITEMALL_WX_INDEX_TOPIC);
    }

    public static Integer getCatlogListLimit() {
        return getConfigInt(LITEMALL_WX_INDEX_CATLOG_LIST);
    }

    public static Integer getCatlogMoreLimit() {
        return getConfigInt(LITEMALL_WX_INDEX_CATLOG_GOODS);
    }

    public static boolean isAutoCreateShareImage() {
        return getConfigBoolean(LITEMALL_WX_SHARE);
    }

    public static BigDecimal getFreight() {
        return getConfigBigDec(LITEMALL_EXPRESS_FREIGHT_VALUE);
    }

    public static BigDecimal getFreightLimit() {
        return getConfigBigDec(LITEMALL_EXPRESS_FREIGHT_MIN);
    }

    public static Integer getOrderUnpaid() {
        return getConfigInt(LITEMALL_ORDER_UNPAID);
    }

    public static Integer getOrderUnconfirm() {
        return getConfigInt(LITEMALL_ORDER_UNCONFIRM);
    }

    public static Integer getOrderComment() {
        return getConfigInt(LITEMALL_ORDER_COMMENT);
    }

    public static String getMallName() {
        return getConfig(LITEMALL_MALL_NAME);
    }

    public static String getMallAddress() {
        return getConfig(LITEMALL_MALL_ADDRESS);
    }

    public static String getMallPhone() {
        return getConfig(LITEMALL_MALL_PHONE);
    }

    public static String getMallQQ() {
        return getConfig(LITEMALL_MALL_QQ);
    }

    public static LocalTime getTianyuAdminStarttime() {
        return getConfigLocalTime(TIANYU_ADMIN_STARTTIME);
    }

    public static LocalTime getTianyuAdminEndtime() {
        return getConfigLocalTime(TIANYU_ADMIN_ENDTIME);
    }

    public static Integer [] getTianyuAdminCoursePlan() {
        return getConfigInts(TIANYU_ADMIN_COURSEPLAN);
    }

    public static String getTianyuHomeTitle() {
        return getConfig(TIANYU_WX_HOME_TITLE);
    }

    public static String getTianyuHomeInfo() {
        return getConfig(TIANYU_WX_HOME_INFO);
    }

    public static String getTianyuAboutName() {
        return getConfig(TIANYU_WX_ABOUT_NAME);
    }

    public static String getTianyuAboutDesc() {
        return getConfig(TIANYU_WX_ABOUT_DESC);
    }

    public static String getTianyuAboutAddress() {
        return getConfig(TIANYU_WX_ABOUT_ADDRESS);
    }

    public static String getTianyuAboutPhone() {
        return getConfig(TIANYU_WX_ABOUT_PHONE);
    }

    public static String getTianyuAboutQQNumber() {
        return getConfig(TIANYU_WX_ABOUT_QQNUMBER);
    }

    public static Double getTianyuAboutLatitude() {
        return getConfigDouble(TIANYU_WX_ABOUT_LATITUDE);
    }

    public static Double getTianyuAboutLongitude() {
        return getConfigDouble(TIANYU_WX_ABOUT_LONGITUDE);
    }

    public static Integer getTianyuAboutScale() {
        return getConfigInt(TIANYU_WX_ABOUT_SCALE);
    }

    public static String getTianyuAboutVersion() {
        return getConfig(TIANYU_WX_ABOUT_VERSION);
    }

    public static void setConfigs(Map<String, String> configs) {
        SYSTEM_CONFIGS = configs;
    }

    public static void updateConfigs(Map<String, String> data) {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            SYSTEM_CONFIGS.put(entry.getKey(), entry.getValue());
        }
    }
}