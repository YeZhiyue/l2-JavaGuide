package min_io.桶操作;


import io.minio.MinioClient;
import io.minio.errors.MinioException;
import io.minio.messages.Bucket;
import min_io.Config;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class GetBucketList {


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

            // 列出所有存储桶
            List<Bucket> bucketList = minioClient.listBuckets();
            for (Bucket bucket : bucketList) {
                System.out.println(bucket.creationDate() + ", " + bucket.name());
            }

        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }
}

