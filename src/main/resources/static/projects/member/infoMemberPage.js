window.onload=function(){
    let cd = document.getElementById("createDate").innerText;
    console.log(cd)
    cd = cd.replace('T', ' ');
    document.getElementById("createDate").innerText=cd;

};
layui.use(['form', 'layedit', 'laydate','jquery'], function() {
    var form = layui.form
        , layer = layui.layer
        , layedit = layui.layedit
        , $=layui.jquery
        , laydate = layui.laydate;
    $("input").attr("disabled",true);

});