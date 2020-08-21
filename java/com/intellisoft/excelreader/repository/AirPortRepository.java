package com.intellisoft.excelreader.repository;

import com.intellisoft.excelreader.domain.entity.AirPort;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AirPortRepository extends JpaRepository<AirPort,Long> {
    @Override
    Optional<AirPort> findById(Long aLong);

    @Override
    <S extends AirPort> S save(S s);

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(AirPort airPort);

    List<AirPort> findAll();
    AirPort findByAirportName(String name);
    AirPort findByIataApCode(String iataCode);
    AirPort findByIcaoApCode(String icaoCode);
    List<AirPort> findByCountryCode(String countryCode);

}
