package com.marijana.library1223.controllers;

import com.marijana.library1223.dtos.AccountDto;
import com.marijana.library1223.services.AccountService;
import com.marijana.library1223.services.ReservationService;
import jakarta.validation.Valid;
import org.hibernate.query.sqm.function.SelfRenderingFunctionSqlAstExpression;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    //1.constructor injection
    private final AccountService accountService;

    public AccountController(AccountService accountService, ReservationService reservationService) {
        this.accountService = accountService;
    }

    //2.post-mapping
    @PostMapping
    public ResponseEntity<Object> createNewAccount(@Valid @RequestBody AccountDto accountDto, BindingResult bindingResult) {
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

    //3.get-mapping-all (all in general + all belonging to the same class)
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
    @GetMapping("/{idAccount}")
    public ResponseEntity<AccountDto> getOneAccount(@PathVariable Long idAccount) {
        AccountDto accountDto = accountService.showOneAccount(idAccount);
        return ResponseEntity.ok(accountDto);
    }


    //5.put-mapping
    @PutMapping("/{idAccount}")
    public ResponseEntity<AccountDto> fullUpdateAccount(@PathVariable Long idAccount, @Valid @RequestBody AccountDto accountDto) {
        AccountDto accountDto1 = accountService.updateOneAccount(idAccount, accountDto);
        return ResponseEntity.ok().body(accountDto1);
    }


    //6.patch-mapping       //TODO: CHECK IF I am testing it correctly?
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
