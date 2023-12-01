package com.marijana.library1223.controllers;

import com.marijana.library1223.dtos.AccountDto;
import com.marijana.library1223.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
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
    //add @Valid and BindingResult
    public ResponseEntity<Object> createNewAccount(@Valid @RequestBody AccountDto accountDto, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()) {
            //create a string which we return as body
            StringBuilder stringBuilder = new StringBuilder();
            for(FieldError fieldError : bindingResult.getFieldErrors()) {
                stringBuilder.append(fieldError.getField());
                stringBuilder.append(" : ");
                stringBuilder.append(fieldError.getDefaultMessage());
                stringBuilder.append(("\n"));
            }
            return ResponseEntity.badRequest().body(stringBuilder.toString());
        }

        accountService.createAccount(accountDto);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + accountDto.getId())
                .toUriString());
        return ResponseEntity.created(uri).body(accountDto);
    }

    //3.get-mapping-all
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
            return ResponseEntity.ok(accountService.showAllAccounts());
    }



    //4.get-mapping-one
    @GetMapping("/{idAccount}")
    public ResponseEntity<AccountDto> getOneAccount(@PathVariable Long idAccount) {
        AccountDto accountDto = accountService.showOneAccount(idAccount);
        return ResponseEntity.ok(accountDto);
    }

    //5.put-mapping
    @PutMapping("/{idAccount}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Long idAccount, @Valid @RequestBody AccountDto accountDto) {
        AccountDto accountDto1 = accountService.updateOneAccount(idAccount, accountDto);
        return ResponseEntity.ok().body(accountDto1);
    }


    //6.patch-mapping

    //7.delete-mapping
    @DeleteMapping("/{idAccount}")
    public ResponseEntity<Object> deleteOneAccount(@PathVariable Long idAccount) {
        accountService.deleteById(idAccount);
        return ResponseEntity.noContent().build();
    }







}
