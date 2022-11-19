package com.codegym.model.hotel;

import com.codegym.model.Flight;
import com.codegym.model.Seat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityDefine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cityId")
    private Long cityId;

    @Column(name = "cityName")
    private String cityName;

    @OneToMany(mappedBy = "hotelId",cascade = CascadeType.ALL)
    private List<Hotel> hotels;
}
