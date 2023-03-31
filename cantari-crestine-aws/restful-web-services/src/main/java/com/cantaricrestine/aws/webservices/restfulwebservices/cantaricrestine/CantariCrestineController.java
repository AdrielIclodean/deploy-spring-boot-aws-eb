package com.cantaricrestine.aws.webservices.restfulwebservices.cantaricrestine;

import com.cantaricrestine.aws.webservices.restfulwebservices.s3.S3ObjectOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class CantariCrestineController {

	private static final Logger logger = LoggerFactory.getLogger(CantariCrestineController.class);

	@Autowired
	private S3ObjectOperations s3ObjectOperations;

	@PostMapping("/addNewSong")
	public ResponseEntity<Void> uploadNewSong(@RequestBody Song song){
		logger.info("entered with song " + song);

		s3ObjectOperations.uploadSong(song);
		return ResponseEntity.created(URI.create("welcome")).build();
	}
}
