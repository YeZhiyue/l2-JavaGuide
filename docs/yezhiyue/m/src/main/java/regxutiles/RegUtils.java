package regxutiles;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
<<<<<<< HEAD
 * @author 叶之越
 * Description
 * Date 2020/7/28
 * Time 13:25
 * Mail 739153436@qq.com
=======
 * @author yzy
 * Description
 * Date 2020/7/28
 * Time 13:25
>>>>>>> 1d18854992f77a3f72366616aac27d18bf023e91
 */
public class RegUtils {

    public static void main(String[] args) {
        String str = "Hello World !";
        String reg = "[a-z]{2,4}?";
        // 获取匹配
        Matcher matcher = getMatcher(str, reg);
        // 打印分组匹配分组信息
        printGroupMsg(str, reg, Pattern.DOTALL);
        // 打印匹配字符
        printMatchString(str, reg, Pattern.DOTALL);


    }

    /**
     * @description 打印组别信息和开始结束下标
<<<<<<< HEAD
     * @author 叶之越
=======
     * @author yzy
>>>>>>> 1d18854992f77a3f72366616aac27d18bf023e91
     * @email 739153436@qq.com
     * @date 2020/7/28 13:16
     */
    public static void printGroupMsg(String str, String reg, int flag) {
        Matcher matcher = getMatcher(str, reg, flag);
        int count = 1;
        while (matcher.find()) {
            System.out.println("\n匹配号： " + count++ + "  组别：" + matcher.groupCount() + "  匹配到的字符串： " + matcher.group());
            System.out.println("开始下标：" + matcher.start() + "  结束下标：" + matcher.end());
        }
    }

    /**
     * @description 打印输出
<<<<<<< HEAD
     * @author 叶之越
=======
     * @author yzy
>>>>>>> 1d18854992f77a3f72366616aac27d18bf023e91
     * @email 739153436@qq.com
     * @date 2020/7/28 12:57
     */
    public static void printMatchString(String str, String reg, int flag) {
        Matcher matcher = getMatcher(str, reg, flag);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    /**
     * @description 获取Matcher对象
<<<<<<< HEAD
     * @author 叶之越
=======
     * @author yzy
>>>>>>> 1d18854992f77a3f72366616aac27d18bf023e91
     * @email 739153436@qq.com
     * @date 2020/7/28 12:55
     */
    public static Matcher getMatcher(String str, String reg, int flag) {
        return flag == -1 ? Pattern.compile(reg).matcher(str) : Pattern.compile(reg, flag).matcher(str);
    }

    /**
     * @description 获取Matcher对象
<<<<<<< HEAD
     * @author 叶之越
=======
     * @author yzy
>>>>>>> 1d18854992f77a3f72366616aac27d18bf023e91
     * @email 739153436@qq.com
     * @date 2020/7/28 12:55
     */
    public static Matcher getMatcher(String str, String reg) {
        return getMatcher(str, reg, -1);
    }
}
