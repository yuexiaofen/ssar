$(function () {
    /**
     * 表格信息
     * @type {jQuery|HTMLElement}
     */
    var practiceGrid = $("#practiceGrid");
    var practiceGridAction = $("#practiceGridAction");

    var currentRow;
    practiceGrid.datagrid({
        fit:true,
        border:false,
        url: '/teacher/practice/list',
        singleSelect: true,
        pagination:true,
        columns:[[
            {field:'practiceName',title:'名称',width:180},
            {field:'teacher',title:'发布教师',width:200, formatter:function (val) {
                    return this.userName;
                }
            },
            {field:'createTime',title:'创建时间',width:200},
            {field:'updateTime',title:'更新时间',width:200},
            {field:'enable',title:'状态',width:80,align:'center',formatter:function (val) {
                    return val?"可用":"禁用";
                }
            },
            {field:'edit',title:'操作',width:100,align:'center',formatter:function (val,row) {
                return practiceGridAction.children("a.actions").attr('data-id',row.id).end().html();
                }
            }
        ]],
        toolbar: "#practiceGridToobar"
    });

    var gridPanel = practiceGrid.datagrid("getPanel");
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
                    url: "/teacher/practice/delete?id=" + id,
                    success: function () {
                        practiceGrid.datagrid('reload');
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
            title:(id?'编辑':'创建')+'训练题',
            href:'/teacher/practice/'+(id?'load?id='+id:'form'),
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
                        var practiceForm = $("#practiceForm");
                        if (practiceForm.form("validate")){
                            $.ajax({
                                type: "post",
                                url: "/teacher/practice/"+(id?'update':'save'),
                                data: practiceForm.serialize(),
                                success:function () {
                                    practiceGrid.datagrid('reload');
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
            //practiceGrid.datagrid('reload');
        },
        width: 300,
        menu:'#mm',
        prompt:'请输入'
    });
});