package com.codegym.repository;


import com.codegym.dto.IDto.SeatTicketDto;

import com.codegym.dto.IDto.TicketDto;
import com.codegym.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;

@Repository
@Transactional

public interface ITicketRepository extends JpaRepository<Ticket, Long> {

    //    SonNH lấy list không trả về page
    @Query(value = "SELECT ticket.id, ticket.birthday_ticket, ticket.buyer_ticket, ticket.code_ticket,ticket.date_ticket, ticket.del_flag_ticket,\n" +
            "        ticket.email_ticket, ticket.gender_ticket,ticket.id_card, ticket.phone_ticket, ticket.point_ticket, ticket.price_ticket,\n" +
            "        ticket.status_ticket, ticket.id_customer, ticket.id_employee, ticket.id_seat\n" +
            "        FROM ticket\n" +
            "        WHERE ticket.id_customer = ?1 ", nativeQuery = true, countQuery = "select count(*) from ticket")
    List<Ticket> getAllTicketsByCustomerID(Long id);

    @Query(value = "SELECT ticket_history.id, ticket_history.birthday_ticket, ticket_history.buyer_ticket, ticket_history.code_ticket,ticket_history.date_ticket, ticket_history.del_flag_ticket,\n" +
            "        ticket_history.email_ticket, ticket_history.gender_ticket,ticket_history.id_card, ticket_history.phone_ticket, ticket_history.point_ticket, ticket_history.price_ticket,\n" +
            "        ticket_history.status_ticket, ticket_history.id_customer, ticket_history.id_employee, ticket_history.id_seat\n" +
            "        FROM ticket_history\n" +
            "        WHERE ticket_history.id_customer = ?1", nativeQuery = true, countQuery = "select count(*) from ticket")
    List<Ticket> getHistoryTicketsByCustomerID(Long id);


    //    SonNH lấy list ticket bằng Email của customtrả về page
    @Query(value = "SELECT ticket.id, ticket.birthday_ticket, ticket.buyer_ticket, ticket.code_ticket,ticket.date_ticket, ticket.del_flag_ticket,\n" +
            "        ticket.email_ticket, ticket.gender_ticket,ticket.id_card, ticket.phone_ticket, ticket.point_ticket, ticket.price_ticket,\n" +
            "        ticket.status_ticket, ticket.id_customer, ticket.id_employee, ticket.id_seat\n" +
            "        FROM ticket\n" +
            "        WHERE ticket.id_customer = ?1 ", nativeQuery = true, countQuery = "select count(*) from ticket")
    Page<Ticket> getAllTicketsByCustomerEmail(Long id, Pageable pageable);

    @Query(value = "SELECT ticket_history.id, ticket_history.birthday_ticket, ticket_history.buyer_ticket, ticket_history.code_ticket,ticket_history.date_ticket, ticket_history.del_flag_ticket,\n" +
            "        ticket_history.email_ticket, ticket_history.gender_ticket,ticket_history.id_card, ticket_history.phone_ticket, ticket_history.point_ticket, ticket_history.price_ticket,\n" +
            "        ticket_history.status_ticket, ticket_history.id_customer, ticket_history.id_employee, ticket_history.id_seat\n" +
            "        FROM ticket_history\n" +
            "        WHERE ticket_history.id_customer = ?1", nativeQuery = true, countQuery = "select count(*) from ticket")
    Page<Ticket> getHistoryTicketsByCustomerEmail(Long id, Pageable pageable);

//    SonNH xử lý đặt vé với employee
    //    SonNH lấy list ticket bằng Email của custom trả về page

    @Query(value = "SELECT ticket.id, ticket.birthday_ticket, ticket.buyer_ticket, ticket.code_ticket,ticket.date_ticket, ticket.del_flag_ticket,\n" +
            "        ticket.email_ticket, ticket.gender_ticket,ticket.id_card, ticket.phone_ticket, ticket.point_ticket, ticket.price_ticket,\n" +
            "        ticket.status_ticket, ticket.id_customer, ticket.id_employee, ticket.id_seat\n" +
            "        FROM ticket join employee on ticket.id_employee = employee.id\n" +
            "        WHERE employee.email_employee = ?1 and ticket.status_ticket =0 ", nativeQuery = true,
            countQuery = "select count(*) from (SELECT ticket.id, ticket.birthday_ticket,\n" +
                    "                 ticket.buyer_ticket, ticket.code_ticket,\n" +
                    "                 ticket.date_ticket, ticket.del_flag_ticket,\n" +
                    "                 ticket.email_ticket,\n" +
                    "                 ticket.gender_ticket,\n" +
                    "                 ticket.id_card,\n" +
                    "                 ticket.phone_ticket,\n" +
                    "                 ticket.point_ticket,\n" +
                    "                 ticket.price_ticket,\n" +
                    "                 ticket.status_ticket,\n" +
                    "                 ticket.id_customer,\n" +
                    "                 ticket.id_employee,\n" +
                    "                 ticket.id_seat FROM ticket join employee on ticket.id_employee = employee.id\n" +
                    "                    WHERE employee.email_employee = ?1 and ticket.status_ticket =0) as babba")
    Page<Ticket> getAllTicketsEmployeeEmail(String emailEmployee, Pageable pageable);

    @Query(value = "SELECT ticket_history.id, ticket_history.birthday_ticket, ticket_history.buyer_ticket, ticket_history.code_ticket,ticket_history.date_ticket, ticket_history.del_flag_ticket,\n" +
            "        ticket_history.email_ticket, ticket_history.gender_ticket,ticket_history.id_card, ticket_history.phone_ticket, ticket_history.point_ticket, ticket_history.price_ticket,\n" +
            "        ticket_history.status_ticket, ticket_history.id_customer, ticket_history.id_employee, ticket_history.id_seat\n" +
            "        FROM ticket_history join employee on ticket_history.id_employee = employee.id\n" +
            "        WHERE employee.email_employee = ?1", nativeQuery = true, countQuery = "select count(*) from ticket_history")
    Page<Ticket> getHistoryTicketsByEmployeeEmail(String emailEmployee, Pageable pageable);

//    SonNH search theo ten email, trạng thái vé true/false, ngày bắt đầu bay, người đi.

    @Query(value = "SELECT ticket_history.id, ticket_history.birthday_ticket, ticket_history.buyer_ticket, ticket_history.code_ticket,ticket_history.date_ticket, ticket_history.del_flag_ticket,\n" +
            "                    ticket_history.email_ticket, ticket_history.gender_ticket,ticket_history.id_card, ticket_history.phone_ticket, ticket_history.point_ticket, ticket_history.price_ticket,\n" +
            "                    ticket_history.status_ticket, ticket_history.id_customer, ticket_history.id_employee, ticket_history.id_seat\n" +
            "                    FROM ticket_history join employee on ticket_history.id_employee = employee.id join seat on ticket_history.id_seat = seat.id join flight on seat.id_flight = flight.id\n" +
            "                   WHERE employee.email_employee = ?1 \n" +
            "                   and ticket_history.status_ticket = ?2 \n" +
            "                and flight.date_start like %?3%\n" +
            "                  and ticket_history.buyer_ticket like %?4%", nativeQuery = true,
            countQuery = "select count(*) from (SELECT ticket_history.id, ticket_history.birthday_ticket, ticket_history.buyer_ticket, ticket_history.code_ticket,ticket_history.date_ticket, ticket_history.del_flag_ticket,\n" +
                    "                    ticket_history.email_ticket, ticket_history.gender_ticket,ticket_history.id_card, ticket_history.phone_ticket, ticket_history.point_ticket, ticket_history.price_ticket,\n" +
                    "                    ticket_history.status_ticket, ticket_history.id_customer, ticket_history.id_employee, ticket_history.id_seat\n" +
                    "                    FROM ticket_history join employee on ticket_history.id_employee = employee.id join seat on ticket_history.id_seat = seat.id join flight on seat.id_flight = flight.id\n" +
                    "                   WHERE employee.email_employee = ?1 \n" +
                    "                   and ticket_history.status_ticket = ?2 \n" +
                    "                and flight.date_start like %?3%\n" +
                    "                  and ticket_history.buyer_ticket like %?4%) as Tdmtcl ")
    Page<Ticket> searchHistoryTicketsByEmployeeEmail(String emailEmployee, Boolean statusTicket, String startFlight, String buyer, Pageable pageable);

    //    SonNH search theo ten email, trạng thái vé true/false, ngày bắt đầu bay, người đi.

    @Query(value = "SELECT ticket_history.id, ticket_history.birthday_ticket, ticket_history.buyer_ticket, ticket_history.code_ticket,ticket_history.date_ticket, ticket_history.del_flag_ticket,\n" +
            "                    ticket_history.email_ticket, ticket_history.gender_ticket,ticket_history.id_card, ticket_history.phone_ticket, ticket_history.point_ticket, ticket_history.price_ticket,\n" +
            "                    ticket_history.status_ticket, ticket_history.id_customer, ticket_history.id_employee, ticket_history.id_seat\n" +
            "                    FROM ticket_history join employee on ticket_history.id_employee = employee.id join seat on ticket_history.id_seat = seat.id join flight on seat.id_flight = flight.id\n" +
            "                   WHERE employee.email_employee = ?1 \n" +
            "                   and ticket_history.status_ticket is null \n" +
            "                and flight.date_start like %?2%\n" +
            "                  and ticket_history.buyer_ticket like %?3%", nativeQuery = true,
            countQuery = "select count(*) from (SELECT ticket_history.id, ticket_history.birthday_ticket, ticket_history.buyer_ticket, ticket_history.code_ticket,ticket_history.date_ticket, ticket_history.del_flag_ticket,\n" +
                    "                    ticket_history.email_ticket, ticket_history.gender_ticket,ticket_history.id_card, ticket_history.phone_ticket, ticket_history.point_ticket, ticket_history.price_ticket,\n" +
                    "                    ticket_history.status_ticket, ticket_history.id_customer, ticket_history.id_employee, ticket_history.id_seat\n" +
                    "                    FROM ticket_history join employee on ticket_history.id_employee = employee.id join seat on ticket_history.id_seat = seat.id join flight on seat.id_flight = flight.id\n" +
                    "                   WHERE employee.email_employee = ?1 \n" +
                    "                   and ticket_history.status_ticket is null \n" +
                    "                and flight.date_start like %?2%\n" +
                    "                  and ticket_history.buyer_ticket like %?3%) as Tdmtcl ")
    Page<Ticket> searchHistoryTicketsByEmployeeEmailAbort(String emailEmployee, String startFlight, String buyer, Pageable pageable);

    //    SonNH search theo ten email, trạng thái vé, ngày bắt đầu bay, người đi.

    @Query(value = "SELECT ticket_history.id, ticket_history.birthday_ticket, ticket_history.buyer_ticket, ticket_history.code_ticket,ticket_history.date_ticket, ticket_history.del_flag_ticket,\n" +
            "                    ticket_history.email_ticket, ticket_history.gender_ticket,ticket_history.id_card, ticket_history.phone_ticket, ticket_history.point_ticket, ticket_history.price_ticket,\n" +
            "                    ticket_history.status_ticket, ticket_history.id_customer, ticket_history.id_employee, ticket_history.id_seat\n" +
            "                    FROM ticket_history join employee on ticket_history.id_employee = employee.id join seat on ticket_history.id_seat = seat.id join flight on seat.id_flight = flight.id\n" +
            "                   WHERE employee.email_employee = ?1 \n" +
            "                and flight.date_start like %?2%\n" +
            "                  and ticket_history.buyer_ticket like %?3%", nativeQuery = true,
            countQuery = "select count(*) from (SELECT ticket_history.id, ticket_history.birthday_ticket, ticket_history.buyer_ticket, ticket_history.code_ticket,ticket_history.date_ticket, ticket_history.del_flag_ticket,\n" +
                    "                    ticket_history.email_ticket, ticket_history.gender_ticket,ticket_history.id_card, ticket_history.phone_ticket, ticket_history.point_ticket, ticket_history.price_ticket,\n" +
                    "                    ticket_history.status_ticket, ticket_history.id_customer, ticket_history.id_employee, ticket_history.id_seat\n" +
                    "                    FROM ticket_history join employee on ticket_history.id_employee = employee.id join seat on ticket_history.id_seat = seat.id join flight on seat.id_flight = flight.id\n" +
                    "                   WHERE employee.email_employee = ?1 \n" +
                    "                and flight.date_start like %?2%\n" +
                    "                  and ticket_history.buyer_ticket like %?3%) as Tdmtcl ")
    Page<Ticket> searchHistoryTicketsByEmployeeEmailNotStatus(String emailEmployee, String startFlight, String buyer, Pageable pageable);

    @Query(value = "SELECT\n" +
            " ticket.id,\n" +
            " ticket.birthday_ticket,\n" +
            " ticket.buyer_ticket,\n" +
            " ticket.code_ticket,ticket.date_ticket,\n" +
            " ticket.del_flag_ticket,\n" +
            " ticket.email_ticket,\n" +
            " ticket.gender_ticket,ticket.id_card,\n" +
            " ticket.phone_ticket,\n" +
            " ticket.point_ticket,\n" +
            " ticket.price_ticket,\n" +
            " ticket.status_ticket,\n" +
            " ticket.id_customer,\n" +
            " ticket.id_employee,\n" +
            " ticket.id_seat\n" +
            "FROM ticket\n" +
            "WHERE ticket.code_ticket=?", nativeQuery = true)
    Ticket findTicketByCodeTicket(String codeTicket);

    @Modifying
    @Query(value = "UPDATE `c1021g1_sprint_1`.`ticket` SET ticket.status_ticket =true WHERE ticket.code_ticket=?", nativeQuery = true)
    void payTicketByCodeTicket(String codeTicket);

    @Modifying
    @Transactional
//    UPDATE `c1021g1_sprint_1`.`ticket` SET `birthday_ticket` = null, `buyer_ticket` = null, `email_ticket` = null, `gender_ticket` = null, `phone_ticket` = null, `point_ticket` = '12', `price_ticket` = null, `status_ticket` = null, `id_customer` = null, `id_employee` = null WHERE (`id` = '4');
//    @Query(value = "UPDATE `c1021g1_sprint_1`.`ticket` SET ticket.status_ticket =null WHERE ticket.code_ticket=?",nativeQuery = true)
    @Query(value = "UPDATE `ticket` SET ticket.birthday_ticket = null,ticket.id_card=null ,ticket.date_ticket=null , ticket.buyer_ticket = null, ticket.email_ticket = null, ticket.gender_ticket = null, ticket.phone_ticket = null, ticket.price_ticket = null, ticket.status_ticket = null, ticket.id_customer = null, ticket.id_employee = null WHERE ticket.code_ticket=?", nativeQuery = true)
    void abortTicketByCodeTicket(String codeTicket);

    @Modifying
    @Query(value = "UPDATE `ticket_history` SET ticket_history.status_ticket =1 WHERE ticket_history.code_ticket=?", nativeQuery = true)
    void payHistoryTicketByCodeTicket(String codeTicket);

    @Modifying
    @Query(value = "UPDATE `ticket_history` SET ticket_history.status_ticket =null WHERE ticket_history.code_ticket=?", nativeQuery = true)
    void abortHistoryTicketByCodeTicketTicketByCodeTicket(String codeTicket);

    @Query(value = "SELECT  ticket.id as idTicket, ticket.birthday_ticket as birthdayTicket," +
            "               ticket.buyer_ticket as buyerTicket, seat_type.name_seat_type as nameSeatType, \n" +
            "\t             ticket.code_ticket as codeTicket, ticket.del_flag_ticket as delFlagTicket,\n" +
            "               ticket.email_ticket as emailTicket, ticket.gender_ticket as genderTicket,\n" +
            "               ticket.phone_ticket as phoneTicket, ticket.point_ticket as pointTicket,\n" +
            "               ticket.price_ticket as priceTicket, ticket.status_ticket as statusTicket,\n" +
            "               ticket.id_customer as idCustomer, ticket.id_employee as idEmployee,\n" +
            "               employee.name_employee as nameEmployee, ticket.id_seat as idSeat, \n" +
            "               seat.id_flight as idFlight, flight.from_flight as fromFlight,\n" +
            "               flight.to_flight as toFlight, flight.date_start as dateStart\n" +
            "                    FROM ticket  \n" +
            "                    JOIN employee on employee.id = ticket.id_employee\n" +
            "                    JOIN seat on seat.id = ticket.id_seat\n" +
            "                    JOIN seat_type on seat_type.id = seat.id_seat_type" +
            "                    JOIN flight on flight.id = seat.id_flight " +
            "WHERE ticket.buyer_ticket LIKE %:keyword% AND ticket.del_flag_ticket = 1 ", nativeQuery = true,
            countQuery = " SELECT COUNT(*) FROM (SELECT ticket.id as idTicket, ticket.birthday_ticket as birthdayTicket, " +
                    "                                   ticket.buyer_ticket as buyerTicket, seat_type.name_seat_type as nameSeatType, " +
                    "                                   ticket.code_ticket as codeTicket, ticket.del_flag_ticket as delFlagTicket, " +
                    "                                   ticket.email_ticket as emailTicket, ticket.gender_ticket as genderTicket, " +
                    "                                   ticket.phone_ticket as phoneTicket, ticket.point_ticket as pointTicket, " +
                    "                                   ticket.price_ticket as priceTicket, ticket.status_ticket as statusTicket, " +
                    "                                   ticket.id_customer as idCustomer, ticket.id_employee as idEmployee, " +
                    "                                   employee.name_employee as nameEmployee, ticket.id_seat as idSeat,  " +
                    "                                   seat.id_flight as idFlight, flight.from_flight as fromFlight, " +
                    "                                   flight.to_flight as toFlight, flight.date_start as dateStart " +
                    "                                       FROM ticket " +
                    "                                       JOIN employee on employee.id = ticket.id_employee " +
                    "                                       JOIN seat on seat.id = ticket.id_seat " +
                    "                                       JOIN seat_type on seat_type.id = seat.id_seat_type " +
                    "                                       JOIN flight on flight.id = seat.id_flight  " +
                    "                                       WHERE ticket.buyer_ticket LIKE %:keyword% AND ticket.del_flag_ticket = 1 ) as buyer ")

    Page<TicketDto> ticketByBuyer(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "SELECT  ticket.id as idTicket, ticket.birthday_ticket as birthdayTicket," +
            "               ticket.buyer_ticket as buyerTicket, seat_type.name_seat_type as nameSeatType, \n" +
            "\t             ticket.code_ticket as codeTicket, ticket.del_flag_ticket as delFlagTicket,\n" +
            "               ticket.email_ticket as emailTicket, ticket.gender_ticket as genderTicket,\n" +
            "               ticket.phone_ticket as phoneTicket, ticket.point_ticket as pointTicket,\n" +
            "               ticket.price_ticket as priceTicket, ticket.status_ticket as statusTicket,\n" +
            "               ticket.id_customer as idCustomer, ticket.id_employee as idEmployee,\n" +
            "               employee.name_employee as nameEmployee, ticket.id_seat as idSeat, \n" +
            "               seat.id_flight as idFlight, flight.from_flight as fromFlight,\n" +
            "               flight.to_flight as toFlight, flight.date_start as dateStart\n" +
            "                    FROM ticket  \n" +
            "                    JOIN employee on employee.id = ticket.id_employee\n" +
            "                    JOIN seat on seat.id = ticket.id_seat\n" +
            "                    JOIN seat_type on seat_type.id = seat.id_seat_type" +
            "                    JOIN flight on flight.id = seat.id_flight " +
            "WHERE flight.to_flight LIKE %:keyword% AND ticket.del_flag_ticket = 1 ", nativeQuery = true,
            countQuery = " SELECT COUNT(*) FROM (SELECT ticket.id as idTicket, ticket.birthday_ticket as birthdayTicket, " +
                    "                                   ticket.buyer_ticket as buyerTicket, seat_type.name_seat_type as nameSeatType, " +
                    "                                   ticket.code_ticket as codeTicket, ticket.del_flag_ticket as delFlagTicket, " +
                    "                                   ticket.email_ticket as emailTicket, ticket.gender_ticket as genderTicket, " +
                    "                                   ticket.phone_ticket as phoneTicket, ticket.point_ticket as pointTicket, " +
                    "                                   ticket.price_ticket as priceTicket, ticket.status_ticket as statusTicket, " +
                    "                                   ticket.id_customer as idCustomer, ticket.id_employee as idEmployee, " +
                    "                                   employee.name_employee as nameEmployee, ticket.id_seat as idSeat,  " +
                    "                                   seat.id_flight as idFlight, flight.from_flight as fromFlight, " +
                    "                                   flight.to_flight as toFlight, flight.date_start as dateStart " +
                    "                                       FROM ticket " +
                    "                                       JOIN employee on employee.id = ticket.id_employee " +
                    "                                       JOIN seat on seat.id = ticket.id_seat " +
                    "                                       JOIN seat_type on seat_type.id = seat.id_seat_type " +
                    "                                       JOIN flight on flight.id = seat.id_flight  " +
                    "                                       WHERE flight.to_flight LIKE %:keyword% AND ticket.del_flag_ticket = 1 ) as toFlight ")

    Page<TicketDto> ticketToFlight(@Param("keyword") String keyword, Pageable pageable);


    @Query(value = "SELECT  ticket.id as idTicket, ticket.birthday_ticket as birthdayTicket," +
            "               ticket.buyer_ticket as buyerTicket, seat_type.name_seat_type as nameSeatType, \n" +
            "\t             ticket.code_ticket as codeTicket, ticket.del_flag_ticket as delFlagTicket,\n" +
            "               ticket.email_ticket as emailTicket, ticket.gender_ticket as genderTicket,\n" +
            "               ticket.phone_ticket as phoneTicket, ticket.point_ticket as pointTicket,\n" +
            "               ticket.price_ticket as priceTicket, ticket.status_ticket as statusTicket,\n" +
            "               ticket.id_customer as idCustomer, ticket.id_employee as idEmployee,\n" +
            "               employee.name_employee as nameEmployee, ticket.id_seat as idSeat, \n" +
            "               seat.id_flight as idFlight, flight.from_flight as fromFlight,\n" +
            "               flight.to_flight as toFlight, flight.date_start as dateStart\n" +
            "                    FROM ticket  \n" +
            "                    JOIN employee on employee.id = ticket.id_employee\n" +
            "                    JOIN seat on seat.id = ticket.id_seat\n" +
            "                    JOIN seat_type on seat_type.id = seat.id_seat_type" +
            "                    JOIN flight on flight.id = seat.id_flight " +
            "WHERE flight.from_flight LIKE %:keyword% AND ticket.del_flag_ticket = 1 ", nativeQuery = true,
            countQuery = " SELECT COUNT(*) FROM (SELECT ticket.id as idTicket, ticket.birthday_ticket as birthdayTicket, " +
                    "                                   ticket.buyer_ticket as buyerTicket, seat_type.name_seat_type as nameSeatType, " +
                    "                                   ticket.code_ticket as codeTicket, ticket.del_flag_ticket as delFlagTicket, " +
                    "                                   ticket.email_ticket as emailTicket, ticket.gender_ticket as genderTicket, " +
                    "                                   ticket.phone_ticket as phoneTicket, ticket.point_ticket as pointTicket, " +
                    "                                   ticket.price_ticket as priceTicket, ticket.status_ticket as statusTicket, " +
                    "                                   ticket.id_customer as idCustomer, ticket.id_employee as idEmployee, " +
                    "                                   employee.name_employee as nameEmployee, ticket.id_seat as idSeat,  " +
                    "                                   seat.id_flight as idFlight, flight.from_flight as fromFlight, " +
                    "                                   flight.to_flight as toFlight, flight.date_start as dateStart " +
                    "                                       FROM ticket " +
                    "                                       JOIN employee on employee.id = ticket.id_employee " +
                    "                                       JOIN seat on seat.id = ticket.id_seat " +
                    "                                       JOIN seat_type on seat_type.id = seat.id_seat_type " +
                    "                                       JOIN flight on flight.id = seat.id_flight  " +
                    "                      WHERE flight.from_flight LIKE %:keyword% AND ticket.del_flag_ticket = 1 ) as formFlight")

    Page<TicketDto> ticketFromFlight(@Param("keyword") String keyword, Pageable pageable);


    @Query(value = "SELECT  ticket.id as idTicket, ticket.birthday_ticket as birthdayTicket," +
            "               ticket.buyer_ticket as buyerTicket, seat_type.name_seat_type as nameSeatType, \n" +
            "\t             ticket.code_ticket as codeTicket, ticket.del_flag_ticket as delFlagTicket,\n" +
            "               ticket.email_ticket as emailTicket, ticket.gender_ticket as genderTicket,\n" +
            "               ticket.phone_ticket as phoneTicket, ticket.point_ticket as pointTicket,\n" +
            "               ticket.price_ticket as priceTicket, ticket.status_ticket as statusTicket,\n" +
            "               ticket.id_customer as idCustomer, ticket.id_employee as idEmployee,\n" +
            "               employee.name_employee as nameEmployee, ticket.id_seat as idSeat, \n" +
            "               seat.id_flight as idFlight, flight.from_flight as fromFlight,\n" +
            "               flight.to_flight as toFlight, flight.date_start as dateStart\n" +
            "                    FROM ticket  \n" +
            "                    JOIN employee on employee.id = ticket.id_employee\n" +
            "                    JOIN seat on seat.id = ticket.id_seat\n" +
            "                    JOIN seat_type on seat_type.id = seat.id_seat_type" +
            "                    JOIN flight on flight.id = seat.id_flight " +
            "WHERE ticket.code_ticket LIKE %:keyword% AND ticket.del_flag_ticket = 1 ", nativeQuery = true,
            countQuery = " SELECT COUNT(*) FROM (SELECT  ticket.id as idTicket, ticket.birthday_ticket as birthdayTicket," +
                    "               ticket.buyer_ticket as buyerTicket, seat_type.name_seat_type as nameSeatType, \n" +
                    "\t             ticket.code_ticket as codeTicket, ticket.del_flag_ticket as delFlagTicket,\n" +
                    "               ticket.email_ticket as emailTicket, ticket.gender_ticket as genderTicket,\n" +
                    "               ticket.phone_ticket as phoneTicket, ticket.point_ticket as pointTicket,\n" +
                    "               ticket.price_ticket as priceTicket, ticket.status_ticket as statusTicket,\n" +
                    "               ticket.id_customer as idCustomer, ticket.id_employee as idEmployee,\n" +
                    "               employee.name_employee as nameEmployee, ticket.id_seat as idSeat, \n" +
                    "               seat.id_flight as idFlight, flight.from_flight as fromFlight,\n" +
                    "               flight.to_flight as toFlight, flight.date_start as dateStart\n" +
                    "                    FROM ticket  \n" +
                    "                    JOIN employee on employee.id = ticket.id_employee\n" +
                    "                    JOIN seat on seat.id = ticket.id_seat\n" +
                    "                    JOIN seat_type on seat_type.id = seat.id_seat_type " +
                    "                    JOIN flight on flight.id = seat.id_flight " +
                    "WHERE ticket.code_ticket LIKE %:keyword% AND ticket.del_flag_ticket = 1 ) as codeTicket")

    Page<TicketDto> ticketCodeTicket(@Param("keyword") String keyword, Pageable pageable);
    //        SonNH lấy list trả về page
    @Query(value = "SELECT ticket.id, ticket.birthday_ticket, ticket.buyer_ticket, ticket.code_ticket,ticket.date_ticket, ticket.del_flag_ticket,\n" +
            "        ticket.email_ticket, ticket.gender_ticket,ticket.id_card, ticket.phone_ticket, ticket.point_ticket, ticket.price_ticket,\n" +
            "        ticket.status_ticket, ticket.id_customer, ticket.id_employee, ticket.id_seat\n" +
            "        FROM ticket\n" +
            "        WHERE ticket.id_customer = ?1 ", nativeQuery = true, countQuery = "select count(*) from ticket")
    Page<Ticket> getAllTicketsByCustomerIDPage(Long id, Pageable pageable);

    @Query(value = "SELECT ticket_history.id, ticket_history.birthday_ticket, ticket_history.buyer_ticket, ticket_history.code_ticket,ticket_history.date_ticket, ticket_history.del_flag_ticket,\n" +
            "        ticket_history.email_ticket, ticket_history.gender_ticket,ticket_history.id_card, ticket_history.phone_ticket, ticket_history.point_ticket, ticket_history.price_ticket,\n" +
            "        ticket_history.status_ticket, ticket_history.id_customer, ticket_history.id_employee, ticket_history.id_seat\n" +
            "        FROM ticket_history\n" +
            "        WHERE ticket_history.id_customer = ?1", nativeQuery = true, countQuery = "select count(*) from ticket")
    Page<Ticket> getHistoryTicketsByCustomerIDPage(Long id, Pageable pageable);

    //    SonNH lấy list ticket bằng Email của custom trả về page

    @Query(value = "SELECT ticket.id, ticket.birthday_ticket, ticket.buyer_ticket, ticket.code_ticket,ticket.date_ticket, ticket.del_flag_ticket,\n" +
            "        ticket.email_ticket, ticket.gender_ticket,ticket.id_card, ticket.phone_ticket, ticket.point_ticket, ticket.price_ticket,\n" +
            "        ticket.status_ticket, ticket.id_customer, ticket.id_employee, ticket.id_seat\n" +
            "        FROM ticket join customer on ticket.id_customer = customer.id\n" +
            "        WHERE customer.email_customer = ?1 and ticket.status_ticket =0 ", nativeQuery = true, countQuery = "select count(*) from (SELECT ticket.id, ticket.birthday_ticket,\n" +
            "                 ticket.buyer_ticket, ticket.code_ticket,\n" +
            "                 ticket.date_ticket, ticket.del_flag_ticket,\n" +
            "                 ticket.email_ticket,\n" +
            "                 ticket.gender_ticket,\n" +
            "                 ticket.id_card,\n" +
            "                 ticket.phone_ticket,\n" +
            "                 ticket.point_ticket,\n" +
            "                 ticket.price_ticket,\n" +
            "                 ticket.status_ticket,\n" +
            "                 ticket.id_customer,\n" +
            "                 ticket.id_employee,\n" +
            "                 ticket.id_seat FROM ticket join customer on ticket.id_customer = customer.id\n" +
            "                    WHERE customer.email_customer = ?1 and ticket.status_ticket =false ) as babba")
    Page<Ticket> getAllTicketsByCustomerEmail(String emailCustomer, Pageable pageable);

    @Query(value = "SELECT ticket_history.id, ticket_history.birthday_ticket, ticket_history.buyer_ticket, ticket_history.code_ticket,ticket_history.date_ticket, ticket_history.del_flag_ticket,\n" +
            "        ticket_history.email_ticket, ticket_history.gender_ticket,ticket_history.id_card, ticket_history.phone_ticket, ticket_history.point_ticket, ticket_history.price_ticket,\n" +
            "        ticket_history.status_ticket, ticket_history.id_customer, ticket_history.id_employee, ticket_history.id_seat\n" +
            "        FROM ticket_history join customer on ticket_history.id_customer = customer.id\n" +
            "        WHERE customer.email_customer = ?1", nativeQuery = true, countQuery = "select count(*) from ticket_history")
    Page<Ticket> getHistoryTicketsByCustomerEmail(String emailCustomer, Pageable pageable);

//    SonNH search theo ten email, trạng thái vé true/false, ngày bắt đầu bay, người đi.

    @Query(value = "SELECT ticket_history.id, ticket_history.birthday_ticket, ticket_history.buyer_ticket, ticket_history.code_ticket,ticket_history.date_ticket, ticket_history.del_flag_ticket,\n" +
            "                    ticket_history.email_ticket, ticket_history.gender_ticket,ticket_history.id_card, ticket_history.phone_ticket, ticket_history.point_ticket, ticket_history.price_ticket,\n" +
            "                    ticket_history.status_ticket, ticket_history.id_customer, ticket_history.id_employee, ticket_history.id_seat\n" +
            "                    FROM ticket_history join customer on ticket_history.id_customer = customer.id join seat on ticket_history.id_seat = seat.id join flight on seat.id_flight = flight.id\n" +
            "                   WHERE customer.email_customer = ?1 \n" +
            "                   and ticket_history.status_ticket = ?2 \n" +
            "                and flight.date_start like %?3%\n" +
            "                  and ticket_history.buyer_ticket like %?4%", nativeQuery = true,
            countQuery = "select count(*) from (SELECT ticket_history.id, ticket_history.birthday_ticket, ticket_history.buyer_ticket, ticket_history.code_ticket,ticket_history.date_ticket, ticket_history.del_flag_ticket,\n" +
                    "                    ticket_history.email_ticket, ticket_history.gender_ticket,ticket_history.id_card, ticket_history.phone_ticket, ticket_history.point_ticket, ticket_history.price_ticket,\n" +
                    "                    ticket_history.status_ticket, ticket_history.id_customer, ticket_history.id_employee, ticket_history.id_seat\n" +
                    "                    FROM ticket_history join customer on ticket_history.id_customer = customer.id join seat on ticket_history.id_seat = seat.id join flight on seat.id_flight = flight.id\n" +
                    "                   WHERE customer.email_customer = ?1 \n" +
                    "                   and ticket_history.status_ticket = ?2 \n" +
                    "                and flight.date_start like %?3%\n" +
                    "                  and ticket_history.buyer_ticket like %?4%) as Tdmtcl ")
    Page<Ticket> searchHistoryTicketsByCustomerEmail(String emailCustomer, Boolean statusTicket, String startFlight, String buyer, Pageable pageable);

    //    SonNH search theo ten email, trạng thái vé true/false, ngày bắt đầu bay, người đi.

    @Query(value = "SELECT ticket_history.id, ticket_history.birthday_ticket, ticket_history.buyer_ticket, ticket_history.code_ticket,ticket_history.date_ticket, ticket_history.del_flag_ticket,\n" +
            "                    ticket_history.email_ticket, ticket_history.gender_ticket,ticket_history.id_card, ticket_history.phone_ticket, ticket_history.point_ticket, ticket_history.price_ticket,\n" +
            "                    ticket_history.status_ticket, ticket_history.id_customer, ticket_history.id_employee, ticket_history.id_seat\n" +
            "                    FROM ticket_history join customer on ticket_history.id_customer = customer.id join seat on ticket_history.id_seat = seat.id join flight on seat.id_flight = flight.id\n" +
            "                   WHERE customer.email_customer = ?1 \n" +
            "                   and ticket_history.status_ticket is null \n" +
            "                and flight.date_start like %?2%\n" +
            "                  and ticket_history.buyer_ticket like %?3%", nativeQuery = true,
            countQuery = "select count(*) from (SELECT ticket_history.id, ticket_history.birthday_ticket, ticket_history.buyer_ticket, ticket_history.code_ticket,ticket_history.date_ticket, ticket_history.del_flag_ticket,\n" +
                    "                    ticket_history.email_ticket, ticket_history.gender_ticket,ticket_history.id_card, ticket_history.phone_ticket, ticket_history.point_ticket, ticket_history.price_ticket,\n" +
                    "                    ticket_history.status_ticket, ticket_history.id_customer, ticket_history.id_employee, ticket_history.id_seat\n" +
                    "                    FROM ticket_history join customer on ticket_history.id_customer = customer.id join seat on ticket_history.id_seat = seat.id join flight on seat.id_flight = flight.id\n" +
                    "                   WHERE customer.email_customer = ?1 \n" +
                    "                   and ticket_history.status_ticket is null \n" +
                    "                and flight.date_start like %?2%\n" +
                    "                  and ticket_history.buyer_ticket like %?3%) as Tdmtcl ")
    Page<Ticket> searchHistoryTicketsByCustomerEmailAbort(String emailCustomer, String startFlight, String buyer, Pageable pageable);

    //    SonNH search theo ten email, trạng thái vé, ngày bắt đầu bay, người đi.

    @Query(value = "SELECT ticket_history.id, ticket_history.birthday_ticket, ticket_history.buyer_ticket, ticket_history.code_ticket,ticket_history.date_ticket, ticket_history.del_flag_ticket,\n" +
            "                    ticket_history.email_ticket, ticket_history.gender_ticket,ticket_history.id_card, ticket_history.phone_ticket, ticket_history.point_ticket, ticket_history.price_ticket,\n" +
            "                    ticket_history.status_ticket, ticket_history.id_customer, ticket_history.id_employee, ticket_history.id_seat\n" +
            "                    FROM ticket_history join customer on ticket_history.id_customer = customer.id join seat on ticket_history.id_seat = seat.id join flight on seat.id_flight = flight.id\n" +
            "                   WHERE customer.email_customer = ?1 \n" +
            "                and flight.date_start like %?2%\n" +
            "                  and ticket_history.buyer_ticket like %?3%", nativeQuery = true,
            countQuery = "select count(*) from (SELECT ticket_history.id, ticket_history.birthday_ticket, ticket_history.buyer_ticket, ticket_history.code_ticket,ticket_history.date_ticket, ticket_history.del_flag_ticket,\n" +
                    "                    ticket_history.email_ticket, ticket_history.gender_ticket,ticket_history.id_card, ticket_history.phone_ticket, ticket_history.point_ticket, ticket_history.price_ticket,\n" +
                    "                    ticket_history.status_ticket, ticket_history.id_customer, ticket_history.id_employee, ticket_history.id_seat\n" +
                    "                    FROM ticket_history join customer on ticket_history.id_customer = customer.id join seat on ticket_history.id_seat = seat.id join flight on seat.id_flight = flight.id\n" +
                    "                   WHERE customer.email_customer = ?1 \n" +
                    "                and flight.date_start like %?2%\n" +
                    "                  and ticket_history.buyer_ticket like %?3%) as Tdmtcl ")
    Page<Ticket> searchHistoryTicketsByCustomerEmailNotStatus(String emailCustomer, String startFlight, String buyer, Pageable pageable);


    @Query(value = "select ticket.id,ticket.buyer_ticket,ticket.birthday_ticket,ticket.code_ticket,ticket.del_flag_ticket,ticket.email_ticket,ticket.date_ticket,ticket.gender_ticket,ticket.phone_ticket,ticket.id_card,ticket.point_ticket,ticket.price_ticket,ticket.status_ticket,ticket.id_customer,ticket.id_employee,ticket.id_seat " +
            "from ticket " +
            "join seat on seat.id = ticket.id_seat " +
            "join flight on flight.id = seat.id_flight " +
            "join seat_type on seat_type.id = seat.id_seat_type " +
            "where flight.id = :idFlight " +
            "and seat.status_seat = 0 " +
            "and seat_type.name_seat_type = :typeSeat " +
            "and ticket.id = :idTicket " +
            "and ticket.del_flag_ticket = 1", nativeQuery = true)
    Ticket getTicketByFlightIdAndTypeSeatAndTicketId(@Param("idFlight") Long idFlight, @Param("typeSeat") String typeSeat, @Param("idTicket") Long idTicket);

    @Query(value = "select ticket.id,ticket.buyer_ticket,ticket.birthday_ticket,ticket.code_ticket,ticket.del_flag_ticket,ticket.email_ticket,ticket.date_ticket,ticket.gender_ticket,ticket.phone_ticket,ticket.id_card,ticket.point_ticket,ticket.price_ticket,ticket.status_ticket,ticket.id_customer,ticket.id_employee,ticket.id_seat " +
            "from ticket " +
            "where ticket.id = :idTicket"
            , nativeQuery = true)
    Ticket getTicketAddHistory(@Param("idTicket") Long idTicket);



    @Query(value = "select ticket.id,ticket.buyer_ticket,ticket.birthday_ticket,ticket.code_ticket,ticket.del_flag_ticket,ticket.email_ticket,ticket.date_ticket,ticket.gender_ticket,ticket.phone_ticket,ticket.id_card,ticket.point_ticket,ticket.price_ticket,ticket.status_ticket,ticket.id_customer,ticket.id_employee,ticket.id_seat \n" +
            "            from ticket \n" +
            "            join seat on seat.id = ticket.id_seat \n" +
            "            join flight on flight.id = seat.id_flight \n" +
            "            join seat_type on seat_type.id = seat.id_seat_type \n" +
            "            where seat.id = :idSeat\n" +
            "           and ticket.del_flag_ticket = 1", nativeQuery = true)
    Ticket getTicketBySeat(@Param("idSeat") Long idSeat);


    @Query(value = "select seat.id,seat.code_seat,seat.del_flag_seat,seat.position_seat,seat.status_seat,seat.id_flight,seat.id_seat_type " +
            " from seat " +
            " join flight on flight.id = seat.id_flight " +
            " join seat_type on seat_type.id = seat.id_seat_type " +
            " where flight.id = :idFlight " +
            " and seat_type.name_seat_type = :typeSeat ", nativeQuery = true)
    Page<SeatTicketDto> getListSeatByIdFlightAndTypeSeat(@Param("idFlight") Long idFlight, @Param("typeSeat") String typeSeat, Pageable pageable);


    @Modifying
    @Query(value = "update ticket " +
            "join seat on seat.id = ticket.id_seat " +
            "set seat.status_seat = 1 " +
            ",buyer_ticket = :buyer " +
            ",birthday_ticket = :birthDay " +
            ",email_ticket = :email " +
            ",gender_ticket = :gender " +
            ",phone_ticket = :phone " +
            ",status_ticket = 0 " +
            ",date_ticket = :dateTicket " +
            ",id_employee = :employee " +
            ",id_customer = :customer" +
            ",price_ticket = :price " +
            ",id_card = :idCard " +
            "where ticket.id = :idTicket " +
            "and del_flag_ticket = 1 " +
            "and ticket.status_ticket is null", nativeQuery = true)
    void updateTicketByIdTicketAndIdEmployee(
            @Param("buyer") String buyer,
            @Param("birthDay") String birthDay,
            @Param("email") String email,
            @Param("gender") Boolean gender,
            @Param("phone") String phone,
            @Param("dateTicket") String dateTicket,
            @Param("price") Double price,
            @Param("idCard") String idCard,
            @Param("employee") Long employee,
            @Param("customer") Long customer,
            @Param("idTicket") Long idTicket

    );

    @Query(value = "select employee.id from employee " +
            "where employee.email_employee = :email " +
            "and employee.del_flag_employee = 1 ", nativeQuery = true)
    Long getIdEmployeeByEmailRole(@Param("email") String emailEmployee);

    @Query(value = "select customer.id from customer " +
            "where customer.email_customer = :email " +
            "and customer.del_flag_customer = 1 ", nativeQuery = true)
    Long getIdCustomerEmailRole(@Param("email") String emailCustomer);


    @Query(value = "select ticket.id,ticket.buyer_ticket,ticket.birthday_ticket,ticket.code_ticket,ticket.del_flag_ticket,ticket.email_ticket,ticket.date_ticket,ticket.gender_ticket,ticket.id_card,ticket.phone_ticket,ticket.point_ticket,ticket.price_ticket,ticket.status_ticket,ticket.id_customer,ticket.id_employee,ticket.id_seat " +
            "from ticket " +
            "join seat on seat.id = ticket.id_seat " +
            "join flight on flight.id = seat.id_flight " +
            "join seat_type on seat_type.id = seat.id_seat_type " +
            "where seat.id_flight = :idFlight " +
            "and seat.status_seat = 0 " +
            "and seat_type.name_seat_type = :typeSeat " +
            "and del_flag_ticket = 1 " +
            "limit 5", nativeQuery = true)
    List<Ticket> getListNumberTicket(@Param("idFlight") Long idFlight, @Param("typeSeat") String typeSeat);

    @Query(value = "select seat.id,seat.code_seat,seat.del_flag_seat,seat.position_seat,seat.status_seat,seat.id_flight,seat.id_seat_type " +
            " from seat " +
            " join flight on flight.id = seat.id_flight " +
            " join seat_type on seat_type.id = seat.id_seat_type " +
            " where flight.id = :idFlight " +
            " and seat_type.name_seat_type = :typeSeat ", nativeQuery = true)
    List<SeatTicketDto> getListSeatTicket(@Param("idFlight") Long idFlight, @Param("typeSeat") String typeSeat);


    @Modifying
    @Query(value = "INSERT INTO ticket_history (birthday_ticket, buyer_ticket, code_ticket, del_flag_ticket, email_ticket, gender_ticket, " +
            " phone_ticket, point_ticket, price_ticket, status_ticket, id_customer, id_employee, id_seat, date_ticket, id_card) VALUES " +
            " (:birthdayTicket, :buyerTicket, :codeTicket, :delFlagTicket, :emailTicket, :genderTicket, :phoneTicket , :pointTicket, :priceTicket, :statusTicket, :idCustomer, :idEmployee, :idSeat, :dateTicket, :idCard) ", nativeQuery = true)
    void addTicketHistory(
            @Param("birthdayTicket") String birthdayTicket,
            @Param("buyerTicket") String buyerTicket,
            @Param("codeTicket") String codeTicket,
            @Param("delFlagTicket") Boolean delFlagTicket,
            @Param("emailTicket") String emailTicket,
            @Param("genderTicket") Boolean genderTicket,
            @Param("phoneTicket") String phoneTicket,
            @Param("pointTicket") Integer pointTicket,
            @Param("priceTicket") Double priceTicket,
            @Param("statusTicket") Boolean statusTicket,
            @Param("idCustomer") Long idCustomer,
            @Param("idEmployee") Long idEmployee,
            @Param("idSeat") Long idSeat,
            @Param("dateTicket") String dateTicket,
            @Param("idCard") String idCard
    );



    @Query(value = "SELECT ticket.id as idTicket, ticket.birthday_ticket as birthdayTicket,\n" +
            "\t   ticket.buyer_ticket as buyerTicket, seat_type.name_seat_type as nameSeatType,\n" +
            "\t   ticket.code_ticket as codeTicket, ticket.del_flag_ticket as delFlagTicket,\n" +
            "       ticket.email_ticket as emailTicket, ticket.gender_ticket as genderTicket,\n" +
            "       ticket.phone_ticket as phoneTicket, ticket.point_ticket as pointTicket,\n" +
            "       ticket.price_ticket as priceTicket, ticket.status_ticket as statusTicket,\n" +
            "       ticket.id_customer as idCustomer, ticket.id_employee as idEmployee,\n" +
            "       employee.name_employee as nameEmployee, ticket.id_seat as idSeat, \n" +
            "       seat.id_flight as idFlight, flight.from_flight as fromFlight,\n" +
            "       flight.to_flight as toFlight, flight.date_start as dateStart,\n" +
            "       seat.id_seat_type as idSeatType, ticket.id_card as idCardTicket\n" +
            "            FROM ticket \n" +
            "            JOIN employee on employee.id = ticket.id_employee\n" +
            "            JOIN seat on seat.id = ticket.id_seat\n" +
            "            JOIN seat_type on seat_type.id = seat.id_seat_type\n" +
            "            JOIN flight on flight.id = seat.id_flight\n" +
            "            WHERE ticket.del_flag_ticket = 1 AND ticket.status_ticket is not null",
            nativeQuery = true, countQuery = "SELECT COUNT(*) FROM (" +
            "       SELECT ticket.id as idTicket, ticket.birthday_ticket as birthdayTicket,\n" +
            "\t   ticket.buyer_ticket as buyerTicket, seat_type.name_seat_type,\n" +
            "\t   ticket.code_ticket as codeTicket, ticket.del_flag_ticket as delFlagTicket,\n" +
            "       ticket.email_ticket as emailTicket, ticket.gender_ticket as genderTicket,\n" +
            "       ticket.phone_ticket as phoneTicket, ticket.point_ticket as pointTicket,\n" +
            "       ticket.price_ticket as priceTicket, ticket.status_ticket as statusTicket,\n" +
            "       ticket.id_customer as idCustomer, ticket.id_employee as idEmployee,\n" +
            "       employee.name_employee as nameEmployee, ticket.id_seat as idSeat, \n" +
            "       seat.id_flight as idFlight, flight.from_flight as fromFlight,\n" +
            "       flight.to_flight as toFlight, flight.date_start as dateStart,\n" +
            "       seat_type.name_seat_type as nameSeatType, seat.id_seat_type as idSeatType,\n" +
            "       ticket.id_card as idCardTicket\n" +
            "            FROM ticket \n" +
            "            JOIN employee on employee.id = ticket.id_employee\n" +
            "            JOIN seat on seat.id = ticket.id_seat\n" +
            "            JOIN seat_type on seat_type.id = seat.id_seat_type\n" +
            "            JOIN flight on flight.id = seat.id_flight\n" +
            "            WHERE ticket.del_flag_ticket = 1 AND ticket.status_ticket is not null ) AS listTicket ")
    Page<TicketDto> findAllListTicket(Pageable pageable);

    @Modifying
    @Query(value = "UPDATE Ticket SET ticket.del_flag_ticket = 0 WHERE ticket.id = ? ", nativeQuery = true)
    void deleteTicketById(Long id);

    @Query(value = "SELECT ticket.id as idTicket, ticket.birthday_ticket as birthdayTicket,\n" +
            "\t   ticket.buyer_ticket as buyerTicket, seat_type.name_seat_type as nameSeatType,\n" +
            "\t   ticket.code_ticket as codeTicket, ticket.del_flag_ticket as delFlagTicket,\n" +
            "       ticket.email_ticket as emailTicket, ticket.gender_ticket as genderTicket,\n" +
            "       ticket.phone_ticket as phoneTicket, ticket.point_ticket as pointTicket,\n" +
            "       ticket.price_ticket as priceTicket, ticket.status_ticket as statusTicket,\n" +
            "       ticket.id_customer as idCustomer, ticket.id_employee as idEmployee,\n" +
            "       employee.name_employee as nameEmployee, ticket.id_seat as idSeat, \n" +
            "       seat.id_flight as idFlight, flight.from_flight as fromFlight,\n" +
            "       flight.to_flight as toFlight, flight.date_start as dateStart,\n" +
            "       seat.id_seat_type as idSeatType, ticket.id_card as idCardTicket\n" +
            "            FROM ticket \n" +
            "            JOIN employee on employee.id = ticket.id_employee\n" +
            "            JOIN seat on seat.id = ticket.id_seat\n" +
            "            JOIN seat_type on seat_type.id = seat.id_seat_type\n" +
            "            JOIN flight on flight.id = seat.id_flight\n" +
            " WHERE ticket.id = ? ", nativeQuery = true)
    Optional<TicketDto> findTicketByIdDto(Long id);

    @Query(value = "SELECT ticket.id as idTicket, ticket.birthday_ticket as birthdayTicket,\n" +
            "\t   ticket.buyer_ticket as buyerTicket, seat_type.name_seat_type,\n" +
            "\t   ticket.code_ticket as codeTicket, ticket.del_flag_ticket as delFlagTicket,\n" +
            "       ticket.email_ticket as emailTicket, ticket.gender_ticket as genderTicket,\n" +
            "       ticket.phone_ticket as phoneTicket, ticket.point_ticket as pointTicket,\n" +
            "       ticket.price_ticket as priceTicket, ticket.status_ticket as statusTicket,\n" +
            "       ticket.id_customer as idCustomer, ticket.id_employee as idEmployee,\n" +
            "       employee.name_employee as nameEmployee, ticket.id_seat as idSeat, \n" +
            "       seat.id_flight as idFlight, flight.from_flight as fromFlight,\n" +
            "       flight.to_flight as toFlight, flight.date_start as dateStart,\n" +
            "       seat_type.name_seat_type as seatTypeName, seat.id_seat_type as idSeatType,\n" +
            "       ticket.id_card as idCardTicket\n" +
            "            FROM ticket \n" +
            "            JOIN employee on employee.id = ticket.id_employee\n" +
            "            JOIN seat on seat.id = ticket.id_seat\n" +
            "            JOIN seat_type on seat_type.id = seat.id_seat_type\n" +
            "            JOIN flight on flight.id = seat.id_flight\n" +
            "            WHERE ticket.del_flag_ticket = 1 AND ticket.status_ticket = 0" +
            "GROUP BY ticket.id "
            , nativeQuery = true, countQuery = "SELECT COUNT(*) FROM ticket ")
    Page<TicketDto> getAllTicketDTONotPagination(Pageable pageable);

    @Query(value = "SELECT  Ticket.id, Ticket.birthday_ticket , Ticket.buyer_ticket, " +
            "Ticket.code_ticket , Ticket.del_flag_ticket, " +
            "Ticket.email_ticket , Ticket.gender_ticket , " +
            "Ticket.phone_ticket , Ticket.point_ticket , " +
            "Ticket.price_ticket , Ticket.status_ticket , " +
            "Ticket.id_customer , Ticket.id_employee , " +
            "Employee.name_employee , Ticket.id_seat , " +
            "Seat.id_flight , Flight.from_flight , " +
            "Flight.to_flight , Flight.date_start , seat_type.name_seat_type, ticket.date_ticket, ticket.id_card " +
            "FROM ticket " +
            "JOIN employee on Employee.id = Ticket .id_employee " +
            "JOIN Seat on seat.id = ticket.id_seat " +
            "JOIN seat_type on seat_type.id = Seat.id_seat_type " +
            "JOIN Flight on Flight.id = Seat.id_flight " +
            "WHERE Ticket.id = ? ", nativeQuery = true)
    Ticket findTicketById(Long id);

    @Modifying
    @Query(value = "UPDATE Ticket SET Ticket.buyer_ticket = ?1 , Ticket.email_ticket  = ?2 where Ticket.id  = ?3",nativeQuery = true)
    void editTicket( String buyer , String emailTicket ,Long id);



    @Query(value = "SELECT ticket.id as idTicket, ticket.birthday_ticket as birthdayTicket, \n" +
            "\t   ticket.buyer_ticket as buyerTicket, seat_type.name_seat_type as nameSeatType,\n" +
            "\t   ticket.code_ticket as codeTicket, ticket.del_flag_ticket as delFlagTicket, \n" +
            "       ticket.email_ticket as emailTicket, ticket.gender_ticket as genderTicket, \n" +
            "       ticket.phone_ticket as phoneTicket, ticket.point_ticket as pointTicket, \n" +
            "       ticket.price_ticket as priceTicket, ticket.status_ticket as statusTicket,\n" +
            "       ticket.id_customer as idCustomer, ticket.id_employee as idEmployee, \n" +
            "       employee.name_employee as nameEmployee, ticket.id_seat as idSeat, \n" +
            "       seat.id_flight as idFlight, flight.from_flight as fromFlight,\n" +
            "       flight.to_flight as toFlight, flight.date_start as dateStart,\n" +
            "       seat.id_seat_type as idSeatType, ticket.id_card as idCardTicket \n" +
            "            FROM ticket \n" +
            "            JOIN employee on employee.id = ticket.id_employee\n" +
            "            JOIN seat on seat.id = ticket.id_seat\n" +
            "            JOIN seat_type on seat_type.id = seat.id_seat_type\n" +
            "            JOIN flight on flight.id = seat.id_flight\n" +
            "            WHERE ticket.del_flag_ticket = 1 AND ticket.status_ticket is not null" +
            " AND ticket.buyer_ticket LIKE %?1% AND flight.to_flight LIKE %?2% AND flight.from_flight LIKE %?3% AND ticket.code_ticket LIKE %?4% ",
            nativeQuery = true, countQuery = "SELECT COUNT(*) FROM (SELECT ticket.id as idTicket, ticket.birthday_ticket as birthdayTicket, \n" +
            "\t               ticket.buyer_ticket as buyerTicket, seat_type.name_seat_type, \n" +
            "\t               ticket.code_ticket as codeTicket, ticket.del_flag_ticket as delFlagTicket, \n" +
            "                   ticket.email_ticket as emailTicket, ticket.gender_ticket as genderTicket, \n" +
            "                   ticket.phone_ticket as phoneTicket, ticket.point_ticket as pointTicket,\n" +
            "                   ticket.price_ticket as priceTicket, ticket.status_ticket as statusTicket,\n" +
            "                   ticket.id_customer as idCustomer, ticket.id_employee as idEmployee,\n" +
            "                   employee.name_employee as nameEmployee, ticket.id_seat as idSeat, \n" +
            "                   seat.id_flight as idFlight, flight.from_flight as fromFlight,\n" +
            "                   flight.to_flight as toFlight, flight.date_start as dateStart,\n" +
            "                   seat_type.name_seat_type as nameSeatType, seat.id_seat_type as idSeatType,\n" +
            "                   ticket.id_card as idCardTicket\n" +
            "                        FROM ticket \n" +
            "                           JOIN employee on employee.id = ticket.id_employee\n" +
            "                           JOIN seat on seat.id = ticket.id_seat\n" +
            "                           JOIN seat_type on seat_type.id = seat.id_seat_type\n" +
            "                           JOIN flight on flight.id = seat.id_flight \n" +
            "                   WHERE ticket.del_flag_ticket = true AND ticket.status_ticket is not null" +
            " AND ticket.buyer_ticket LIKE %?1% AND flight.to_flight LIKE %?2% AND flight.from_flight LIKE %?3% AND ticket.code_ticket LIKE %?4% ) as findByAllTicket ")
    Page<TicketDto> findByAllTicket(String buyer,String toFlight,String fromFlight, String code, Pageable pageable);
}


