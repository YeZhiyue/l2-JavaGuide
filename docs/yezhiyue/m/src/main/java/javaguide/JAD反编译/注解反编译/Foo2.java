package javaguide.JAD反编译.注解反编译;

import java.lang.annotation.Annotation;

/**
 * 注解反编译结果
 */
interface  Foo2 extends Annotation {
    public abstract String[] value();

    public abstract boolean bar();
}
