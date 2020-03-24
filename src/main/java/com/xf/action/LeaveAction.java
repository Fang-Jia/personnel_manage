package com.xf.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xf.entity.Department;
import com.xf.entity.Leave;
import com.xf.service.DeptService;
import com.xf.service.LeaveService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门管理   控制层
 */
public class LeaveAction extends ActionSupport implements ModelDriven {

//    封装向前端传递的数据格式
    private Map<String,Object> map = new HashMap<>();
    public Map<String, Object> getMap() { return map; }
    public void setMap(Map<String, Object> map) { this.map = map; }

    //    注入DeptService属性
    private LeaveService leaveService;
    public void setLeaveService(LeaveService leaveService) { this.leaveService = leaveService; }

    //    封装前端传递的数据
    private Leave leave = new Leave();
    @Override
    public Object getModel() {
        return leave;
    }

//    定义获取request属性
    private HttpServletRequest request;

    /**
     * 申请请假主页
     */
    public String home(){
        return "home";
    }

    /**
     * 提交申请信息
     */
    public String addInfo(){
        int number = leaveService.addInfo(leave);
        ServletContext servletContext = ServletActionContext.getServletContext();
        servletContext.setAttribute("number",number);
        return "home";
    }

    /**
     * 审批请假 主页
     */
    public String lookMain(){
        return "main";
    }

    /**
     * 查询请假信息
     */
    public String pageInfo(){
//        获取前端传递的分页参数
        request = ServletActionContext.getRequest();
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));

//        获取数据记录总数
        int count = leaveService.getLeaveInfo().size();
        List<Leave> leaveList = new ArrayList<>();
        leaveList = leaveService.getPageInfo(page,limit);

//        封装成表格需要的数据格式
        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",leaveList);
        return SUCCESS;
    }

    /**
     * 同意审批
     */
    public void agree(){
        leaveService.agree(leave.getLid());
        ServletContext servletContext = ServletActionContext.getServletContext();
        int number = (int) servletContext.getAttribute("number");
        servletContext.setAttribute("number",number-1);
    }

    /**
     * 拒绝审批
     */
    public void refuse(){
        leaveService.refuse(leave.getLid());
        ServletContext servletContext = ServletActionContext.getServletContext();
        int number = (int) servletContext.getAttribute("number");
        servletContext.setAttribute("number",number-1);
    }

    /**
     * 查看请假信息   主页
     */
    public String detail(){
        return "detail";
    }

    /**
     * 查看请假信息
     */
    public String detailInfo(){
        Leave leave1 = leaveService.detailInfo(leave.getLid());

        map.put("code",0);
        map.put("msg","");
        map.put("data",leave1);
        return SUCCESS;
    }
}
