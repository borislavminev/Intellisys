package com.intellisoft.excelreader.service.pnrAndPnlService;

import com.intellisoft.excelreader.domain.entity.AirLine;
import com.intellisoft.excelreader.repository.AirLineRepository;
import com.intellisoft.excelreader.service.pnrAndPnlService.AirLineService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirLineServiceImpl implements AirLineService {

    private AirLineRepository airLineRepository;

    public AirLineServiceImpl(AirLineRepository airLineRepository) {
        this.airLineRepository = airLineRepository;
    }

    @Override
    public List<AirLine> findAllAirline() {
        return airLineRepository.findAll();
    }

    @Override
    public AirLine findAirlineByName(String name) {
        return airLineRepository.findByName(name);
    }

    @Override
    public AirLine findByIataCode(String iataCode) {
        return airLineRepository.findByIataCode(iataCode);
    }

    @Override
    public AirLine findByIcaoCode(String icaoCode) {
        return airLineRepository.findByIcaoCode(icaoCode);
    }

    @Override
    public List<AirLine> getAirLinesByIdAfterAndIdBefore(Long idAfter, Long idBefore) {
        return airLineRepository.getAirLinesByIdAfterAndIdBefore(idAfter,idBefore);
    }


    @Override
    public void deleteAirline(Long id) {
        AirLine airLine = airLineRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Airline not found"));
        airLineRepository.delete(airLine);
    }

    @Override
    public void deleteAirlineByObj(AirLine airLine) {
        airLineRepository.delete(airLine);

    }

    @Override
    public void saveAirline(AirLine airLine) {
        airLineRepository.save(airLine);
    }




}
