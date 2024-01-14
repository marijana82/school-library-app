package com.marijana.library1223.controllers;

import com.marijana.library1223.dtos.AccountDto;
import com.marijana.library1223.services.AccountService;
import com.marijana.library1223.services.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
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

        if(bindingResult.hasFieldErrors()) {

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

    //3.(all in general + all belonging to the same class)
    //add authentication principal
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(@RequestParam(value="studentClass", required=false) Optional<String> studentClass) {
        List<AccountDto> accountDtos;

        if(studentClass.isEmpty()){
            accountDtos = accountService.showAllAccounts();
        } else {
            accountDtos = accountService.showAllAccountsByStudentClass(studentClass.get());
        }
        return ResponseEntity.ok().body(accountDtos);
    }


    //4.get-mapping-one (specific id)
    //add authentication principal
    @GetMapping("/{idAccount}")
    public ResponseEntity<AccountDto> getOneAccount(
            @PathVariable Long idAccount,
            @AuthenticationPrincipal UserDetails userDetails) throws AccessDeniedException {

        if(userDetails.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("STUDENT"))) {
            AccountDto accountDto = accountService.showOneAccount(idAccount);
            return ResponseEntity.ok(accountDto);
        } else {
            throw new AccessDeniedException("It seems you are not authorized to access this account.");
        }
    }


    //5.put-mapping
    @PutMapping("/{idAccount}")
    //add authentication principal
    public ResponseEntity<AccountDto> fullUpdateAccount(@PathVariable Long idAccount, @Valid @RequestBody AccountDto accountDto) {
        AccountDto accountDto1 = accountService.updateOneAccount(idAccount, accountDto);
        return ResponseEntity.ok().body(accountDto1);
    }


    //6.patch-mapping
    //add authentication principal
    @PatchMapping("/{idAccount}")
    public ResponseEntity<AccountDto> partialUpdateAccount(@PathVariable Long idAccount, @Valid @RequestBody AccountDto accountDto) {
        AccountDto accountDto1 = accountService.updateAccountPartially(idAccount, accountDto);
        return ResponseEntity.ok().body(accountDto1);
    }


    //7.delete-mapping
    @DeleteMapping("/{idAccount}")
    public ResponseEntity<Object> deleteOneAccount(@PathVariable Long idAccount) {
        accountService.deleteById(idAccount);
        return ResponseEntity.noContent().build();
    }







}
