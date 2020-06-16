$(function () {
    var types = {
        MENU:"菜单",
        FUNCTION:"功能",
        BLOCK:"区域"
    }
    /**
     * 表格信息
     * @type {jQuery|HTMLElement}
     */
    var permissionGrid = $("#permissionGrid");
    permissionGrid.treegrid({
        fit:true,
        border:false,
        url: '/system/permission/list',
        idField:'id',
        treeField:'name',
        singleSelect: true,
        modal:true,
        columns:[[
            {field:'name',title:'名称',width:180},
            {field:'permissionKey',title:'标识',width:150},
            {field:'type',title:'类型',width:80,align:'center',formatter:function (val) {
                return types[val];
            }},
            {field:'path',title:'路径',width:200},
            {field:'resource',title:'资源',width:200},
            {field:'weight',title:'权重',align:'center',width:80},
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
            text:'创建权限',
            handler: function () {
                formDailog();
            }
        }]
    });

    var gridPanel = permissionGrid.treegrid("getPanel");
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
                    url: "/system/permission/delete?id=" + id,
                    success: function () {
                        permissionGrid.treegrid('reload');
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
            title:(id?'编辑':'创建')+'权限',
            href:'/system/permission/'+(id?'load?id='+id:'form'),
            width:380,
            height:460,
            onClose: function(){
                // 销毁窗口
                $(this).dialog("destroy");
            },
            buttons:[
                {
                    text:'保存',
                    handler:function () {
                        var permissionForm = $("#permissionForm");
                        if (permissionForm.form("validate")){
                            $.ajax({
                                type: "post",
                                url: "/system/permission/"+(id?'update':'save'),
                                data: permissionForm.serialize(),
                                success:function () {
                                    permissionGrid.treegrid('reload');
                                    dialog.dialog('close')
                                }
                            });
                        }
                    }
                }
            ]
        });
    }
});