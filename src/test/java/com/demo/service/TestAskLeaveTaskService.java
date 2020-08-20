package com.demo.service;

import com.demo.result.TaskResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAskLeaveTaskService {
    @Autowired
    private AskLeaveTaskService askLeaveTaskService;
    @Test
    public void deployProcess(){
        askLeaveTaskService.deployProcess();
    }
    @Test
    public void startProcess(){
        Long uid=1L;
        String taskid=askLeaveTaskService.startProcess(uid);
        System.out.println(taskid);
    }
    @Test
    public void queryTask(){
        Long uid=3L;
        List<TaskResult>results=askLeaveTaskService.queryTask(uid);
        for(TaskResult result:results) {
            System.out.println(result);
        }
    }
    @Test
    public void workerCommit(){
        String taskId="2507";
        Long uid=1L;
        String comment="提交请假申请";
        String result=askLeaveTaskService.workerCommit(taskId,uid,comment);
        System.out.println(result);
    }
    public void departmentCommit(){
        String taskId="5005";
        Long uid=3L;
        String comment="department_manager同意";
        String pass="1";
        String result=askLeaveTaskService.departmentCommit(taskId,uid,comment,pass);
        System.out.println(result);
    }
    @Test
    public void managerCommit(){
        String taskId="7505";
        Long uid=5L;
        String comment="manager同意";
        String pass="1";
        String result=askLeaveTaskService.managerCommit(taskId,uid,comment,pass);
        System.out.println(result);
    }
}
