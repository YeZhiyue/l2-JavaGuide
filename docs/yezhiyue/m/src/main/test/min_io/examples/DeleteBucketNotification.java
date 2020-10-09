package min_io.examples;/*
 * MinIO Java SDK for Amazon S3 Compatible Cloud Storage, (C) 2017 MinIO, Inc.
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

import io.minio.DeleteBucketNotificationArgs;

import io.minio.MinioClient;
import min_io.Config;
import io.minio.errors.MinioException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class DeleteBucketNotification {
  /** MinioClient.removeAllBucketNotification() example. */
  public static void main(String[] args)
      throws IOException, NoSuchAlgorithmException, InvalidKeyException {
    try {
      /* play.min.io for test and development. */
      MinioClient minioClient =
          MinioClient.builder()
              .endpoint(Config.ENDPOIN)
              .credentials(Config.ACCESS_KEY, Config.SECRET_KEY)
              .build();

      minioClient.deleteBucketNotification(
          DeleteBucketNotificationArgs.builder().bucket(Config.BUCKETS[0]).build());
      System.out.println("Removed all bucket notification");
    } catch (MinioException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}
