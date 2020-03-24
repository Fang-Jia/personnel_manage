package com.xf.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xf.entity.User;
import com.xf.service.UserService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录注册 action层
 */
public class UserAction extends ActionSupport implements ModelDriven {

//    导入userService
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

//    封装浏览器传送过来的表单数据，封装到user类中
    private User user = new User();
    @Override
    public Object getModel() {
        return user;
    }

    private String msg;
    public String getMsg() {
        return msg;
    }

    private String name;
    public String getName() {
        return name;
    }

    //    封装向前端传递的数据格式
    private Map<String,Object> map = new HashMap<>();
    public Map<String, Object> getMap() { return map; }
    public void setMap(Map<String, Object> map) { this.map = map; }

    //    定义获取request属性
    private HttpServletRequest request;

    /**
     * 主页
     */
    public String main(){
        return "home";
    }

    /**
     * 登录页面
     */
    public String loginPage(){
        return "loginPage";
    }

    /**
     * 注册页面
     */
    public String registerPage(){
        return "registerPage";
    }

    /**
     * 编辑用户信息界面
     */
    public String edit(){
        return "edit";
    }

    /**
     * 登录接口
     * @return
     */
    public String login(){
//        如果用户没有输入账号和密码，直接返回登录界面
        if (user.getUsername() == null ||  user.getPassword() == null){
            return "loginPage";
        }

//        判断用户信息是否正确
        boolean status = userService.loginStatus(user);
        if (status){
//            获取HttpServletRequest对象
            HttpServletRequest request = ServletActionContext.getRequest();
//            获取HttpSession对象
            HttpSession session = request.getSession();
//            向HttpSession对象域中设置值，用来存储用户名
            session.setAttribute("username",user.getUsername());
            return "home";
        }else {
            msg = "您的账号或者密码错误";
            return "loginPage";
        }
    }

    /**
     * 注册接口
     * @return
     */
    public String register(){
        boolean status = userService.add(user);
        if (!status){
            msg = "您的账号已经被注册，请您换个账号名再注册";
        }
        return "loginPage";
    }

    /**
     * 退出接口
     */
    public String quit(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
//        删除整个会话
        session.invalidate();
        return "loginPage";
    }

    /**
     * 用户中心界面
     */
    public String home(){
        return "userHome";
    }

    /**
     * 获取用户信息
     */
    public String pageInfo(){
//        获取前端传递的分页参数
        request = ServletActionContext.getRequest();
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));

//        获取数据记录总数
        int count = userService.getUserInfo().size();
        List<User> userList = userService.getPageInfo(page,limit);

//        封装成表格需要的数据格式
        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",userList);

        return SUCCESS;
    }

    /**
     * 个人信息主页
     */
    public String myInfo(){return "myInfo";}

    /**
     * 跟个人信息
     */
    public String searchMyInfo(){
        String username = (String) ServletActionContext.getRequest().getSession().getAttribute("username");
        List<User> list = userService.searchMyInfo(username);

        map.put("code",0);
        map.put("msg","");
        map.put("data",list);
        return SUCCESS;
    }

    /**
     * 通过用户名搜索用户信息
     */
    public String search(){
        List<User> userList = userService.searchByName(user.getUsername());
        int count = userList.size();

        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",userList);
        return SUCCESS;
    }

    /**
     * 通过ID搜索部门信息
     */
    public String searchById(){
        User user1 = userService.searchById(user.getUid());

        map.put("code",0);
        map.put("msg","");
        map.put("data",user1);
        return SUCCESS;
    }

    /**
     * 删除用户
     */
    public void delUser(){
        userService.delUser(user.getUid());
    }

    /**
     * 编辑用户信息
     */
    public void editUser(){
        userService.editUser(user);
    }

}
