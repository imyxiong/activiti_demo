package com.demo.service;

import com.demo.entity.User;
import com.demo.mapper.UserMapper;
import com.demo.result.TaskResult;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AskLeaveTaskServiceImpl implements AskLeaveTaskService{
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProcessEngine processEngine;
    private final String PROCESS_DEFINITION_KEY="AskLeaveTask";
    private final String PROCESS_RESOOURCES="processes/AskLeaveTask.bpmn";
    private final String DEPARTMENT_MANAGER_TYPE="department_manager";
    private final String MANAGER_TYPE="manager";

    /**
     * @Author: imyxiong@163.com
     * @Description:部署流程
     * @Date: 下午2:11 2020/8/20
     */
    public String deployProcess() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                // 给流程起一个名字
                .name("购物流程")
                // 添加流程图资源文件
                .addClasspathResource(PROCESS_RESOOURCES)
                // 添加流程图片资源文件
//            .addClasspathResource("processes/AskLeave.png")
                // 部署
                .deploy();
        System.out.println("ID: " + deploy.getId());
        return deploy.getId();
    }

    /**
     * @Author: imyxiong@163.com
     * @Description:由用户启动一个请假流程
     * @Date: 上午11:35 2020/8/20
     * @param uid 用户id
     */
    public String startProcess(Long uid) {
        User user=userMapper.selectById(uid);
        if(null==user) {
            return "error";
        }
        Map<String, Object> variables = new HashMap<>();
        variables.put("username", user.getName());
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY, String.valueOf(user.getId()),variables);
        System.out.println("Id: " + instance.getId());
        return instance.getId();
    }
    /**
     * @Author: imyxiong@163.com
     * @Description:查询当前用户的代办流程
     * @Date: 上午11:36 2020/8/20
     * @param uid 用户id
     */
    public List<TaskResult> queryTask(Long uid) {
        User user=userMapper.selectById(uid);
        if(null==user) {
            return null;
        }
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(user.getName())
                // 分页查询
                // .listPage(firstResult, maxResults)
                // 排序
                // .orderByTaskCreateTime().desc()
                .list();
        List<TaskResult> results=new ArrayList<>();
        for (Task task : tasks) {
            TaskResult result=new TaskResult();
            result.setId(task.getId());
            result.setAssignee(task.getAssignee());
            result.setName(task.getName());
            result.setComments(taskService.getProcessInstanceComments(task.getProcessInstanceId()));
            results.add(result);
        }
        return results;
    }
    /**
     * @Author: imyxiong@163.com
     * @Description:普通用户提交请假
     * @Date: 上午11:36 2020/8/20
     * @param taskId 任务id
     * @param uid 用户id
     * @param comment
     */
    public String workerCommit(String taskId,Long uid,String comment){
        User user=userMapper.selectById(uid);
        if(null==user) {
            return "error";
        }
        String departmentManagerName = userMapper.findOneByDepartmentAndType(user.getDepartment(),DEPARTMENT_MANAGER_TYPE);
        if(null==departmentManagerName){
            return "error";
        }
        Map<String,Object> variables=new HashMap<>();
        variables.put("username",departmentManagerName);
        completeTask(taskId,comment,user.getName(),variables);
        return "success";
    }
    /**
     * @Author: imyxiong@163.com
     * @Description:
     * @Date: 上午11:38 2020/8/20
     * @param taskId 任务id
     * @param uid 用户id
     * @param comment 部门经理批注
     * @param pass 是否通过
     */
    public String departmentCommit(String taskId,Long uid,String comment,String pass){
        User user=userMapper.selectById(uid);
        if(null==user){
            return "error";
        }
        System.out.println(user);
        String managerName=userMapper.findOneByType(MANAGER_TYPE);
        if(null==managerName){
            return "error";
        }
        Map<String,Object> variables=new HashMap<>();
        variables.put("username",managerName);
        variables.put("pass",pass);
        completeTask(taskId,comment,user.getName(),variables);
        return "success";
    }
    /**
     * @Author: imyxiong@163.com
     * @Description:
     * @Date: 上午11:39 2020/8/20
     * @param taskId 任务id
     * @param uid 用户id
     * @param comment 经理批注
     * @param pass 是否通过
     */
    public String managerCommit(String taskId,Long uid,String comment,String pass){

        User user=userMapper.selectById(uid);
        if(null==user){
            return "error";
        }
        System.out.println(user);
        Map<String,Object> variables=new HashMap<>();
        variables.put("pass",pass);
        completeTask(taskId,comment,user.getName(),variables);
        return "success";
    }
    /**
     * @Author: imyxiong@163.com
     * @Description:
     * @Date: 上午11:39 2020/8/20
     * @param taskId 任务id
     * @param comment 批注
     * @param authenticatedUserId 是谁批注的
     * @param variables 变量
     */
    private void completeTask(String taskId,String comment,String authenticatedUserId,Map<String, Object> variables) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstanceId = task.getProcessInstanceId();
        Authentication.setAuthenticatedUserId(authenticatedUserId);//设置是谁批注的
        taskService.addComment(taskId, processInstanceId, comment);
        taskService.complete(taskId,variables);
    }
}
