package com.alatamli.web.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alatamli.web.requests.ProviderRequest;
import com.alatamli.web.services.ProviderService;
import com.alatamli.web.shared.dto.ProviderDto;


@RestController
@RequestMapping("/providers")
public class ProvidersController {

	@Autowired
	ProviderService providerService;
	
	
	@GetMapping
	public List<ProviderDto> getProviders( Principal principal ) {
		
		List<ProviderDto> providers = providerService.getProviders( principal.getName() );
		
		return providers;
	}
	
	
	@PostMapping
	public ProviderDto storeProvider( @RequestBody ProviderRequest providerRequest ) {
		
		ProviderDto providerDto = providerService.addProvider( providerRequest );
		
		return providerDto;
	}
	
}
