package com.intellisoft.excelreader.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "airports")
public class AirPort {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false, unique = true)
    private Long id;
    @Column(name = "IATA_AP_CODE")
    private String iataApCode;
    @Column(name = "VALID_SINCE")
    private  String validSince;
    @Column(name = "VALID_UNTIL")
    private String validUntil;
    @Column(name = "ICAO_CODE")
    private String icaoApCode;
    @Column(name = "AP_NAME")
    private String airportName;
    @Column(name = "COUNTRY_CODE")
    private String countryCode;
    @Column(name = "DST_ZONE_CODE")
    private String dstZoneCode;
    @Column(name = "AREA")
    private String area;
    @Column(name = "CATEGORY")
    private String category;
    @Column(name = "TIME_ZONE")
    private String timeZone;
    @Column(name = "COORD_LONGITUDE")
    private String longitude;
    @Column(name = "COORD_LATITUDE")
    private String latitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIataApCode() {
        return iataApCode;
    }

    public void setIataApCode(String iataApCode) {
        this.iataApCode = iataApCode;
    }

    public String getValidSince() {
        return validSince;
    }

    public void setValidSince(String validSince) {
        this.validSince = validSince;
    }

    public String getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(String validUntil) {
        this.validUntil = validUntil;
    }

    public String getIcaoApCode() {
        return icaoApCode;
    }

    public void setIcaoApCode(String icaoApCode) {
        this.icaoApCode = icaoApCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDstZoneCode() {
        return dstZoneCode;
    }

    public void setDstZoneCode(String dstZoneCode) {
        this.dstZoneCode = dstZoneCode;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
