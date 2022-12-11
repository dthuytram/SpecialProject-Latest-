package com.tramdt.repository;

import com.tramdt.dto.FlightDto;
import com.tramdt.dto.IDto.FlightDTO;
import com.tramdt.dto.IDto.FlightSearchDto;
import com.tramdt.dto.IDto.IFlightDto;
import com.tramdt.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface IFlightRepository extends JpaRepository<Flight, Long> {
    @Query(value = "select flight.id, airline_type.image_airline, " +
            "flight.code_flight, " +
            "flight.date_start, " +
            "flight.date_end, " +
            "airline_type.price_airline, " +
            "flight.to_flight, " +
            "flight.from_flight " +
            "from flight " +
            "left join airline_type " +
            "on flight.id_airline_type = airline_type.id " +
            "where flight.from_flight = :from_flight " +
            "and flight.to_flight = :to_flight " +
            "and flight.date_start " +
            "like concat('%',:date_start,'%') " +
            "and flight.date_end " +
            "like concat('%',:date_end,'%') order by :sortOption ASC ",
            nativeQuery = true)
    Page<FlightSearchDto> searchFlight(@Param("from_flight") String departureDestination,
                                       @Param("to_flight") String arrivalDestination,
                                       @Param("date_start") String departureDate,
                                       @Param("date_end") String arrivalDate,
                                       @Param("sortOption") String sortOption,
                                       Pageable pageable);


    List<Flight> findFlightsByDateStartContains(String date);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Flight(code_flight,from_flight,to_flight,date_start,date_end,id_airline_type,del_flag_flight)" +
            " VALUES(?1,?2,?3,?4,?5,?6, ?7) ", nativeQuery = true)
    void createFlight(String codeFlight, String fromFlight, String toFlight, String dateStart,
                      String dateEnd, Long airlineType, Boolean delFlagFlight);

    @Query(value =" SELECT id" +
            ", code_flight"+
            ", from_flight"+
            ", to_flight"+
            ", date_start"+
            ", date_end"+
            ", del_flag_flight"+
            ", id_airline_type "+
            "FROM flight WHERE id=?", nativeQuery = true)
    Flight findByIdFlight(Long id);

    //TẠO câu lệnh query chỉnh sửa chuyến bay
    @Transactional
    @Modifying
    @Query(value = "UPDATE Flight AS f SET f.code_flight = ?1 , f.from_flight = ?2, f.to_flight = ?3, f.date_start = ?4," +
            "f.date_end = ?5, f.id_airline_type = ?6, f.del_flag_flight = ?7 WHERE id = ?8", nativeQuery = true)
    void updateFlight(String codeFlight, String fromFlight, String toFlight, String dateStart,
                      String dateEnd, Long airlineType, Boolean delFlagFlight, Long id);

//    Lấy giá trị validate trùng nhau
    @Query(value = "select count(code_flight) from flight where code_flight = ?", nativeQuery = true)
    Integer finByCodeFlight(String codeFlight);

    @Query(value = "SELECT id" +
            ", code_flight" +
            ", from_flight" +
            ", to_flight" +
            ", date_start" +
            ", date_end" +
            ", del_flag_flight" +
            ", id_airline_type " +
            "FROM flight " +
            "WHERE del_flag_flight = 1 ORDER BY id DESC",
            nativeQuery = true,
            countQuery = "SELECT COUNT(*) " +
                    "FROM flight " +
                    "WHERE del_flag_flight = 1")
    Page<Flight> findAllFlight(Pageable pageable);

    @Query(value = "SELECT id" +
            ", code_flight" +
            ", from_flight" +
            ", to_flight" +
            ", date_start" +
            ", date_end" +
            ", del_flag_flight" +
            ", id_airline_type " +
            "FROM flight " +
            "WHERE del_flag_flight = 1 ",
            nativeQuery = true,
            countQuery = "SELECT COUNT(*) " +
                    "FROM flight " +
                    "WHERE del_flag_flight = 1")
    Page<Flight> findAllFlightNotPage(Pageable pageable);

    @Query(value =" SELECT id" +
            ", code_flight"+
            ", from_flight"+
            ", to_flight"+
            ", date_start"+
            ", date_end"+
            ", del_flag_flight"+
            ", id_airline_type "+
            "FROM flight WHERE id=?", nativeQuery = true)
    IFlightDto findByIdFlight1(Long id);

    @Modifying
    @Query(value = "UPDATE flight SET del_flag_flight = 0 WHERE id = ?", nativeQuery = true)
    void deleteById(Long id);

    @Query(value = "select flight.id " +
            ", code_flight, " +
            "date_start, " +
            "date_end, " +
            "from_flight, " +
            "to_flight, " +
            "del_flag_flight, " +
            "id_airline_type " +
            "from flight " +
            "where from_flight like %?1% and to_flight like %?2%  and date_start like %?3%  and date_end like %?4%", nativeQuery = true,
            countQuery = " select count(*) from ( select flight.id " +
                    ", code_flight, " +
                    "date_start, " +
                    "date_end, " +
                    "from_flight, " +
                    "to_flight, " +
                    "del_flag_flight, " +
                    "id_airline_type " +
                    "from flight " +
                    "where from_flight like %?1% and to_flight like %?2%  and date_start like %?3%  and date_end like %?4% ) as Tdmtcl ")
    Page<FlightSearchDto> searchAllByFields(String from_flight, String to_flight, String date_start, String date_end, Pageable pageable);


    @Query(value =" SELECT  count(seat.id), seat.id\n" +
            "FROM flight JOIN seat ON flight.id = seat.id_flight \n" +
            "WHERE flight.code_flight = ? ", nativeQuery = true)
    Integer countSeat();

    @Query(value =" SELECT count(code_flight)\n" +
            "FROM flight where code_flight = ?", nativeQuery = true,
            countQuery = "select count(*) from (SELECT  count(seat.id), seat.id\n" +
            "FROM flight JOIN seat ON flight.id = seat.id_flight \n" +
            "WHERE flight.code_flight = ?")
    List<Flight> countFlight(String codeFlight);

    @Query(value = "select flight.id,code_flight,date_end,date_start,del_flag_flight, flight.from_flight, flight.to_flight,id_airline_type \n" +
            "from flight\n" +
            "join airline_type \n" +
            "on flight.id_airline_type = airline_type.id \n" +
            "where del_flag_flight =true order by airline_type.price_airline asc limit 10",nativeQuery = true )
    List<Flight> selectTopTen();

}
