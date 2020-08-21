package com.intellisoft.excelreader.service.pnrAndPnlService;

import com.intellisoft.excelreader.domain.entity.AirLine;
import com.intellisoft.excelreader.domain.entity.AirPort;

import java.util.List;

public interface AirPortService {


    public List<AirPort> findAllAirports();
    public AirPort findAllAirportsByAirportName(String airportName);
    public AirPort findAirPortByIataCode(String airportIataCode);
    public AirPort findAirPortByIcaoCode(String airportIcaoCode);
    public AirPort findAirPortName(String name);
    public void deleteAirPort(Long id);
    public void deleteAirPortByObj(AirPort airPort);
    public void saveAirPort(AirPort airPort);
    public List<AirPort> findAllAirportsByCountryCode(String countryCode);
    public void deleteDuplicateItems();

}
