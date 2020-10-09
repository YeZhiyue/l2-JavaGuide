package javaguide.proxy.CGLIB代理;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author 叶之越
 * Description
 * Date 2020/9/20
 * Time 10:41
 * Mail 739153436@qq.com
 */
public class CglibProxyFactory {

    public static Object getProxy(Class<?> clazz) {
        // 创建动态代理增强类
        Enhancer enhancer = new Enhancer();
        // 设置类加载器
        enhancer.setClassLoader(clazz.getClassLoader());
        // 设置被代理类
        enhancer.setSuperclass(clazz);
        // 设置方法拦截器
        enhancer.setCallback(new DebugMethodInterceptor());
        // 创建代理类
        return enhancer.create();
    }
}
