<!DOCTYPE html>
<head>
    <title>洋桃跨境供应链后台管理中心-菜单列表</title>
    <#include "../../common/memberCommon.jsp"/>
</head>
<body>
<link rel="stylesheet" href="/style/function.css?_v=${css$version!}"/>
<div class="main" >
    <div class="table-container">
        <div class="tree-cont">
            <div class="treeMenu">
                <div class="easyui-panel">
                    <ul id="menuTree" class="easyui-tree">

                    </ul>
                </div>
            </div>
            <!-- 菜单列表 -->
            <div class="tree-right" id="menuRight">
                <shiro:hasPermission name="menu:add">
                <div class="add-ccont">
                    <button type="button" onclick="editOrAdd()" class="button button-ffac00">新增菜单</button>
                </div>
                </shiro:hasPermission>
                <div class="word-screen word-screen-hide margin-l-20  margin-t-10">
                    <div class="word-screen-operation">
                        <a class="word-screen-button gatherTableList-qry" id="menulist-qry" href="javascript:;"><i></i>搜索</a>
                        <a class="click-show-hide" href="javascript:;"><i></i><span></span></a>
                    </div>
                    <div class="word-screen-cont">
                        <form id="searchForm">
                            <ul>
                                <li>
                                    <label class="name">菜单名称：</label>
                                    <input class="type-text" type="text" id="menuName">
                                </li>
                                <li>
                                    <label class="name">状态：</label>
                                    <div class="select-box">
                                        <select id="menuState">
                                            <option value="">请选择</option>
                                        </select>
                                        <i></i>
                                    </div>
                                </li>
                            </ul>
                        </form>
                        <div class="clear"></div>
                    </div>
                </div>
                <div class="yt-table" style="padding: 20px 20px;box-sizing: border-box;">
                    <table id="menulist" class="easyui-datagrid" style="width:98%"></table>
                </div>
            </div>
            <!-- 按钮列表 -->
            <div class="tree-right" id="buttonRight" >
                <shiro:hasPermission name="menu:addButton">
                <div class="add-ccont">
                    <button type="button" onclick="editOrAddButton()"  class="addbtn">新增按钮</button>
                </div>
                </shiro:hasPermission>
                <div class="yt-table" style="padding: 20px 20px;box-sizing: border-box;">
                    <table id="buttonlist" class="easyui-datagrid" style="width:980px;height:400px"></table>
                </div>
            </div>
        </div>
    </div>
    </div>
</div>
<script>
    $(function(){
        // 左边菜单树
        var $tree = $('#menuTree');
        $tree.html('拼命加载中...');
        $.ajax({
            timeout : Ms.AJAX_TIME_OUT,
            type : "POST",
            url : '/menu/getMenuData',
            cache : false
        }).done(function (result) {
            // 初始化树菜单，
            $tree.tree({
                data : result,
                animate:true,
                onClick: function (node) {
                    $tree.tree('toggle', node.target).tree('select', node.target);
                    // 如果是最底层菜单查询按钮列表
                    if ($tree.tree("isLeaf", node.target)) {
                        var options = $('#buttonlist').datagrid('options');
                        options.url = '/menu/button/paging';
                        options.queryParams = {
                            menuIdx : node.id
                        };
                        $("#buttonlist").datagrid('reload');
                        $('#buttonRight').show();
                        $('#menuRight').hide();
                    }else {
                        // 查询菜单列表
                        $('#buttonRight').hide();
                        $('#menuRight').show();

                        $('#menulist').datagrid('load',{
                            name : $.trim($('#name').val()),
                            pid : node ? node.id : null,
                            status : $('#status').val()
                        });
                    }
                },
                onLoadSuccess:function(){
                    // remove menu
                    if('${hideMenuIdxs!}'){
                        var menuIdxs = '${hideMenuIdxs!}';
                        var menuIdxArr = menuIdxs.split(",");
                        for (x in menuIdxArr) {
                            var node = $tree.tree('find',menuIdxArr[x]);
                            $tree.tree('remove', node.target);
                        }
                    }

                }
            })
        }).fail(function(result) {
            parent.layer.msg('加载菜单树失败,请重试...', {timeout:1500,icon: 2});
        });
        // 初始化菜单表格
        $('#menulist').datagrid({
            url : '/menu/paging',
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
                width : "17%",
                title : '操作',
                formatter : function(value, rowData, rowIndex){
                    var array=[];
                    array.push('<a class="margin-auto click-icon click-icon-check" href="javascript:void(0)" title="查看" onclick="showForMenu('+rowData.idx+')"></a>');
                    if(rowData.status == '1'){
                        array.push('<a class="margin-auto click-icon click-icon-off" href="javascript:void(0)" title="禁用" onclick="$updateStatus(\'/menu/updateStatus/'+rowData.idx+'/2\', null, \'menulist\');"></a>');
                        array.push('<a class="margin-auto click-icon click-icon-edit" href="javascript:void(0)" title="编辑" onclick="editOrAdd('+rowData.idx+')"></a>');
                        array.push('<a class="margin-auto click-icon click-icon-del" href="javascript:void(0)" title="删除" onclick="$updateStatus(\'/menu/updateStatus/'+rowData.idx+'/0\', null, \'menulist\');"></a>');
                    }
                    if(rowData.status == '2'){
                        array.push('<a class="margin-auto click-icon click-icon-open" href="javascript:void(0)" title="激活" onclick="$updateStatus(\'/menu/updateStatus/'+rowData.idx+'/1\', null, \'menulist\');"></a>');
                        array.push('<a class="margin-auto click-icon click-icon-del" href="javascript:void(0)" title="删除" onclick="$updateStatus(\'/menu/updateStatus/'+rowData.idx+'/0\', null, \'menulist\');"></a>');
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
                title : '菜单名称'
            }, {
                field : 'url',
                align : 'center',
                sortable : true,
                width : 70,
                title : '菜单url'
            },{
                field : 'zindex',
                align : 'center',
                sortable : true,
                width : 30,
                title : '顺序'
            },{
                field : 'code',
                align : 'center',
                sortable : true,
                width : 70,
                title : '编码'
            },{
                field : 'topShow',
                align : 'center',
                width : 50,
                sortable : true,
                title : '首页顶部显示',
                formatter : function(value, rowData, rowIndex){
                    if(value == '1'){
                        return '<span >是</span>';
                    }
                    return '<span >否</span>';

                }
            },{
                field : 'status',
                align : 'center',
                width : 40,
                sortable : true,
                title : '状态',
                formatter : function(value, rowData, rowIndex){
                    return '<span class="state-'+value+'">' + ${statusMapJson!}[value] + '</span>';
                }
            } ] ],
            onLoadSuccess: function (data) {
                $(this).datagrid("fixRownumber");/* 表格行数宽度随内容变化 */
                $('#menulist').datagrid('clearSelections');
                // 不能直接将按钮列表隐藏，后面显示会出现问题，需要表格全部加载后再隐藏，希望后续有更好的方法
                $('#buttonRight').hide();
            }
        });
        $('#menulist-qry').on('click',function () {
            var node = $tree.tree('getSelected');
            $('#menulist').datagrid('load',{
                name : $.trim($('#menuName').val()),
                //pid : node ? node.id : null,
                status : $('#menuState').val()
            });
        });
        // 初始化按钮
        $('#buttonlist').datagrid({
           // url : '/button/paging',
            fit : true,
            nowrap : true,
            fitColumns : true,
            pagination : true,
            rownumbers : true,
            pageSize : 10,
            pageList : [10, 20, 30, 40, 50 ],
            idField : 'id',
            columns : [ [
                {
                field : 'control',
                align : 'center',
                width : 40,
                title : '操作',
                formatter : function(value, rowData, rowIndex){
                    var array=[];
                    array.push('<a class="handle refer" href="javascript:void(0)" title="查看" onclick="showForButton('+rowData.idx+')"></a>');
                    if(rowData.status == '1'){
                        array.push('<a class="handle forbidden" href="javascript:void(0)" title="禁用" onclick="$updateStatus(\'/menu/button/updateStatus/'+rowData.idx+'/2\', null, \'buttonlist\');"></a>');
                        array.push('<a class="handle edit" href="javascript:void(0)" title="编辑" onclick="editOrAddButton('+rowData.idx+')"></a>');
                        array.push('<a class="handle delete" href="javascript:void(0)" title="删除" onclick="$updateStatus(\'/menu/button/updateStatus/'+rowData.idx+'/0\', null, \'buttonlist\');"></a>');
                    }
                    if(rowData.status == '2'){
                        array.push('<a class="handle putaway" href="javascript:void(0)" title="激活" onclick="$updateStatus(\'/menu/button/updateStatus/'+rowData.idx+'/1\', null, \'buttonlist\');"></a>');
                        array.push('<a class="handle delete" href="javascript:void(0)" title="删除" onclick="$updateStatus(\'/menu/button/updateStatus/'+rowData.idx+'/0\', null, \'buttonlist\');"></a>');

                    }
                    return array.join('');
                }},
                {
                field : 'idx',
                align : 'center',
                width : 30,
                sortable : true,
                title : 'ID'
            }, {
                field : 'name',
                align : 'center',
                width : 40,
                sortable : true,
                title : '按钮名称'
            }, {
                field : 'code',
                align : 'center',
                width : 40,
                sortable : true,
                title : '编码'
            },{
                field : 'status',
                align : 'center',
                width : 20,
                sortable : true,
                title : '状态',
                formatter : function(value, rowData, rowIndex){
                    return '<span class="state-'+value+'">' + ${statusMapJson!}[value] + '</span>';
                }
            }
             ] ],
            onLoadSuccess: function (data) {
                $(this).datagrid("fixRownumber");/* 表格行数宽度随内容变化 */
                $('#buttonlist').datagrid('clearSelections');
            }
        });
    });
    /**
     *  编辑或者新增
     * @param idx 主键idx
     */
    function editOrAdd(idx) {
        var url = idx ? '/menu/edit/'+idx : '/menu/add/1';
        var title =idx ? '修改菜单' : '新增菜单';
        //页面层
        parent.layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['保存','关闭'],
            resize:false,
            //skin: 'layui-layer-rim', //加上边框
            area: ['580px', '500px'], //宽高
            content: url,
            yes: function(index, layero){
                parent.layer.load(2, {shade : 0.01, time : 5000});
                var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                iframeWin.submitForm($('#menulist'));
                return false;
            }
        });
    }
    /**
     *  查看按钮
     * @param idx 主键idx
     */
    function showForMenu(idx) {
        var url ='/menu/view/'+idx ;
        var title ='查看菜单';
        //页面层
        parent.layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['关闭'],
            resize:false,
            area: ['580px', '500px'], //宽高
            content: url
        });
    }
    /**
     *  编辑或者新增
     * @param idx 主键idx
     */
    function editOrAddButton(idx) {
        var node = $('#menuTree').tree('getSelected');
        var url = idx ? '/menu/button/edit/'+idx : '/menu/button/add/1?menuIdx=' + node.id;
        var title =idx ? '修改按钮' : '新增按钮';
        //页面层
        parent.layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['保存','关闭'],
            resize:false,
            area: ['525px', '450px'], //宽高
            content: [url, 'no'],
            yes: function(index, layero){
                parent.layer.load(2, {shade : 0.01, time : 5000});
                var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                iframeWin.submitForm($('#buttonlist'));
                return false;
            }
        });
    }
    /**
     *  查看按钮
     * @param idx 主键idx
     */
    function showForButton(idx) {
        var node = $('#menuTree').tree('getSelected');
        var url = '/menu/button/view/'+idx;
        var title = '查看按钮';
        //页面层
        parent.layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['关闭'],
            resize:false,
            area: ['525px', '500px'], //宽高
            content: [url, 'no']
        });
    }
</script>
</body>
</html>
