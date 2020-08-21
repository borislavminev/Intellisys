package com.intellisoft.excelreader.repository;
import com.intellisoft.excelreader.domain.entity.AirLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AirLineRepository extends JpaRepository<AirLine,Long> {

    @Override
    Optional<AirLine> findById(Long aLong);

    AirLine findByName(String name);

    AirLine findByIataCode(String iataCode);

    AirLine findByIcaoCode(String icaoCode);

    List<AirLine> getAirLinesByIdAfterAndIdBefore(Long idAfter,Long idBefore);

    @Override
    List<AirLine> findAll();

    @Override
    void deleteById(Long aLong);
    @Override
    void delete(AirLine airLine);

    @Override
    <S extends AirLine> S save(S s);

}
