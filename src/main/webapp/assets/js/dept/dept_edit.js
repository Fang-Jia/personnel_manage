
layui.use(['form'], function () {
    let form = layui.form;
    let $ = layui.$;

    let getRequestParams = function(param){
        let reg = new RegExp("(^|&)" + param + "=([^&]*)(&|$)", "i");
        let r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    };
    let did = getRequestParams("did");

    $.ajax({
        type: 'get',
        url: '/dept_searchById?did=' + did,
        success: function (data) {
            document.getElementById('name').value = data.data.name;
            document.getElementById('description').value = data.data.description;
            document.getElementById('leader').value = data.data.leader;
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