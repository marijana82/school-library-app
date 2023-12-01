package com.marijana.library1223.services;

import com.marijana.library1223.dtos.AccountDto;
import com.marijana.library1223.models.Account;
import com.marijana.library1223.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    //createAccount method - post mapping
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = new Account();
        account.setFirstNameStudent(accountDto.getFirstNameStudent());
        account.setLastNameStudent(accountDto.getLastNameStudent());
        account.setDob(accountDto.getDob());
        account.setStudentClass(accountDto.getStudentClass());
        account.setNameOfTeacher(accountDto.getNameOfTeacher());
        accountRepository.save(account);
        accountDto.setId(account.getId());
       return accountDto;
    }

    //showAllAccounts method - get mapping (all)
    public List<AccountDto> showAllAccounts() {
        List<Account> accountList = accountRepository.findAll();
        List<AccountDto> accountDtoList = new ArrayList<>();
        for (Account account : accountList) {
            AccountDto accountDto = transferAccountToAccountDto(account);
            accountDtoList.add(accountDto);
        }
        return accountDtoList;
    }



    //helper methods ...............................................................

    //helper method - transfer Account to AccountDto
    private AccountDto transferAccountToAccountDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setFirstNameStudent(account.getFirstNameStudent());
        accountDto.setLastNameStudent(account.getLastNameStudent());
        accountDto.setDob(account.getDob());
        accountDto.setStudentClass(account.getStudentClass());
        accountDto.setNameOfTeacher(account.getNameOfTeacher());
        accountDto.setId(account.getId());
        return accountDto;
    }

    //helper method - transfer AccountDto to Account
    public Account transferAccountDtoToAccount(AccountDto accountDto) {
        Account account = new Account();
        account.setFirstNameStudent(accountDto.getFirstNameStudent());
        account.setLastNameStudent(accountDto.getLastNameStudent());
        account.setDob(accountDto.getDob());
        account.setStudentClass(accountDto.getStudentClass());
        account.setNameOfTeacher(accountDto.getNameOfTeacher());
        account.setId(accountDto.getId());
        return account;
    }




}
