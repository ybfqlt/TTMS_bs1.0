package com.coco.config;

import java.io.FileWriter;
import java.io.IOException;
/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {


	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092800618328";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDCSAJYN58cnDoT3IfIlfYIggqC/OV4i/c+oUrvikFwaJPbjZapZuL3iyF+cx4916KbnYgDxHGaDfFyun+twT8/WsU7MhI1SflgfM7f7jkbju0FMS7AayW/OuavUq3hAkZES236R5ecHhWxaa/eqvj8SqanDyYREA34k0gKncdR5qqKflqgZEBMhKMB/gExqQ2Q/W14Qt5yegZW1MVNVFRJac3bGoMxea0dm57DKrys9mbq+SP+n8eAhwK3oTVjYyfUI+HdYPWeblsYHooMdVOFDCx5E4qsY1LW6XS7IFpOpCmENmTjfmZx3BCc4TjdJVrJVIqyB+CsOv6p7b/WOaF9AgMBAAECggEAcBFxqMmM8sf7iHFxsfcvEQFSZvTSuZKjcHFB9KNf1CO/BqrJ+bXrvrd2/qYRD8NPfUyFpQawMRlR6chC1kve3ADUPK1Ja4vcWhgkjW+xaYAsoQ4Z1cz43TJw1zluK5lQRxzrt6+MePWxtcEtPdiJ/55gO4W1g6orJPRwXg9dZpQSubWRq7R2lmQcW94YAwUGDjvqzucLOHzSJJoQCRJoKY0XrTZIXCb6vTJg7oy3Hb4KA4yqJx+dz+IKitqLFJ8/qBhkiB8DI2RtzjPaM6x3C+SJwFI1BKml3NyrEPu4APNwho2IMY5OsP8mNI1VsTM+qI72bNy4akHyFnPGUrJogQKBgQDqSJpaPoFrKFeTlCjoyjm785pvDlDbniTdE7mQ+zqOoRWlkH5ZqQr1OZvEVl3BkVtE0kJa0TE/4x4Q4ktuIk3knzcNZrkVQpF3isAeKJuLz4mlNKGHL+XjrB7jH3gQlAAl845j8HSWLmQW5dKAVTinLCFp8X41Gpcv8wrMJuokhQKBgQDUSi1idMBV0A1naHPagKVZqiiPVnULQbXDUuWKYdlL5sGG1jsLXlf5AbvE236UYIH4j7B9eHN0CMi8ONGNnlrtuW9N3UPEfz3TTyppWNlNHEf0egYd4HNJXRoY0MiKwrdl9XzO7PshcsAPQFaDc0A2SJGWZHl9lPuROVo2CAr2mQKBgQDarQe9YghEb/RGYI1Ks67t2vPz4sXgJ5066PVU+NnwVTSyQ8tbfugmei4FNaPvT/CDrUmYIRg6DJB8q+xCESzpTx8AmDf7OxYyIUUvITIGbqGInTaDy1ZliruNvUrBFpOI2Ou4JrF7mDN+F+fCN0lY6F0L2Qe3I2morM8QPOZhTQKBgEnWnyCOsVNnsTHVj+5hLqIYYFEy5jULHviYPcS7MgSHd6q5mwHiR8ERjEj1e3qKcsqM1/tcSPQEFiX5sVGL0d67z+4KVwbipxN3hq3rvHFos9JiGvyLVC4hVvFotUgmX/nzv/yNSvAgZJMpcq34CU6YQSNJQUSf7Dxf8XO3qnCJAoGBAMe0ky9XkuU4vw7qm/Q6ZtQohut45HpdVs/rRA7SkZPnLjYTyzTisHD76gRdJEASEBxjz9cBwjh/310pv895\neLP2duttxrF4kJqc7RY/5RxOrcn1dyTIG+TFlttL4xCBziv9wUKDr2uVJKe6HVsx\nXn1Ds64Zo2mEDJrPxQus9Vap";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqITV6VCPxBCT2jK5Uws3czStb5m5TXWJZjnYAQZLwLqf+SNhw9NQNXWJrfH5xXl71YYTeKxXE0M8ouK749oMoh7EJ7gDVWXB9XqK0GAWRNuPnvlGzUlS4jOK5fKSY/v5hGGUJhV+ytUWiYSJwe3cp0XHjQIWozXMZhpRbAVpCgQ9xlzTN0j2QI8bK1piKNMhW0t+Tp8FN6ABHMUOey8YhT3vWMrxyVEU7O+SU5lqwS8pZwvBOxN/nwnziUIJ1x0oR3NJ1/6kn3FLneamDgSzE3mm8q6VYr3Xx0kukvWv+S5h6BJpofX+DXeovqfUgSixSFeuLbD3FNGXuX9vjS8WrQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/src/main/webapp/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/src/main/webapp/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

