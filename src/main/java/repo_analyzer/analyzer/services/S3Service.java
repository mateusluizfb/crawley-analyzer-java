package repo_analyzer.analyzer.services;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class S3Service {
	final AmazonS3 s3Client;
	private String bucketName = "crawley-repos";
	
	public S3Service() {
		s3Client = AmazonS3ClientBuilder
		  		.standard()
		  		.withCredentials(new EnvironmentVariableCredentialsProvider())
		  		.withRegion(Regions.SA_EAST_1)
				.build();
	}
	
	public List<String> listBucketFiles() {

	  ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
	  		.withBucketName(bucketName);

	  ObjectListing objectListing;

	  List<String> fileNames = new ArrayList<String>();

	  do {
	      objectListing = s3Client.listObjects(listObjectsRequest);
	      objectListing
	      	.getObjectSummaries()
	      	.forEach((S3ObjectSummary objectSummary) -> fileNames.add(objectSummary.getKey()));

	      listObjectsRequest.setMarker(objectListing.getNextMarker());
	  } while (objectListing.isTruncated());

	  return fileNames;
	}

	public void downloadFile(String fileName, String outputFolder) throws IOException {
	 S3Object object = s3Client.getObject(new GetObjectRequest(bucketName, fileName));
	 InputStream reader = new BufferedInputStream(object.getObjectContent());
	 File file = new File(outputFolder + fileName);      
	 OutputStream writer = new BufferedOutputStream(new FileOutputStream(file));

	 int read = -1;

	 while ((read = reader.read()) != -1 ) {
	     writer.write(read);
	 }

	 writer.flush();
	 writer.close();
	 reader.close();
	}
}
