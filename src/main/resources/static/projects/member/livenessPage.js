layui.use(['layer', 'layuimini', 'echarts', 'form'], function () {
    let $ = layui.jquery,
        layer = layui.layer,
        layuimini = layui.layuimini,
        form = layui.form;
    echarts = layui.echarts;

    //设置select默认为当前月
    $("select[name='month']").each(function () {
        $(this).find("option").eq(new Date().getMonth()).prop("selected", true)
    });

    layui.form.render('select');  // 更新select

    /**
     * 报表功能
     */
    function e(a, month, type) {
        $.getJSON("/consumer/getJSONData", {month: month, type: type}, function (data) {
            if (data.info != null) {
                layer.alert(data.info);
                return false;
            }
            a.setOption(data, true);
        });
    }

    var echartsRecords = echarts.init(document.getElementById('echarts-records'), 'walden');
    e(echartsRecords, null, null);

    // echarts 窗口缩放自适应
    window.onresize = function () {
        echartsRecords.resize();
    };

    form.on('select(type)', function (data) {
        console.log("type", data.value);
        e(echartsRecords, null, data.value);
    });
    form.on('select(month)', function (data) {
        console.log("month", data.value);
        e(echartsRecords, data.value, null);
    });


});