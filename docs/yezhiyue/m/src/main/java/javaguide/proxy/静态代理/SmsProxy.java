package javaguide.proxy.静态代理;

/**
 * @author 叶之越
 * Description
 * Date 2020/9/20
 * Time 9:15
 * Mail 739153436@qq.com
 */
public class SmsProxy implements SmsService {

    /**
     * 代入原来的对象，用于实现原来的方法
     */
    private final SmsService smsService;

    public SmsProxy(SmsService smsService) {
        this.smsService = smsService;
    }

    /**
     * 静态代理，对原来的方法进行
     */
    @Override
    public String send(String message) {
        System.out.println("代理方法执行之前执行");
        String result = smsService.send(message);
        System.out.println("代理方法执行之后执行");
        return result;
    }
}
