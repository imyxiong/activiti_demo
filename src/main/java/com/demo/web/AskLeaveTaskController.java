package com.demo.web;

import com.demo.result.TaskResult;
import com.demo.service.AskLeaveTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/leave")
public class AskLeaveTaskController {
    @Autowired
    private AskLeaveTaskService askLeaveTaskService;
    /**
     * @Author: imyxiong@163.com
     * @Description:部署流程API
     * @Date: 下午2:10 2020/8/20
     */
    @GetMapping("/deploy")
    public String deployProcess(){
        return askLeaveTaskService.deployProcess();
    }
    /**
     * @Author: imyxiong@163.com
     * @Description:用户启动请假流程API
     * @Date: 下午2:12 2020/8/20
     * @param uid 用户id
     */
    @GetMapping("/start")
    public String startProcess(Long uid){
        return askLeaveTaskService.startProcess(uid);
    }
    /**
     * @Author: imyxiong@163.com
     * @Description:流程信息查询API
     * @Date: 下午2:12 2020/8/20
     * @param uid 用户id
     */
    @GetMapping("/query")
    public List<TaskResult> queryTask(Long uid){
        return askLeaveTaskService.queryTask(uid);
    }
    /**
     * @Author: imyxiong@163.com
     * @Description:员工提交请教申请API
     * @Date: 下午2:12 2020/8/20
     * @param taskId 流程id
     * @param uid 员工用户id
     * @param comment
     */
    @GetMapping("/commit/worker")
    public String workerCommit(String taskId,Long uid,String comment){
        return askLeaveTaskService.workerCommit(taskId,uid,comment);
    }
    /**
     * @Author: imyxiong@163.com
     * @Description:部门经理审批API
     * @Date: 下午2:12 2020/8/20
     * @param taskId 流程id
     * @param uid 部门经理用户id
     * @param comment 审批信息
     * @param pass  是否通过审批
     */
    @GetMapping("/commit/department")
    public String departmentCommit(String taskId,Long uid,String comment,String pass){
        return askLeaveTaskService.departmentCommit(taskId,uid,comment,pass);
    }
    /**
     * @Author: imyxiong@163.com
     * @Description:经理审批API
     * @Date: 下午2:12 2020/8/20
     * @param taskId 流程id
     * @param uid 经理用户id
     * @param comment 经理审批信息
     * @param pass 是否通过审批
     */
    @GetMapping("/commit/manager")
    public String managerCommit(String taskId,Long uid,String comment,String pass){
        return askLeaveTaskService.managerCommit(taskId,uid,comment,pass);
    }
}
