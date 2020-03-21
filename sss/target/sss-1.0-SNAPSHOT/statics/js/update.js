$(function () {
    //获取 上一个搜索页面传来的参数
    var url = window.location.href;
    var result = url.split("="); //截取 url中的“=”,获得“=”后面的参数
    var id = result[1];
    $.ajax({
        //几个参数需要注意一下
        type: 'GET',//方法类型
        contentType: 'application/json;charset=UTF-8',
        url: '/resume/findOne',//url
        data: {"id":id},
        success: function (result) {
            $("#resumeId").attr("value",id);
            $("#name").attr("value",result.name);
            $("#address").attr("value",result.address);
            $("#phone").attr("value",result.phone);
        },
        error: function () {

        }
    })
})


function save() {
    var id = $("#resumeId").val();
    var name = $("#name").val();
    var address = $("#address").val();
    var phone = $("#phone").val();
    $.ajax({
        //几个参数需要注意一下
        type: 'POST',//方法类型
        contentType: 'application/json;charset=UTF-8',
        url: '/resume/addOrSave',//url
        data: JSON.stringify({id:id,name:name,address:address,phone:phone}),
        success: function (result) {
            console.log(result);//打印服务端返回的数据(调试用)
            alert("SUCCESS");
            window.location.href = "/resume/home";
        },
        error: function () {

        }
    });
}