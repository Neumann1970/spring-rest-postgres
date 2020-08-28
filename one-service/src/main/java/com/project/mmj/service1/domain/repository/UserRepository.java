/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.mmj.service1.domain.repository;

import com.project.mmj.service1.domain.model.entity.User;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Neumann
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM USERS u ORDER BY u.created DESC LIMIT 1", nativeQuery = true)
    Collection<User> findFirstByOrderByDateDesc();

    @Query(value = "SELECT * FROM USERS u WHERE u.email = ?1", nativeQuery = true)
    Collection<User> findByEmail(String email);
}
