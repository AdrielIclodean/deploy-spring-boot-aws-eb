package com.cantaricrestine.aws.webservices.restfulwebservices.s3;

import com.cantaricrestine.aws.webservices.restfulwebservices.cantaricrestine.Song;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Service
public final class S3ObjectOperations {

    private static final Logger logger = LoggerFactory.getLogger(S3ObjectOperations.class);
    
    private static S3Client s3;

    public S3ObjectOperations() {
        if(s3 == null) {
            try {
                logger.info("S3 is null");
                Region region = Region.EU_CENTRAL_1;
                s3 = S3Client.builder()
                        .region(region)
                        .build();

                logger.info("S3 was initialized");
            }catch (Exception ex){
                logger.info("Got exception", ex);
            }
        }
    }

    public void uploadSong(Song song) {
        try {
            PutObjectRequest objectRequest = PutObjectRequest.builder()
                    .bucket("cantari-crestine")
                    .key(song.getAlbumName().getValue() + "/" + song.getSongName() + ".txt")
                    .build();

            logger.info("Object to put is:");
            logger.info(objectRequest.toString());
            PutObjectResponse result = s3.putObject(objectRequest, RequestBody.fromString(song.getText()));
            logger.info("Result is {}", result);
        } catch (S3Exception ex){
            logger.info("Got exception", ex);
        }
    }

}
