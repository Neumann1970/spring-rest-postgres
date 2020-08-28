/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.mmj.service1.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.mmj.service1.domain.valueobject.View.Bar;
import com.project.mmj.service1.domain.valueobject.View.Foo;
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
@Entity
@Data
@NoArgsConstructor
@Table(name = "Users")
public class User implements Serializable {

    private static final long serialVersionUID = -3009157732242241606L;

    public User(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
        created = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Foo.class)
    private long id;

    @Column(name = "name")
    @JsonView(Bar.class) 
    private String name;

    @Column(name = "email")
    @JsonView(Bar.class) 
    private String email;

    @Column(name = "age")
    @JsonView(Bar.class) 
    private int age;

    @CreatedDate
    @Column(name = "created", nullable = false, updatable = false)
    @JsonView(Bar.class) 
    private Date created;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return String.format("Customer[firstName='%s', email='%s']", getName(), getEmail());
    }
}
