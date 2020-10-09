package min_io.文件操作;


import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import min_io.Config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class FileUploadStream {


    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InvalidKeyException {
        try {
            // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
            MinioClient minioClient = new MinioClient(Config.ENDPOIN,
                    Config.ACCESS_KEY,
                    Config.SECRET_KEY);

            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists(Config.BUCKETS[0]);
            if (isExist) {
                System.out.println("Bucket already exists.");
            } else {
                // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
                minioClient.makeBucket(Config.BUCKETS[0]);
            }

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 1000; i++) {
                builder.append("Sphinx of black quartz, judge my vow: Used by Adobe InDesign to display font samples. ");
                builder.append("(29 letters)\n");
                builder.append("Jackdaws love my big sphinx of quartz: Similarly, used by Windows XP for some fonts. ");
                builder.append("(31 letters)\n");
                builder.append("Pack my box with five dozen liquor jugs: According to Wikipedia, this one is used on ");
                builder.append("NASAs Space Shuttle. (32 letters)\n");
                builder.append("The quick onyx goblin jumps over the lazy dwarf: Flavor text from an Unhinged Magic Card. ");
                builder.append("(39 letters)\n");
                builder.append("How razorback-jumping frogs can level six piqued gymnasts!: Not going to win any brevity ");
                builder.append("awards at 49 letters long, but old-time Mac users may recognize it.\n");
                builder.append("Cozy lummox gives smart squid who asks for job pen: A 41-letter tester sentence for Mac ");
                builder.append("computers after System 7.\n");
                builder.append("A few others we like: Amazingly few discotheques provide jukeboxes; Now fax quiz Jack! my ");
                builder.append("brave ghost pled; Watch Jeopardy!, Alex Trebeks fun TV quiz game.\n");
                builder.append("- --\n");
            }
            ByteArrayInputStream bais = new
                    ByteArrayInputStream(builder.toString().getBytes("UTF-8"));
            // 创建对象
//            "mybucket", "myobject", bais, bais.available(), "application/octet-stream")
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(Config.BUCKETS[0])
                            .object(Config.TEXT_FILE)
                            .contentType("text/text")
                            .stream(bais, bais.available(), -1)
                            .build()
            );

            bais.close();
            System.out.println("myobject is uploaded successfully");

        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }
}

