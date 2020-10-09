package javaguide.JAD反编译.注解反编译;

import java.util.Arrays;

/**
 * 注解的实际使用
 */
@Foo1(value={"v1","v2"},bar = true)
class Demo{
    public static void main(String[] args) {
        Foo1 foo1 = Demo.class.getAnnotation(Foo1.class);
        System.out.println(Arrays.toString(foo1.value()));
        System.out.println(foo1.bar());
    }
}
