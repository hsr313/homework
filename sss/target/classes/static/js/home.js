$(function () {
    $.ajax({
        type:"get",
        url:'/LoginProject/resume/queryAll',
        contentType: 'application/json;charset=utf-8',
        dataType: 'json',
        success : function(data) {
            var str = "";
            for(var i=0;i<data.length;i++){
                str += "<tr>" +
                    "<td>" + data[i].id + "</td>" +
                    "<td>" + data[i].name + "</td>" +
                    "<td>" + data[i].address + "</td>" +
                    "<td>" + data[i].phone + "</td>" +
                    "<td><input type='button' value='编辑' onclick='update(this)'/>|<input type='button' value='删除' onclick='del(this)'/></td>"
                    "</tr>";
            }
            console.log(str);
            $("#t_body").append(str);
        },
        error : function() {
            alert('异常');
        }

    })

})

function update(obj) {
        var tr = $(obj).closest("tr");
        var id = tr.find("td:eq(0)").text();
        location.href = "../statics/page/update.html?id=" + id;
}

function del(obj) {
    var tr = $(obj).closest("tr");
    var id = tr.find("td:eq(0)").text();
    if(confirm('确定要删除吗？')){
        $.ajax({
            type:"GET",
            url:"/LoginProject/resume/deleteById",
            contentType: 'application/json;charset=utf-8',
            data: {"id":id},
            success : function(data){
                alert(data);
                window.location.reload();
            },
            error : function() {
                alert('异常');
                window.location.reload();
            }
        })
    }
}
