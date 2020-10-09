package mdutils;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;

/**
<<<<<<< HEAD
 * @author 叶之越
 * Description
 * Date 2020/8/9
 * Time 22:18
 * Mail 739153436@qq.com
=======
 * @author yzy
 * Description
 * Date 2020/8/9
 * Time 22:18
>>>>>>> 1d18854992f77a3f72366616aac27d18bf023e91
 */
public class MDUtils {


    // IO读取路径
    public static String FILE_PATH
            = "C:\\a_project\\MarkDown\\MarkDown\\我的学习笔记\\Java\\Spring其他技术\\Redis\\RedisTemplate常用API.md";

    // IO输出路径
    public static String PATH_OUT =
            "C:\\a_project\\MarkDown\\MarkDown\\输出打印文件\\mdOut.text";

    // 目录样式
    private static final String DIRECTORY_CONTENT = "<a id=\"_top\"></a>\n\n## `目录:`\n\n";

    /**
     * @description 获取自定义md的目录树
<<<<<<< HEAD
     * @author 叶之越
=======
     * @author yzy
>>>>>>> 1d18854992f77a3f72366616aac27d18bf023e91
     * @email 739153436@qq.com
     * @date 2020/8/9 22:21
     */
    public static void getDirectory() {
        FileReader reader = new FileReader(FILE_PATH);
        StringBuilder directBuilder = new StringBuilder(DIRECTORY_CONTENT);
        int firstLevelDirectory = 0;
        int secondLevelDirectory = 1;
        for (String s : reader.readLines()) {
            if (s.startsWith("**")) {
                directBuilder.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#_" + firstLevelDirectory + "." + secondLevelDirectory++ + "\" rel=\"nofollow\" target=\"_self\">" + s.substring(5, s.length() - 2) + "</a>\n");
            } else if (s.startsWith("## `")) {
                secondLevelDirectory = 1;
                firstLevelDirectory++;
                directBuilder.append("### <a href=\"#_" + firstLevelDirectory + "\" rel=\"nofollow\" target=\"_self\"><font size=5 color=CC3333 face=微软雅黑>" + s.substring(4, s.length() - 1) + "</font></a>\n");
            }
        }
        String directory = directBuilder.toString();
        FileWriter writer = new FileWriter(PATH_OUT);
        writer.write(directory);
        System.out.println(directory);
    }
}
