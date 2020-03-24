
layui.use(['table','layer'],function () {
   let table = layui.table;
   let layer = layui.layer;
   let $ = layui.$;

   let tableId = 'lookInfo';

   table.render({
       elem: '#' + tableId,
       height: 640,
       url: '/leave_pageInfo',
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
           {field: 'lid', title: 'ID', width: '5%', fixed: 'left', align: 'center'},
           {field: 'name', title: '姓名', width: '6%', align: "center"},
           {field: 'deptName', title: '部门', width: '7%', align: "center"},
           {field: 'checkTimeStr', title: '申请日期', width: '10%', align: "center"},
           {field: 'reason', title: '请假缘由', width: '10%', align: "center"},
           {field: 'leaveTimeStr', title: '起始时间', width: '10%', align: "center"},
           {field: 'endTimeStr', title: '结束时间', width: '10%', align: "center"},
           {field: 'totalDay', title: '累计天数', width: '8%', align: "center"},
           {field: 'leaveTypeText', title: '请假类型', width: '8%', align: "center"},
           {field: 'statusText', title: '审批状态', width: '8%', align: "center"},
           {fixed: 'right', title: '操作', align: "center", width:'18%', toolbar: '#tool'}
       ]],
       done: function (res) {
           let ele = document.getElementsByClassName('laytable-cell-1-0-10');
           for (let x = 1; x < ele.length/2; x++){
               let lid = res.data[x-1].lid;
               ele[x].children[0].id = 'detail-' + lid;
               ele[x].children[1].id = 'agree-' + lid;
               ele[x].children[2].id = 'refuse-' + lid;

               if (res.data[x-1].statusText === '已审批'){
                   document.getElementById('agree-'+lid).style.display = 'none';
                   document.getElementById('refuse-'+lid).style.display = 'none';
               }else if (res.data[x-1].statusText === '已拒绝'){
                   document.getElementById('agree-'+lid).style.display = 'none';
                   document.getElementById('refuse-'+lid).style.display = 'none';
                   document.getElementById('detail-'+lid).style.display = 'none';
               }
           }
       }
   });

    //    监听工具栏点击事件
    table.on('tool(look-event)',function (obj) {
        let data = obj.data;    //获取当前行数据
        let event = obj.event;  //获取lay-event的值
        let tr = obj.tr;    //获取当前行tr的dom对象

        let lid = data.lid;

        //编辑事件
        if (event === 'detail'){
            layer.open({
                type: 2,
                title: ['修改部门信息'],
                skin: 'layui-layer-molv',
                area: ['700px','600px'],
                shadeClose: true,
                content: '/leave_detail?lid=' + lid,
            });
        }
        else if (event === 'agree'){
            $.ajax({
                type: 'get',
                url: '/leave_agree?lid=' + lid,
            });
            layer.open({
                content: '已同意',
                end: function () {
                    table.reload(tableId);
                }
            });
        }
        else if (event === 'refuse'){
            $.ajax({
                type: 'get',
                url: '/leave_refuse?lid=' + lid,
            });
            layer.open({
                content: '已拒绝',
                end: function () {
                    table.reload(tableId);
                }
            });
        }
    });
});