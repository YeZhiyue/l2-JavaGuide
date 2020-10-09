package javaguide.JAD反编译;

/**
 * @author 叶之越
 * Description
 * Date 2020/9/20
 * Time 11:21
 * Mail 739153436@qq.com
 */
public class AutomaticUnboxingOrBoxing {
    public static void main(String[] args) {
        // 自动拆箱
        // intValue()拆箱：int i = (new Integer(10)).intValue();
        int x = new Integer(10);
        // 自动装箱
        // valueOf()拆箱：Integer integer = Integer.valueOf(i);
        Integer y = x;
    }
}
