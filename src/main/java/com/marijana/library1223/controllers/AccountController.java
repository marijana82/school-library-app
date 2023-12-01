package com.marijana.library1223.controllers;

import com.marijana.library1223.dtos.AccountDto;
import com.marijana.library1223.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    //1.constructor injection
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //2.post-mapping
    @PostMapping
    //add @Valid and BindingResult!!
    public ResponseEntity<?> createNewAccount(@RequestBody AccountDto accountDto) {
        accountService.createAccount(accountDto);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + accountDto.getId())
                .toUriString());
        return ResponseEntity.created(uri).body(accountDto);
    }

    //3.get-mapping
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return ResponseEntity.ok(accountService.showAllAccounts);
    }




}
