<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>洋桃跨境供应链后台管理中心-用户类别列表</title>
    <jsp:include page="../common/memberCommon.jsp"></jsp:include>
</head>
<body>
<section class="main">
    <div class="table-container">
        <shiro:hasPermission name="memberCate:add">
        <div class="function-button">
            <button type="button"  class="button button-ffac00" onclick="editOrAddMemberCate()">新增</button>
        </div>
        </shiro:hasPermission>
        <div class="main-content">
            <div class="word-screen word-screen-hide">
                <div class="word-screen-operation">
                    <a class="word-screen-button" id="memberCatelist-qry" href="javascript:;"><i></i>搜索</a>
                    <a class="click-show-hide" href="javascript:;"><i></i><span></span></a>
                </div>
                <div class="word-screen-cont">
                    <form id="searchForm">
                        <ul>
                            <li>
                                <label class="name">成员类别名称：</label>
                                <input class="type-text" type="text"  id="memberCateName">
                            </li>
                            <li>
                                <label class="name" style="padding-left: 10px;">状态：</label>
                                <div class="select-box">
                                    <select  id="memberCateState">
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
                <table id="memberCatelist" class="easyui-datagrid" style="width:98%;height: 464px;">
                </table>
            </div>
        </div>
    </div>
</section>
<script>
    $(function(){
        $('#memberCatelist').datagrid({
            url : '/memberCate/paging',
            fit : true,
            nowrap : true,
            fitColumns : true,
            pagination : true,
            rownumbers : true,
            pageSize : 10,
            pageList : [10, 20, 30, 40, 50 ],
            idField : 'id',
            columns : [ [ /*{
                field : 'idx',
                checkbox:true
            },*/
                {
                    field : 'control',
                    align : 'center',
                    width : "20%",
                    title : '操作',
                    formatter : function(value, rowData, rowIndex){
                        var array=[];
                        array.push('<a class="margin-auto click-icon click-icon-check" href="javascript:void(0)" title="查看" onclick="showForMemberCate('+rowData.idx+')"></a>');
                        if(rowData.status == '1'){
                            <shiro:hasPermission name="memberCate:disabled">
                            array.push('<a class="margin-auto click-icon click-icon-off" href="javascript:void(0)" title="禁用" onclick="$updateStatus(\'/memberCate/updateStatus/'+rowData.idx+'/2\', null, \'memberCatelist\');"></a>');
                            </shiro:hasPermission>
                            <shiro:hasPermission name="memberCate:edit">
                            array.push('<a class="margin-auto click-icon click-icon-edit" href="javascript:void(0)" title="编辑" onclick="editOrAddMemberCate('+rowData.idx+')"></a>');
                            </shiro:hasPermission>
                            <shiro:hasPermission name="memberCate:del">
                            array.push('<a class="margin-auto click-icon click-icon-del" href="javascript:void(0)" title="删除" onclick="$updateStatus(\'/memberCate/updateStatus/'+rowData.idx+'/0\', null, \'memberCatelist\');"></a>');
                            </shiro:hasPermission>
                        }
                        if(rowData.status == '2'){
                            <shiro:hasPermission name="memberCate:recover">
                            array.push('<a class="margin-auto click-icon click-icon-open" href="javascript:void(0)" title="激活" onclick="$updateStatus(\'/memberCate/updateStatus/'+rowData.idx+'/1\', null, \'memberCatelist\');"></a>');
                            </shiro:hasPermission>
                            <shiro:hasPermission name="memberCate:del">
                            array.push('<a class="margin-auto click-icon click-icon-del" href="javascript:void(0)" title="删除" onclick="$updateStatus(\'/memberCate/updateStatus/'+rowData.idx+'/0\', null, \'memberCatelist\');"></a>');
                            </shiro:hasPermission>
                        }
                        return array.join('');
                    }
                },
                {
                field : 'idxCodeS',
                align : 'center',
                width : '20%',
                sortable : true,
                title : 'ID'
            },
          /*  {
                 field : 'idxCodeS',
                 align : 'center',
                 width : 35,
                 sortable : true,
                 title : 'IdxCode'
            },*/
            {
                field : 'name',
                align : 'center',
                width : '30%',
                sortable : true,
                title : '成员类别名称'
            },{
                field : 'status',
                align : 'center',
                width : '30%',
                title : '状态',
                sortable : true,
                formatter : function(value, rowData, rowIndex){
                    return '<span class="state-'+value+'">' + ${statusMapJson}[value] + '</span>';
                }
            } ] ],
            onLoadSuccess: function (data) {
                $(this).datagrid("fixRownumber");/* 表格行数宽度随内容变化 */
                $('#memberCatelist').datagrid('clearSelections');
            }
        });
        $('#memberCatelist-qry').on('click',function () {
            $('#memberCatelist').datagrid('load',{
                name:$.trim($('#memberCateName').val()),
                status:$('#memberCateState').val()
            });
        });
    });
    /**
     *  编辑或者新增
     * @param idx 主键idx
     */
    function editOrAddMemberCate(idx) {
        var url = idx ? '/memberCate/edit/'+idx : '/memberCate/add/1';
        var title =idx ? '修改成员类别' : '新增成员类别';
        //页面层
        parent.layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['保存','关闭'],
            resize:false,
            area: ['500px', '300px'], //宽高
            content: [url, 'no'],
            yes: function(index, layero){
                parent.layer.load(2, {shade : 0.01, time : 5000});
                var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                iframeWin.submitForm($('#memberCatelist'));
                return false;
            }
        });
    }
    function showForMemberCate(idx) {
        var url = '/memberCate/view/'+idx ;
        var title ='查看成员类别';
        //页面层
        parent.layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['关闭'],
            resize:false,
            area: ['500px', '300px'], //宽高
            content: [url, 'no']
        });
    }
</script>
<!-- 使用easyui的tabs head标签的东西，在首页读取不到，只会把css和js放在body中 -->
<link rel="stylesheet" href="${static$domain}/css/common/current.css?_v=${css$version}"/>
</body>
</html>