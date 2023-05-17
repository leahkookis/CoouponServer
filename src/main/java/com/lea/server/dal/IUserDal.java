package com.lea.server.dal;


import com.lea.server.beans.SuccessfulLoginData;
import com.lea.server.beans.UserDto;
import com.lea.server.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserDal extends CrudRepository<User, Long> {



    @Query("SELECT new com.lea.server.beans.UserDto(u.id, u. userName, u. password, u.userType, u.company.name) " +
            "FROM User u JOIN Company com ON u.company.id = com.id " +
            "WHERE u.company.id= :companyId")
    List<UserDto> getUsersByCompanyID(@Param("companyId") long companyId, Pageable pageable);

    @Query("SELECT new com.lea.server.beans.UserDto(u.id, u. userName, u. password, u.userType, u.company.name) " +
            "FROM User u JOIN Company com ON u.company.id = com.id")
    List<UserDto> findAll(Pageable pageable);

    @Query("SELECT new com.lea.server.beans.UserDto(u.id, u. userName, u. password, u.userType, u.company.name) " +
            "FROM User u JOIN Company com ON u.company.id = com.id " +
            "WHERE u.id= :id")
    UserDto findById(@Param("id") long id);

    @Query("SELECT new com.lea.server.beans.SuccessfulLoginData(u.id, u.userType, u.company.id) " +
            "FROM User u where u.userName= :userName and u.password= :password")
    SuccessfulLoginData login(@Param("userName") String userName, @Param("password") String password);

    boolean existsByUserName(String userName);


}

