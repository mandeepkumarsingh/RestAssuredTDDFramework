package com.spotify.oauth2.api;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import com.spotify.oauth2.pojo.Playlist;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestResources {

	public static Response post(String path, String token, Object requestPlaylist) {

		return given(specBuilder.getRequestSpec()).auth().oauth2(token).body(requestPlaylist)
				.when().post(path).then().spec(specBuilder.getResponseSpec()).extract().response();

	}

	public static Response get(String path, String token) {

		return given(specBuilder.getRequestSpec()).auth().oauth2(token).when().get(path).then()
				.spec(specBuilder.getResponseSpec()).extract().response();
	}

	public static Response put(String path, String token, Object requestPlaylist) {

		return given(specBuilder.getRequestSpec()).auth().oauth2(token).body(requestPlaylist)
				.when().put(path).then().spec(specBuilder.getResponseSpec()).extract().response();

	}

	public static Response postAccount(HashMap<String, String> formdata) {
		return RestAssured.given(specBuilder.getAccountRequestSpec()).formParams(formdata).when().post("/api/token")
				.then().spec(specBuilder.getResponseSpec()).extract().response();

	}

}
