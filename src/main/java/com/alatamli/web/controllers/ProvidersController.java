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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alatamli.web.requests.ProviderAttachRequest;
import com.alatamli.web.requests.ProviderRequest;
import com.alatamli.web.responses.ProviderResponseDetails;
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
		
		Type listType  = new TypeToken<List<ProviderResponseDetails>>() {}.getType(); 
		List<ProviderResponseDetails> providersResponse = modelMapper.map( providers , listType);
		
		return new ResponseEntity<List<ProviderResponseDetails>> ( providersResponse , HttpStatus.OK );
	}
	
	
	@PostMapping
	public ResponseEntity<ProviderResponseDetails> storeProvider( @RequestBody ProviderRequest providerRequest ) {
		
		ProviderDto provider = modelMapper.map(providerRequest, ProviderDto.class);
		
		ProviderDto providerDto = providerService.addProvider( provider );
		
		ProviderResponseDetails providerResponse= modelMapper.map(providerDto, ProviderResponseDetails.class);
		
		return new ResponseEntity<ProviderResponseDetails>(providerResponse , HttpStatus.CREATED);
	}
	
	
	@PutMapping("/{providerId}")
	public ResponseEntity<ProviderResponseDetails> updateProvider( @PathVariable("providerId") long providerId , @RequestBody ProviderRequest providerRequest ) {
		
		ProviderDto provider = modelMapper.map(providerRequest, ProviderDto.class); 
		
		ProviderDto providerDto = providerService.updateProvider( providerId , provider );
		
		
		ProviderResponseDetails providerResponse= modelMapper.map(providerDto, ProviderResponseDetails.class);
		
		return new ResponseEntity<ProviderResponseDetails>( providerResponse , HttpStatus.ACCEPTED );
	}
	
	@PutMapping("/{providerId}/entity/attach")
	public ResponseEntity<ProviderResponseDetails> attachEntitiesToProvider(
				@PathVariable("providerId") long providerId ,
				@RequestBody ProviderAttachRequest entityList 
	){
			
		ProviderDto providerDto = providerService.attachProviderToEntity( providerId , entityList );
		
		ProviderResponseDetails providerResponse= modelMapper.map(providerDto, ProviderResponseDetails.class);
		
		return new ResponseEntity<ProviderResponseDetails>( providerResponse , HttpStatus.ACCEPTED );
	}
	
	@PutMapping("/{providerId}/entity/deattach")
	public ProviderDto deattachEntitiesToProvider( @PathVariable("providerId") long providerId , @RequestBody ProviderRequest providerRequest ){
		
		ProviderDto provider = modelMapper.map(providerRequest, ProviderDto.class); 
		
		return provider ;
		
	}
	
	
	
	
	
}
