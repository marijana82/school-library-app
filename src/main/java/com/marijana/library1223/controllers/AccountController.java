package com.marijana.library1223.controllers;

import com.marijana.library1223.configuration.HandleBindingErrors;
import com.marijana.library1223.dtos.AccountDto;

import com.marijana.library1223.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Object> createNewAccount(
            @Valid @RequestBody AccountDto accountDto,
            BindingResult bindingResult) {

        ResponseEntity<Object> bindingErrorResponse = HandleBindingErrors.handleBindingErrors(bindingResult);

        if (bindingErrorResponse == null) {
            return bindingErrorResponse;
        }

        accountService.createAccount(accountDto);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + accountDto.getId())
                .toUriString());
        return ResponseEntity.created(uri).body(accountDto);
    }


    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(
            @RequestParam(value="studentClass", required=false) Optional<String> studentClass) {

        List<AccountDto> accountDtos;

        if(studentClass.isEmpty()){
            accountDtos = accountService.showAllAccounts();

        } else {
            accountDtos = accountService.showAllAccountsByStudentClass(studentClass.get());
        }

        return ResponseEntity.ok().body(accountDtos);
    }


    @GetMapping("/{idAccount}")
    public ResponseEntity<Object> getOneAccount(
            @PathVariable Long idAccount) {

            AccountDto accountDto = accountService.showOneAccount(idAccount);
            return ResponseEntity.ok(accountDto);

    }

    @PutMapping("/{idAccount}")
    public ResponseEntity<AccountDto> fullUpdateAccount(
            @PathVariable Long idAccount,
            @Valid @RequestBody AccountDto accountDto) {

            AccountDto accountDto1 = accountService.updateOneAccount(idAccount, accountDto);
            return ResponseEntity.ok().body(accountDto1);

    }


    @PatchMapping("/{idAccount}")
    public ResponseEntity<AccountDto> partialUpdateAccount (
            @PathVariable Long idAccount,
            @Valid @RequestBody AccountDto accountDto) {

            AccountDto accountDto1 = accountService.updateAccountPartially(idAccount, accountDto);
            return ResponseEntity.ok().body(accountDto1);

    }



    @DeleteMapping("/{idAccount}")
    public ResponseEntity<Object> deleteOneAccount(@PathVariable Long idAccount) {
        accountService.deleteById(idAccount);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{idAccount}/users/{username}")
    public ResponseEntity<Object> assignUserToAccount(@PathVariable Long idAccount, @PathVariable String username) {
        accountService.assignUserToAccount(idAccount, username);
        return ResponseEntity.noContent().build();
    }

}
