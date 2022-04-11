package com.spotify.oauth2.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;


@Builder
@Data
@Jacksonized
public class Error {

	@JsonProperty("error")
	private InnerError error;

	
	
}
