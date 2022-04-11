package com.spotify.oauth2.api.applicationApi;

import static io.restassured.RestAssured.given;

import com.spotify.oauth2.api.RestResources;
import static  com.spotify.oauth2.api.Routes.*;
import com.spotify.oauth2.api.TokenManager;
import com.spotify.oauth2.api.specBuilder;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigFactory;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class PlaylistApi {

	static String access_token = TokenManager.getToken();
	@Step
	public static Response post(Playlist requestPlaylist) {

		return RestResources.post(USERS+"/"+ConfigFactory.getconfig().user_id()+PLAYLISTS, access_token, requestPlaylist);

	}

	@Step

	public static Response get(String playlistId) {

		return RestResources.get(PLAYLISTS+"/" + playlistId, access_token);

	}
	@Step
	public static Response put(String playlistId, Playlist requestPlaylist) {

		return RestResources.put(PLAYLISTS+"/" + playlistId, access_token, requestPlaylist);

	}
	@Step
	public static Response post(Playlist requestPlaylist, String Invalid_token) {

		return RestResources.post(USERS+"/"+ConfigFactory.getconfig().user_id()+PLAYLISTS, Invalid_token, requestPlaylist);

	}

}
