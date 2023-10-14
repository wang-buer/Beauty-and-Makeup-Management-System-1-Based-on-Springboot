layui.use(['form', 'util', 'table', 'laydate'], function () {
    var $ = layui.jquery,
        form = layui.form,
        util = layui.util,
        laydate = layui.laydate,
        table = layui.table;
    let id=$("#cid").attr("data");
    //日期
    laydate.render({
        elem: '#birthday'
    });
    table.render({
        elem: '#currentTableId',
        url: '/member/checkList/'+id,
        cols: [
            [
                {type: "numbers", title: '序号', fixed: "left"},
                {field: 'cname', title: '姓名'},
                { title: '签到时间',templet:function (arg) {
                    return arg.indate.replace('T',' ');
                    }},
                {title: '考勤状态',width:'7%',templet:function(arg) {
                       return arg.isin==1?"已签到":"未签到";
                    }
                },
                {field: 'bodyfat',width:'6%', title: '脂肪率'},
                {field: 'fatmass',width:'6%', title: '脂肪量'},
                {field: 'weight',width:'5%', title: '体重'},
                {field: 'stature',width:'5%', title: '身高'},
                {field: 'bust',width:'5%', title: '胸围'},
                {field: 'hip',width:'5%', title: '臀围'},
                {field: 'waistline',width:'5%', title: '腰围'},
                {field: 'empname', title: '负责人'},
                {field: 'whyNo', title: '备注'}
            ]
        ],
        limits: [10, 15, 20, 25, 50, 100],
        limit: 10,
        page: true
    });
});