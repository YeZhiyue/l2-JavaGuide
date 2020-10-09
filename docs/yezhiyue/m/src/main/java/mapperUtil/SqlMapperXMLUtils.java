package mapperUtil;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;

import java.io.File;
import java.util.List;

/**
<<<<<<< HEAD
 * @author 叶之越
 * Description
 * Date 2020/8/9
 * Time 22:37
 * Mail 739153436@qq.com
=======
 * @author yzy
 * Description
 * Date 2020/8/9
 * Time 22:37
>>>>>>> 1d18854992f77a3f72366616aac27d18bf023e91
 */
public class SqlMapperXMLUtils {

    public static String DIRE_PATH;

    /**
     * 获取resultMap && sql字段
     *
     * @throws Exception
     */
    public static void getAll() throws Exception {
        readSqlMapper(true, true);
    }

    /**
     * 获取resultMap
     *
     * @throws Exception
     */
    public static void getMapper() throws Exception {
        readSqlMapper(true, false);
    }

    /**
     * 获取 sql字段
     *
     * @throws Exception
     */
    public static void getSQLColumn() throws Exception {
        readSqlMapper(false, true);
    }


    /**
     * @param mapperFlag 是否需要resultMap
     * @param columnFlag 是否需要sql字段标签
     * @throws Exception
     */
    private static void readSqlMapper(boolean mapperFlag, boolean columnFlag) throws Exception {
        if (DIRE_PATH.equals(null) || DIRE_PATH.equals("")) {
            throw new Exception("请添加文件路径");
        }
        StringBuilder builder = new StringBuilder();
        File file = new File(DIRE_PATH);
        for (File listFile : file.listFiles()) {
            String fileName = listFile.getName();
            FileReader reader = new FileReader(listFile.getAbsolutePath());
            List<String> lines = reader.readLines();
            builder.append("文件名：" + fileName + "\n");
            for (int i = 0; i < lines.size(); i++) {
                if (mapperFlag) {
                    if (lines.get(i).contains("<resultMap id=\"BaseResultMap\"")) {
                        while (!lines.get(i).contains("</resultMap>")) {
                            builder.append(lines.get(i++) + "\n");
                        }
                        builder.append("    </resultMap>" + "\n");
                    }
                }
                if (columnFlag) {
                    if (lines.get(i).contains("Base_Column_List")) {
                        String sqlId = fileName.substring(0, fileName.indexOf('.')) + "_Base_Info_Column";
                        builder.append(
                                lines.get(i++).replace("Base_Column_List", sqlId) + "\n" +
                                        lines.get(i++) + "\n" +
                                        lines.get(i++) + "\n"
                        );
                        break;
                    }
                }
            }
        }
        String result = builder.toString()
                .replaceAll(", weight, create_time, update_time, create_by, update_by, version, deleted, extra", "")
                .replaceAll(", tenant_id", "")
                // 注意 \$ 这个符号在Java字符串中需要转义
                .replaceAll(", ", ", \\${prefix}")
                .replaceAll("\\sid,", "\\${prefix}id,");
        FileWriter writer = new FileWriter("P:\\MarkDown - 副本\\输出打印文件\\SqlXmlOut.text");
        System.out.println(result);
        writer.write(result);
    }
}
