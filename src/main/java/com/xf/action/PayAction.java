package com.xf.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xf.entity.Pay;
import com.xf.service.PayService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 薪酬管理     控制层
 */
public class PayAction extends ActionSupport implements ModelDriven {

//    吓唬人payService属性
    private PayService payService;
    public void setPayService(PayService payService) {
        this.payService = payService;
    }

//    封装向前端传递的数据格式
    private Map<String,Object> map = new HashMap<>();
    public Map<String, Object> getMap() { return map; }
    public void setMap(Map<String, Object> map) { this.map = map; }

//    封装表单数据
    private Pay pay = new Pay();
    @Override
    public Object getModel() {
        return pay;
    }

//    定义获取request属性
    private HttpServletRequest request;

    /**
     * 薪酬管理主页
     */
    public String payHome(){
        return "home";
    }

    /**
     * 添加薪酬页面
     */
    public String addPay(){
        return "add";
    }

    /**
     * 编辑薪酬页面
     */
    public String edit(){
        return "edit";
    }

    /**
     * 获取薪酬信息   （分页）
     */
    public String pageInfo(){
//        获取前端传递的分页参数
        request = ServletActionContext.getRequest();
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));

//        获取数据记录总数
        int count = payService.getPayInfo().size();
        List<Pay> payList = payService.getPageInfo(page,limit);

//        封装成表格需要的数据格式
        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",payList);

        return SUCCESS;
    }

    /**
     * 删除薪酬信息
     */
    public void delPay(){
        payService.delPay(pay.getPid());
    }

    /**
     * 添加薪酬信息
     */
    public void addPayInfo(){
        payService.addPayInfo(pay);
    }

    /**
     * 通过名称搜索员工薪酬信息
     */
    public String search(){
        List<Pay> payList = payService.search(pay.getName());
        int count = payList.size();
        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",payList);

        return SUCCESS;
    }

    /**
     * 通过ID搜索部门信息
     */
    public String searchById(){
        Pay pay1 = payService.searchById(pay.getPid());

        map.put("code",0);
        map.put("msg","");
        map.put("data",pay1);
        return SUCCESS;
    }

    /**
     * 更新部门信息
     */
    public void editPay(){
        payService.editPay(pay);
    }

}
