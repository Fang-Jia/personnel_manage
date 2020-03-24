package com.xf.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xf.entity.Department;
import com.xf.entity.Employee;
import com.xf.service.EmplService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 员工管理     控制层
 */
public class EmplAction extends ActionSupport implements ModelDriven {
//    注入EmplService属性
    private EmplService emplService;
    public void setEmplService(EmplService emplService) { this.emplService = emplService; }

//    注入封装前端数据属性
    private Employee employee = new Employee();
    @Override
    public Object getModel() { return employee; }

//    定义获取request属性
    private HttpServletRequest request;

//    封装向前端传递的数据格式
    private Map<String,Object> map = new HashMap<>();
    public Map<String, Object> getMap() { return map; }
    public void setMap(Map<String, Object> map) { this.map = map; }

    /**
     * 员工管理主页
     */
    public String emplHome(){ return "home"; }

    /**
     * 添加员工页面
     */
    public String addEmpl(){
        return "add";
    }

    /**
     * 编辑员工信息页面
     */
    public String edit(){
        return "edit";
    }

    /**
     * 获取员工信息   （分页）
     */
    public String pageInfo(){
//        获取前端传递的分页参数
        request = ServletActionContext.getRequest();
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));

//        获取数据记录总数
        int count = emplService.getEmplInfo().size();
        List<Employee> employeeList = emplService.getPageInfo(page,limit);
//        封装成表格需要的数据格式
        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",employeeList);

        return SUCCESS;
    }

    /**
     * 添加员工信息
     */
    public void addEmplInfo(){
        System.out.println(employee.toString());
        emplService.addEmplInfo(employee);
    }

    /**
     * 搜索员工信息
     */
    public String search(){
        List<Employee> employeeList = emplService.search(employee.getName());
        int count = employeeList.size();

        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",employeeList);

        return SUCCESS;
    }

    /**
     * 删除员工信息
     */
    public void delEmpl(){
        emplService.delEmpl(employee);
    }

    /**
     * 通过ID搜素一个信息
     */
    public String searchById(){
        Employee employee1 = emplService.searchById(employee.getEid());

        map.put("code",0);
        map.put("msg","");
        map.put("data",employee1);
        return SUCCESS;
    }

    /**
     * 编辑员工信息
     */
    public void editEmpl(){
        emplService.editEmpl(employee);
    }
}
