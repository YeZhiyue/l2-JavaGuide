import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
<<<<<<< HEAD
 * @author 叶之越
 * Description
 * Date 2020/8/2
 * Time 11:29
 * Mail 739153436@qq.com
=======
 * @author yzy
 * Description
 * Date 2020/8/2
 * Time 11:29
>>>>>>> 1d18854992f77a3f72366616aac27d18bf023e91
 */
public class NormalTest {
    @Test
    public void test02() {
        Pattern.compile("");
    }
    @Test
    public void test01() {
        String firstDoorLock = "(?<=\\\"doorOneBtnStatus\\\": )\\d*";
        String secondDoorLock = "(?<=\\\"doorTwoBtnStatus\\\": )\\d*";
        String thirdDoorLock = "(?<=\\\"doorThreeBtnStatus\\\": )\\d*";
        String forthDoorLock = "(?<=\\\"doorFourBtnStatus\\\": )\\d*";

        List<String> doorLockStatusList = Arrays.asList(
                firstDoorLock,
                secondDoorLock,
                thirdDoorLock,
                forthDoorLock
        );

        for (int i = 0; i < doorLockStatusList.size(); i++) {
            String doorLockStatus = doorLockStatusList.get(i);
            Pattern compile = Pattern.compile(doorLockStatus);
            Matcher matcher = compile.matcher(deviceStatus);
            // 打印所有匹配
            while (matcher.find()) {
                System.out.println(matcher.group());
            }
        }
    }


    String deviceStatus = "{\"eventDateTime\":1598494012409,\"functionId\":\"0x40\",\"functionName\":\"远程开门\",\"snNumber\":423194759,\"eventType\":1,\"type\":\"17\",\"functionSuccess\":1}";
    String deviceStatus2 = "{\n" +
            "    \"eventDateTime\": 1598426895163,\n" +
            "    \"controllerDateTime\": \"2020-08-26 15:28:10\",\n" +
            "    \"keyValue\": 0,\n" +
            "    \"recordReasonDescriptionCn\": \"超级密码开门\",\n" +
            "    \"recordReasonDescriptionEn\": \"Super Password Open Door\",\n" +
            "    \"doorNumber\": 2, // 开门的编号，需要开二号门\n" +
            "    \"doorOneBtnStatus\": 0, \n" +
            "    \"doorTwoBtnStatus\": 0,\n" +
            "    \"doorThreeBtnStatus\": 0,\n" +
            "    \"doorFourBtnStatus\": 0,\n" +
            "    \"doorOneStatus\": 1,\n" +
            "    \"doorTwoStatus\": 1,\n" +
            "    \"doorThreeStatus\": 1,\n" +
            "    \"doorFourStatus\": 1,\n" +
            "    \"type\": \"17\",\n" +
            "    \"recordType\": 2,\n" +
            "    \"recordIndex\": 416,\n" +
            "    \"functionId\": \"0x20\",\n" +
            "    \"recordValid\": 1,\n" +
            "    \"snNumber\": 423194759,\n" +
            "    \"forciblyLock\": 0,\n" +
            "    \"errorcode\": 0,\n" +
            "    \"serialNumber\": \"0\",\n" +
            "    \"recordInOrOut\": 1,\n" +
            "    \"relayStatus\": 0,\n" +
            "    \"functionName\": \"查询控制器状态\",\n" +
            "    \"recordInOrOutDescription\": \"进门\",\n" +
            "    \"eventType\": 1,\n" +
            "    \"recordValidDescription\": \"离线权限通过\",\n" +
            "    \"recordTime\": \"2020-08-26 12:09:23\",\n" +
            "    \"recordReason\": 25,\n" +
            "    \"cardNumber\": 10\n" +
            "}\n";
}
