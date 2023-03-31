package com.cantaricrestine.aws.webservices.restfulwebservices.cantaricrestine;

import com.cantaricrestine.aws.webservices.restfulwebservices.s3.S3ObjectOperations;

public class TestAwz {
    public static void main(String[] args) {
        new S3ObjectOperations().uploadSong(new Song(AlbumNames.BBSO, "abc", "this is the text"));
    }
}
