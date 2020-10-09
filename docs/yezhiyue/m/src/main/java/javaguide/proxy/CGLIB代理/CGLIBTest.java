package javaguide.proxy.CGLIB代理;

/**
 * @author 叶之越
 * Description
 * Date 2020/9/20
 * Time 10:43
 * Mail 739153436@qq.com
 */
public class CGLIBTest {
    public static void main(String[] args) {
        AliSmsService aliSmsService = (AliSmsService) CglibProxyFactory.getProxy(AliSmsService.class);
        aliSmsService.send("java");
    }
}
