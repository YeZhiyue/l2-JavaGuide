package min_io.examples;/*
 * MinIO Java SDK for Amazon S3 Compatible Cloud Storage, (C) 2020 MinIO, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import io.minio.GetObjectTagsArgs;

import io.minio.MinioClient;
import min_io.Config;
import io.minio.errors.MinioException;
import io.minio.messages.Tags;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class GetObjectTags {
  /** MinioClient.getObjectTags() example. */
  public static void main(String[] args)
      throws IOException, NoSuchAlgorithmException, InvalidKeyException {
    try {
      /* play.min.io for test and development. */
      MinioClient minioClient =
          MinioClient.builder()
              .endpoint(Config.ENDPOIN)
              .credentials(Config.ACCESS_KEY, Config.SECRET_KEY)
              .build();

      /* Amazon S3: */
      // MinioClient minioClient =
      //     MinioClient.builder()
      //         .endpoint("https://s3.amazonaws.com")
      //         .credentials("YOUR-ACCESSKEY", "YOUR-SECRETACCESSKEY")
      //         .build();

      Tags tags =
          minioClient.getObjectTags(
              GetObjectTagsArgs.builder().bucket(Config.BUCKETS[0]).object("my-objectname").build());
      System.out.println("Object tags: " + tags.get());
    } catch (MinioException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}
