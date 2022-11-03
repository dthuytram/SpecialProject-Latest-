package com.codegym.dto.IDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightDTO {
    private Long id;

    private String code_flight;

    private String from_flight;

    private String to_flight;

    private String date_start;

    private String date_end;
}
