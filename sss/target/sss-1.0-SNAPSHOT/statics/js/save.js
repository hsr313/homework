function add() {
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
            alert("修改成功");
            window.location.href = "/resume/home";
        },
        error: function () {

        }
    });
}