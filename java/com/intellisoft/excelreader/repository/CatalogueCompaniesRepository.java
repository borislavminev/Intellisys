package com.intellisoft.excelreader.repository;

import com.intellisoft.excelreader.domain.entity.CatalogueCompanies;
import com.intellisoft.excelreader.domain.entity.Status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.Sort.Direction.ASC;


@Repository
public interface CatalogueCompaniesRepository extends JpaRepository<CatalogueCompanies, Long> {

    @Override
    void deleteById(Long aLong);

    @Override
    <S extends CatalogueCompanies> S save(S s);

    @Override
    Optional<CatalogueCompanies> findById(Long aLong);

    boolean existsByPartnerName(String name);

    @Override
    Page<CatalogueCompanies> findAll(Pageable pageable);

    @Override
    List<CatalogueCompanies> findAll();

    List<CatalogueCompanies> findAllByPartnerAddressContainingAndStatusEquals(String cityName,Status status);

    @Override
    <S extends CatalogueCompanies> S saveAndFlush(S s);

    @Override
    <S extends CatalogueCompanies> List<S> saveAll(Iterable<S> iterable);


}
