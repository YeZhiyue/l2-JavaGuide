package javaguide.JAD反编译;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author 叶之越
 * Description
 * Date 2020/9/20
 * Time 11:25
 * Mail 739153436@qq.com
 */
public class ForeachSyntacticSugar {
    public static void main(String[] args) {

    }
    /**
     * 对于数组类foreach的反编译结果
     */
    public void arr() {
        /// 原始代码
        int[] arr = {1, 2, 3};
        for (int item : arr) {
            System.out.println(item);
        }

        /// 反编译代码
        int ai[] = {1, 2, 3};
        int ai1[] = ai;
        for (int i = 0; i < ai1.length; i++) {
            int k = ai1[i];
            System.out.println(k);
        }
    }

    /**
     * 对于容器类foreach的反编译结果
     */
    public void list() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(1));
        arrayList.add(Integer.valueOf(2));
        arrayList.add(Integer.valueOf(3));
        Integer integer;
        for (Iterator iterator = arrayList.iterator(); iterator.hasNext(); System.out.println(integer)) {
            integer = (Integer) iterator.next();
        }
    }
}
