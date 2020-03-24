<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" href="assets/libs/particles/css/style.css">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/login.css" />
</head>
<body>
<div id="wrapper">
    <div>
        <strong>人事管理系统后台登录</strong>
    </div>
    <h2 style="color: red" ><s:property value="msg"/></h2>
    <nav class="switch_nav">
        <a href="/html/register.html" id="switch_signup" class="switch_btn">注册</a>
        <a href="javascript:;" id="switch_login" class="switch_btn on">登陆</a>
        <div class="switch_bottom" id="switch_bottom"></div>
    </nav>
    <div id="login">
        <form method="post" action="/user_login.action" target="_parent">
            <ul class="group_input">
                <li>
                    <input type="text" class="mobile required" id="mobile" placeholder="账户" name="username" />
                </li>
                <li>
                    <input type="password" class="psd required" id="psd" placeholder="密码" name="password"/>
                </li>
            </ul>
            <button type="submit" class="submit_btn" id="btnSubmit">登陆</button>
        </form>
    </div>
    <div id="footer">
        <span>&copy;2017知乎</span><span>·</span><a href="javascript:;">知乎圆桌</a><span>·</span><a href="javascript:;">发现</a><span>·</span><a href="javascript:;">移动应用</a><span>·</span><a href="javascript:;">使用机构账号登录</a><span>·</span><a href="javascript:;">联系我们</a><span>·</span><a href="javascript:;">工作来知乎</a><br />
        <span>·</span><a href="javascript:;">京ICP证110745号</a><span>·</span><span>京公网安备11010802010035号</span><span>·</span><a href="javascript:;">出版物经营许可证</a>
    </div>
</div>
<script src="assets/libs/jquery-1.12.4/jquery.min.js"></script>
<script src="assets/libs/particles/particles.min.js"></script>
<script src="assets/libs/particles/js/app.js"></script>
<script>
    $(function(){
        //为表单元素添加失去焦点事件
        $("form :input").blur(function(){
            let $parent = $(this).parent();
            $parent.find(".msg").remove(); //删除以前的提醒元素（find()：查找匹配元素集中元素的所有匹配元素）
            //验证密码
            if($(this).is("#psd")){
                let psdVal = $.trim(this.value);
                let regPsd = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/;
                if(psdVal== "" || !regPsd.test(psdVal)){
                    let errorMsg = " 密码为6-20位字母、数字的组合！";
                    $parent.append("<span class='msg onError'>" + errorMsg + "</span>");
                }
                else{
                    let okMsg=" 输入正确";
                    $parent.append("<span class='msg onSuccess'>" + okMsg + "</span>");
                }
            }
        }).keyup(function(){
            //triggerHandler 防止事件执行完后，浏览器自动为标签获得焦点
            $(this).triggerHandler("blur");
        }).focus(function(){
            $(this).triggerHandler("blur");
        });

        $('#btnSubmit').click(function () {
        })
    })

</script>
</body>
</html>
