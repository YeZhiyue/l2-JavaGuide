package javaguide.proxy.静态代理;

/**
 * @author 叶之越
 * Description
 * Date 2020/9/20
 * Time 9:20
 * Mail 739153436@qq.com
 */
public class ProxyTest {
    public static void main(String[] args) {
        SmsService smsService = new SmsServiceImpl();
        SmsProxy smsProxy = new SmsProxy(smsService);
        smsProxy.send("java");
        /**
         * 执行结果：
         *
         * 代理方法执行之前执行
         * 静态代理发送消息：java
         * 代理方法执行之后执行
         */
    }
}
