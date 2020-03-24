package com.xf.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xf.entity.Department;
import com.xf.entity.Position;
import com.xf.service.PositionService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 岗位表  控制层
 */
public class PositionAction extends ActionSupport implements ModelDriven {

    private PositionService positionService;

    public void setPositionService(PositionService positionService) {
        this.positionService = positionService;
    }

    private Position position = new Position();
    @Override
    public Object getModel() {
        return position;
    }

//    封装向前端传递的数据格式
    private Map<String,Object> map = new HashMap<>();
    public Map<String, Object> getMap() { return map; }
    public void setMap(Map<String, Object> map) { this.map = map; }

//    定义获取request属性
    private HttpServletRequest request;

    /**
     * 部门管理主页
     */
    public String posiHome(){
        return "home";
    }

    /**
     * 添加部门页面
     */
    public String addPosi(){
        return "add";
    }

    /**
     * 编辑部门页面
     */
    public String edit(){
        return "edit";
    }

    /**
     * 获取岗位信息   （分页）
     */
    public String pageInfo(){
//        获取前端传递的分页参数
        request = ServletActionContext.getRequest();
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));

//        获取数据记录总数
        int count = positionService.getPosiInfo().size();
        List<Position> positionList = positionService.getPageInfo(page,limit);

//        封装成表格需要的数据格式
        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",positionList);

        return SUCCESS;
    }

    /**
     * 添加岗位信息
     */
    public void addPosiInfo(){
        positionService.addPosiInfo(position);
    }

    /**
     * 搜索部门信息
     * @return
     */
    public String search(){
        List<Position> positionList = positionService.search(position.getName());
        int count = positionList.size();
        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",positionList);

        return SUCCESS;
    }

    /**
     * 删除部门
     */
    public void delPosi(){
        positionService.delPosi(position.getPid());
    }

    /**
     * 通过ID搜索部门信息
     */
    public String searchById(){
        Position position1 = positionService.searchById(position.getPid());

        map.put("code",0);
        map.put("msg","");
        map.put("data",position1);
        return SUCCESS;
    }

}
