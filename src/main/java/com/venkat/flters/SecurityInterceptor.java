package com.venkat.flters;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.ext.Provider;

@Provider
public class SecurityInterceptor implements javax.ws.rs.container.ContainerRequestFilter {
	@Override
	public void filter(ContainerRequestContext requestContext) {
		System.out.println("Security filter..");
	}
}
