/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.mmj.service1.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonView;

/**
 *
 * @author Neumann
 */
public class Alert {
    
   
    private String msg;
   

    public Alert(String msg){
        this.msg = msg;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

   
}
