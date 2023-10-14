layui.use(['form', 'util','table'], function () {
    var $ = layui.jquery,
        form = layui.form,
        util = layui.util,
        table = layui.table;

    table.render({
        elem: '#currentTableId',
        url: '/role/pageQuery',
        cols: [
            [
                {type: "numbers", title: '序号', fixed: "left"},
                {field: 'rname',  title: '角色名称'},
                {field: 'des', title: '描述'},
                {field: 'createDate',  title: '创建时间'},
                {field: 'updateDate',  title: '修改时间', sort: true},
                {title: '操作',  width:'20%', templet: '#currentTableBar', fixed: "right", align: "center"}
            ]
        ],

        page: false
    });

    // 监听添加操作
    $(".data-add-btn").on("click", function () {
        layer.open({
            type: 1,
            title: "添加角色",
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
           where: {
                rname: result.rname
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
            layer.confirm('确定删除选中的角色？', {icon: 3, title: '提示信息'}, function (index) {
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
            layer.msg("请选择需要删除的角色");
        }
    });



    table.on('tool(currentTableFilter)', function (obj) {
        console.log("--------------");
        var data = obj.data;
        //修改
        if (obj.event === 'edit') {
            form.val("edit-form", { //formTest 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
                "rid": data.rid // "name": "value"
                , "rname": data.rname
                , "des": data.des
            });
            layer.open({
                type: 1,
                title: "修改角色",
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
                $.post('/role/delete', {rid: data.rid}, function (result) {
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
        }else if (obj.event=="powerSetting"){
            layer.open({

                type: 1,
                title: "角色资源设置",
                closeBtn: 1,
                area: ['500px', '400px'],
                btn: ['保存', '取消'],
                btnAlign: 'c',
                shadeClose: false,
                content: $("#res-setting"),
                success: function () {
                    table.render({
                        elem: '#res_tableId'
                        , url: '/role/getRes/' + data.rid
                        , cols: [
                            [
                                {type: 'checkbox'}
                                , {field: 'cname', title: '资源名称'}
                            ]
                        ],
                        done: function(res, page, count){
                            //可以自行添加判断的条件是否选中
                            //这句才是真正选中，通过设置关键字LAY_CHECKED为true选中，这里只对第一行选中
                            console.log(count);
                            console.log(res.data);
                            if (res.data.length>0) {
                                for (var i in res.data) {
                                    if (res.data[i].isdel) {
                                        res.data[i]["LAY_CHECKED"]='true';
                                        //下面三句是通过更改css来实现选中的效果
                                        var index= res.data[i]['LAY_TABLE_INDEX'];
                                        $('tr[data-index=' + index + '] input[type="checkbox"]').prop('checked', true);
                                        $('tr[data-index=' + index + '] input[type="checkbox"]').next().addClass('layui-form-checked');
                                    }
                                }
                            }
                        },
                        page: false
                    });
                },
                yes: function (index) {
                    var checkStatus = table.checkStatus('res_tableId'),
                        data1 = checkStatus.data,
                        ids = [];
                    if (data1.length > 0) {
                        for (var i in data1) {
                            ids.push(data1[i].id);
                        }
                    }
                    $.post('/role/setRes', {resIds: JSON.stringify(ids),rid: data.rid}, function (result) {
                        if (result.success) {
                            layer.alert(result.info, {title: "系统提示"});
                            layer.close(index);
                        } else {
                            layer.alert(result.info, {title: "系统提示"});
                            layer.close(index);
                        }
                    }, 'json');

                }
            });
        }
    });

});


//提交
function submit($, params) {

    $.post('/role/addOrUpdate', params, function (result) {
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
    $.post('/role/resources/getRes', {rid:rid}, function (result) {
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
        window.location.href='/role/roleManagePage'
    },1500);
}