package com.marijana.library1223.services;

import com.marijana.library1223.dtos.AccountDto;
import com.marijana.library1223.exceptions.IdNotFoundException;
import com.marijana.library1223.exceptions.RecordNotFoundException;
import com.marijana.library1223.exceptions.ResourceAlreadyExistsException;
import com.marijana.library1223.models.Account;
import com.marijana.library1223.models.User;
import com.marijana.library1223.repositories.AccountRepository;
import com.marijana.library1223.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public AccountService(
            AccountRepository accountRepository,
            UserRepository userRepository,
            UserService userService) {

        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public AccountDto createAccount(AccountDto accountDto) {

        if(accountRepository.existsByFirstNameStudentIgnoreCaseAndLastNameStudentIgnoreCase(accountDto.getFirstNameStudent(), accountDto.getLastNameStudent())) {
            throw new ResourceAlreadyExistsException("Account already exists!");

        } else {
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
    }


    public List<AccountDto> showAllAccounts() {
        List<Account> accountList = accountRepository.findAll();
        List<AccountDto> accountDtoList = new ArrayList<>();

        for (Account account : accountList) {
            AccountDto accountDto = transferAccountToAccountDto(account);

            if(account.getUser() !=null) {
                accountDto.setUserDto(userService.transferUserToUserDto(account.getUser()));
            }
            accountDtoList.add(accountDto);
        }
        return accountDtoList;
    }


    public List<AccountDto> showAllAccountsByStudentClass(String studentClass) {
        List<Account> accountList = accountRepository.findAllAccountsByStudentClassEqualsIgnoreCase(studentClass);
        List<AccountDto> accountDtoList = new ArrayList<>();
        for(Account account : accountList) {
            AccountDto accountDto = transferAccountToAccountDto(account);

            if(account.getUser() !=null) {
                accountDto.setUserDto(userService.transferUserToUserDto(account.getUser()));
            }
            accountDtoList.add(accountDto);
        }
        return accountDtoList;
     }


    public AccountDto showOneAccount(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);

        if(optionalAccount.isPresent()) {
            Account accountFound = optionalAccount.get();
            AccountDto accountDto = transferAccountToAccountDto(accountFound);

            if(accountFound.getUser() !=null) {
                accountDto.setUserDto(userService.transferUserToUserDto(accountFound.getUser()));
            }

            return accountDto;

        } else {
            throw new IdNotFoundException("Account with id number " + id + " does not exist.");
        }
    }


    public String deleteById(Long id) {
        if(accountRepository.existsById(id)) {
            Optional<Account> accountFound = accountRepository.findById(id);
            Account accountToDelete = accountFound.get();
            accountRepository.delete(accountToDelete);
            return "Account with id number " + id + " has been successfully deleted.";
        } else {
            throw new IdNotFoundException("Account with id number " + id + " does not exist.");
        }
    }



    public AccountDto updateOneAccount(Long id, AccountDto accountDto) {

        Optional<Account> optionalAccount = accountRepository.findById(id);

        if(optionalAccount.isEmpty()) {

            throw new RecordNotFoundException("Account with id number " + id + " not found.");

        } else {

            Account account = optionalAccount.get();
            Account updatedAccount = transferAccountDtoToAccount(accountDto);
            updatedAccount.setId(account.getId());
            accountRepository.save(updatedAccount);
            return transferAccountToAccountDto(updatedAccount);
        }
    }



    public AccountDto updateAccountPartially(Long id, AccountDto accountDto) {

        Optional<Account> optionalAccount = accountRepository.findById(id);

        if(optionalAccount.isEmpty()) {
            throw new RecordNotFoundException();

        } else {

            Account accountToUpdate = optionalAccount.get();

            Account account1 = transferAccountDtoToAccount(accountDto);

            account1.setId(accountToUpdate.getId());

            if(accountDto.getFirstNameStudent() !=null) {
                accountToUpdate.setFirstNameStudent(accountDto.getFirstNameStudent());
            }
            if(accountDto.getLastNameStudent() !=null) {
                accountToUpdate.setLastNameStudent(accountDto.getLastNameStudent());
            }
            if(accountDto.getDob() !=null) {
                accountToUpdate.setDob(accountDto.getDob());
            }
            if(accountDto.getStudentClass() !=null) {
                accountToUpdate.setStudentClass(accountDto.getStudentClass());
            }
            if(accountDto.getNameOfTeacher() !=null) {
                accountToUpdate.setNameOfTeacher(accountDto.getNameOfTeacher());
            }

            Account returnAccount = accountRepository.save(account1);
            return transferAccountToAccountDto(returnAccount);

        }

    }



    //helper methods ...............................................................
    public AccountDto transferAccountToAccountDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setFirstNameStudent(account.getFirstNameStudent());
        accountDto.setLastNameStudent(account.getLastNameStudent());
        accountDto.setDob(account.getDob());
        accountDto.setStudentClass(account.getStudentClass());
        accountDto.setNameOfTeacher(account.getNameOfTeacher());
        accountDto.setId(account.getId());

        if(account.getUser() !=null) {
            accountDto.setUserDto(userService.transferUserToUserDto(account.getUser()));

        }
        return accountDto;
    }


    public Account transferAccountDtoToAccount(AccountDto accountDto) {
        Account account = new Account();
        account.setFirstNameStudent(accountDto.getFirstNameStudent());
        account.setLastNameStudent(accountDto.getLastNameStudent());
        account.setDob(accountDto.getDob());
        account.setStudentClass(accountDto.getStudentClass());
        account.setNameOfTeacher(accountDto.getNameOfTeacher());
        account.setId(accountDto.getId());
        account.setUser(userService.transferUserDtoToUser(accountDto.getUserDto()));

        return account;
    }


    public void assignUserToAccount(Long idAccount, String username) {
        Optional<User> optionalUser = userRepository.findById(username);
        Optional<Account> optionalAccount = accountRepository.findById(idAccount);

        if(optionalUser.isPresent() && optionalAccount.isPresent()) {
            User userIsPresent = optionalUser.get();
            Account accountIsPresent = optionalAccount.get();

            accountIsPresent.setUser(userIsPresent);
            accountRepository.save(accountIsPresent);

        } else {
            throw new RecordNotFoundException("Account not found.");
        }

    }


}
