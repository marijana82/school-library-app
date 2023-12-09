package com.marijana.library1223.services;

import com.marijana.library1223.dtos.AccountDto;
import com.marijana.library1223.exceptions.IdNotFoundException;
import com.marijana.library1223.exceptions.RecordNotFoundException;
import com.marijana.library1223.exceptions.ResourceAlreadyExistsException;
import com.marijana.library1223.models.Account;
import com.marijana.library1223.models.Reservation;
import com.marijana.library1223.repositories.AccountRepository;
import com.marijana.library1223.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final ReservationRepository reservationRepository;

    public AccountService(AccountRepository accountRepository, ReservationRepository reservationRepository) {
        this.accountRepository = accountRepository;
        //relations - added reservationRepository (delete reservation service in accountcontroller??)
        this.reservationRepository = reservationRepository;
    }

    //createAccount method - post mapping
    public AccountDto createAccount(AccountDto accountDto) {

        if(accountRepository.existsByFirstNameStudentIgnoreCaseAndLastNameStudentIgnoreCase(accountDto.getFirstNameStudent(), accountDto.getLastNameStudent())) {
            //here i'm getting status 500 internal server error instead of "Account already exists".
            throw new ResourceAlreadyExistsException("Account already exists!");

        } else {
        Account account = new Account();
        account.setFirstNameStudent(accountDto.getFirstNameStudent());
        account.setLastNameStudent(accountDto.getLastNameStudent());
        account.setDob(accountDto.getDob());
        account.setStudentClass(accountDto.getStudentClass());
        account.setNameOfTeacher(accountDto.getNameOfTeacher());
        //here we check if reservation ids exist in accountDto
        for(long id : accountDto.reservationIds) {
            Reservation reservation = reservationRepository.findById(id).get();  //happy flow (otherwise check with .isPresent() if the id is present!!
            account.getReservations().add(reservation);
        }
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
            //setting the already existing id from the database
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
                //account1.....as in patch book service
            }
            if(accountDto.getLastNameStudent() !=null) {
                accountToUpdate.setLastNameStudent(accountDto.getLastNameStudent());
                //account1.....as in patch book service
            }
            if(accountDto.getDob() !=null) {
                accountToUpdate.setDob(accountDto.getDob());
                //account1.....as in patch book service
            }
            if(accountDto.getStudentClass() !=null) {
                accountToUpdate.setStudentClass(accountDto.getStudentClass());
                //account1.....as in patch book service
            }
            if(accountDto.getNameOfTeacher() !=null) {
                accountToUpdate.setNameOfTeacher(accountDto.getNameOfTeacher());
                //account1.....as in patch book service
            }

            Account returnAccount = accountRepository.save(account1);
            return transferAccountToAccountDto(returnAccount);

        }

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
