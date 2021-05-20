package com.jiang.school_guide.untils;

import com.baidu.aip.contentcensor.AipContentCensor;
import com.baidu.aip.contentcensor.EImgType;
import org.json.JSONObject;

public class Sample {
    //设置APPID/AK/SK
    public static final String APP_ID = "24213757";
    public static final String API_KEY = "BfwcxspT1vo3yOQvQG9El21i";
    public static final String SECRET_KEY = "qxOGoQuErXjmX2Qa5B2GPGXnIH5GewiE";

    public static void main(String[] args) {
        System.out.println("-----------");
        // 初始化一个AipContentCensor
        AipContentCensor client = new AipContentCensor(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(10000);
        client.setSocketTimeoutInMillis(10000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        String path = "C:\\Users\\Administrator\\Pictures\\QQ图片20210520233615.jpg";
        JSONObject response = client.imageCensorUserDefined(path, EImgType.FILE, null);
        System.out.println(response.toString());
/*        String text = "测试文本";
        JSONObject response = client.textCensorUserDefined(text);*/
        System.out.println(response.toString());
    }
}
