
layui.use(['form'], function () {
    let form = layui.form;
    let $ = layui.$;

    let getRequestParams = function(param){
        let reg = new RegExp("(^|&)" + param + "=([^&]*)(&|$)", "i");
        let r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    };
    let uid = getRequestParams("uid");

    $.ajax({
        type: 'get',
        url: '/user_searchById?uid=' + uid,
        success: function (data) {
            document.getElementById('username').value = data.data.username;
            document.getElementById('password').value = data.data.password;
            document.getElementById('uid').value = uid;
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