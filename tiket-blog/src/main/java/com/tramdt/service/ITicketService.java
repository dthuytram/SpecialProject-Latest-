package com.codegym.service;

import com.codegym.dto.EditTicketDto;
import com.codegym.dto.IDto.SeatTicketDto;
import com.codegym.dto.IDto.TicketDto;
import com.codegym.model.Flight;
import com.codegym.model.SeatType;
import com.codegym.model.Ticket;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITicketService {

    List<Ticket> findAllTicketsByCustomerId(Long customerId);

    List<Ticket> findHistoryTicketsByCustomerId(Long customerId);

    Page<Ticket> findAllTicketsByCustomerIdPage(Long customerId,Pageable pageable);

    Page<Ticket> findHistoryTicketsByCustomerIdPage(Long customerId,Pageable pageable);

    Page<Ticket> getAllTicketsByCustomerEmail(String customerEmail,Pageable pageable);

    Page<Ticket> getHistoryTicketsByCustomerEmail(String customerEmail,Pageable pageable);

    Page<Ticket> searchHistoryTicketsByCustomerEmail(String emailCustomer,Boolean statusTicket,String startFlight,String buyer, Pageable pageable);
    Page<Ticket> searchHistoryTicketsByCustomerEmailAbort(String emailCustomer,String startFlight,String buyer, Pageable pageable);
    Page<Ticket> searchHistoryTicketsByCustomerEmailNotStatus(String emailCustomer,String startFlight,String buyer, Pageable pageable);

    //SonNH tạo dịch vụ cho Employee

    Page<Ticket> getAllTicketsByEmployeeEmail(String EmployeeEmail,Pageable pageable);

    Page<Ticket> getHistoryTicketsByEmployeeEmail(String EmployeeEmail,Pageable pageable);

    Page<Ticket> searchHistoryTicketsByEmployeeEmail(String emailEmployee,Boolean statusTicket,String startFlight,String buyer, Pageable pageable);
    Page<Ticket> searchHistoryTicketsByEmployeeEmailAbort(String emailEmployee,String startFlight,String buyer, Pageable pageable);
    Page<Ticket> searchHistoryTicketsByEmployeeEmailNotStatus(String emailEmployee,String startFlight,String buyer, Pageable pageable);

    Ticket findTicketByCodeTicket(String codeTicket);


    void payTicketByCodeTicket(String codeTicket);
    void abortTicketByCodeTicket(String codeTicket);

//    Page<TicketDto> findAllTicketDTO(Pageable pageable);
//
//    List<Ticket> findAllTicketDto(int index);

//    TicketDto findTicketById(Long id);


    Page<TicketDto> ticketByBuyer(String keyword, Pageable pageable);

    Page<TicketDto> ticketFromFlight(String keyword, Pageable pageable);

    Page<TicketDto> ticketToFlight(String keyword, Pageable pageable);

    Page<TicketDto> ticketCodeTicket(String keyword, Pageable pageable);
    public Long getIdEmployeeByEmailRole(String emailEmployee);
    public Long getIdCustomerEmailRole(String emailCustomer);
    List<Ticket> getListNumberTicket(Long idFlight, String typeSeat);
    Ticket getTicketAddHistory(Long idTicket);
    Ticket getTicketByFlightIdAndTypeSeatAndTicketId(Long idFlight, String typeSeat, Long idTicket);

    void updateFirstTicket(
            String buyer,
            String birthDay,
            String email,
            Boolean gender,
            String phone,
            String dateTicket,
            Double price,
            String idCard,
            Long employee,
            Long customer,
            Long idTicket

    );


//    Ticket findById(Long id);

    Flight findFlightById(Long id);

    List<SeatType> getAllSeatTypes();


    void addTicketHistory(
            String birthdayTicket,
            String buyerTicket,
            String codeTicket,
            Boolean delFlagTicket,
            String emailTicket,
            Boolean genderTicket,
            String phoneTicket,
            Integer pointTicket,
            Double priceTicket,
            Boolean statusTicket,
            Long idCustomer,
            Long idEmployee,
            Long idSeat,
            String dateTicket,
            String idCard);

    List<SeatTicketDto> getListSeatTicket(@Param("idFlight") Long idFlight, @Param("typeSeat") String typeSeat);

    Ticket getTicketBySeat(Long idSeat);

    Page<SeatTicketDto> getListSeatByIdFlightAndTypeSeat(@Param("idFlight") Long idFlight, @Param("typeSeat") String typeSeat, Pageable pageable);








    Page<TicketDto> findAllTicket(Pageable pageable);

    TicketDto findTicketByIdDto(Long id);

    void deleteTicketById(Long id);

    Page<TicketDto> findByAllTicket(String buyer, String toFlight, String fromFlight, String code, Pageable pageable);


    Page<TicketDto> getAllTicketDTONotPagination(Pageable pageable);

    void editTicket( EditTicketDto editTicketDto);

    Ticket findTicketById(Long id);
}
