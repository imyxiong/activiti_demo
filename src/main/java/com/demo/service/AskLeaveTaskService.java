package com.demo.service;

import com.demo.result.TaskResult;
import java.util.List;

public interface AskLeaveTaskService {
    public String deployProcess();
    public String startProcess(Long uid);
    public List<TaskResult> queryTask(Long uid);
    public String workerCommit(String taskId,Long uid,String comment);
    public String departmentCommit(String taskId,Long uid,String comment,String pass);
    public String managerCommit(String taskId,Long uid,String comment,String pass);
}
