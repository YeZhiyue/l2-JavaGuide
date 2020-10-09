package min_io.文件操作;/*
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
