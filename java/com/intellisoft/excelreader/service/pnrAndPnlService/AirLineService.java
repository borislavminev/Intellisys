package com.intellisoft.excelreader.service.pnrAndPnlService;

import com.intellisoft.excelreader.domain.entity.AirLine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface AirLineService {

    List<AirLine> findAllAirline();

    AirLine findAirlineByName(String name);
    AirLine findByIataCode(String iataCode);
    AirLine findByIcaoCode(String icaoCode);
    List<AirLine> getAirLinesByIdAfterAndIdBefore(Long idAfter,Long idBefore);
    void deleteAirline(Long id);

    void deleteAirlineByObj(AirLine airLine);

    void saveAirline(AirLine airLine);
}