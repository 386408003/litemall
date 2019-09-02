package org.linlinjava.litemall.core.system;

import org.linlinjava.litemall.core.util.SystemInfoPrinter;
import org.linlinjava.litemall.db.service.LitemallSystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 系统启动服务，用于设置系统配置信息、检查系统状态及打印系统信息
 */
@Component
class SystemInistService {
    private SystemInistService systemInistService;


    @Autowired
    private Environment environment;

    @PostConstruct
    private void inist() {
        systemInistService = this;
        initConfigs();
        SystemInfoPrinter.printInfo("Litemall 初始化信息", getSystemInfo());
    }


    private final static Map<String, String> DEFAULT_CONFIGS = new HashMap<>();

    static {
        // 小程序相关配置默认值
        DEFAULT_CONFIGS.put(SystemConfig.LITEMALL_WX_INDEX_NEW, "6");
        DEFAULT_CONFIGS.put(SystemConfig.LITEMALL_WX_INDEX_HOT, "6");
        DEFAULT_CONFIGS.put(SystemConfig.LITEMALL_WX_INDEX_BRAND, "4");
        DEFAULT_CONFIGS.put(SystemConfig.LITEMALL_WX_INDEX_TOPIC, "4");
        DEFAULT_CONFIGS.put(SystemConfig.LITEMALL_WX_INDEX_CATLOG_LIST, "4");
        DEFAULT_CONFIGS.put(SystemConfig.LITEMALL_WX_INDEX_CATLOG_GOODS, "4");
        DEFAULT_CONFIGS.put(SystemConfig.LITEMALL_WX_SHARE, "false");
        // 运费相关配置默认值
        DEFAULT_CONFIGS.put(SystemConfig.LITEMALL_EXPRESS_FREIGHT_VALUE, "8");
        DEFAULT_CONFIGS.put(SystemConfig.LITEMALL_EXPRESS_FREIGHT_MIN, "88");
        // 订单相关配置默认值
        DEFAULT_CONFIGS.put(SystemConfig.LITEMALL_ORDER_UNPAID, "30");
        DEFAULT_CONFIGS.put(SystemConfig.LITEMALL_ORDER_UNCONFIRM, "7");
        DEFAULT_CONFIGS.put(SystemConfig.LITEMALL_ORDER_COMMENT, "7");
        // 订单相关配置默认值
        DEFAULT_CONFIGS.put(SystemConfig.LITEMALL_MALL_NAME, "litemall");
        DEFAULT_CONFIGS.put(SystemConfig.LITEMALL_MALL_ADDRESS, "上海");
        DEFAULT_CONFIGS.put(SystemConfig.LITEMALL_MALL_PHONE, "021-xxxx-xxxx");
        DEFAULT_CONFIGS.put(SystemConfig.LITEMALL_MALL_QQ, "738696120");
        // 课程计划批量添加配置
        DEFAULT_CONFIGS.put(SystemConfig.TIANYU_ADMIN_STARTTIME, "08:00");
        DEFAULT_CONFIGS.put(SystemConfig.TIANYU_ADMIN_ENDTIME, "20:00");
        DEFAULT_CONFIGS.put(SystemConfig.TIANYU_ADMIN_COURSEPLAN, "1,1,1,1,1,1,1,1,1,1,1,1");
        // 微信小程序首页配置默认值
        DEFAULT_CONFIGS.put(SystemConfig.TIANYU_WX_HOME_TITLE, "天瑜瑜伽工作室简介");
        DEFAULT_CONFIGS.put(SystemConfig.TIANYU_WX_HOME_INFO, "天瑜瑜伽工作室课程体系属于人体功能恢复瑜伽体系。");
        // 微信小程序关于页面配置默认值
        DEFAULT_CONFIGS.put(SystemConfig.TIANYU_WX_ABOUT_NAME, "天瑜瑜珈工作室");
        DEFAULT_CONFIGS.put(SystemConfig.TIANYU_WX_ABOUT_DESC, "天瑜瑜珈工作室");
        DEFAULT_CONFIGS.put(SystemConfig.TIANYU_WX_ABOUT_ADDRESS, "郑州市经开区九大街航海路交叉口南500米");
        DEFAULT_CONFIGS.put(SystemConfig.TIANYU_WX_ABOUT_PHONE, "15617927721");
        DEFAULT_CONFIGS.put(SystemConfig.TIANYU_WX_ABOUT_QQNUMBER, "386408003");
        DEFAULT_CONFIGS.put(SystemConfig.TIANYU_WX_ABOUT_LATITUDE, "34.718656");
        DEFAULT_CONFIGS.put(SystemConfig.TIANYU_WX_ABOUT_LONGITUDE, "113.769854");
        DEFAULT_CONFIGS.put(SystemConfig.TIANYU_WX_ABOUT_SCALE, "14");
        DEFAULT_CONFIGS.put(SystemConfig.TIANYU_WX_ABOUT_VERSION, "1.0.0");
    }

    @Autowired
    private LitemallSystemConfigService litemallSystemConfigService;

    private void initConfigs() {
        // 1. 读取数据库全部配置信息
        Map<String, String> configs = litemallSystemConfigService.queryAll();

        // 2. 分析DEFAULT_CONFIGS
        for (Map.Entry<String, String> entry : DEFAULT_CONFIGS.entrySet()) {
            if (configs.containsKey(entry.getKey())) {
                continue;
            }

            configs.put(entry.getKey(), entry.getValue());
            litemallSystemConfigService.addConfig(entry.getKey(), entry.getValue());
        }

        SystemConfig.setConfigs(configs);
    }

    private Map<String, String> getSystemInfo() {

        Map<String, String> infos = new LinkedHashMap<>();

        infos.put(SystemInfoPrinter.CREATE_PART_COPPER + 0, "系统信息");
        // 测试获取application-db.yml配置信息
        infos.put("服务器端口", environment.getProperty("server.port"));
        infos.put("数据库USER", environment.getProperty("spring.datasource.druid.username"));
        infos.put("数据库地址", environment.getProperty("spring.datasource.druid.url"));
        infos.put("调试级别", environment.getProperty("logging.level.org.linlinjava.litemall.wx"));

        // 测试获取application-core.yml配置信息
        infos.put(SystemInfoPrinter.CREATE_PART_COPPER + 1, "模块状态");
        infos.put("邮件", environment.getProperty("litemall.notify.mail.enable"));
        infos.put("短信", environment.getProperty("litemall.notify.sms.enable"));
        infos.put("模版消息", environment.getProperty("litemall.notify.wx.enable"));
        infos.put("快递信息", environment.getProperty("litemall.express.enable"));
        infos.put("快递鸟ID", environment.getProperty("litemall.express.appId"));
        infos.put("对象存储", environment.getProperty("litemall.storage.active"));
        infos.put("本地对象存储路径", environment.getProperty("litemall.storage.local.storagePath"));
        infos.put("本地对象访问地址", environment.getProperty("litemall.storage.local.address"));
        infos.put("本地对象访问端口", environment.getProperty("litemall.storage.local.port"));

        // 微信相关信息
        infos.put(SystemInfoPrinter.CREATE_PART_COPPER + 2, "微信相关");
        infos.put("微信APP KEY", environment.getProperty("litemall.wx.app-id"));
        infos.put("微信APP-SECRET", environment.getProperty("litemall.wx.app-secret"));
        infos.put("微信支付MCH-ID", environment.getProperty("litemall.wx.mch-id"));
        infos.put("微信支付MCH-KEY", environment.getProperty("litemall.wx.mch-key"));
        infos.put("微信支付通知地址", environment.getProperty("litemall.wx.notify-url"));

        //测试获取System表配置信息
        infos.put(SystemInfoPrinter.CREATE_PART_COPPER + 3, "系统设置");
        infos.put("自动创建朋友圈分享图", Boolean.toString(SystemConfig.isAutoCreateShareImage()));
        infos.put("商场名称", SystemConfig.getMallName());
        infos.put("首页显示记录数：NEW,HOT,BRAND,TOPIC,CatlogList,CatlogMore",
                SystemConfig.getNewLimit() + "," + SystemConfig.getHotLimit() + "," + SystemConfig.getBrandLimit() +
                        "," + SystemConfig.getTopicLimit() + "," + SystemConfig.getCatlogListLimit() + "," + SystemConfig.getCatlogMoreLimit());
        infos.put("天瑜后台批量添加课程配置信息：StartTime,EndTime,CoursePlan",
                SystemConfig.getTianyuAdminStarttime() + "," + SystemConfig.getTianyuAdminEndtime() + "," + SystemConfig.getTianyuAdminCoursePlan());
        return infos;
    }
}
