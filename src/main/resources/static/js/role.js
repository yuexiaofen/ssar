$(function () {
    /**
     * 表格信息
     * @type {jQuery|HTMLElement}
     */
    var roleGrid = $("#roleGrid");
    var rolePermissionPanel = $("#rolePermissionPanel");
    var rolePermissonTree = $("#rolePermissonTree");
    var currentRow;
    roleGrid.datagrid({
        fit:true,
        border:false,
        url: '/system/role/list',
        singleSelect: true,
        modal:true,
        columns:[[
            {field:'roleName',title:'名称',width:180},
            {field:'roleKey',title:'标识',width:150},
            {field:'description',title:'描述',width:200},
            {field:'enable',title:'状态',width:80,align:'center',formatter:function (val) {
                    return val?"可用":"禁用";
                }
            },
            {field:'edit',title:'操作',width:100,align:'center',formatter:function (val,row) {
                    var btns = [];
                    btns.push('<a data-id="'+row.id+'" class="actions fa fa-edit edit">编辑</a>');
                    btns.push('<a data-id="'+row.id+'" class="actions fa fa-trash-o delete">删除</a>');
                    return btns.join("");
                }
            }
        ]],
        toolbar:[{
            iconCls:'fa fa-plus',
            text:'创建角色',
            handler: function () {
                formDailog();
            }
        }],
        onSelect:function (index,row) {
            //记录当前选中的行
            currentRow = row;
            rolePermissionPanel.panel("setTitle","为["+row.roleName+"]分配权限");

            //取消以前已经选中的项目
            $.each(rolePermissonTree.tree("getChecked"),function () {
                rolePermissonTree.tree("uncheck", this.target);
            });

            //加载当前选中角色的已有权限
            $.ajax({
                type: 'get',
                url:"/system/role/permission/"+row.id,
                success:function (data) {
                    $.each(data,function () {
                        var node = rolePermissonTree.tree('find',this.id);
                        //只有当前权限是树的叶子结点时才执行节点的check方法进行选择
                        if(rolePermissonTree.tree('isLeaf',node.target)){
                            rolePermissonTree.tree('check',node.target)
                        }
                    });
                }
            });
        }
    });

    var gridPanel = roleGrid.datagrid("getPanel");
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
                    url: "/system/role/delete?id=" + id,
                    success: function () {
                        roleGrid.datagrid('reload');
                    }
                });
            }
        })
    });

    /**
     * 表单窗口
     */
    function formDailog(id){
        var dialog = $("<div/>").dialog({
            iconCls:'fa fa-plus',
            id: 'formDailog',
            title:(id?'编辑':'创建')+'角色',
            href:'/system/role/'+(id?'load?id='+id:'form'),
            width:380,
            height:280,
            onClose: function(){
                // 销毁窗口
                $(this).dialog("destroy");
            },
            buttons:[
                {
                    text:'保存',
                    handler:function () {
                        var roleForm = $("#roleForm");
                        if (roleForm.form("validate")){
                            $.ajax({
                                type: "post",
                                url: "/system/role/"+(id?'update':'save'),
                                data: roleForm.serialize(),
                                success:function () {
                                    roleGrid.datagrid('reload');
                                    dialog.dialog('close')
                                }
                            });
                        }
                    }
                }
            ]
        });
    }

    rolePermissonTree.tree({
        url:'/system/role/permission/tree',
        checkbox: true
    });

//    权限保存的按钮事件
    $("#rolePermissionSave").on('click',function () {
        if(currentRow){
            // 获取打勾和实心的节点
            var nodes = rolePermissonTree.tree("getChecked",["checked","indeterminate"]);
            var permissionIds = [];
            $.each(nodes, function () {
                permissionIds.push(this.id);
            });
            var params = "roleId="+currentRow.id+"&permissionId="+permissionIds.join("&permissionId=");
            $.ajax({
                type: 'post',
                data: params,
                url: '/system/role/permission/save',
                success:function (resp) {
                    if(resp){
                        $.messager.alert("提示","授权成功！");
                    }

                }
            });
        }
    });
});