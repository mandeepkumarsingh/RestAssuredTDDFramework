package com.spotify.oauth2.tests;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.hamcrest.core.Is;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.specBuilder;
import com.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.pojo.Playlist.PlaylistBuilder;
import com.spotify.oauth2.utils.FakerUtils;

import static io.restassured.RestAssured.given;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;


@Epic("Spotify Oath 2.0")
@Feature("Playlist API")
public class PlaylistTests extends BaseTest {
    @Story("Crate a playlist Story")
	@Description("This test case takes the input from users and creates the paylist on basis of that")
	@Link("https://example.org")
	@Link(name = "allure", type = "mylink")
	@Issue("LKUI-5662")
	@Test(description = "Should be able To create the Playlist")
	public void shouldBeAbleToCreateAPlaylist() {

		Playlist requestPlaylist = playlistBuilder(FakerUtils.generateName(),FakerUtils.generateDescription(), false);
		Response response = PlaylistApi.post(requestPlaylist);
		assertThat(response.statusCode(), equalTo(StatusCode.CODE_201.code));
		assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);

	}

	@Test
	public void shouldBeAbleTogetAPlaylist() {

		Playlist requestPlaylist = playlistBuilder("New Playlist", "New playlist description", false);
		Response response = PlaylistApi.get("2jWHUxYSQTrqVOmfTZJm1k");
		assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.code));
		assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);

	}

	@Test
	public void shouldBeAbleToUpdateAPlaylist() {

		Playlist requestPlaylist = playlistBuilder("New Playlist", "New playlist description", false);
		Response response = PlaylistApi.put("1CnAdpjsCysZ2MLfbpzWLu", requestPlaylist);
		assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.code));

	}
	@Story("Crate a playlist Story")
	@Test
	public void shouldNotBeAbleToCreateAPlaylist() {

		Playlist requestPlaylist = playlistBuilder("", FakerUtils.generateDescription(), false);
		Response response = PlaylistApi.post(requestPlaylist);
		assertThat(response.statusCode(), equalTo(StatusCode.CODE_400.code));

		assertError(response.as(Error.class), StatusCode.CODE_400);
	}
	@Story("Crate a playlist Story")
	@Test
	public void shouldNotBeAbleToCreateAPlaylistWithExpiredToken() {

		Playlist requestPlaylist = playlistBuilder(FakerUtils.generateName(),FakerUtils.generateDescription(), false);

		Response response = PlaylistApi.post(requestPlaylist, "1234aswer");
		assertThat(response.statusCode(), equalTo(401));
		assertError(response.as(Error.class),StatusCode.CODE_401);

	}

	@Step
	public static Playlist playlistBuilder(String name, String description, boolean _public) {

		return Playlist.builder().name(name).description(description)._public(_public).build();

	}
	@Step
	public static void assertPlaylistEqual(Playlist responsePlaylist, Playlist requestPlaylist) {

		assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
		assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
		assertThat(responsePlaylist.get_public(), equalTo(requestPlaylist.get_public()));
	}
	@Step
	public static void assertError(Error error, StatusCode statusCode) {

		assertThat(error.getError().getStatus(), equalTo(statusCode.code));
		assertThat(error.getError().getMessage(), equalTo(statusCode.msg));

	}

}
