$(function () {
    /**
     * 表格信息
     * @type {jQuery|HTMLElement}
     */
    var clsGrid = $("#clsGrid");
    clsGrid.datagrid({
        fit:true,
        border:false,
        url: '/system/cls/list',
        singleSelect: true,
        modal:true,
        columns:[[
            {field:'id',title:'编号',width:30},
            {field:'className',title:'名称',width:180},
            {field:'grade',title:'年级',width:80,align:'center',formatter:function (val) {
                return val.name;
            }},
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
            text:'添加班级',
            handler: function () {
                formDailog();
            }
        }]
    });

    var gridPanel = clsGrid.treegrid("getPanel");
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
                    url: "/system/cls/delete?id=" + id,
                    success: function () {
                        clsGrid.datagrid('reload');
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
            title:(id?'编辑':'添加')+'班级',
            href:'/system/cls/'+(id?'load?id='+id:'form'),
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
                        var clsForm = $("#clsForm");
                        if (clsForm.form("validate")){
                            $.ajax({
                                type: "post",
                                url: "/system/cls/"+(id?'update':'save'),
                                data: clsForm.serialize(),
                                success:function () {
                                    clsGrid.datagrid('reload');
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