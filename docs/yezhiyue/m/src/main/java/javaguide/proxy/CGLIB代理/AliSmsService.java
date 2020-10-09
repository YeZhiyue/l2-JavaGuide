package javaguide.proxy.CGLIB代理;

/**
 * @author 叶之越
 * Description
 * Date 2020/9/20
 * Time 10:40
 * Mail 739153436@qq.com
 */
public class AliSmsService {
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}