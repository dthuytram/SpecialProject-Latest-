package com.codegym.model.hotel;

import java.util.List;

import javax.persistence.*;

import com.codegym.model.Countries;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author Dtt3020 Nov 2, 2022
 */
@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotelId")
    private Long hotelId;
    @Column(name = "name")
    private String hotelName;
    @Column(name = "rating")
    private int rating;
    @Column(name = "city")
    private int cityId;
    @Column(name = "pincode")
    private int pincode;
    @Column(name = "contact")
    private String contact;
    @Column(name = "email")
    private String email;
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "hotel",cascade = CascadeType.ALL)
    private List<Room> rooms;

    @ManyToOne
    @JoinColumn(name = "hotel")
    private CityDefine city;
    /**
     *
     */
    public Hotel() {

    }

    /**
     *
     * @param hotelId
     * @param hotelName
     * @param rating
     * @param city
     * @param pincode
     * @param contact
     * @param email
     * @param description
     */
    public Hotel(Long hotelId, String hotelName, int rating, int city, int pincode, String contact, String email,
                 String description) {
        super();
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.rating = rating;
        this.cityId = city;
        this.pincode = pincode;
        this.contact = contact;
        this.email = email;
        this.description = description;
    }

    /**
     * @return the hotelId
     */
    public Long getHotelId() {
        return hotelId;
    }

    /**
     * @param hotelId the hotelId to set
     */
    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    /**
     * @return the hotelName
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * @param hotelName the hotelName to set
     */
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    /**
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * @return the city
     */
    public int getCity() {
        return cityId;
    }

    /**
     * @param city the city to set
     */
    public void setCity(int city) {
        this.cityId = city;
    }

    /**
     * @return the pincode
     */
    public int getPincode() {
        return pincode;
    }

    /**
     * @param pincode the pincode to set
     */
    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    /**
     * @return the contactNum
     */
    public String getContact() {
        return contact;
    }

    /**
     *
     * @param contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonManagedReference
    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

}