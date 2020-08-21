package com.intellisoft.excelreader.service;

import com.intellisoft.excelreader.domain.entity.Role;
import com.intellisoft.excelreader.repository.RolesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class RoleModelServiceImpl implements RoleModelService {

    private final RolesRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleModelServiceImpl(RolesRepository roleRepository, ModelMapper mapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = mapper;
    }

    @Override
    public void seedRolesInDb() {
        if (this.roleRepository.count() == 0) {
            this.roleRepository.saveAndFlush(new Role("ROLE_USER"));
            this.roleRepository.saveAndFlush(new Role("ROLE_MODERATOR"));
            this.roleRepository.saveAndFlush(new Role("ROLE_ADMIN"));
            this.roleRepository.saveAndFlush(new Role("ROLE_ROOT"));
        }
    }

//    @Override
//    public void assignUserRoles(UserServiceModel userServiceModel, long numberOfUsers) {
//        if (numberOfUsers == 0) {
//            userServiceModel
//                    .setAuthorities(this.roleRepository
//                            .findAll()
//                            .stream()
//                            .map(r -> this.modelMapper.map(r, RoleServiceModel.class))
//                            .collect(Collectors.toSet()));
//        }
//    }


    @Override
    public Set<RoleModelService> findAllRoles() {
        return this.roleRepository.findAll()
                .stream()
                .map(r -> this.modelMapper.map(r, RoleModelService.class))
                .collect(Collectors.toSet());
    }

    @Override
    public RoleModelService findByName(String name) {
        return this.modelMapper.map(this.roleRepository.findByName(name), RoleModelService.class);
    }




}
