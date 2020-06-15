package com.example.me.endpoint;

import com.example.me.*;
import com.example.me.service.BundleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class BundleEndpoint {
	private static final String NAMESPACE_URI = "http://example.com/me";
	
	@Autowired
    private BundleService bundleService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBundleRequest")
	@ResponsePayload
	public GetBundleResponse getBundle(@RequestPayload GetBundleRequest request) {
		GetBundleResponse response = new GetBundleResponse();
		response.setBundle(bundleService.getBundle(request.getId()));
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addBundleRequest")
	@ResponsePayload
	public AddBundleResponse addBundle(@RequestPayload AddBundleRequest request) {
		AddBundleResponse response = new AddBundleResponse();
		response.setMessage(bundleService.addBundle(request.getId(), request.getName(), request.getPrice()));
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteBundleRequest")
	@ResponsePayload
	public DeleteBundleResponse deleteBundle(@RequestPayload DeleteBundleRequest request) {
		DeleteBundleResponse response = new DeleteBundleResponse();
		response.setMessage(bundleService.deleteBundle(request.getId()));
		return response;
	}
}
