package com.xf.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xf.entity.Department;
import com.xf.service.DeptService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门管理   控制层
 */
public class DeptAction extends ActionSupport implements ModelDriven {

//    封装向前端传递的数据格式
    private Map<String,Object> map = new HashMap<>();
    public Map<String, Object> getMap() { return map; }
    public void setMap(Map<String, Object> map) { this.map = map; }

    //    注入DeptService属性
    private DeptService deptService;
    public void setDeptService(DeptService deptService) {
        this.deptService = deptService;
    }

//    封装前端传递的数据
    private Department department = new Department();
    @Override
    public Object getModel() {
        return department;
    }

//    定义获取request属性
    private HttpServletRequest request;

    /**
     * 部门管理主页
     */
    public String deptHome(){
        return "home";
    }

    /**
     * 添加部门页面
     */
    public String addDept(){
        return "add";
    }

    /**
     * 编辑部门页面
     */
    public String edit(){
        return "edit";
    }

    /**
     * 获取部门信息   （分页）
     */
    public String pageInfo(){
//        获取前端传递的分页参数
        request = ServletActionContext.getRequest();
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));

//        获取数据记录总数
        int count = deptService.getDeptInfo().size();
        List<Department> deptList = deptService.getPageInfo(page,limit);

//        封装成表格需要的数据格式
        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",deptList);

        return SUCCESS;
    }

    /**
     * 删除部门
     */
    public void delDept(){
        request = ServletActionContext.getRequest();
        int did = Integer.parseInt(request.getParameter("did"));
        deptService.delDept(did);
    }

    /**
     * 添加部门
     */
    public void addDeptInfo(){
        deptService.addDept(department);
    }

    /**
     * 搜索部门信息
     * @return
     */
    public String search(){
        List<Department> departmentList = deptService.search(department.getName());
        int count = deptService.search(department.getName()).size();
        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",departmentList);

        return SUCCESS;
    }

    /**
     * 通过ID搜索部门信息
     */
    public String searchById(){
        Department department1 = deptService.searchById(department.getDid());

        map.put("code",0);
        map.put("msg","");
        map.put("data",department1);
        return SUCCESS;
    }

    /**
     * 更新部门信息
     */
    public void editDept(){
        deptService.editDept(department);
    }
}
