package com.bhavna.java.testClass;

import java.io.IOException;
import java.time.LocalDate;
import org.testng.Assert;

import com.bhavna.java.commonmethods.API_Common_Method;
import com.bhavna.java.repository.Post_req_repository;

import io.restassured.path.json.JsonPath;

public class Post_Tc1 {
	
	public static void execute() throws IOException {
		
			
			int res_Status_Code = API_Common_Method.response_StatusCode(Post_req_repository.base_URI(),
					Post_req_repository.post_resource(), Post_req_repository.post_TC1());
			
			if(res_Status_Code == 201) {
			
			String response_Body = API_Common_Method.response_Body (Post_req_repository.base_URI(),
					Post_req_repository.post_resource(), Post_req_repository.post_TC1());
			
			System.out.println(res_Status_Code);
			System.out.println(response_Body);
				
			// Parse the response body
			JsonPath jsp = new JsonPath(response_Body);
			String res_name = jsp.getString("name");
			String res_job = jsp.getString("job");
			String res_id = jsp.getString("id");
			String res_createdAt=jsp.getString("createdAt"); 
			String currentdate=LocalDate.now().toString();
			
			System.out.println(currentdate);
			
			// Validate the response body parameters
			Assert.assertEquals(res_name, "bhavna");
			Assert.assertEquals(res_job, "test");
			Assert.assertNotNull(res_id);
			Assert.assertEquals(res_createdAt.substring(0,10), currentdate);
				
			}
	}
}

