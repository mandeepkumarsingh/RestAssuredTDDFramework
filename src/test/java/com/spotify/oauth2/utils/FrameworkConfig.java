package com.spotify.oauth2.utils;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({ "system:properties", "system:env", "file:${user.dir}/src/test/resources/config.properties" })

public interface FrameworkConfig extends Config {

	String client_id();

	String client_secret();

	String grant_type();

	String refresh_token();

	String user_id();

}
