package com.marijana.library1223.services;

import com.marijana.library1223.dtos.BorrowalDto;
import com.marijana.library1223.exceptions.BadRequestException;
import com.marijana.library1223.exceptions.IdNotFoundException;
import com.marijana.library1223.exceptions.RecordNotFoundException;
import com.marijana.library1223.models.Account;
import com.marijana.library1223.models.BookCopy;
import com.marijana.library1223.models.Borrowal;
import com.marijana.library1223.repositories.AccountRepository;
import com.marijana.library1223.repositories.BookCopyRepository;
import com.marijana.library1223.repositories.BorrowalRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowalService {
    private final BorrowalRepository borrowalRepository;
    private final AccountRepository accountRepository;
    private final BookCopyRepository bookCopyRepository;

    public BorrowalService(BorrowalRepository borrowalRepository,
                           AccountRepository accountRepository,
                           BookCopyRepository bookCopyRepository
                           ) {
        this.borrowalRepository = borrowalRepository;
        this.accountRepository = accountRepository;
        this.bookCopyRepository = bookCopyRepository;
    }


    public BorrowalDto createBorrowal(BorrowalDto borrowalDto) {
        Borrowal borrowal = new Borrowal();
        borrowal.setDateOfBorrowal(borrowalDto.getDateOfBorrowal());
        borrowal.setDueDate(borrowalDto.getDueDate());
        borrowal.setNumberOfBooksBorrowed(borrowalDto.getNumberOfBooksBorrowed());
        borrowalRepository.save(borrowal);
        borrowalDto.setId(borrowal.getId());
        return borrowalDto;
    }


    public List<BorrowalDto> getAllBorrowals() {
        List<Borrowal> borrowalList = borrowalRepository.findAll();
        List<BorrowalDto> borrowalDtoList = new ArrayList<>();

        for (Borrowal borrowal : borrowalList) {
            BorrowalDto borrowalDto = transferBorrowalToBorrowalDto(borrowal);

            if(borrowal.getBookCopy() !=null) {
                borrowalDto.setBookCopyId(borrowal.getBookCopy().getId());
            }

            if(borrowal.getAccount() !=null) {
                borrowalDto.setAccountId(borrowal.getAccount().getId());
            }

            borrowalDtoList.add(borrowalDto);
        }
        return borrowalDtoList;
    }


    public BorrowalDto getSingleBorrowal(Long id) {
        Optional<Borrowal> optionalBorrowal = borrowalRepository.findById(id);
        if(optionalBorrowal.isPresent()) {
            Borrowal borrowalFound = optionalBorrowal.get();
            BorrowalDto borrowalDto = transferBorrowalToBorrowalDto(borrowalFound);

            if(borrowalFound.getBookCopy() !=null) {
                borrowalDto.setBookCopyId(borrowalFound.getBookCopy().getId());
            }

            if(borrowalFound.getAccount() !=null) {
                borrowalDto.setAccountId(borrowalFound.getAccount().getId());
            }

            return borrowalDto;

                } else {

            throw new RecordNotFoundException("Borrowal has not been found.");
        }
    }


    public BorrowalDto fullUpdateBorrowal(Long id, BorrowalDto borrowalDto) {
        Optional<Borrowal> optionalBorrowal = borrowalRepository.findById(id);
        if(optionalBorrowal.isEmpty()) {
            throw new RecordNotFoundException("Borrowal with id number " + id + " not found.");
        } else {
            Borrowal borrowal = optionalBorrowal.get();
            Borrowal updatedBorrowal = transferBorrowalDtoToBorrowal(borrowalDto);
            updatedBorrowal.setId(borrowal.getId());
            borrowalRepository.save(updatedBorrowal);
            return transferBorrowalToBorrowalDto(updatedBorrowal);
        }
    }


    public BorrowalDto partialUpdateBorrowal(Long id, BorrowalDto borrowalDto) {

        Optional<Borrowal> optionalBorrowal = borrowalRepository.findById(id);


        if(borrowalRepository.existsById(id)) {

            Borrowal existingBorrowal = optionalBorrowal.get();

            Borrowal partialUpdates = transferBorrowalDtoToBorrowal(borrowalDto);

            partialUpdates.setId(existingBorrowal.getId());

            if(partialUpdates.getDateOfBorrowal() !=null) {
                existingBorrowal.setDateOfBorrowal(partialUpdates.getDateOfBorrowal());
            }

            if(partialUpdates.getDueDate() != null) {
                existingBorrowal.setDueDate(partialUpdates.getDueDate());
            }

            if(partialUpdates.getNumberOfBooksBorrowed() !=null) {
                existingBorrowal.setNumberOfBooksBorrowed(partialUpdates.getNumberOfBooksBorrowed());
            }

            if(partialUpdates.getAccount() !=null) {
                existingBorrowal.setAccount(partialUpdates.getAccount());
            }

            if(partialUpdates.getBookCopy() !=null) {
                existingBorrowal.setBookCopy(partialUpdates.getBookCopy());
            }

            Borrowal newBorrowalSaved = borrowalRepository.save(existingBorrowal);

            return transferBorrowalToBorrowalDto(newBorrowalSaved);

        } else {

            throw new IdNotFoundException("Borrowal with id" + id + "is not found and cannot be updated");

        }

    }


    public void deleteBorrowal(Long id) {
        borrowalRepository.deleteById(id);
    }


    //.........................................
    public Borrowal transferBorrowalDtoToBorrowal(BorrowalDto borrowalDto) {
        Borrowal borrowal = new Borrowal();
        borrowal.setDateOfBorrowal(borrowalDto.getDateOfBorrowal());
        borrowal.setDueDate(borrowalDto.getDueDate());
        borrowal.setNumberOfBooksBorrowed(borrowalDto.getNumberOfBooksBorrowed());
        borrowal.setId(borrowalDto.getId());
        return borrowal;
    }


    public BorrowalDto transferBorrowalToBorrowalDto(Borrowal borrowal) {
        BorrowalDto borrowalDto = new BorrowalDto();
        borrowalDto.setDateOfBorrowal(borrowal.getDateOfBorrowal());
        borrowalDto.setDueDate(borrowal.getDueDate());
        borrowalDto.setNumberOfBooksBorrowed(borrowal.getNumberOfBooksBorrowed());
        borrowalDto.setId(borrowal.getId());

        if(borrowal.getAccount() !=null) {
            borrowalDto.setAccountId(borrowal.getAccount().getId());
        }

        if(borrowal.getBookCopy() !=null) {
            borrowalDto.setBookCopyId(borrowal.getBookCopy().getId());
        }

        return borrowalDto;
    }



    public void assignBookCopyToBorrowal(Long idBorrowal, Long idCopy) {
        Optional<Borrowal> optionalBorrowal = borrowalRepository.findById(idBorrowal);
        Optional<BookCopy> optionalBookCopy = bookCopyRepository.findById(idCopy);

        if(optionalBookCopy.isPresent() && optionalBorrowal.isPresent()) {
            BookCopy copyFound = optionalBookCopy.get();
            Borrowal borrowalFound = optionalBorrowal.get();

            if(borrowalFound.getBookCopy() !=null) {

                throw new BadRequestException("This borrowal already contains one book copy. No new book copies can be added.");
            }

            borrowalFound.setBookCopy(copyFound);
            borrowalRepository.save(borrowalFound);

        } else {

            throw new RecordNotFoundException();
        }
    }
    

    public void assignAccountToBorrowal(Long idBorrowal, Long idAccount) {
        Optional<Borrowal> optionalBorrowal = borrowalRepository.findById(idBorrowal);
        Optional<Account> optionalAccount = accountRepository.findById(idAccount);

        if(optionalBorrowal.isPresent() && optionalAccount.isPresent()) {
            Borrowal borrowalPresent = optionalBorrowal.get();
            Account accountPresent = optionalAccount.get();

            if(borrowalPresent.getAccount() !=null) {

                throw new BadRequestException("Borrowal is already connected to an account.");
            }

            borrowalPresent.setAccount(accountPresent);
            borrowalRepository.save(borrowalPresent);

        } else {
            throw new RecordNotFoundException();
        }
        
    }

}
