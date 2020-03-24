
layui.use(['form'], function () {
    let form = layui.form;
    let $ = layui.$;

    let getRequestParams = function(param){
        let reg = new RegExp("(^|&)" + param + "=([^&]*)(&|$)", "i");
        let r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    };
    let pid = getRequestParams("pid");

    $.ajax({
        type: 'get',
        url: '/pay_searchById?pid=' + pid,
        success: function (data) {
            document.getElementById('name').value = data.data.name;
            document.getElementById('monthPay').value = data.data.monthPay;
            document.getElementById('performance').value = data.data.performance;
            document.getElementById('foodAllowance').value = data.data.foodAllowance;
            document.getElementById('phoneAllowance').value = data.data.phoneAllowance;
            document.getElementById('travelAllowance').value = data.data.travelAllowance;
            document.getElementById('allowance').value = data.data.allowance;
            document.getElementById('overtimeAllowance').value = data.data.overtimeAllowance;
            document.getElementById('reduceAllowance').value = data.data.reduceAllowance;
            document.getElementById('casualReduceAllowance').value = data.data.casualReduceAllowance;
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