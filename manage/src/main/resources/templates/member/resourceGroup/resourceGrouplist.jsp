<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>洋桃跨境供应链后台管理中心-资源组列表</title>
    <jsp:include page="../common/memberCommon.jsp"></jsp:include>
</head>
<body>
<section class="main">
    <div class="table-container">
        <shiro:hasPermission name="authManage:add">
        <div class="function-button">
            <button type="button"  class="button button-ffac00" onclick="editOrAddResourcesGroup()">新增</button>
        </div>
        </shiro:hasPermission>
        <div class="main-content">
            <div class="word-screen word-screen-hide">
                <div class="word-screen-operation">
                    <a class="word-screen-button gatherTableList-qry" id="resourceGourplist-qry" href="javascript:;"><i></i>搜索</a>
                    <a class="click-show-hide" href="javascript:;"><i></i><span></span></a>
                </div>
                <div class="word-screen-cont">
                    <form id="searchForm">
                        <ul>
                            <li>
                                <label class="name">资源组名称：</label>
                                <input class="type-text" type="text" id="resourceGourpName">
                            </li>
                            <li>
                                <label class="name" style="padding-left: 10px;">状态：</label>
                                <div class="select-box">
                                    <select id="resourceGourpState">
                                        <option value="">请选择</option>
                                        <c:forEach items="${statusMap}" var="m">
                                            <option value="${m.key}">${m.value}</option>
                                        </c:forEach>
                                    </select>
                                    <i></i>
                                </div>
                            </li>
                        </ul>
                    </form>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="yt-table" >
                <table id="resourceGourplist" class="easyui-datagrid" style="width:98%;height: 464px;"></table>
            </div>
        </div>
    </div>

</section>
<script type="text/javascript">
    $(function(){
        $('#resourceGourplist').datagrid({
            url : '/authManage/paging',
            fit : true,
            nowrap : true,
            fitColumns : true,
            pagination : true,
            rownumbers : true,
            pageSize : 10,
            pageList : [10, 20, 30, 40, 50 ],
            idField : 'id',
            columns : [ [/* {
                field : 'idx',
                checkbox:true
            },*/

            {
                field : 'control',
                align : 'center',
                width : "15%",
                title : '操作',
                formatter : function(value, rowData, rowIndex){
                    var array=[];
                    if(rowData.status == '1'){
                        <shiro:hasPermission name="authManage:disabled">
                        array.push('<a class="margin-auto click-icon click-icon-off" href="javascript:void(0)" title="禁用" onclick="$updateStatus(\'/authManage/updateStatus/'+rowData.idx+'/2\', null, \'resourceGourplist\');"></a>');
                        </shiro:hasPermission>

                        <shiro:hasPermission name="authManage:edit">
                        array.push('<a class="margin-auto click-icon click-icon-edit" href="javascript:void(0)" title="编辑" onclick="editOrAddResourcesGroup('+rowData.idx+')"></a>');
                        </shiro:hasPermission>
                        <shiro:hasPermission name="authManage:del">
                        array.push('<a class="margin-auto click-icon click-icon-del" href="javascript:void(0)" title="删除" onclick="$updateStatus(\'/authManage/updateStatus/'+rowData.idx+'/0\', null, \'resourceGourplist\');"></a>');
                        </shiro:hasPermission>
                    }
                    if(rowData.status == '2'){
                        <shiro:hasPermission name="authManage:recover">
                        array.push('<a class="margin-auto click-icon click-icon-open" href="javascript:void(0)" title="激活" onclick="$updateStatus(\'/authManage/updateStatus/'+rowData.idx+'/1\', null, \'resourceGourplist\');"></a>');
                        </shiro:hasPermission>
                        <shiro:hasPermission name="authManage:del">
                        array.push('<a class="margin-auto click-icon click-icon-del" href="javascript:void(0)" title="删除" onclick="$updateStatus(\'/authManage/updateStatus/'+rowData.idx+'/0\', null, \'resourceGourplist\');"></a>');
                        </shiro:hasPermission>
                    }
                    return array.join('');
                }
            },
            {
                field : 'idx',
                align : 'center',
                width : 30,
                sortable : true,
                title : 'ID'
            }, {
                field : 'name',
                align : 'center',
                sortable : true,
                width : 50,
                title : '资源组名称'
            },{
                field : 'code',
                align : 'center',
                sortable : true,
                width : 50,
                title : '编码'
            },{
                field : 'status',
                align : 'center',
                sortable : true,
                width : 50,
                title : '状态',
                formatter : function(value, rowData, rowIndex){
                    return '<span class="state-'+value+'">' + ${statusMapJson}[value] + '</span>';
                }
            } ] ],
            onLoadSuccess: function (data) {
                $(this).datagrid("fixRownumber");/* 表格行数宽度随内容变化 */
                $('#resourceGourplist').datagrid('clearSelections');
            }
        });
        $('#resourceGourplist-qry').on('click',function () {
            $('#resourceGourplist').datagrid('load',{
                name:$.trim($('#resourceGourpName').val()),
                status:$('#resourceGourpState').val()
            });
        });
    });
    /**
     *  编辑或者新增
     * @param idx 主键idx
     */
    function editOrAddResourcesGroup(idx) {
        var url = idx ? '/authManage/edit/'+idx : '/authManage/add/1';
        var title =idx ? '修改资源组' : '新增资源组';
        //页面层
        parent.layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['保存','关闭'],
            resize:false,
            area: ['650px', '520px'], //宽高
            content: [url, 'no'],
            yes: function(index, layero){
                parent.layer.load(2, {shade : 0.01, time : 5000});
                var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                iframeWin.saveMenuAndButton($('#resourceGourplist'));
                return false;
            }
        });
    }
</script>
</body>
</html>
