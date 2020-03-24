
layui.use(['form'], function () {
    let form = layui.form;
    let $ = layui.$;

    let getRequestParams = function(param){
        let reg = new RegExp("(^|&)" + param + "=([^&]*)(&|$)", "i");
        let r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    };
    let cid = getRequestParams("cid");

    $.ajax({
        type: 'get',
        url: '/card_detailInfo?cid=' + cid,
        success: function (data) {
            document.getElementById('name').innerText = data.data.name;
            document.getElementById('deptName').innerText = data.data.deptName;
            document.getElementById('checkTimeStr').innerText = data.data.checkTimeStr;
            document.getElementById('cardTimeStr').innerText = data.data.cardTimeStr;
            document.getElementById('reason').innerText = data.data.reason;
            document.getElementById('checkName').innerText = data.data.checkName;
            document.getElementById('deptLeader').innerText = data.data.deptLeader;
            document.getElementById('status').innerText = data.data.statusText;
        }
    });

    //监听取消按钮点击事件
    $('#cancel').click(function () {
        //获取当前iframe的index
        let index = parent.layer.getFrameIndex(window.name);
        //当点击提交后关闭当前iframe页
        parent.layer.close(index);
    });

    //监听保存按钮点击事件
    form.on('submit(saveInfo)', function(){
        //获取当前iframe的index
        let index = parent.layer.getFrameIndex(window.name);
        //当点击提交后关闭当前iframe页
        parent.layer.close(index);
    });
});