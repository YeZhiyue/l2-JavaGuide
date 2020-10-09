package min_io.文件操作;


import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import min_io.Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class FileUploadLocal {


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

            // 使用putObject上传一个文件到存储桶中。
            String fileName = "pic1.png";
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(Config.BUCKETS[0]).object(fileName)
                            .stream(new FileInputStream("B:\\"+fileName), -1, 10485760)
                            .contentType("image/png")
                            .build());
            System.out.println(fileName+" is upload success!");
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }
}

