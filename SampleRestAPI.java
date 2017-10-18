package com.Rest.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class SampleRestAPI {
	
	@Test
	public void GetWeatherDetails()
	{   
		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequest = RestAssured.given();

		// Make a request to the server by specifying the method Type and the method URL.
		// This will return the Response from the server. Store the response in a variable.
		Response response = httpRequest.request(Method.GET, "/Hyderabad");
		System.out.println("City is "+response.jsonPath().getString("City"));
		// Now let us print the body of the message to see what response
		// we have recieved from the server
		String responseBody = response.getBody().asString();
		
		System.out.println("Response Body is =>  " + responseBody);

	}
	
	
	//@Test
	public void orderfulfilment() throws IOException{
		
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/octet-stream");
		RequestBody body = RequestBody.create(mediaType, "{\r\n    \"orderNumber\": \"123\",\r\n\"oracleCustomerId\": \"6003711902\",\r\n\"oracleCustomerName\": \"akshaya5\",\r\n\"itContactEmail\": \"akshaya5@mailinator.com\",\r\n\"itContactFirstName\": \"akshaya5\",\r\n\"itContactLastName\": \"\",\r\n\"fulfillmentStepId\": \"1234\" ,\r\n\"requestSubmittedBy\": \"apanigrahi@blackberry.com\",\r\n\"itemList\": [{\r\n\"appId\": \"a3x16000000U0fS\"\r\n}\r\n]\r\n}");
		Request request = new Request.Builder()
		  .url("https://gecos-qa46.good.com/gs/uniteOrderfulfillment")
		  .post(body)
		  .addHeader("authorization", "Basic Z2RuLXBjZS1zZXJ2ZXIxQGdvb2QuY29tOjVmODY3NGEyLWI1ODMtNGIwMi1iMjBjLWNmMjA2YTNmNmU0MA==")
		  .addHeader("cache-control", "no-cache")
		  .addHeader("postman-token", "9e329fd5-4845-5231-533a-c5e82785b540")
		  .build();

		com.squareup.okhttp.Response response = client.newCall(request).execute();
		System.out.println(response);
	}

}
