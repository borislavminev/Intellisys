package com.intellisoft.excelreader.service.pnrAndPnlService;

import com.intellisoft.excelreader.domain.entity.AirLine;
import com.intellisoft.excelreader.domain.entity.AirPort;
import com.intellisoft.excelreader.repository.AirPortRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.PushBuilder;
import java.util.*;

@Service
public class AirPortServiceImpl implements AirPortService{
    private AirPortRepository airPortRepository;

    public AirPortServiceImpl(AirPortRepository airPortRepository) {
        this.airPortRepository = airPortRepository;
    }

    @Override
    public List<AirPort> findAllAirports() {
        return airPortRepository.findAll();
    }


    @Override
    public AirPort findAllAirportsByAirportName(String airportName) {
        return airPortRepository.findByAirportName(airportName);
    }

    @Override
    public AirPort findAirPortByIataCode(String airportIataCode) {
        return airPortRepository.findByIataApCode(airportIataCode);
    }
    @Override
    public AirPort findAirPortByIcaoCode(String airportIcaoCode) {
        return airPortRepository.findByIcaoApCode(airportIcaoCode);
    }

    @Override
    public AirPort findAirPortName(String name) {
        return airPortRepository.findByAirportName(name);
    }


    @Override
    public void deleteAirPort(Long id) {
         airPortRepository.deleteById(id);
    }

    @Override
    public void deleteAirPortByObj(AirPort airPort) {
        airPortRepository.delete(airPort);
    }

    @Override
    public void saveAirPort(AirPort airPort) {
        airPortRepository.save(airPort);
    }


    @Override
    public List<AirPort> findAllAirportsByCountryCode(String countryCode) {
        return airPortRepository.findByCountryCode(countryCode);
    }

    public void deleteDuplicateItems(){
        List <AirPort> airport = airPortRepository.findAll(Sort.by(Sort.Order.asc("iataApCode")));
       Set airportSet = new HashSet<>();

       for (AirPort port: airport
             ) {
            if(!airportSet.add(port.getIataApCode())){
                airPortRepository.delete(port);
            }else{
                System.out.println(port.getAirportName());
            }
        }


    }


}
