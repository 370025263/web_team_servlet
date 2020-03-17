package com.major.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Team {
    public String teamname ;

    public String getname(){

        return teamname;
    }
    public void setname(String input){
        teamname=input;

    }

    public Team() {
    }



}
