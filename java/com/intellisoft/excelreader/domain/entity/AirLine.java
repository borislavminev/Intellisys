package com.intellisoft.excelreader.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "air_lines")
public class AirLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false, unique = true)
    private Long id;;
    @Column(name = "IATA")
    private String iataCode;
    @Column(name = "ICAO")
    private String icaoCode;
    @Column(name = "Airline")
    private String name;
    @Column(name = "Call_sign")
    private String callSigh;
    @Column(name = "Country_Region")
    private String countryRegion;
    @Column(name = "Comments")
    private String comment;

    public AirLine() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getIcaoCode() {
        return icaoCode;
    }

    public void setIcaoCode(String icaoCode) {
        this.icaoCode = icaoCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCallSigh() {
        return callSigh;
    }

    public void setCallSigh(String callSigh) {
        this.callSigh = callSigh;
    }

    public String getCountryRegion() {
        return countryRegion;
    }

    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
