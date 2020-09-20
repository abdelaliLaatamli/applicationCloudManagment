package com.alatamli.web.controllers;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alatamli.web.requests.ProviderRequest;
import com.alatamli.web.responces.ProviderResponseDetails;
import com.alatamli.web.services.ProviderService;
import com.alatamli.web.shared.dto.ProviderDto;


@RestController
@RequestMapping("/providers")
public class ProvidersController {

	@Autowired
	ProviderService providerService;
	
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<List<ProviderResponseDetails>> getProviders( Principal principal ) {
		
		List<ProviderDto> providers = providerService.getProviders( principal.getName() );
		
		//ProviderResponseDetails providerResponse = modelMapper.map(providers, ProviderResponseDetails.class);
		
		Type listType  = new TypeToken<List<ProviderResponseDetails>>() {}.getType(); 
		List<ProviderResponseDetails> providersResponse = modelMapper.map( providers , listType);
		
		return new ResponseEntity<List<ProviderResponseDetails>> ( providersResponse , HttpStatus.OK );
	}
	
	
	@PostMapping
	public ProviderDto storeProvider( @RequestBody ProviderRequest providerRequest ) {
		
		ProviderDto providerDto = providerService.addProvider( providerRequest );
		
		return providerDto;
	}
	
}
