package min_io.examples;/*
 * Minio Java SDK for Amazon S3 Compatible Cloud Storage, (C) 2019 Minio, Inc.
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

import io.minio.*;
import io.minio.errors.MinioException;
import min_io.Config;

import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class ComposeObject {
  /** MinioClient.composeObject() example. */
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

      {
        // Create a ComposeSource to compose Object.
        List<ComposeSource> sources = new ArrayList<ComposeSource>();
        sources.add(
            ComposeSource.builder()
                .bucket(Config.BUCKETS[0])
                .object(Config.FILES[0])
                .build());
        sources.add(
            ComposeSource.builder()
                .bucket(Config.BUCKETS[1])
                .object(Config.FILES[1])
                .build());

        minioClient.composeObject(
            ComposeObjectArgs.builder()
                .bucket("my-destination-bucket")
                .object("my-destination-object")
                .sources(sources)
                .build());
        System.out.println("Object Composed successfully");
      }

      {
        ServerSideEncryptionCustomerKey srcSsec =
            new ServerSideEncryptionCustomerKey(
                new SecretKeySpec(
                    "01234567890123456789012345678901".getBytes(StandardCharsets.UTF_8), "AES"));

        ServerSideEncryption sse =
            new ServerSideEncryptionCustomerKey(
                new SecretKeySpec(
                    "12345678912345678912345678912345".getBytes(StandardCharsets.UTF_8), "AES"));

        List<ComposeSource> sources = new ArrayList<ComposeSource>();
        sources.add(
            ComposeSource.builder()
                .bucket(Config.BUCKETS[0])
                .object(Config.FILES[0])
                .ssec(srcSsec)
                .build());
        sources.add(
            ComposeSource.builder()
                .bucket(Config.BUCKETS[0])
                .object(Config.FILES[1])
                .ssec(srcSsec)
                .build());

        minioClient.composeObject(
            ComposeObjectArgs.builder()
                .bucket("my-destination-bucket")
                .object("my-destination-object")
                .sources(sources)
                .sse(sse)
                .build());
        System.out.println("Object Composed successfully");
      }

    } catch (MinioException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}
