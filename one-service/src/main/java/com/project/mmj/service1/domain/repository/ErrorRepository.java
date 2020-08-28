/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.mmj.service1.domain.repository;

import com.project.mmj.service1.domain.model.entity.ErrorProfile;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Neumann
 */
public interface ErrorRepository extends JpaRepository<ErrorProfile, Long> {
     @Query(value = "SELECT * FROM ERROR e ORDER BY e.created DESC LIMIT 1", nativeQuery = true)
    Collection<ErrorProfile> findFirstByOrderByDateDesc();
}
