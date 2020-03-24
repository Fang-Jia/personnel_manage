package com.xf.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xf.entity.Card;
import com.xf.service.CardService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门管理   控制层
 */
public class CardAction extends ActionSupport implements ModelDriven {

//    封装向前端传递的数据格式
    private Map<String,Object> map = new HashMap<>();
    public Map<String, Object> getMap() { return map; }
    public void setMap(Map<String, Object> map) { this.map = map; }

    //    注入DeptService属性
    private CardService cardService;
    public void setCardService(CardService cardService) { this.cardService = cardService; }

    //    封装前端传递的数据
    private Card card = new Card();
    @Override
    public Object getModel() {
        return card;
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
        int number = cardService.addInfo(card);
        ServletContext servletContext = ServletActionContext.getServletContext();
        servletContext.setAttribute("cardNumber",number);
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
        int count = cardService.getCardInfo().size();
        List<Card> cardList = cardService.getPageInfo(page,limit);

//        封装成表格需要的数据格式
        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",cardList);
        return SUCCESS;
    }

    /**
     * 同意审批
     */
    public void agree(){
        cardService.agree(card.getCid());
        ServletContext servletContext = ServletActionContext.getServletContext();
        int number = (int) servletContext.getAttribute("cardNumber");
        servletContext.setAttribute("cardNumber",number-1);
    }

    /**
     * 拒绝审批
     */
    public void refuse(){
        cardService.refuse(card.getCid());
        ServletContext servletContext = ServletActionContext.getServletContext();
        int number = (int) servletContext.getAttribute("cardNumber");
        servletContext.setAttribute("cardNumber",number-1);
    }

    /**
     * 查看请假信息   主页
     */
    public String detail(){
        return "detail";
    }

    /**
     * 查看请假信息
     * @return
     */
    public String detailInfo(){
        Card card1 = cardService.detailInfo(card.getCid());

        map.put("code",0);
        map.put("msg","");
        map.put("data",card1);
        return SUCCESS;
    }
}
