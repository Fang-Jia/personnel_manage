<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>主页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="../assets/layui/css/layui.css" rel="stylesheet" media="all">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <!--    头部导航栏区域-->
    <div class="layui-header">
        <div class="layui-logo">人事管理系统</div>
        <ul class="layui-nav layui-layout-left top">
            <li class="layui-nav-item"><a href="/user_main" target="_top">首页</a></li>
            <li class="layui-nav-item">
                <a href="/leave_lookMain" target="body">
                    请假审批
                    <span class="layui-badge">
                        <s:property value="#application.number"/>
                    </span>
                </a>
            </li>
            <li class="layui-nav-item">
                <a href="/card_lookMain" target="body">
                    补卡审批
                    <span class="layui-badge">
                        <s:property value="#application.cardNumber"/>
                    </span>
                </a>
            </li>
            <li class="layui-nav-item">
                <a href="/user_home" target="body">账户中心<span class="layui-badge-dot"></span></a>
            </li>
            <li class="layui-nav-item">
                <a href="/user_myInfo" target="body">你好，<s:property value="#session.username"/></a>
            </li>
            <li class="layui-nav-item"><a href="/user_quit">退出 <i class="layui-icon layui-icon-logout" style="color: red"></i></a></li>
        </ul>
    </div>
    <!--    左侧导航栏区域-->
    <div class="layui-side layui-bg-black main-left">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree">
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="#">组织架构</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/dept_deptHome" target="body">部门管理</a></dd>
                        <dd><a href="/posi_posiHome" target="body">岗位管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="/pay_payHome" target="body">薪酬管理</a>
                </li>
                <li class="layui-nav-item">
                    <a href="/empl_emplHome" target="body">员工管理</a>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="#">考勤管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/leave_home" target="body">申请请假</a></dd>
                        <dd><a href="/card_home" target="body">申请补卡</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <!--    内容主题区域-->
    <div class="layui-body">
        <iframe frameborder="0" scrolling="yes" style="width: 100%;height: 100%" src="/empl_emplHome" id="inner" name="body"></iframe>
    </div>

    <!--    底部固定区域-->
    <div class="layui-footer">
        @ xf.com 底部固定区域
    </div>
</div>

<script src="../assets/layui/layui.js"></script>
<script>
    layui.use(['element'],function () {
        var element = layui.element;
        let $ = layui.$;
        element.render('element');
    });

</script>
</body>
</html>
