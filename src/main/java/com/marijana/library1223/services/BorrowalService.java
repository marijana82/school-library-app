package com.marijana.library1223.services;

import com.marijana.library1223.dtos.BorrowalDto;
import com.marijana.library1223.exceptions.RecordNotFoundException;
import com.marijana.library1223.models.Borrowal;
import com.marijana.library1223.repositories.BorrowalRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowalService {

    //constructor injector
    private final BorrowalRepository borrowalRepository;
    public BorrowalService(BorrowalRepository borrowalRepository) {
        this.borrowalRepository = borrowalRepository;
    }

    //createBorrowal - post mapping
    public BorrowalDto createBorrowal(BorrowalDto borrowalDto) {
        Borrowal borrowal = new Borrowal();
        borrowal.setDateOfBorrowal(borrowalDto.getDateOfBorrowal());
        borrowal.setDueDate(borrowalDto.getDueDate());
        borrowal.setBookTitle(borrowalDto.getBookTitle());
        borrowal.setNumberOfBooksBorrowed(borrowalDto.getNumberOfBooksBorrowed());
        borrowalRepository.save(borrowal);
        borrowalDto.setId(borrowal.getId());
        return borrowalDto;
    }

    //getAllBorrowals - get mapping
    public List<BorrowalDto> getAllBorrowals() {
        List<Borrowal> borrowalList = borrowalRepository.findAll();
        List<BorrowalDto> borrowalDtoList = new ArrayList<>();
        for (Borrowal borrowal : borrowalList) {
            BorrowalDto borrowalDto = transferBorrowalToBorrowalDto(borrowal);
            borrowalDtoList.add(borrowalDto);
        }
        return borrowalDtoList;
    }

    //getSingleBorrowal - get mapping
    public BorrowalDto getSingleBorrowal(Long id) {
        Optional<Borrowal> optionalBorrowal = borrowalRepository.findById(id);
        if(optionalBorrowal.isPresent()) {
            Borrowal borrowalFound = optionalBorrowal.get();
            return transferBorrowalToBorrowalDto(borrowalFound);
        } else {
            throw new RecordNotFoundException("Borrowal with id number " + id + " has not been found.");
        }
    }

    //fullUpdateBorrowal - put mapping
    public BorrowalDto fullUpdateBorrowal(Long id, BorrowalDto borrowalDto) {
        Optional<Borrowal> optionalBorrowal = borrowalRepository.findById(id);
        if(optionalBorrowal.isEmpty()) {
            throw new RecordNotFoundException("Account with id number " + id + " not found.");
        } else {
            Borrowal borrowal = optionalBorrowal.get();
            Borrowal updatedBorrowal = transferBorrowalDtoToBorrowal(borrowalDto);
            updatedBorrowal.setId(borrowal.getId());
            borrowalRepository.save(updatedBorrowal);
            return transferBorrowalToBorrowalDto(updatedBorrowal);
        }
    }

    //partial update Borrowal - patch mapping
    public BorrowalDto partialUpdateBorrowal(Long id, BorrowalDto borrowalDto) {
        Optional<Borrowal> optionalBorrowal = borrowalRepository.findById(id);

        if(optionalBorrowal.isEmpty()) {
            throw new RecordNotFoundException("Borrowal with id number " + id + " not found.");
        } else {
            Borrowal borrowalToUpdate = optionalBorrowal.get();

            Borrowal borrowal1 = transferBorrowalDtoToBorrowal(borrowalDto);
            borrowal1.setId(borrowalToUpdate.getId());

            if(borrowalDto.getDateOfBorrowal() !=null) {
                borrowalToUpdate.setDateOfBorrowal(borrowalDto.getDateOfBorrowal());
            }
            if(borrowalDto.getDueDate() != null) {
                borrowalToUpdate.setDueDate(borrowalDto.getDueDate());
            }
            if(borrowalDto.getBookTitle() !=null) {
                borrowalToUpdate.setBookTitle(borrowalDto.getBookTitle());
            }
            //TODO: check error "operator != cannot be applied to int null
            if(borrowalDto.getNumberOfBooksBorrowed() !=-1) {
                borrowalToUpdate.setNumberOfBooksBorrowed(borrowalDto.getNumberOfBooksBorrowed());
            }
            Borrowal returnBorrowal = borrowalRepository.save(borrowal1);
            return transferBorrowalToBorrowalDto(returnBorrowal);
        }

    }


    //helper methods.........................................

    //helper method - transfer BorrowalDto to Borrowal
    public Borrowal transferBorrowalDtoToBorrowal(BorrowalDto borrowalDto) {
        Borrowal borrowal = new Borrowal();
        borrowal.setDateOfBorrowal(borrowalDto.getDateOfBorrowal());
        borrowal.setDueDate(borrowalDto.getDueDate());
        borrowal.setBookTitle(borrowalDto.getBookTitle());
        borrowal.setNumberOfBooksBorrowed(borrowalDto.getNumberOfBooksBorrowed());
        borrowal.setId(borrowalDto.getId());
        return borrowal;
    }

    //helper method = transfer Borrowal to BorrowalDto
    public BorrowalDto transferBorrowalToBorrowalDto(Borrowal borrowal) {
        BorrowalDto borrowalDto = new BorrowalDto();
        borrowalDto.setDateOfBorrowal(borrowal.getDateOfBorrowal());
        borrowalDto.setDueDate(borrowal.getDueDate());
        borrowalDto.setBookTitle(borrowal.getBookTitle());
        borrowalDto.setNumberOfBooksBorrowed(borrowal.getNumberOfBooksBorrowed());
        borrowalDto.setId(borrowal.getId());
        return borrowalDto;
    }



}
