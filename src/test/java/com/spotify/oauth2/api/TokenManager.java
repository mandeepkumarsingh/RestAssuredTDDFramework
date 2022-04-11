package com.spotify.oauth2.api;

import java.time.Instant;
import java.util.HashMap;

import javax.management.RuntimeErrorException;

import com.spotify.oauth2.utils.ConfigFactory;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TokenManager {
	
	private static String access_token ;
	private static Instant expiryTime;
	
	
	public static synchronized String getToken() {
		try {
			System.out.println("Renewing Token ....");
			if(access_token==null || Instant.now().isAfter(expiryTime)) {
			Response res=renewToken();
			access_token=res.path("access_token");
			int expiryTimeInsecond=res.path("expires_in");
			expiryTime=Instant.now().plusSeconds(expiryTimeInsecond-300);
			}else {
				
				System.out.println("token is good to use");
			}
			
		} catch (Exception e) {
			throw new RuntimeException("Abort !!! Failed to get the token");
		}
		
		return access_token;
		
	}

	private static  Response renewToken() {

		HashMap<String, String> formdata=new HashMap<String, String>();
		formdata.put("grant_type", ConfigFactory.getconfig().grant_type());
		formdata.put("refresh_token", ConfigFactory.getconfig().refresh_token());
		formdata.put("client_id", ConfigFactory.getconfig().client_id());
		formdata.put("client_secret",ConfigFactory.getconfig().client_secret());
		Response response=RestResources.postAccount(formdata);
		if(response.statusCode()!=200) {

			throw new RuntimeException("Abort !! Renew Token Failed ");
		}
		return response;



	}

}
