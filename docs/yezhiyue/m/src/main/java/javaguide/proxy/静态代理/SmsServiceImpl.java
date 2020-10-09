package javaguide.proxy.静态代理;

/**
 * @author 叶之越
 * Description
 * Date 2020/9/20
 * Time 9:13
 * Mail 739153436@qq.com
 */
public class SmsServiceImpl implements SmsService {
    @Override
    public String send(String message) {
        System.out.println("静态代理发送消息：" + message);
        return message;
    }
}
