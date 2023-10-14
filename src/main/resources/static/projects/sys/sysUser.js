layui.use(['form', 'util','table'], function () {
    var $ = layui.jquery,
        form = layui.form,
        util = layui.util,
        table = layui.table;

    table.render({
        elem: '#currentTableId',
        url: '/sys/pageQuery',
        cols: [
            [
                {type: "checkbox",  fixed: "left"},
                {type: "numbers", title: '序号', fixed: "left"},
                {field: 'login',  title: '账号'},
                {field: 'empname', title: '用户名'},
                {field: 'emptype',  title: '用户类型'},
                {field: 'roles',  title: '角色', sort: true},
                {field:'createDate',title:'创建时间'},
                {title: '操作',  width:'20%', templet: '#currentTableBar', fixed: "right", align: "center"}
            ]
        ],
        limits: [10, 15, 20, 25, 50, 100],
        limit: 10,
        page: true
    });

    // 监听添加操作
    $(".data-add-btn").on("click", function () {
        layer.open({
            type: 1,
            title: "添加用户",
            closeBtn: 1,
            area: ['500px', '400px'],
            shadeClose: false,
            content: $("#add-main"),
            end: function () {
                $("#add-form")[0].reset();
            }
        });
    });



    //添加弹框关闭
    var obj = document.getElementById('closeBtn');
    obj.addEventListener('click', function cancel() {
        layer.closeAll();
    });

    //编辑弹框关闭
    var obj = document.getElementById('edit-closeBtn');
    obj.addEventListener('click', function cancel() {
        layer.closeAll();
    });

    //添加弹框提交
    form.on('submit(save)', function (data) {
        console.log("添加弹框")
        params = data.field;
        submit($, params);
        layClose($, table);
        return false;
    })

    // 监听搜索操作
    form.on('submit(data-search-btn)', function (data) {
        var result = data.field;
        console.log(result);
        //执行搜索重载
        table.reload('currentTableId', {
            page: {
                curr: 1
            }
            , where: {
                empname: result.empname
            }
        }, 'data');
        return false;
    });


    $(".data-delete-btn").on("click", function () {
        var checkStatus = table.checkStatus('currentTableId'),
            data = checkStatus.data,
            ids = [];
        if (data.length > 0) {
            for (var i in data) {
                ids.push(data[i].id);
            }
            layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
                $.post('/sys/delete', {id: JSON.stringify(ids)}, function (result) {
                    if (result.success) {
                        layer.alert(result.info, {title: "系统提示"});
                        trelode($,table);
                    } else {
                        layer.alert(result.info, {title: "系统提示"});
                        trelode($,table);
                    }
                }, 'json');
                layer.close(index);
            })
        } else {
            layer.msg("请选择需要删除的用户");
        }
    });



    table.on('tool(currentTableFilter)', function (obj) {
        console.log("--------------");
        var data = obj.data;
        //修改
        if (obj.event === 'edit') {
            form.val("edit-form", { //formTest 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
                "id": data.id // "name": "value"
                , "empname": data.empname
                , "login": data.login
                ,"emptype":data.emptype
            });
            layer.open({
                type: 1,
                title: "修改用户",
                closeBtn: 1,
                area: ['500px', '400px'],
                shadeClose: false,
                content: $("#edit-main"),
                end: function () {
                    $("#edit-form")[0].reset();
                }
            });

        }
        //删除
        else if (obj.event === 'delete') {
            layer.confirm('确定删除这一条数据', {icon: 3, title: '提示信息'}, function (index) {
                $.post('/sys/delete', {id: data.id}, function (result) {
                    if (result.success) {
                        layer.alert(result.info, {title: "系统提示"});

                        trelode($,table);
                    } else {
                        layer.alert(result.info, {title: "系统提示"});
                        trelode($,table);
                    }
                }, 'json');
                layer.close(index);
            });
            //资源分配
        } else if (obj.event === 'roleSetting') {
            layer.open({
                type: 1,
                title: "角色设置",
                closeBtn: 1,
                btn: ['保存', '取消'],
                btnAlign: 'c',
                area: ['500px', '400px'],
                shadeClose: false,
                content: $("#role-setting"),
                yes:function(index){
                    console.log("data.id:",data);
                    var checkStatus = table.checkStatus('roleset')
                        ,roledata = checkStatus.data;
                    $.post('/sys/setRole', {id: data.id,rid:JSON.stringify(roledata[0]['rid']),
                        rname:JSON.stringify(roledata[0]['rname'])
                    }, function (result) {
                        if (result.success) {
                            layer.alert(result.info, {title: "系统提示"});
                        } else {
                            layer.alert(result.info, {title: "系统提示"});
                        }
                    }, 'json');
                    trelode($,table);
                    layer.close(index);
                },
                success: function () {
                    table.render({
                        elem: '#roleset'
                        ,url:'/role/query'
                        ,cols: [
                            [
                                {type:'radio'}
                                ,{field:'rid',  title: 'ID' }
                                ,{field:'rname',  title: '角色名'}
                            ]
                        ]
                        ,
                        done: function () {
                            $("[data-field='rid']").css('display','none');
                        }
                    });
                }
            });
        }
    });

});


//提交
function submit($, params) {

    $.post('/sys/addOrUpdate', params, function (result) {
        console.log("ceshi");
        console.log(params)
        console.log("*******************");
        if (result.success) {
            layer.alert(result.info, {title: "系统提示"});
        } else {
            layer.alert(result.info, {title: "系统提示"});
        }
    }, 'json');
}

//获取资源数据
function getData($,rid) {
    var data = [];
    $.post('/sys/resources/getRes', {rid:rid}, function (result) {
        if (result!=null) {
            data =result;
            console.log(data);
        } else {
            layer.alert("获取资源失败", {title: "系统提示"});
        }
    }, 'json');
    return data;
}

//关闭弹框并清除数据
function layClose($, table) {
    layer.closeAll();
    $("#add-form")[0].reset();
    trelode($,table);
}

//table刷新
function trelode($,table) {

    setTimeout(function (){
        window.location.href='/sys/userManagerPage'
    },1500);
}