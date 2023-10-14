layui.use(['form','util','table','laydate'],function() {
    let $ = layui.jquery;
    let form = layui.form;
    let util = layui.util;
    let table = layui.table;
    let empid=$("input[name='empid']").val();
    console.log("data:",empid);
    var laydate = layui.laydate;
    //常规用法
    laydate.render({
        elem: '#startDate'
    });

    laydate.render({
        elem: '#endDate'
    });

    table.render({
        elem: '#currentTableId',
        url: '/emp/signList?empid='+empid,
        cols: [[
            {type: "numbers", title: '序号', fixed: "left"},
            {field: "isin",title:'状态',templet:function (d) {
                    if (d.isin==1){
                        return "已签到"
                    }else{
                        return "未签到"
                    }
                }},
            {field: 'indate', title: '签到时间',sort:true},
            {field: "whyNo",title:"备注"},
        ]],
        limits: [10, 15, 20, 25, 50, 100],
        limit: 10,
        page: true
    });
    /*搜索*/
    form.on('submit(data-search-btn)',function (data) {
        let result = data.field; //获取数据
        if (!isDate(result.startDate)){
            return false;
        }
        if (!isDate(result.endDate)){
            return false;
        }
        //执行搜索
        table.reload('currentTableId',{
            page:{
                curr:1
            },where:{
                startDate:result.startDate,
                endDate:result.endDate
            }
        },'data');
        return false;
    });
    function isDate(dateString){
        if(dateString!=null && dateString.trim()!="" ){
            var r=dateString.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
            if(r==null){
                layer.alert("请输入格式正确的日期\n\r日期格式：yyyy-mm-dd\n\r例    如：2008-08-08\n\r");
                return false;
            }
            var d=new Date(r[1],r[3]-1,r[4]);
            var num = (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]);
            if(num==0){
                layer.alert("请输入格式正确的日期\n\r日期格式：yyyy-mm-dd\n\r例    如：2008-08-08\n\r");
            }
        }
        return (num!=0);
    }
    //返回
    $(".back").on("click",function () {
        window.history.go(-1);
    });
});