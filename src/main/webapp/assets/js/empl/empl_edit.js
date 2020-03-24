
layui.use(['form','laydate'], function () {
    let form = layui.form;
    let $ = layui.$;
    let laydate = layui.laydate;

    let getRequestParams = function(param){
        let reg = new RegExp("(^|&)" + param + "=([^&]*)(&|$)", "i");
        let r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    };
    let eid = getRequestParams("eid");

    $.ajax({
        type: 'get',
        url: '/empl_searchById?eid=' + eid,
        success: function (data) {
            document.getElementById('eid').value = data.data.eid;
            document.getElementById('name').value = data.data.name;
            if (data.data.sex == '0'){
                let ele = document.getElementById('rad2');
                ele.checked = true;
                ele.nextElementSibling.className = "layui-unselect layui-form-radio layui-form-radioed";
                ele.nextElementSibling.children[0].className = "layui-anim layui-icon layui-anim-scaleSpring";
            }else {
                let ele = document.getElementById('rad2');
                ele.checked = true;
                ele.nextElementSibling.className = "layui-unselect layui-form-radio layui-form-radioed";
                ele.nextElementSibling.children[0].className = "layui-anim layui-icon layui-anim-scaleSpring";
            }
            document.getElementById('age').value = data.data.age;
            document.getElementById('position').value = data.data.position;
            document.getElementById('entryTime').value = data.data.entryTime;
            document.getElementById('dateOfBirth').value = data.data.dateOfBirth;
            document.getElementById('education').value = data.data.education;
            document.getElementById('graduateSchool').value = data.data.graduateSchool;
            document.getElementById('specialty').value = data.data.specialty;
            document.getElementById('graduateTime').value = data.data.graduateTime;
            document.getElementById('idCard').value = data.data.idCard;
            document.getElementById('phone').value = data.data.phone;
            document.getElementById('email').value = data.data.email;
            document.getElementById('province').value = data.data.province;
            document.getElementById('cities').value = data.data.cities;
            document.getElementById('county').value = data.data.county;
            document.getElementById('dept_name').value = data.data.dept_name;
            document.getElementById('dept_leader').value = data.data.dept_leader;
            if (data.data.marry == '0'){
                let ele = document.getElementById('marry1');
                ele.checked = true;
                ele.nextElementSibling.className = "layui-unselect layui-form-radio layui-form-radioed";
                ele.nextElementSibling.children[0].className = "layui-anim layui-icon layui-anim-scaleSpring";
            }else {
                let ele = document.getElementById('marry2');
                ele.checked = true;
                ele.nextElementSibling.className = "layui-unselect layui-form-radio layui-form-radioed";
                ele.nextElementSibling.children[0].className = "layui-anim layui-icon layui-anim-scaleSpring";
            }
            if (data.data.childToStudy == '0'){
                let ele = document.getElementById('child1');
                ele.checked = true;
                ele.nextElementSibling.className = "layui-unselect layui-form-radio layui-form-radioed";
                ele.nextElementSibling.children[0].className = "layui-anim layui-icon layui-anim-scaleSpring";
            }else {
                let ele = document.getElementById('child2');
                ele.checked = true;
                ele.nextElementSibling.className = "layui-unselect layui-form-radio layui-form-radioed";
                ele.nextElementSibling.children[0].className = "layui-anim layui-icon layui-anim-scaleSpring";
            }
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