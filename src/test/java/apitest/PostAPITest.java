package apitest;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostAPITest {
	
	PostCommentData commentData = new PostCommentData();
	
	@Test
	public  void createComment() {
		String randomComment = RandomStringUtils.randomAlphabetic(6);
		
		   int randomPostId=  new Random().nextInt(50);
		   
		   System.out.println(randomComment);
		   System.out.println(randomPostId);
		commentData.setBody(randomComment);
		commentData.setPostId(randomPostId);
		
		RestAssured.baseURI = "http://localhost:3000";

		Response res = RestAssured.given()
				.contentType(ContentType.JSON)
				.body(commentData)
				.when()
				.post("/comments");
		
		System.out.println(res.asPrettyString());
		
	}


}
