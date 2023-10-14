layui.use(['layer', 'jquery', 'laydate', 'element', 'form'], function () {

    var $ = layui.jquery;
    var layer = layui.layer;
    var element = layui.element;
    var form = layui.form;
    let laydate = layui.laydate;
    let empId = $("input[name='empId']").val();
    //日期
    laydate.render({
        elem: '#birthday'
    });

    //自定义验证规则
    form.verify({
        cname: function (value) {
            if (value == "") {
                return "请填写会员姓名";
            }
        },
        stature: function (value) {
            if (isNaN(value) || value <= 0) {
                return '请填入正确的身高';
            }
        },
        age: function (value) {
            if (isNaN(value) || value <= 0 || value > 200) {
                return '请填入正确的年龄(年龄在0到200之间)';
            }
        },
        weight: function (value) {
            if (isNaN(value) || value <= 0) {
                return '请填入正确的体重信息';
            }
        },
        price: function (value) {
            if (isNaN(value) || value <= 0) {
                return "请填入正确的价格";
            }
        }

    });
    //监听提交
    form.on('submit(save1)', function (data) {
        let vipid = $("input[name='vipid']:checked").val();
        if (vipid == undefined) {
            layer.alert("您还未选择会员卡类型！");
            return false;
        }
        $.ajax({
            url: "/member/addCuspay",
            type: "post",
            data: JSON.stringify(data.field),
            contentType: "application/json;charsetset=UTF-8",
            success: function (date) {
                layer.alert(date.info);
            }
        });
        return false;
    });

    /*-------------------套餐------------------------*/
    function sumMethod() {
        let sum = 0; //数量
        let unitPrice = 0.0;//单价
        $(".group .add").on("click", function () {
            // let index = $(this).parent().parent().index(); //获取当前的父级元素索引
            sum = parseInt($(this).parent().parent().find(".sum").text());
            unitPrice = parseFloat($(this).parent().parent().find(".unit-price").text());
            sum = 1 + sum;
            $(this).parent().parent().find("button.sum").text(sum);
            groupPrice(sum, $, unitPrice,this);
            sums=0;
        });
        $(".group .minus").on("click", function () {
            // let index = $(this).parent().parent().index(); //获取当前的父级元素索引
            sum = parseInt($(this).parent().parent().find(".sum").text());
            unitPrice = parseFloat($(this).parent().parent().find(".unit-price").text());
            if (sum <= 0) {
                return false;
            }
            sum -= 1;
            $(this).parent().parent().find(".sum").text(sum);
            groupPrice(sum, $, unitPrice, this);
            sums=0;
        });

        //单个套餐的合计方法
        function groupPrice(s, $,price,t) {
            let sumss = (s * price).toFixed(2);
            $(t).parent().parent().find(".group-sum").text(sumss);
            sumsPrice(t);
        }

        let sums = Number(0);
        let str;
        $("#pack").on("click",function () {
            sums=0;
            str = document.getElementsByClassName("groupSum1");
        });

        $("#produce").on("click",function () {
            sums = 0;
            str = document.getElementsByClassName("groupSum2");
        });
        //总价方法
        function sumsPrice(t) {
            $.each(str, function (i, val) {
                if (Number(val.innerText) != 0) {
                    sums += Number(val.innerText);
                }
            });
            $(t).parent().parent().parent().parent().find(".group .sums").text(sums);
        }
    }

    //计算加减套餐
    sumMethod();
//监听提交
    form.on('submit(save2)', function () {
        let sums = $(this).parent().parent(".group").find(".sums").text();  //总价
        console.log(sums)
        let cid = $("input[name='cid']").val();
        //获取账单信息
        let arr = $(".group-meal1");
        let array = new Array();
        let resultAlert = $('#resultAlert');
        $.each(arr, function (i, val) {
            let sum = Number($(this).find(".layui-btn-group .sum").text());
            if (sum > 0) {
                let name = $(this).find(".unit-name").text();
                let price = Number($(this).find(".unit-price").text());
                let groupPrice = Number($(this).find(".group-sum").text());
                let pack = $(this).find("input[name='pid']").val();
                let produces = new Array();
                let produce = $(this).find(".produces");
                $.each(produce, function (j, v) {
                    let produceName = $(this).find("span[name='produceName']").text();
                    produces.push({"name": produceName, "num": sum})
                });
                array.push({
                    "empId": empId,
                    "cid": cid,
                    "pack": pack,
                    "sum": sum,
                    "name": name,
                    "price": price,
                    "groupPrice": groupPrice,
                    "produces": produces
                });
            }

        });

        array.forEach(function (val, index) {
            resultAlert.append("<p style='text-align: left;line-height: 32px;'>" +
                "<span>" + (index + 1) + ". </span>" +
                "<span>" + val.name + "</span>" +
                "<span style='color: #d9dada'>&nbsp;&nbsp;￥</span>" +
                "<span>" + val.price + "</span>" +
                "<span style='margin-left: 20%'>" + val.sum + "个</span>" +
                "<span style='margin-left: 20%'><span style='color: #bbbbbb'>￥</span>" + val.groupPrice + "</span>" +
                "</p>")
        });
        resultAlert.append("<p style='margin: 4%;'>合计：" + sums + "</p>");
        if (sums <= 0) {
            layer.alert("您还没有选择套餐不能提交！");
            return false;
        }
        resultAlert.css("display", "block");
        layer.open({
            type: 1,
            title: "购买详单",
            content: $('#resultAlert'), //这里content是一个普通的String
            area: ['600px', '330px'],
            btn: ['确认提交', '取消'],//可以无限个按钮
            btnAlign: 'c',
            cancel: function (index, layero) {
                layer.close(index);
                f(resultAlert, array);

                return false;
            },
            yes: function (index, layero) {
                $.ajax({
                    url: "/member/addCuspayList",
                    type: "post",
                    contentType: 'application/json',
                    data: JSON.stringify(array),
                    success: function (date) {
                        layer.alert(date.info);
                        f(resultAlert,array);
                    }
                });
                layer.close(index);
                f(resultAlert,array);
            },
            btn2: function (index, layero) {
                f(resultAlert, array);
            }
        });

        return false;
    });

    function f(resultAlert,array) {
        resultAlert.css("display", "none");
        resultAlert.html("");
        array.splice(0, array.length);
        //清空数据
        $(".sums").text("0.00");
        $(".group-sum").text("0.00");
        $(".sum").text("0");
        console.log("-------------------")
    }
    //------------购买产品--------------
    form.on("submit(save3)", function () {
        //5.总产品的价格
        let sums = $(this).parent().prev(".group").find(".sums").text();
        //顾客id
        let cid = $("input[name='cid']").val();
        //获取账单信息
        let arr = $(".group-meal2");
        //装数据的数组
        let array = new Array();
        $.each(arr, function (i, val) {
            //3.产品数量
            let sum = Number($(this).find(".layui-btn-group .sum").text());
            if(sum >0){
                //1.产品名称
                let name= $(this).find("span[name='name']").text();
                let id=$(this).find("input[name='id']").val();
                //2.产品单价
                let price= $(this).find(".unit-price").text();
                //4.产品总价
                let prices = $(this).find(".groupSum2").text();
                array.push({empId:empId,sum:sum,cid:cid,produceId:id,produces:[{id:id,name:name,price:price,num:sum}],prices:prices});
            }
        });

        /*array.forEach(function (val,index) {
            console.log(index,val);
        })*/

        //弹框父标签
        let resultAlert = $('#resultAlert1');
        array.forEach(function (val, index) {
            resultAlert.append("<p style='text-align: left;line-height: 32px;'>" +
                "<span>" + (index + 1) + ". </span>" +
                "<span>" + val.produces[0].name + "</span>" +
                "<span style='color: #d9dada'>&nbsp;&nbsp;￥</span>" +
                "<span>" + val.produces[0].price + "</span>" +
                "<span style='margin-left: 20%'>" + val.produces[0].num + "盒</span>" +
                "<span style='margin-left: 20%'><span style='color: #bbbbbb'>￥</span>" + val.prices + "</span>" +
                "</p>")
        });
        resultAlert.append("<p style='margin: 4%;'>合计：" + sums + "</p>");
        if (sums <= 0) {
            layer.alert("您还没有选择套餐不能提交！");
            return false;
        }
        resultAlert.css("display", "block");
        layer.open({
            type: 1,
            title: "购买产品详单",
            content: $('#resultAlert1'), //这里content是一个普通的String
            area: ['600px', '330px'],
            btn: ['确认提交', '取消'],//可以无限个按钮
            btnAlign: 'c',
            cancel: function (index, layero) {
                layer.close(index);
                f(resultAlert, array);
                return false;
            },
            yes: function (index, layero) {
                $.ajax({
                    url: "/member/addProduce",
                    type: "post",
                    contentType: 'application/json',
                    data: JSON.stringify(array),
                    success: function (date) {
                        layer.alert(date.info);
                        f(resultAlert,array);
                    }
                });
                layer.close(index);
                f(resultAlert,array);
            },
            btn2: function (index, layero) {
                f(resultAlert, array);
            }
        });

        return false;
    });
});