
layui.use(['table','layer','form','laydate'],function () {
    let table = layui.table;
    let layer = layui.layer;
    let $ = layui.$;
    let form = layui.form;
    let laydate = layui.laydate;

    //表格ID值
    let tableId = 'empl';

    //表格渲染
    table.render({
        elem: '#'+tableId,
        height: 620,
        url: '/empl_pageInfo',
        page: true,
        limit: 10,  //设置每页显示的条数，值低于page参数中的limit参数
        loading: true,  //是否开启加载条
        text: {
            none: '暂无相关数据'  //定义空数据时的异常提示
        },
        skin: 'row',    //开启列边框样式
        even: true,     //设置表格开启隔行背景
        size: 'lg',     //设置表格的尺寸
        cols: [[
            {field: 'eid', title: 'ID', width: '6%', sort: true, fixed: 'left', align: 'center'},
            {field: 'name', title: '姓名', width: '6%', align: "center"},
            {field: 'sex', title: '性别', width: '6%', align: "center"},
            {field: 'age', title: '年龄', width: '6%', align: "center"},
            {field: 'position', title: '职位', width: '10%', align: "center"},
            {field: 'entryTimeStr', title: '入职时间', width: '11%', align: "center"},
            {field: 'dateOfBirthStr', title: '出生年月', width: '11%', align: "center"},
            {field: 'education', title: '学历', width: '6%',align: "center"},
            {field: 'graduateSchool', title: '毕业院校', width: '9%', align: "center"},
            {field: 'specialty', title: '专业',  width: '10%', align: "center"},
            {field: 'graduateTimeStr', title: '毕业时间', width: '11%', align: "center"},
            {field: 'idCard', title: '证件信息', width: '11%', align: "center"},
            {field: 'phone', title: '联系方式', width: '11%', align: "center"},
            {field: 'email', title: '邮箱', width: '15%', align: "center"},
            {field: 'province', title: '省份', width: '6%', align: "center"},
            {field: 'cities', title: '地市', width: '6%', align: "center"},
            {field: 'county', title: '县区', width: '6%', align: "center"},
            {field: 'dept_name', title: '所属部门', width: '10%', align: "center"},
            {field: 'dept_leader', title: '部门领导', width: '8%', align: "center"},
            {field: 'marryStr', title: '是否已婚', width: '8%', align: "center"},
            {field: 'childToStudyStr', title: '子女是否上学', width: '11%', align: "center"},
            {fixed: 'right', title: '操作', width: '15%', align: "center", toolbar: '#tool'}
        ]]
    });

//    监听工具栏点击事件
    table.on('tool(empl-event)',function (obj) {
        let data = obj.data;    //获取当前行数据
        let event = obj.event;  //获取lay-event的值
        let tr = obj.tr;    //获取当前行tr的dom对象

        //编辑事件
        if (event === 'edit'){
            layer.open({
                type: 2,
                title: ['修改部门信息'],
                skin: 'layui-layer-molv',
                area: ['500px','600px'],
                shadeClose: true,
                content: '/empl_edit?eid=' + data.eid,
                //当提交完成后刷新部门表
                end: function () {
                    table.reload(tableId);
                }
            });
        }       //删除事件
        else if (event === 'del'){
            layer.confirm('真的要删除行么',function (index) {
                obj.del();  //删除对应行（tr）的DOM结构，并更新缓存
                layer.close(index);

                let eid = data.eid;     //获取要删除行的id值
                $.ajax({
                    type: 'get',
                    url: '/empl_delEmpl?eid='+eid
                })
            })
        }
    });

//    添加按钮点击事件
    $('#btnAdd').click(function () {
        layer.open({
            type: 2,
            title: ['添加员工'],
            skin: 'layui-layer-molv',
            area: ['500px','700px'],
            content: '/empl_addEmpl',
            //当提交完成后刷新部门表
            end: function () {
                table.reload(tableId);
            }
        })
    });

    //监听提交
    form.on('submit(submitInfo)', function(data){
        //获取当前iframe的index
        let index = parent.layer.getFrameIndex(window.name);
        //当点击提交后关闭当前iframe页
        parent.layer.close(index);
    });

//    搜索按钮点击事件
    $('#btnSearch').click(function () {
        table.reload(tableId,{
            url: '/empl_search?',
            where: {name: $('#name').val()}     //设定异步数据接口的额外参数
        })
    });

//    渲染时间选择框
    laydate.render({
        elem: '#entryTime',
    });
    laydate.render({
        elem: '#dateOfBirth',
    });
    laydate.render({
        elem: '#graduateTime',
    });
});