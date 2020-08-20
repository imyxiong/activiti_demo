package com.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("tb_user")
public class User {
    @TableId(type= IdType.AUTO)
    private Long id;
    private String type;
    private String department;
    private String name;
    public void setId(Long id){
        this.id=id;
    }
    public Long getId(){
        return this.id;
    }
    public void setType(String type){
        this.type=type;
    }
    public String getType(){
        return this.type;
    }
    public void setDepartment(String department){
        this.department=department;
    }
    public String getDepartment(){
        return this.department;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", department='" + department + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
