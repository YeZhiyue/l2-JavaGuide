import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import mapperUtil.SqlMapperXMLUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
<<<<<<< HEAD
 * @author 叶之越
 * Description
 * Date 2020/8/3
 * Time 9:32
 * Mail 739153436@qq.com
=======
 * @author yzy
 * Description
 * Date 2020/8/3
 * Time 9:32
>>>>>>> 1d18854992f77a3f72366616aac27d18bf023e91
 */
public class SqlMapperXMLUtil {


    @Test
    public void SqlMapperTest() throws Exception {
        SqlMapperXMLUtils.DIRE_PATH = "P:\\second\\beilun\\ace-provider\\ace-park-provider\\src\\main\\resources\\mysql\\mappers";
//        SqlMapperXMLUtils.getAll();
//        SqlMapperXMLUtils.getMapper();
        SqlMapperXMLUtils.getSQLColumn();
    }
}
