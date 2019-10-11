package repo_analyzer.analyzer.repo_analyzer.analyzer.sevices;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class S3Service {
 public void listBucketFiles() {
 	String bucketName = "crawley-repos";
	
    final AmazonS3 s3client = AmazonS3ClientBuilder
    		.standard()
    		.withCredentials(new EnvironmentVariableCredentialsProvider())
    		.withRegion(Regions.SA_EAST_1)
			.build();
    
    ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
    		.withBucketName(bucketName);
    
    ObjectListing objectListing;
    
    List<String> filesNames = new ArrayList<String>();
    
    do {
        objectListing = s3client.listObjects(listObjectsRequest);
        objectListing
        	.getObjectSummaries()
        	.forEach((S3ObjectSummary objectSummary) -> filesNames.add(objectSummary.getKey()));
        
        listObjectsRequest.setMarker(objectListing.getNextMarker());
    } while (objectListing.isTruncated());
 }
}
