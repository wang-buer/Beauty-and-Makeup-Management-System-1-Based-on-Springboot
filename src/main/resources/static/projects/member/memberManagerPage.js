layui.use(['form', 'util', 'table', 'laydate'], function () {
    var $ = layui.jquery,
        form = layui.form,
        util = layui.util,
        laydate = layui.laydate,
        table = layui.table;
    let uId=$("#user").text(); //员工id
    //日期
    laydate.render({
        elem: '#birthday'
    });
    table.render({
        elem: '#currentTableId',
        url: '/member/pageQuery',
        cols: [
            [
                {type: "numbers", title: '序号', fixed: "left"},
                {field: 'cid', hide: true},
                {field: 'cname',width:'10%', title: '姓名'},
                {field: 'age',width:'6%', title: '年龄'},
                {field: 'birthday',width:'9%', title: '生日'},
                {field: 'phone',width:'11%', title: '电话'},
                {field: 'address', title: '地址'},
                {field: 'createDate', title: '创建时间'},
                {title: '操作',width: "36%", templet: function (arg) {
                    let cId=arg.cid;
                    let node = '';
                   let result='';
                   $.ajax({
                       type: 'get',
                       url: '/member/operating/'+uId,
                       data: "cid="+cId,
                       async: false, //必须设置为同步，不然外层的result获取不到数据
                       success:function (data) {
                           result=data.info;
                       }
                   });
                        if (result == "1"){
                            node+='<a class="layui-btn layui-btn-xs data-count-edit layui-btn-danger" lay-event="del">删除</a>'
                                +'<a class="layui-btn layui-btn-xs data-count-edit layui-btn-warm" lay-event="update">修改</a>'
                                // +'<a class="layui-btn layui-btn-xs data-count-edit" lay-event="recharge">充值</a>'
                                +'<a class="layui-btn layui-btn-xs data-count-edit layui-btn-normal" lay-event="goBuy">购买</a>'
                                +'<a class="layui-btn layui-btn-xs data-count-edit" lay-event="signIn">签到</a>'
                                +'<a class="layui-btn layui-btn-xs data-count-edit" lay-event="signOn">未签到</a>';
                        }
                        node+='<a class="layui-btn layui-btn-xs data-count-edit" lay-event="check">考勤</a>'
                              +'<a class="layui-btn layui-btn-xs data-count-edit layui-btn-normal" lay-event="info">详情</a>';
                        return node;
                    }, fixed: "right", align: "center"}
            ]
        ],
        limits: [10, 15, 20, 25, 50, 100],
        limit: 10,
        page: true
    });

    // 监听搜索操作
    form.on('submit(data-search-btn)', function (data) {
        var result = data.field;
        //执行搜索重载
        table.reload('currentTableId', {
            page: {
                curr: 1
            }
            , where: {
                cname: result.cname,
                age: result.age,
                birthday: result.birthday,
                address: result.address,
            }
        }, 'data');
        return false;
    });

    //增加顾客
    $(".data-add-btn").on("click", function () {
        window.location = "/member/addMemberPage";
    })

    /*表格中的操作*/
    table.on('tool(currentTableFilter)',function (obj) {
        let data=obj.data;
        if (obj.event=='del'){
            //点击确定执行 function
            layer.confirm("确定要删除吗？", {icon: 3, title: '提示'}, function (index) {
                layer.close(index);
                $.get("/member/delCustomerInfo/" + data.cid, function (data) {
                    layer.alert(data.info);
                    table.reload('currentTableId', {
                        url: '/member/pageQuery'
                    });
                });

            });

        }
        if (obj.event=='update'){
            window.location.href="/member/updatePage/"+data.cid;
        }
        if (obj.event=='recharge'){ //充值
                layer.alert("此功能暂未开放！");
        }
        if (obj.event=='check'){ //考勤
            window.location.href="/member/checkListPage/"+data.cid;
        }
        if (obj.event=='info'){
            window.location.href="/member/infoPage/"+data.cid;
        }
        if (obj.event=='signIn') { //签到
            // window.location.href = "/member/signIn/" + data.cid;
            //获取前一次签到的数据
            $.ajax({
                url:"/member/signIn/" + data.cid,
                type:"post",
                dataType:"json",
                success:function (datas) {
                    let d=datas.data;
                    if (datas.code==1){
                        layer.alert(datas.msg,function () {
                                layer.closeAll();
                        })
                    }else if (datas.code==2){
                        layer.alert(data.msg);
                    }else if(datas.code ==3){
                        $("#signIn").find("input[name='empid']").val(uId);
                        $("#signIn").find("input[name='cusid']").val(data.cid);
                        $("#signIn").find("input[name='cname']").val(d.cname);
                        $("#signIn").find("input[name='stature']").val(d.stature);
                        $("#signIn").find("input[name='weight']").val(d.weight);
                        $("#signIn").find("input[name='bust']").val(d.bust);
                        $("#signIn").find("input[name='waistline']").val(d.waistline);
                        $("#signIn").find("input[name='hip']").val(d.hip);
                        $("#signIn").find("input[name='bodyfat']").val(d.bodyfat);
                        $("#signIn").find("input[name='fatmass']").val(d.fatmass);
                    }
                }
            });
            $("#signIn").css({"margin-top":"7%","margin-bottom":"6%"});
            $("#signIn .layui-form-item").css("display", " inline-block");
            layer.open({
                type: 1,
                title: "签到卡",
                closeBtn: 1,
                area: ['700px', '430px'],
                shadeClose: false,
                content: $("#signIn"),
            });
        }
        if (obj.event=='signOn'){ //未签到
            $("#cid").val(data.cid);
            layer.open({
                type: 1,
                title: "未签到原因",
                closeBtn: 1,
                area: ['400px', '300px'],
                shadeClose: false,
                content: $("#unSigned"),
            });
        }
        if(obj.event=="goBuy"){ //购买 ： 套餐；产品；卡
            window.location.href="/member/buy/"+data.cid;
        }
    });
    //未签到信息
    form.on('submit(unSign-data-search-btn)',function (d) {
        let whyNo = d.field.whyNo;
        let cid = d.field.cid;
        $.post("/member/sign/" + cid + "/" + uId, {isin: 0, whyNo: whyNo}, function (data) {

            layer.alert(data.info,function () {
                layer.closeAll();
            });
        });

        return false;
    });
    //提交签到信息
    form.on('submit(signIn-data-search-btn)',function (d) {
        console.log(d.field);
        $.post("/member/saveSignIn",d.field, function (data) {
            layer.alert(data.info,function () {
                layer.closeAll();
            });
        });
        return false;
    })
});