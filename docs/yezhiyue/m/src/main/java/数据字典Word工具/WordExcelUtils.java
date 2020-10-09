package 数据字典Word工具;


import cn.hutool.core.io.file.FileWriter;
import cn.hutool.poi.excel.ExcelUtil;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
<<<<<<< HEAD
 * @author 叶之越
 * Description
 * Date 2020/7/25
 * Time 9:31
 * Mail 739153436@qq.com
=======
 * @author yzy
 * Description
 * Date 2020/7/25
 * Time 9:31
>>>>>>> 1d18854992f77a3f72366616aac27d18bf023e91
 */
public class WordExcelUtils {

    public static void main(String[] args) {
//        printLog();
//        printTableList();
        printAll();
    }

    // 数据字典的Excel
    private static String C_USERS_QQ_739_DESKTOP_数据字典_XLSX =
            "P:\\MarkDown - 副本\\MavenUtil\\src\\main\\resources\\数据字典.docx";
    private static String PATH_OUT =
            "P:\\MarkDown - 副本\\输出打印文件\\sql.sql";

    // 唯一约束添加
    private static final String NOT_NULL = " NOT NULL";
    // 字符集选择
    private static final String CHARACTER_SET_UTF_8_COLLATE_UTF_8_GENERAL_CI = " CHARACTER SET utf8 COLLATE utf8_general_ci ";

    // 数据表读取的对象
    private static List<List<Object>> readAll = ExcelUtil.getReader(C_USERS_QQ_739_DESKTOP_数据字典_XLSX).read();
    ;

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


    /**
     * @description 打印日志信息
<<<<<<< HEAD
     * @author 叶之越
=======
     * @author yzy
>>>>>>> 1d18854992f77a3f72366616aac27d18bf023e91
     * @email 739153436@qq.com
     * @date 2020/7/28 17:28
     */
    public static void printLog() {
        int minSize = 100;
        for (List<Object> objects : readAll) {
            if (objects.size() < minSize) {
                minSize = objects.size();
            }
            System.out.println(objects);
        }
        System.out.println("对象列表的最小长度：" + minSize);
    }

    /**
     * @description 打印表信息
<<<<<<< HEAD
     * @author 叶之越
=======
     * @author yzy
>>>>>>> 1d18854992f77a3f72366616aac27d18bf023e91
     * @email 739153436@qq.com
     * @date 2020/7/28 17:28
     */
    public static void printTableList() {
        StringBuilder builder = new StringBuilder();
        String tableName = "";
        // 循环遍历拼接
        for (List<Object> objects : readAll) {
            String indexOf0 = objects.get(0).toString().replaceAll("\\s", "");
            // 无效的列表对象直接跳过
            if (invalidObj(objects, indexOf0)) continue;
            // 表头操作，如果匹配表头信息，那么我们就进行里面信息的提取
            Matcher matcher1 = getMatcher(indexOf0, "(?<=（|\\().+(?=）|\\))");
            if (matcher1.find()) {
                tableName = matcher1.group().replaceAll("\\s", "");
                System.out.println("\"" + tableName + "\",");
                builder.append("DROP TABLE IF EXISTS `" + tableName + "`;\n" +
                        "CREATE TABLE `" + tableName + "`  (\n");
                continue;
            }
        }
    }

    /**
     * @description 打印所有
<<<<<<< HEAD
     * @author 叶之越
=======
     * @author yzy
>>>>>>> 1d18854992f77a3f72366616aac27d18bf023e91
     * @email 739153436@qq.com
     * @date 2020/8/9 23:00
     */
    public static void printAll() {
        StringBuilder builder = new StringBuilder();
        String isNullCurrent = "";
        String characterSetCurrent = "";
        String tableName = "";
        String tableDesc = "";
        // 循环遍历拼接
        for (List<Object> objects : readAll) {
            String indexOf0 = objects.get(0).toString().replaceAll("\\s", "");
            // 无效的列表对象直接跳过
            if (invalidObj(objects, indexOf0)) continue;
            // 表头操作，如果匹配表头信息，那么我们就进行里面信息的提取
            Matcher matcher1 = getMatcher(indexOf0, "(?<=（|\\().+(?=）|\\))");
            Matcher matcher2 = getMatcher(indexOf0, ".+(?=（|\\()");
            if (matcher1.find() && matcher2.find()) {
                tableName = matcher1.group().replaceAll("\\s", "");
                tableDesc = matcher2.group().replaceAll("\\s", "");
                System.out.println("\"" + tableName + "\",");
                builder.append("DROP TABLE IF EXISTS `" + tableName + "`;\n" +
                        "CREATE TABLE `" + tableName + "`  (\n");
                continue;
            }
            // 判断是否是文本类型，如果是文本类型，那么就为其配置编码
            String indexOf1 = objects.get(1).toString().replaceAll("\\s", "");
            if (indexOf1.startsWith("varchar")) {
                characterSetCurrent = CHARACTER_SET_UTF_8_COLLATE_UTF_8_GENERAL_CI;
            } else {
                characterSetCurrent = "";
            }
            // 判断这个字段是否允许为空
            String indexOf2 = objects.get(2).toString().replaceAll("\\s", "");
            if (indexOf2.equals("允许为空")) {
                isNullCurrent = "";
            } else {
                isNullCurrent = NOT_NULL;
            }
            // 拼接：字段名 + 类型 + 是否为空 + 注释
            builder.append(
                    "`" + indexOf0
                            + "` " + indexOf1 + characterSetCurrent +
                            isNullCurrent + " COMMENT '" + objects.get(3).toString().replaceAll("\\s", "") + "',\n"
            );
            // 是否到达表的结尾字段判断，通过字段名称判断
            if (indexOf0.equals("tenant_id")) {
                builder.append(" PRIMARY KEY (`id`) USING BTREE\n" +
                        ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '" + tableDesc + "' ROW_FORMAT = Dynamic;\n\n\n");
            }
        }
        FileWriter writer = new FileWriter(PATH_OUT);
        writer.write(builder.toString());
        System.out.println(builder.toString());
    }

    private static boolean invalidObj(List<Object> objects, String indexOf0) {
        if (objects.size() < 4
                // 如果
                || indexOf0.equals("列名")
                || indexOf0.length() == 0) {
            return true;
        }
        return false;
    }

}