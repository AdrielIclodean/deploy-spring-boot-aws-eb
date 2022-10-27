package com.cantari.crestine;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
public class HelloWorldController {

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        S3Client client = S3Client.builder().region(Region.EU_CENTRAL_1).build();
        listBucketObjects(client, "cantari-crestine");
        //throw new RuntimeException("Some Error has Happened! Contact Support at ***-***");
        return new HelloWorldBean("Hello World - Changed");
    }

    ///hello-world/path-variable/in28minutes
    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

    public static void listBucketObjects(S3Client s3, String bucketName) {

        try {
            ListObjectsRequest listObjects = ListObjectsRequest
                    .builder()
                    .bucket(bucketName)
                    .build();

            ListObjectsResponse res = s3.listObjects(listObjects);
            List<S3Object> objects = res.contents();
            for (S3Object myValue : objects) {
                System.out.print("\n The name of the key is " + myValue.key());
                System.out.print("\n The object is " + calKb(myValue.size()) + " KBs");
                System.out.print("\n The owner is " + myValue.owner());
                GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                        .bucket(bucketName)
                        .key(myValue.key())
                        .build();

                ResponseInputStream<GetObjectResponse> initialStream = s3.getObject(getObjectRequest);
                File targetFile = new File("src/main/resources/targetFile.tmp");

                try {
                    targetFile.createNewFile();
                    java.nio.file.Files.copy(
                            initialStream,
                            targetFile.toPath(),
                            StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        initialStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

//					IOUtils.closeQuietly(initialStream);
            }

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }

    //convert bytes to kbs.
    private static long calKb(Long val) {
        return val / 1024;
    }
}
