package com.intellisoft.excelreader.repository;


import com.intellisoft.excelreader.domain.entity.User;
import com.intellisoft.excelreader.domain.model.bindingModel.UserBindingModel;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository  extends JpaRepository<User,Long> {

     @Override
    <S extends User> List<S> findAll(Example<S> example);
    Optional<User> findByEmail(String email);

}
