<font color=#ca0c16 size=8> MinIO文件上传

<a id="_top"></a>

@[TOC](文章目录)

# 前言

<font color=#999AAA > 文件上传下载是我们平时开发中经常会碰到的场景，这里我们就可以随着博主来简单的搭建一个文件服务，让你可以在10分钟内实现文件的上传下载。

<hr style=" border:solid; width:100px; height:1px;" color=#000000 size=1">

# MinIO服务器安装(推荐Docker)

**1. 安装**

Docker安装时最快最无脑的，一行命令就可以实现。

- 这里设置了你的账户密码
- 设置了你的端口号
- 设置了你的镜像磁盘映射

```java
docker run -p 9000:9000 --name minio1 \
  -e "MINIO_ACCESS_KEY=AKIAIOSFODNN7EXAMPLE" \
  -e "MINIO_SECRET_KEY=wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY" \
  -v /mnt/data:/data \
  -v /mnt/config:/root/.minio \
  minio/minio server /data
```

**2. 登录你的文件服务**


http://localhost:9000

账户密码就是你刚刚设置的MINIO_ACCESS_KEY和MINIO_SECRET_KEY。

![](http://qft6wmzla.hn-bkt.clouddn.com/picgo/20200920222529.png?picgo)

# 依赖

```java
<dependency>
    <groupId>io.minio</groupId>
    <artifactId>minio</artifactId>
    <version>7.1.2</version>
</dependency>
```

# 文件上传Demo

```java

import io.minio.MinioClient;
import min_io.Config;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class FileUploader {

    private static final String endpoint = "http://localhost:9000";
    private static final String ACCESS_KEY = "AKIAIOSFODNN7EXAMPLE";
    private static final String SECRET_KEY = "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY";
    private static final String BUCKET = "asiatrip2";

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InvalidKeyException {
        try {
            // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
            MinioClient minioClient = new MinioClient(endpoint,
                    ACCESS_KEY,
                    SECRET_KEY);

            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists(BUCKET);
            if (isExist) {
                System.out.println("Bucket already exists.");
            } else {
                // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
                minioClient.makeBucket(BUCKET);
            }

            // 使用putObject上传一个文件到存储桶中。
            String fileName = "个人课表.jpg";
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(BUCKET).object(fileName)
                            .stream(new FileInputStream("B:\\"+fileName), -1, 10485760)
                            .contentType("image/png")
                            .build());
            System.out.println(fileName+" is upload success!");
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }
}
```

# 桶操作

## 获取桶的列表

```java
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
```

##  获取某个桶中的文件

```java
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
```

# 文件操作

## 文件上传(通过本地文件上传)

```java
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
```

## 文件上传(通过流进行文件上传)

```java
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
```

## 文件下载(获取流)

```java
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import min_io.Config;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class GetDownloadFile {
    /**
     * MinioClient.getObject() example.
     */
    public static void main(String[] args)
            throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            /* play.min.io for test and development. */
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint(Config.ENDPOIN)
                            .credentials(Config.ACCESS_KEY, Config.SECRET_KEY)
                            .build();

            // 调用statObject()来判断对象是否存在。
            // 如果不存在, statObject()抛出异常,
            // 否则则代表对象存在。
            minioClient.statObject(Config.BUCKETS[0], Config.FILES[1]);

            // 获取myobject的流并保存到photo.jpg文件中。
            minioClient.getObject(Config.BUCKETS[0], Config.FILES[1], "B:\\photo.jpg");

        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }
}
```

## 文件删除

```java
import io.minio.MinioClient;
import io.minio.RemoveObjectsArgs;
import io.minio.Result;
import io.minio.errors.MinioException;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import min_io.Config;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

public class DeleteFile {
  /** MinioClient.removeObject() example removing multiple objects. */
  public static void main(String[] args)
      throws IOException, NoSuchAlgorithmException, InvalidKeyException {
    try {
      /* play.min.io for test and development. */
      MinioClient minioClient =
          MinioClient.builder()
              .endpoint(Config.ENDPOIN)
              .credentials(Config.ACCESS_KEY, Config.SECRET_KEY)
              .build();


      List<DeleteObject> objects = new LinkedList<>();
      objects.add(new DeleteObject(Config.FILES[0]));
      objects.add(new DeleteObject(Config.FILES[1]));
      Iterable<Result<DeleteError>> results =
          minioClient.removeObjects(
              RemoveObjectsArgs.builder().bucket(Config.BUCKETS[0]).objects(objects).build());
      for (Result<DeleteError> result : results) {
        DeleteError error = result.get();
        System.out.println(
            "Error in deleting object " + error.objectName() + "; " + error.message());
      }
    } catch (MinioException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}
```