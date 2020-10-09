import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import mdutils.MDUtils;
import org.junit.Test;

import java.io.FileNotFoundException;

/**
<<<<<<< HEAD
 * @author 叶之越
=======
 * @author yzy
>>>>>>> 1d18854992f77a3f72366616aac27d18bf023e91
 * @description 生成
 * @email 739153436@qq.com
 * @date 2020/7/28 15:44
 */
public class MarkDownUtils {

    @Test
    public void d() {
        Integer i1 = 100;
        Integer i2 = 100;

        System.out.println(i1 == i2);
    }

    @Test
    public void mdUtilTest() throws FileNotFoundException {
        // 路径设置
        MDUtils.FILE_PATH =
                "C:\\a_project\\MarkDown\\MarkDown\\我的学习笔记\\Java\\Spring其他技术\\RabbitMQ\\MQ官方文档学习.md";
        MDUtils.PATH_OUT =
                "C:\\a_project\\MarkDown\\MarkDown\\输出打印文件\\mdOut.text";
        // 获取目录
        MDUtils.getDirectory();
    }
}
