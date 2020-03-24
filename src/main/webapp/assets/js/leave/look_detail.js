
layui.use(['form'], function () {
    let form = layui.form;
    let $ = layui.$;

    let getRequestParams = function(param){
        let reg = new RegExp("(^|&)" + param + "=([^&]*)(&|$)", "i");
        let r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    };
    let lid = getRequestParams("lid");

    $.ajax({
        type: 'get',
        url: '/leave_detailInfo?lid=' + lid,
        success: function (data) {
            document.getElementById('name').innerText = data.data.name;
            document.getElementById('deptName').innerText = data.data.deptName;
            document.getElementById('checkTime').innerText = data.data.checkTime;
            document.getElementById('reason').innerText = data.data.reason;
            document.getElementById('leaveTime').innerText = data.data.leaveTime;
            document.getElementById('endTime').innerText = data.data.endTime;
            document.getElementById('totalDay').innerText = data.data.totalDay;
            document.getElementById('leaveType').innerText = data.data.leaveType;
            document.getElementById('status').innerText = data.data.status;
        }
    });

    //监听取消按钮点击事件
    $('#cancel').click(function () {
        //获取当前iframe的index
        let index = parent.layer.getFrameIndex(window.name);
        //当点击提交后关闭当前iframe页
        parent.layer.close(index);
    })

    //监听保存按钮点击事件
    form.on('submit(saveInfo)', function(){
        //获取当前iframe的index
        let index = parent.layer.getFrameIndex(window.name);
        //当点击提交后关闭当前iframe页
        parent.layer.close(index);
    });
});