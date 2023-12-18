package com.marijana.library1223.services;

import com.marijana.library1223.dtos.BorrowalDto;
import com.marijana.library1223.exceptions.RecordNotFoundException;
import com.marijana.library1223.models.Account;
import com.marijana.library1223.models.Borrowal;
import com.marijana.library1223.models.Reservation;
import com.marijana.library1223.repositories.AccountRepository;
import com.marijana.library1223.repositories.BorrowalRepository;
import com.marijana.library1223.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowalService {

    //constructor injector
    private final BorrowalRepository borrowalRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;
    private final AccountService accountService;
    private final AccountRepository accountRepository;

    public BorrowalService(BorrowalRepository borrowalRepository,
                           ReservationRepository reservationRepository,
                           ReservationService reservationService,
                           AccountService accountService,
                           AccountRepository accountRepository
                           ) {
        this.borrowalRepository = borrowalRepository;
        this.reservationRepository = reservationRepository;
        this.reservationService = reservationService;
        this.accountService = accountService;
        this.accountRepository = accountRepository;
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
            if(borrowal.getReservation() !=null) {
                borrowalDto.setReservationDto(reservationService.transferReservationToReservationDto(borrowal.getReservation()));
            }
            borrowalDtoList.add(borrowalDto);
        }
        return borrowalDtoList;
    }

    //getSingleBorrowal - get mapping
    public BorrowalDto getSingleBorrowal(Long id) {
        Optional<Borrowal> optionalBorrowal = borrowalRepository.findById(id);
        if(optionalBorrowal.isPresent()) {
            Borrowal borrowalFound = optionalBorrowal.get();
            BorrowalDto borrowalDto = transferBorrowalToBorrowalDto(borrowalFound);
            if(borrowalFound.getReservation() != null) {
                borrowalDto.setReservationDto(reservationService.transferReservationToReservationDto(borrowalFound.getReservation()));
            }
            return borrowalDto;
                } else {
            throw new RecordNotFoundException("Borrowal with id number " + id + " has not been found.");
        }
    }

    //fullUpdateBorrowal - put mapping
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

            if(borrowalDto.getReservationDto() !=null) {
                borrowalToUpdate.setReservation(reservationService.transferReservationDtoToReservation(borrowalDto.getReservationDto()));
            }

            Borrowal returnBorrowal = borrowalRepository.save(borrowal1);
            return transferBorrowalToBorrowalDto(returnBorrowal);
        }

    }

    //TODO: CREATE NEW DELETE METHOD AS IN BOOK SERVICE CLASS
    public void deleteBorrowal(Long id) {
        borrowalRepository.deleteById(id);
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
        //borrowal.setReservation(reservationService.transferReservationDtoToReservation(borrowalDto.getReservationDto()));
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
        //null check
        if(borrowal.getReservation() !=null) {
            borrowalDto.setReservationDto(reservationService.transferReservationToReservationDto(borrowal.getReservation()));
        }
        return borrowalDto;
    }

    //assign Reservation to Borrowal
    public void assignReservationToBorrowal(Long idBorrowal, Long idReservation) {
        Optional<Borrowal> optionalBorrowal = borrowalRepository.findById(idBorrowal);
        Optional<Reservation> optionalReservation = reservationRepository.findById(idReservation);

        if(optionalBorrowal.isPresent() && optionalReservation.isPresent()) {
             Borrowal borrowalPresent = optionalBorrowal.get();
             Reservation reservationPresent = optionalReservation.get();

            if(borrowalPresent.getReservation() !=null) {
                throw new RuntimeException("Borrowal already has a reservation");
            }

            borrowalPresent.setReservation(reservationPresent);
            borrowalRepository.save(borrowalPresent);

        } else {
            throw new RecordNotFoundException();
        }
    }
    
    //assign Account to Borrowal
    public void assignAccountToBorrowal(Long idBorrowal, Long idAccount) {
        Optional<Borrowal> optionalBorrowal = borrowalRepository.findById(idBorrowal);
        Optional<Account> optionalAccount = accountRepository.findById(idAccount);
        
    }

}
