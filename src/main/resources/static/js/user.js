$(function () {
    /**
     * 表格信息
     * @type {jQuery|HTMLElement}
     */
    var userGrid = $("#userGrid");
    var userGridAction = $("#userGridAction");
    var userName = null;
    var account = null;

    var currentRow;
    userGrid.datagrid({
        fit:true,
        border:false,
        url: '/system/user/list'+(userName == null?'':'?userName='+userName)+(account == null?'':'?account='+account),
        singleSelect: true,
        pagination:true,
        columns:[[
            {field:'account',title:'账号',width:180},
            {field:'userName',title:'姓名',width:150},
            {field:'tel',title:'电话',width:200},
            {field:'email',title:'邮箱',width:200},
            {field:'roles',title:'角色',width:200, formatter:function (val) {
                    var roles = [];
                    $.each(val,function () {
                        roles.push(this.roleName);
                    });
                    return roles.join(",");
                }
            },
            {field:'enable',title:'状态',width:80,align:'center',formatter:function (val) {
                    return val?"可用":"禁用";
                }
            },
            {field:'edit',title:'操作',width:100,align:'center',formatter:function (val,row) {
                return userGridAction.children("a.actions").attr('data-id',row.id).end().html();
                }
            }
        ]],
        toolbar: "#userGridToobar"
    });

    var gridPanel = userGrid.datagrid("getPanel");
    //给操作按钮绑定事件
    gridPanel.on("click","a.edit",function () {
        var id = this.dataset.id;
        formDailog(id);
    }).on("click","a.delete",function () {
        var id = this.dataset.id;
        $.messager.confirm("提示","是否删除？",function (r) {
            if(r) {
                $.ajax({
                    type: "get",
                    url: "/system/user/delete?id=" + id,
                    success: function () {
                        userGrid.datagrid('reload');
                    }
                });
            }
        })
    }).on("click","a.create",function () {
        formDailog();
    });

    /**
     * 表单窗口
     */
    function formDailog(id){
        var dialog = $("<div/>").dialog({
            iconCls:'fa fa-plus',
            id: 'formDailog',
            title:(id?'编辑':'创建')+'用户',
            href:'/system/user/'+(id?'load?id='+id:'form'),
            width:380,
            height:380,
            modal:true,
            onClose: function(){
                // 销毁窗口
                $(this).dialog("destroy");
            },
            buttons:[
                {
                    text:'保存',
                    handler:function () {
                        var userForm = $("#userForm");
                        if (userForm.form("validate")){
                            $.ajax({
                                type: "post",
                                url: "/system/user/"+(id?'update':'save'),
                                data: userForm.serialize(),
                                success:function () {
                                    userGrid.datagrid('reload');
                                    dialog.dialog('close')
                                }
                            });
                        }
                    }
                }
            ]
        });
    }

    $('#ss').searchbox({
        searcher:function(value,name){
            alert(value+','+name);
            //userGrid.datagrid('reload');
        },
        width: 300,
        menu:'#mm',
        prompt:'请输入'
    });
});