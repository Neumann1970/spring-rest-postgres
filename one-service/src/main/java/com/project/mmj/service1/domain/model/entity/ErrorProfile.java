/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.mmj.service1.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.mmj.service1.domain.valueobject.View;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

/**
 *
 * @author Neumann
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "error")
public class ErrorProfile implements Serializable {

    public ErrorProfile(String msg) {
        this.msg = msg;
        this.created = new Date();

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "msg")
    private String msg;

    @CreatedDate
    @Column(name = "created", nullable = false, updatable = false)
    private Date created;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
