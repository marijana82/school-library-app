package com.marijana.library1223.services;

import com.marijana.library1223.dtos.AccountDto;
import com.marijana.library1223.exceptions.IdNotFoundException;
import com.marijana.library1223.exceptions.RecordNotFoundException;
import com.marijana.library1223.exceptions.ResourceAlreadyExistsException;
import com.marijana.library1223.models.Account;
import com.marijana.library1223.models.User;
import com.marijana.library1223.repositories.AccountRepository;
import com.marijana.library1223.repositories.ReservationRepository;
import com.marijana.library1223.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    //createAccount2
    public AccountDto createAccount(AccountDto accountDto) {

        if(accountRepository.existsByFirstNameStudentIgnoreCaseAndLastNameStudentIgnoreCase(accountDto.getFirstNameStudent(), accountDto.getLastNameStudent())) {
            //TODO CHECK: here i'm getting status 500 internal server error instead of "Account already exists".
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


    //showAllAccountsByStudentClass method - get mapping (only accounts with the same student class)
    public List<AccountDto> showAllAccountsByStudentClass(String studentClass) {
        List<Account> accountList = accountRepository.findAllAccountsByStudentClassEqualsIgnoreCase(studentClass);
        List<AccountDto> accountDtoList = new ArrayList<>();
        for(Account account : accountList) {
            AccountDto accountDto = transferAccountToAccountDto(account);
            accountDtoList.add(accountDto);
        }
        return accountDtoList;
     }


    //showOneAccount method - get mapping (one)
    public AccountDto showOneAccount(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if(optionalAccount.isPresent()) {
            Account requestedAccount = optionalAccount.get();
            return transferAccountToAccountDto(requestedAccount);
        } else {
            throw new IdNotFoundException("Account with id number " + id + " does not exist.");
        }
    }

    //deleteById method - delete mapping (one)
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


    //updateOneAccount method - put mapping - for changing the whole account
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


    //updateAccountPartially - patch method - for partially updating an account
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

    //helper method - transfer Account to AccountDto
    public AccountDto transferAccountToAccountDto(Account account) {
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

    //assign user to account
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
