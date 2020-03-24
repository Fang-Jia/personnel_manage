
layui.use(['table','layer','form'],function () {
    let table = layui.table;
    let layer = layui.layer;
    let $ = layui.$;
    let form = layui.form;

    //表格ID值
    let tableId = 'pay';

    //表格渲染
    table.render({
        elem: '#'+tableId,
        height: 620,
        url: '/pay_pageInfo',
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
            {field: 'pid', title: 'ID', width: '6%', sort: true, fixed: 'left', align: 'center'},
            {field: 'name', title: '员工姓名', width: '8%', align: "center"},
            {field: 'monthPay', title: '基本工资', sort: true, width: '9%', align: "center"},
            {field: 'performance', title: '绩效工资', width: '8%', align: "center"},
            {field: 'foodAllowance', title: '伙食补贴', width: '8%', align: "center"},
            {field: 'phoneAllowance', title: '通讯补贴', width: '8%', align: "center"},
            {field: 'travelAllowance', title: '交通补贴', width: '8%', align: "center"},
            {field: 'allowance', title: '津贴', width: '8%', align: "center"},
            {field: 'overtimeAllowance', title: '加班工资', width: '8%', align: "center"},
            {field: 'reduceAllowance', title: '迟到早退扣款', width: '10%', align: "center"},
            {field: 'casualReduceAllowance', title: '事假扣款', width: '8%', align: "center"},
            {field: 'personnelTax', title: '个人所得税', width: '10%', align: "center"},
            {field: 'total', title: '合计', width: '8%', sort: true, align: "center"},
            {fixed: 'right', title: '操作', width: '12%', align: "center", toolbar: '#tool'}
        ]]
    });

//    监听工具栏点击事件
    table.on('tool(pay-event)',function (obj) {
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
                content: '/pay_edit?pid=' + data.pid,
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

                let pid = data.pid;     //获取要删除行的id值
                $.ajax({
                    type: 'get',
                    url: '/pay_delPay?pid='+pid
                })
            })
        }
    });

//    添加按钮点击事件
    $('#btnAdd').click(function () {
        layer.open({
            type: 2,
            title: ['添加部门'],
            skin: 'layui-layer-molv',
            area: ['500px','600px'],
            content: window.location.href+'/pay_addPay',
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
            url: '/pay_search?',
            where: {name: $('#name').val()}     //设定异步数据接口的额外参数
        })
    })
});