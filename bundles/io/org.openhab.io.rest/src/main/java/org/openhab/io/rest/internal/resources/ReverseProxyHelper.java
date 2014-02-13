package org.openhab.io.rest.internal.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriBuilder;

public class ReverseProxyHelper {
	public static UriBuilder prefixBuilder(UriBuilder baseBuilder, HttpHeaders headers){
		List<String> reverseProxy = headers.getRequestHeader("X-Forwarded-Path");
		if(reverseProxy == null || reverseProxy.isEmpty()) return baseBuilder;
		String basePath = baseBuilder.build().getPath();
		return baseBuilder.replacePath(reverseProxy.get(0)).path(basePath);
    }
	public static URI prefixUri(URI baseUri, HttpHeaders headers){
		List<String> reverseProxy = headers.getRequestHeader("X-Forwarded-Path");
		if(reverseProxy == null || reverseProxy.isEmpty()) return baseUri;
		String basePath = baseUri.getPath();
		return UriBuilder.fromUri(baseUri).replacePath(reverseProxy.get(0)).path(basePath).build();
	}
}
