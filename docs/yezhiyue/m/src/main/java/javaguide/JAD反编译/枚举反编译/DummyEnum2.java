//package javaguide.JAD反编译.枚举反编译;
//
///**
// * @author 叶之越
// * Description
// * Date 2020/9/20
// * Time 11:57
// * Mail 739153436@qq.com
// */
//public final class DummyEnum2 extends Enum
//{
//    // 功能和单例模式的getInstance()方法相同
//    public static DummyEnum2[] values()
//    {
//        return (DummyEnum2[])$VALUES.clone();
//    }
//    // 调用父类的valueOf方法，并墙砖返回
//    public static DummyEnum2 valueOf(String s)
//    {
//        return (DummyEnum2)Enum.valueOf(DummyEnum2.class, s);
//    }
//    // 默认提供一个私有的私有两个参数的构造器，并调用父类Enum的构造器
//    private DummyEnum2(String s, int i)
//    {
//        super(s, i);
//    }
//    // 初始化一个private static final的本类空数组
//    private static final DummyEnum2 $VALUES[] = new DummyEnum2[0];
//
//}
