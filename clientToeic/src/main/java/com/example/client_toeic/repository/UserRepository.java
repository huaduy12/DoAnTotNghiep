package com.example.client_toeic.repository;

import com.example.client_toeic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    @Query("select u from User u where u.isDeleted = false")
    List<User> findAll();
    @Query("select u from User u where u.userName = :username and u.isDeleted = false")
    User findByUserName(@Param("username") String username);

    @Query("select u from User u where u.userName = :username and u.isDeleted = false and u.active = true")
    User findByUserNameActive(@Param("username") String username);

    @Query("select u from User u where u.email = :email and u.isDeleted = false and u.active = true")
    User findByEmailAndActive(@Param("email") String email);

    @Query("select u from User u where u.email = :email and u.isDeleted = false")
    User findByEmail(@Param("email") String email);

    @Query("select u from User u where u.phoneNumber = :phoneNumber and u.isDeleted = false")
    User findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query("select u from User u where u.userName = :usernameNew and not(u.userName = :usernameOld and u.isDeleted = false)")
    User findByUsernameExist(@Param("usernameNew") String usernameNew,@Param("usernameOld") String usernameOld);

    @Query("select u from User u where u.email = :emailNew and not(u.email = :emailOld) and u.isDeleted = false" )
    User findByEmailExist(@Param("emailNew") String emailNew,@Param("emailOld") String emailOld);

    @Query("select u from User u where u.phoneNumber = :phoneNumberNew and not(u.phoneNumber = :phoneNumberOld) and u.isDeleted = false")
    User findByPhoneNumberExist(@Param("phoneNumberNew") String phoneNumberNew,@Param("phoneNumberOld") String phoneNumberOld);

    @Query("select u from User u where u.email = :email and u.provider = :provider")
    User findByEmailAndProvider(String email,String provider);
}
