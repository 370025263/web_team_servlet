package com.major.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@Scope("singleton")
public class Teams {//Teams是单例的，而team则是多例的
    private List<Team> theTeams = new ArrayList();//这里面耦合有点严重，不知道能不能解决
    private int length;


    public Teams() {
        this.length=0;
    }

    public int addTeam(String teamname){//这个方法不知道是否可以实现注入，在servlet中注解直接注入初始值。
        Team freshteam=new Team();//这个可以交给容器来

        for(int i=0;i<this.length;i++){
            if(teamname.equals(this.theTeams.get(i))){
               return 0;//如果有相同的name的，直接跳过。剩下的就是没有的
            }

        }

        freshteam.setname(teamname);
        this.theTeams.add(freshteam);
        this.length++;
        return 0;

    }
    public Team getTeam(int i){//这个方法不知道是否可以实现注入
        return this.theTeams.get(i);
    }


    public  void delete(String teamname){//这个方便直接删除，不用再写在servlet和逻辑里面了

        for(int i=0;i<this.length;i++){

            if(teamname.equals(this.theTeams.get(i).teamname)){
                this.theTeams.remove(i);
                this.length--;
            }

        }

    }

    int getlength() {
        int length = this.theTeams.size();
        return length;
    }
}
