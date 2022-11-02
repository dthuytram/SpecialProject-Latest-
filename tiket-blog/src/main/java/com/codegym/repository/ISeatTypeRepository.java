package com.codegym.repository;

import com.codegym.model.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISeatTypeRepository extends JpaRepository<SeatType,Long> {
}
