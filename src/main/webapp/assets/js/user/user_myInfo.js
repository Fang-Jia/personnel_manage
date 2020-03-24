
layui.use(['table','layer','form'],function () {
    let table = layui.table;
    let layer = layui.layer;
    let $ = layui.$;
    let form = layui.form;

    //表格ID值
    let tableId = 'user';

    //表格渲染
    table.render({
        elem: '#'+tableId,
        height: 620,
        url: '/user_searchMyInfo',
        loading: true,  //是否开启加载条
        skin: 'row',    //开启列边框样式
        size: 'lg',     //设置表格的尺寸
        cols: [[
            {field: 'uid', title: 'ID', width: 80, sort: true, fixed: 'left', align: 'center'},
            {field: 'username', title: '用户名', align: "center"},
            {field: 'password', title: '密码', align: "center"},
            {field: 'right', title: '操作', align: "center", toolbar: '#tool'}
        ]]
    });

//    监听工具栏点击事件
    table.on('tool(user-event)',function (obj) {
        let data = obj.data;    //获取当前行数据
        let event = obj.event;  //获取lay-event的值
        let tr = obj.tr;    //获取当前行tr的dom对象

        //编辑事件
        if (event === 'edit'){
            layer.open({
                type: 2,
                title: ['修改用户信息'],
                skin: 'layui-layer-molv',
                area: ['500px','300px'],
                content: '/user_edit?uid=' + data.uid,
                //当提交完成后刷新部门表
                end: function () {
                    table.reload(tableId);
                }
            });
        }
    });

    //监听提交
    form.on('submit(submitInfo)', function(data){
        //获取当前iframe的index
        let index = parent.layer.getFrameIndex(window.name);
        //当点击提交后关闭当前iframe页
        parent.layer.close(index);
    });

});