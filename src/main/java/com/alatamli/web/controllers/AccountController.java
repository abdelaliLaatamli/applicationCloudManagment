package com.alatamli.web.controllers;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alatamli.web.requests.AccountRequest;
import com.alatamli.web.requests.AccountActionsRequest;
import com.alatamli.web.responses.AccountResponse;
import com.alatamli.web.responses.AccountResponseFourKeys;
import com.alatamli.web.responses.AccountResponseOneKey;
import com.alatamli.web.responses.AccountResponseTwoKeysDetails;
import com.alatamli.web.services.AccountService;
import com.alatamli.web.services.ProviderService;
import com.alatamli.web.shared.dto.AccountDto;
import com.alatamli.web.shared.dto.AccountFourKeysDto;
import com.alatamli.web.shared.dto.AccountOneKeyDto;
import com.alatamli.web.shared.dto.AccountTwoKeysDto;
import com.alatamli.web.shared.dto.ProviderDto;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	
	@Autowired
	AccountService accountService;
	
	@Autowired
	ProviderService providerService;
	
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@GetMapping(path = "provider/{providerId}")
	public ResponseEntity< List<AccountResponse> > getAccountsByProvider( @PathVariable long providerId , Principal principal ) {
		
	
		List<AccountResponse> accountsResponse = null;
		Type listType  = null ;
		
		
		ProviderDto provider = providerService.getProvider(providerId);
		
		switch (provider.getProviderKeysType()) {
			case ONEKEY:
				List<AccountOneKeyDto> accountsL = accountService.getAccountsOneKey( providerId , principal.getName());
				listType  = new TypeToken<List<AccountResponseOneKey>>() {}.getType(); 
				accountsResponse = modelMapper.map( accountsL , listType);
			
			break;
		case TWOKEYS:
				List<AccountTwoKeysDto> accountsLs = accountService.getAccountsTwoKeys( providerId , principal.getName());
				listType  = new TypeToken<List<AccountResponseTwoKeysDetails>>() {}.getType(); 
				accountsResponse = modelMapper.map( accountsLs , listType);
			break ;
		case FOURKEYS:
			List<AccountFourKeysDto> accountsList = accountService.getAccountsFourKeys( providerId , principal.getName());
			listType  = new TypeToken<List<AccountResponseFourKeys>>() {}.getType(); 
			accountsResponse = modelMapper.map( accountsList , listType);
			
			break;

		default:
			break;
		}
		
		
		return new ResponseEntity<List<AccountResponse>>( accountsResponse , HttpStatus.OK );
	}
	
	
	@GetMapping("/g/{accountId}")
	public ResponseEntity<AccountResponse> getAccount( @PathVariable long accountId ){
		
		AccountDto accountDto = accountService.getAccount( accountId );
		
		AccountResponse accountResponse = modelMapper.map(accountDto, AccountResponse.class);
		
		return new ResponseEntity<AccountResponse>( accountResponse , HttpStatus.OK );
		
	}
	
	
	@PostMapping(path = "/{providerId}")
	public ResponseEntity<AccountResponse> storeAccount( @PathVariable long providerId , @RequestBody AccountRequest accountRequest , Principal principal) {
		
		AccountResponse accountResponse = null ; 
		AccountDto newAccount = null ;
		
		switch (accountRequest.getAccountType()) {
			case ONEKEY : 
				
				AccountOneKeyDto accountOneKeyDto = modelMapper.map(accountRequest, AccountOneKeyDto.class);
				newAccount = accountService.addOneKeyAccount( accountOneKeyDto , providerId , principal.getName());
				accountResponse = modelMapper.map(newAccount, AccountResponseOneKey.class);
				
				break;
			
			case TWOKEYS:
				
				AccountTwoKeysDto accountTwoKeysDto = modelMapper.map(accountRequest, AccountTwoKeysDto.class);
				newAccount = accountService.addTwoKeysAccount( accountTwoKeysDto , providerId , principal.getName());
				accountResponse = modelMapper.map(newAccount, AccountResponseTwoKeysDetails.class);
				
				break;
				
			case FOURKEYS :
				
				AccountFourKeysDto accountFourKeysDto = modelMapper.map( accountRequest , AccountFourKeysDto.class);
				newAccount = accountService.addFourKeysAccount( accountFourKeysDto , providerId , principal.getName());
				accountResponse = modelMapper.map(newAccount, AccountResponseTwoKeysDetails.class);
				
				break;
	
			default: throw new RuntimeException("this type is not Integrated"); 
		}
		
		
		return new ResponseEntity<AccountResponse> ( accountResponse , HttpStatus.CREATED );
	}
	
	
	@PutMapping(path = "/{accountId}")
	public ResponseEntity<AccountResponse> editAccount( @PathVariable long accountId , @RequestBody AccountRequest accountRequest , Principal principal ){
		
		AccountResponse accountResponse = null ; 
		AccountDto newAccount = null ;
		
		switch (accountRequest.getAccountType()) {
			case ONEKEY : 
				
				AccountOneKeyDto accountOneKeyDto = modelMapper.map(accountRequest, AccountOneKeyDto.class);
				newAccount = accountService.editOneKeyAccount( accountId , accountOneKeyDto , principal.getName());
				accountResponse = modelMapper.map(newAccount, AccountResponseOneKey.class);
				
				break;
			
			case TWOKEYS:
				
				AccountTwoKeysDto accountTwoKeysDto = modelMapper.map(accountRequest, AccountTwoKeysDto.class);
				newAccount = accountService.editTwoKeysAccount( accountId , accountTwoKeysDto , principal.getName());
				accountResponse = modelMapper.map(newAccount, AccountResponseTwoKeysDetails.class);
				
				break;
				
			case FOURKEYS :
				
				AccountFourKeysDto accountFourKeysDto = modelMapper.map( accountRequest , AccountFourKeysDto.class);
				newAccount = accountService.editFourKeysAccount( accountId , accountFourKeysDto , principal.getName());
				accountResponse = modelMapper.map(newAccount, AccountResponseTwoKeysDetails.class);
				
				break;
	
			default: throw new RuntimeException("this type is not Integrated"); 
		}
		
		
		return new ResponseEntity<AccountResponse> ( accountResponse , HttpStatus.ACCEPTED );
		
	}
	
	@PutMapping(path = "linkaction/{accountId}")
	public ResponseEntity<AccountResponse> linkaction( @PathVariable long accountId , @RequestBody AccountActionsRequest accountActionsRequest , Principal principal ){
		
		AccountDto newAccount = accountService.updateAction( accountId , accountActionsRequest , principal.getName());
		
		AccountResponse accountResponse = modelMapper.map(newAccount, AccountResponse.class);
		
		return new ResponseEntity<AccountResponse> ( accountResponse , HttpStatus.ACCEPTED );
	}
	
	
	@DeleteMapping(path = "/{accountId}")
	public ResponseEntity<Object> deleteAccount(@PathVariable long accountId , Principal principal){
		
		
		accountService.deactivateAccount( accountId , principal.getName());
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	
	
}
