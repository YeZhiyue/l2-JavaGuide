package javaguide.proxy.动态代理;

/**
 * @author 叶之越
 * Description
 * Date 2020/9/20
 * Time 9:25
 * Mail 739153436@qq.com
 */
public class SmsServiceImpl implements SmsService {
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}
