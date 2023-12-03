package com.marijana.library1223.services;

import com.marijana.library1223.dtos.BorrowalDto;
import com.marijana.library1223.models.Borrowal;
import com.marijana.library1223.repositories.BorrowalRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
