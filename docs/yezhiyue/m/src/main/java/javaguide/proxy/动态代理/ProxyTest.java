package javaguide.proxy.动态代理;

/**
 * @author 叶之越
 * Description
 * Date 2020/9/20
 * Time 9:39
 * Mail 739153436@qq.com
 */
public class ProxyTest {
    public static void main(String[] args) {
        SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        smsService.send("java");
        /**
         * before method send
         * send message:java
         * after method send
         */
    }
}
