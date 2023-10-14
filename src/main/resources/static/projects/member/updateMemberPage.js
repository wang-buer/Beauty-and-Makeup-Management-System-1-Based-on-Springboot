window.onload=function(){
    let cd = document.getElementById("createDate").value;
    cd = cd.replace('T', ' ');
    document.getElementById("createDate").value=cd;
};
layui.use(['form', 'layedit', 'laydate','jquery'], function() {
    var form = layui.form
        , layer = layui.layer
        , layedit = layui.layedit
        , $=layui.jquery
        , laydate = layui.laydate;
    //日期
    laydate.render({
        elem: '#birthday'
    });

    //自定义验证规则
    form.verify({
        cname:function(value){
          if (value ==""){
              return "请填写会员姓名";
          }
        },
        stature: function(value){
            if(isNaN(value) || value <= 0){
                return '请填入正确的身高';
            }
        },
        age:function (value) {
            if (isNaN(value) ||value<=0 || value>200){
                return '请填入正确的年龄(年龄在0到200之间)';
            }
        },
        weight:function(value){
            if(isNaN(value) ||value <= 0){
                return '请填入正确的体重信息';
            }
        },
        price:function (value) {
            if (isNaN(value) || value<=0){
                return "请填入正确的价格";
            }
        }
    });
    //监听提交
    form.on('submit(save)', function(data){
        $.ajax({
           url:"/member/updateCustomer",
           type:"post",
           data:JSON.stringify(data.field),
           contentType : "application/json;charsetset=UTF-8",
           success: function (date) {
               console.log(date);
              layer.alert(date.info);
            }
        });
        return false;
    });

});