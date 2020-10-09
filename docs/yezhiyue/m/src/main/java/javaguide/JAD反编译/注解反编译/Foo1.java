package javaguide.JAD反编译.注解反编译;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 注解
 */
@Retention(RetentionPolicy.RUNTIME)
@interface  Foo1{
    String[] value();

    boolean bar();
}
