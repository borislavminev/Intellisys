package com.intellisoft.excelreader.repository;

import com.intellisoft.excelreader.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface RolesRepository extends JpaRepository<Role,Long> {


    Role findByName(String name);


}
