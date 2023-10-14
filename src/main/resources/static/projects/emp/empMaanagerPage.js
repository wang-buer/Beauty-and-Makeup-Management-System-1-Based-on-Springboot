layui.use(['form','util','table'],function(){
    let $= layui.jquery;
    let form=layui.form;
    let util = layui.util;
    let table = layui.table;
    table.render({
        elem:'#currentTableId',
        url:'/emp/pageQuery',
        cols:[[
            {type:"numbers",title:'序号',fixed:"left"},
            {field: "empname",title: "姓名"},
            {field:'emptype',title:'职位'},
            {field:'createDate',title:'创建时间'},
            {field:'updateDate',title:'修改时间'},
            {field:'login',hide:true},
            {field:'id',hide:true},
            {title:'操作',width:'30%',templet:function (arg) {
                let  login=$("input[name='login']").val(); //获取session中的用户
                let  empRattendance=$("input[name='empRattendance']").val(); //获取签到中的用户
                    console.log("empRattendance",empRattendance);
                let nodes=' <a class="layui-btn layui-btn-xs data-count-edit" lay-event="empInfo">详情</a>\n' +
                    '      <a class="layui-btn layui-btn-xs layui-btn-danger"  href="/emp/signListPage/'+arg.id+'?empname='+arg.empname+'&emptype='+arg.emptype+'">查看考勤</a>' +
                    '';

                let qd='<button class="layui-btn layui-btn-xs layui-btn-normal" type="button" lay-event="sign">签到</button>' +
                    '<button class="layui-btn layui-btn-xs layui-btn-normal" type="button" lay-event="unsign">未签到</button>';

                    if (login == arg.login) {

                        if (empRattendance != "已签到") {

                            nodes += qd;
                        }
                    }
                return nodes
                },fixed: 'right',align:'center'}
        ]],
        limits: [10, 15, 20, 25, 50, 100],
        limit: 10,
        page: true
    });
    //监听搜索
    form.on('submit(data-search-btn)',function (data) {
        let result = data.field; //获取数据
        //执行搜索
        table.reload('currentTableId',{
            page:{
                curr:1
            },where:{
                empname:result.empname,
                emptype:result.emptype
            }
        },'data');
        return false;
    });
    table.on('tool(currentTableFilter)',function (obj) {
        let data=obj.data;
        let myDate = new Date();
        let year=myDate.getFullYear();
        let month=myDate.getMonth();
        let date=myDate.getDate();
        let hour=myDate.getHours();//时
        let minu=myDate.getMinutes();//分
        if (obj.event == 'empInfo') {
            let createDate=new Date(data.createDate);
            year = year -createDate.getFullYear()<=0?"0":year -createDate.getFullYear();
            month=month -createDate.getMonth()<=0?"0":month -createDate.getMonth();
            date=date -createDate.getDate()<=0?"0":date -createDate.getDate();
            hour=hour -createDate.getHours()<=0?"0":hour -createDate.getHours();
            minu = minu - createDate.getMinutes() <= 0?"0":minu - createDate.getMinutes();
            form.val('edit-form', {
                empname: data.empname,
                emptype: data.emptype,
                workDate: year+"年"+month+"月"+date+"日"+" "+hour+"时"+minu+"分",
            });
            layer.open({
                type: 1,
                title: "用户详情",
                closeBtn: 1,
                area: ['400px', '400px'],
                shadeClose: false,
                content: $("#edit-main"),
                end: function () {
                    $("#edit-form")[0].reset();
                }
            });
        }else if(obj.event=="sign"){
            console.log("签到");
            $.post("/emp/sign",{empid:data.id,isin:"1"},function (data) {
                layer.alert(data.info, function () {
                    if (data.info == "签到成功！") {
                        $(".sign-in").css("display", "none");
                        //刷新 页面
                        window.location.href="/emp/empManagerPage";
                    }
                    console.log("签到成功！！！！");
                    layer.closeAll();
                });


            });
        }else if (obj.event=="unsign"){
            layer.open({
                type: 1,
                title: "未签到原因",
                closeBtn: 1,
                area: ['400px', '300px'],
                shadeClose: false,
                content: $("#unSigned"),
            });
        }
        form.on('submit(unSign-data-search-btn)',function (d) {
            let whyNo = d.field.whyNo;
             $.post("/emp/sign",{empid:data.id,isin:"0",whyNo:whyNo},function (data) {
                 layer.alert(data.info,function () {
                    layer.closeAll();
                     window.location.href="/emp/empManagerPage";
                 });
             });
             return false;
        })
    });

});
