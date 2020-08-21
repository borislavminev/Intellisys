package com.intellisoft.excelreader.service;



import org.apache.poi.util.Internal;
import org.sonatype.guice.plexus.config.Roles;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


public interface RoleModelService {

    void seedRolesInDb();

//    void assignUserRoles(UserServiceModel userServiceModel, long numberOfUsers);

    Set<RoleModelService> findAllRoles();

    RoleModelService findByName(String name);


}
