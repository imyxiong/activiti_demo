package com.demo.result;

import org.activiti.engine.task.Comment;

import java.util.List;

public class TaskResult {
    private String id;
    private String assignee;
    private String name;
    private List<Comment> comments;
    public void setId(String id){
        this.id=id;
    }
    public String getId(){
        return this.id;
    }
    public void setAssignee(String assignee){
        this.assignee=assignee;
    }
    public String getAssignee(){
        return this.assignee;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
    public void setComments(List<Comment>comments){
        this.comments=comments;
    }
    public List<Comment> getComments(){
        return this.comments;
    }

    @Override
    public String toString() {
        return "TaskResult{" +
                "\n任务ID:'" + id + '\'' +
                "\n代理人: '" + assignee + '\'' +
                "\n任务名:'" + name + '\'' +
                "\n}";
    }
}
