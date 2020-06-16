$(function () {

    var userForm = $("#userForm");
    $("#submitBtn").click(function () {
            if (userForm.form("validate")){
                $.ajax({
                    type: "post",
                    url: "/system/user/personal/update",
                    data: userForm.serialize(),
                    success:function () {
                        confirm("修改成功！");
                    }
                });
            }
        }
    );
});