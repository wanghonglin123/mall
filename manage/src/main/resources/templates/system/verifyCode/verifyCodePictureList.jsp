<%@ page import="cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>洋桃跨境供应链后台管理中心-验证码图片列表</title>
    <jsp:include page="../../member/common/memberCommon.jsp"></jsp:include>
</head>
<body>
<section class="main">
    <div class="table-container">
        <shiro:hasPermission name="sysVerifyCode:add">
            <div class="function-button">
                <button type="button"  class="button button-ffac00" onclick="save()">上传</button>
            </div>
        </shiro:hasPermission>
        <div class="main-content">
            <div class="word-screen word-screen-hide">
                <div class="word-screen-operation">
                    <a class="word-screen-button gatherTableList-qry" id="verifyCode-qry" href="javascript:;"><i></i>搜索</a>
                    <a class="click-show-hide" href="javascript:;"><i></i><span></span></a>
                </div>
                <div class="word-screen-cont">
                    <form id="searchForm">
                        <ul>
                            <li>
                                <label class="name" >备注：</label>
                                <input class="type-text" type="text" id="verifyCode-remark" />
                            </li>
                            <li>
                                <label class="name">类型：</label>
                                <div class="select-box">
                                    <select id="verifyCode-type">
                                        <option value="">请选择</option>
                                        <c:forEach items="${verifyCodePictureTypeMap}" var="p">
                                            <option value="${p.key}">${p.value}</option>
                                        </c:forEach>
                                    </select>
                                    <i></i>
                                </div>
                            </li>
                            <li>
                                <label class="name">状态：</label>
                                <div class="select-box">
                                    <select id="verifyCode-status">
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
                <table id="verifyCode-tableList" class="easyui-datagrid" style="width:98%;height: 380px;"></table>
            </div>
        </div>
    </div>
</section>
<script>
    $(function(){
        $('#verifyCode-tableList').datagrid({
            url : '/system/verifyCode/list/getPage',
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
                        var deletedStatus = "<%=MsOnionTableRecordStatus.DELETED.getValue()%>";
                        var isDeleted = (rowData.status == deletedStatus);
                        var array = [];
                        if(!isDeleted){
                            array.push('<a class="margin-auto click-icon click-icon-check" href="javascript:void(0)" title="查看" onclick="showDetail(' + rowData.idx + ')"></a>');
                            <shiro:hasPermission name="sysVerifyCode:del">
                            array.push('<a class="margin-auto click-icon click-icon-del" href="javascript:void(0)" title="删除" onclick="delContent(' + rowData.idx + ')"></a>');
                            </shiro:hasPermission>
                        }
                        return array.join('');
                    }
                },
                {
                    field : 'idx',
                    align : 'center',
                    width : "10%",
                    sortable : true,
                    title : 'ID'
                }, {
                    field : 'type',
                    align : 'center',
                    width : "20%",
                    title : '类型',
                    formatter : function(value, rowData, rowIndex){
                        return ${verifyCodePictureTypeJson}[value] ;
                    }
                }, {
                    field : 'imagePath2',
                    align : 'center',
                    width : "10%",
                    title : '图片',
                    formatter : function(value, rowData, rowIndex){
                        return '<img src="' + imgurl + rowData.imagePath + '" width="50" height="50" />';
                    }
                }, {
                    field : 'imagePath',
                    align : 'center',
                    width : "35%",
                    title : '图片路径'
                },{
                    field : 'status',
                    align : 'center',
                    width : "10%",
                    title : '状态',
                    formatter : function(value, rowData, rowIndex){
                        return '<span class="state-'+value+'">' + ${statusMapJson}[value] + '</span>';
                    }
                },
                {
                    title: '创建用户',
                    field: 'createMemberName',
                    width: '22%',
                    align: 'center',
                    sortable: false
                },
                {
                    title: '创建时间',
                    field: 'createTime',
                    width: '22%',
                    align: 'center',
                    sortable: false,
                    formatter: function (value, rowData, rowIndex) {
                        return  rowData.createTimeEnYyyyMMddHHmmss;
                    }
                }
                ] ],
            onLoadSuccess: function (data) {
                $(this).datagrid("fixRownumber");/* 表格行数宽度随内容变化 */
                $('#verifyCode-tableList').datagrid('clearSelections');
            },
            loadFilter: function(data){
                if (data.status == 200) {
                    return data.data;
                } else {
                    parent.layer.msg(data.msg, {icon: 2});
                    return null;
                }
            }
        });
        $('#verifyCode-qry').on('click',function () {
            $('#verifyCode-tableList').datagrid('load',{
                remark:$.trim($('#verifyCode-remark').val()),
                type:$.trim($('#verifyCode-type').val()),
                status:$.trim($('#verifyCode-status').val())
            });
        });
    });
    /**
     *  编辑或者新增
     * @param idx 主键idx
     */
    function showDetail(idx) {
        var url = '/system/verifyCode/list/detail?idxStr='+idx ;
        var title ='查看验证码图片';
        //页面层
        parent.layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['关闭'],
            resize:false,
            area: ['490px', '500px'], //宽高
            content: [url, 'no']
        });
    }
    /**
     * 删除
     * @param idx
     */
    function delContent(idx){
        parent.layer.confirm('您确定要删除吗？', {
            title: false,
            shade: 0,
            btn: ['确定', '关闭']
        }, function (index) {
            $.ajax({
                type: "post",
                url: "/system/verifyCode/list/delete",
                data: {
                    idxStr: idx
                },
                dataType: 'json',
                success: function (d) {
                    if (d.status == 200) {
                        parent.layer.msg('操作成功！', {icon: 6});
                        $('#verifyCode-tableList').datagrid('clearSelections');
                        $('#verifyCode-tableList').datagrid('reload', {});
                    } else {
                        parent.layer.msg(d.msg, {icon: 2});
                    }
                },
                error: function (d) {
                    parent.layer.msg(d.msg, {icon: 2});
                }
            });
        });
    }
    /**
     * 上传页面
     */
    function save(idx) {
        var title = "新增验证码图片";
        parent.layer.open({
            type: 2,
            title: title,
            area: ['490px', '505px'],
            btn: ['确定', '取消'],
            resize:false,
            content: ['/system/verifyCode/list/save', 'no'],
            yes: function (index, layero) {
                parent.Ms.saveLoading();
                var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                iframeWin.submitForm($('#verifyCode-tableList'));
                return false;
            },
            btn2: function () {
                Ms.clearGridSelections('verifyCode-tableList');
            },
            cancel: function () {
                Ms.clearGridSelections('verifyCode-tableList');
            }
        });
    }
</script>
<!-- 使用easyui的tabs head标签的东西，在首页读取不到，只会把css和js放在body中 -->
<link rel="stylesheet" href="${static$domain}/css/common/current.css?_v=${css$version}"/>
</body>
</html>

