<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>洋桃跨境供应链后台管理中心-系统设置列表</title>
    <jsp:include page="../../member/common/memberCommon.jsp"></jsp:include>
</head>
<body>
<section class="main">
    <div class="table-container">
        <shiro:hasPermission name="sysset:add">
            <div class="function-button">
                <button type="button"  class="button button-ffac00" onclick="editOrAddDict2()">新增</button>
            </div>
        </shiro:hasPermission>
        <div class="main-content">
            <div class="word-screen word-screen-hide" >
                <div class="word-screen-operation">
                    <a class="word-screen-button gatherTableList-qry" id="sysset-qry" href="javascript:;"><i></i>搜索</a>
                    <a class="click-show-hide" href="javascript:;"><i></i><span></span></a>
                </div>
                <div class="word-screen-cont">
                    <form id="searchForm">
                        <ul>
                            <li>
                                <label class="name">设置编码：</label>
                                <input class="type-text" type="text" id="setkey" />
                            </li>
                            <li>
                                <label class="name">状态：</label>
                                <div class="select-box">
                                    <select id="syssetState">
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
            <div class="word-hint-box">
                <div class="hint-a">
                    <i>!</i>
                    <p>温馨提示：每一次发布新版本，都要配置好【灰度环境】、【生产环境】的版本号，通过 sys.env.version 进行配置，否则相关域名无法访问。</p>
                </div>
            </div>
            <div class="yt-table" >
                <table id="syssetlist" class="easyui-datagrid" style="width:98%;height: 464px;"></table>
            </div>
        </div>
    </div>
</section>
<script>
    $(function(){
        $('#syssetlist').datagrid({
            url : '/sysset/paging',
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
                    array.push('<a class="margin-auto click-icon click-icon-check" href="javascript:void(0)" title="查看" onclick="showForDict2('+rowData.idx+')"></a>');
                    if(rowData.status == '1'){

                        <shiro:hasPermission name="sysset:edit">
                        array.push('<a class="margin-auto click-icon click-icon-edit" href="javascript:void(0)" title="编辑" onclick="editOrAddDict2('+rowData.idx+')"></a>');
                        </shiro:hasPermission>

                        <shiro:hasPermission name="sysset:disabled">
                        array.push('<a class="margin-auto click-icon click-icon-off" href="javascript:void(0)" title="禁用" onclick="$updateStatus(\'/sysset/updateStatus/'+rowData.idx+'/2\', null, \'syssetlist\');"></a>');
                        </shiro:hasPermission>

                        <shiro:hasPermission name="sysset:del">
                        array.push('<a class="margin-auto click-icon click-icon-del" href="javascript:void(0)" title="删除" onclick="$updateStatus(\'/sysset/updateStatus/'+rowData.idx+'/0\', null, \'syssetlist\');"></a>');
                        </shiro:hasPermission>
                    }
                    if(rowData.status == '2'){
                        <shiro:hasPermission name="sysset:recover">
                        array.push('<a class="margin-auto click-icon click-icon-open" href="javascript:void(0)" title="激活" onclick="$updateStatus(\'/sysset/updateStatus/'+rowData.idx+'/1\', null, \'syssetlist\');"></a>');
                        </shiro:hasPermission>
                        <shiro:hasPermission name="sysset:del">
                        array.push('<a class="margin-auto click-icon click-icon-del" href="javascript:void(0)" title="删除" onclick="$updateStatus(\'/sysset/updateStatus/'+rowData.idx+'/0\', null, \'syssetlist\');"></a>');
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
                field : 'settingKey',
                align : 'center',
                width : 50,
                sortable : true,
                title : '设置编码'
            },{
                field : 'settingValue',
                align : 'center',
                width : 80,
                sortable : true,
                title : '设置值'
            },{
                field : 'remark',
                align : 'center',
                width : 70,
                sortable : true,
                title : '备注'
            }
            ,{
                field : 'status',
                align : 'center',
                width : 20,
                title : '状态',
                    sortable : true,
                formatter : function(value, rowData, rowIndex){
                    return '<span class="state-'+value+'">' + ${statusMapJson}[value] + '</span>';
                }
            } ] ],
            onLoadSuccess: function (data) {
                $(this).datagrid("fixRownumber");/* 表格行数宽度随内容变化 */
                $('#syssetlist').datagrid('clearSelections');
            }
        });

        $('#sysset-qry').on('click',function () {
            $('#syssetlist').datagrid('load',{
                setKey:$.trim($('#setkey').val()),
                status:$.trim($('#syssetState').val())
            });
        });

    });


    /**
     *  编辑或者新增
     * @param idx 主键idx
     */
    function editOrAddDict2(idx) {
        var url = idx ? '/sysset/edit/'+idx : '/sysset/add/1';
        var title =idx ? '修改系统设置' : '新增系统设置';
        //页面层
        parent.layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['保存','关闭'],
            area: ['530px', '340px'], //宽高
            resize:false,
            content: [url, 'no'],
            yes: function(index, layero){
                parent.layer.load(2,{
                    shade : 0.01,
                    time : 5000
                });
                var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                iframeWin.submitForm($('#syssetlist'));
                return false;
            }
        });
    }
    /**
     *  编辑或者新增
     * @param idx 主键idx
     */
    function showForDict2(idx) {
        var url = '/sysset/view/'+idx ;
        var title ='查看系统设置';
        //页面层
        parent.layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['关闭'],
            resize:false,
            area: ['530px', '340px'], //宽高
            content: [url, 'no']
        });
    }

</script>
<!-- 使用easyui的tabs head标签的东西，在首页读取不到，只会把css和js放在body中 -->
<link rel="stylesheet" href="${static$domain}/css/common/current.css?_v=${css$version}"/>

</body>
</html>

