package com.spotify.oauth2.utils;

import org.aeonbits.owner.ConfigCache;


public class ConfigFactory {
	
private ConfigFactory() {}
	
	public static FrameworkConfig getconfig() {
		
		return ConfigCache.getOrCreate(FrameworkConfig.class);
		
	}

}
