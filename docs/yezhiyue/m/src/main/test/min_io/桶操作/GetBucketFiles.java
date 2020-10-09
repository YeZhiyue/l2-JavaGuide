package min_io.桶操作;


import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.MinioException;
import io.minio.messages.Item;
import min_io.Config;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class GetBucketFiles {


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

            try {
                // 检查'mybucket'是否存在。
                boolean found = minioClient.bucketExists(Config.BUCKETS[0]);
                if (found) {
                    // 列出'my-bucketname'里的对象
                    Iterable<Result<Item>> myObjects = minioClient.listObjects(Config.BUCKETS[0]);
                    for (Result<Item> result : myObjects) {
                        Item item = result.get();
                        System.out.println(item.lastModified() + ", " + item.size() + ", " + item.objectName());
                    }
                } else {
//http://59.110.213.92:9000/bucket1/pic1.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIOSFODNN7EXAMPLE%2F20200920%2F%2Fs3%2Faws4_request&X-Amz-Date=20200920T231631Z&X-Amz-Expires=259200&X-Amz-SignedHeaders=host&X-Amz-Signature=7ac35b50a914deb75fa29c10df9fbb73623761f1e8b5d8c96289650ffac57dd2
                    System.out.println("mybucket does not exist");
                }
            } catch (MinioException e) {
                System.out.println("Error occurred: " + e);
            }

        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }
}

