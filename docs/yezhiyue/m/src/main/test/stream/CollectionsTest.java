package stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 叶之越
 * Description
 * Date 2020/9/6
 * Time 9:11
 * Mail 739153436@qq.com
 */
public class CollectionsTest {
    @Test
    public void test01() {
        int[] arr = {1, 2, 4};

        List<Integer> collect = Arrays.stream(arr).boxed().collect(Collectors.toList());
    }

}
