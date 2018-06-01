<!DOCTYPE html>
<head>
    <title>洋桃跨境供应链后台管理中心-成员列表</title>
    <#include "../common/memberCommon.jsp"/>
</head>
<body>
<!-- 使用easyui的tabs head标签的东西，在首页读取不到，只会把css和js放在body中 -->
<link rel="stylesheet" href="/css/common/current.css?_v=${css$version!}"/>
<section class="main">
    <div class="table-container">
        <div class="main-content">
            <div class="word-screen word-screen-hide">
                <div class="word-screen-operation">
                    <a class="word-screen-button" id="memberlist-qry1" href="javascript:;"><i></i>搜索</a>
                    <a class="click-show-hide" href="javascript:;"><i></i><span></span></a>
                </div>
                <div class="word-screen-cont">
                    <form id="searchForm">
                        <ul>
                            <li>
                                <label class="name">状态：</label>
                                <div class="select-box">
                                    <select id="m-status">
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
            <div class="yt-table">
                <table id="memberlist" class="easyui-datagrid" style="width:98%;height: 454px;">
                </table>
            </div>
        </div>
    </div>
</section>
<script>
    $(function () {
        $('#memberlist').datagrid({
            url: '/transcation/list/paging',
            method: 'post',
            fit: true,
            nowrap: true,
            fitColumns: true,
            pagination: true,
            rownumbers: true,
            pageSize: 10,
            pageList: [10, 20, 30, 40, 50],
            idField: 'id',
            columns: [[/*{
             field : 'id',
             checkbox:true
             },*/
                {
                    field: 'control',
                    align: 'center',
                    width: "18%",
                    title: '操作',
                    formatter: function (value, rowData, rowIndex) {
                        var array = [];
                        array.push('<a class="margin-auto click-icon click-icon-check" href="javascript:void(0)" title="查看" onclick="showForMember(' + rowData.idx + ')"></a>');
                        if (rowData.status == '1') {
                            array.push('<a class="margin-auto click-icon click-icon-off" href="javascript:void(0)" title="禁用" onclick="$updateStatus(\'/member/updateStatus/' + rowData.idx + '/2\', null, \'memberlist\');"></a>');
                            array.push('<a class="margin-auto click-icon click-icon-edit" href="javascript:void(0)" title="编辑" onclick="editForMember(' + rowData.idx + ');"></a>');
                            array.push('<a class="margin-auto click-icon click-icon-encrypt" href="javascript:void(0)" title="修改密码" onclick="editPassWord(' + rowData.idx + ')"></a>');
                            array.push('<a class="margin-auto click-icon click-icon-del" href="javascript:void(0)" title="删除" onclick="$updateStatus(\'/member/updateStatus/' + rowData.idx + '/0\', null, \'memberlist\');"></a>');
                        }
                        if (rowData.status == '2') {
                            array.push('<a class="margin-auto click-icon click-icon-open" href="javascript:void(0)" title="激活" onclick="$updateStatus(\'/member/updateStatus/' + rowData.idx + '/1\', null, \'memberlist\');"></a>');
                            array.push('<a class="margin-auto click-icon click-icon-del" href="javascript:void(0)" title="删除" onclick="$updateStatus(\'/member/updateStatus/' + rowData.idx + '/0\', null, \'memberlist\');"></a>');
                        }
                        return array.join('');
                    }
                },
                {
                    field: 'idxCode',
                    align: 'center',
                    width: '15%',
                    sortable: true,
                    title: 'ID'
                }, {
                    field: 'tag',
                    align: 'center',
                    width: '15%',
                    sortable: true,
                    title: '事务标签'
                }, {
                    field: 'transcationBody',
                    align: 'center',
                    sortable: true,
                    width: '15%',
                    title: '事务体'
                }, {
                    field: 'transcationStatus',
                    align: 'center',
                    sortable: false,
                    width: '15%',
                    title: '事务状态'
                }, {
                    field: 'mqSendStatus',
                    align: 'center',
                    sortable: false,
                    width: '15%',
                    title: 'MQ发送状态'
                }, {
                    field: 'mqConsumeStatus',
                    align: 'center',
                    sortable: false,
                    width: '15%',
                    title: 'MQ消费状态'
                }, {
                    field: 'Result status',
                    align: 'center',
                    sortable: false,
                    width: '15%',
                    title: '结果状态'
                }, {
                    field: 'resultMsg',
                    align: 'center',
                    sortable: false,
                    width: '15%',
                    title: '结果信息'
                }, {
                    field: 'cause',
                    align: 'center',
                    sortable: false,
                    width: '15%',
                    title: '异常'
                }, {
                    field: 'createTime',
                    align: 'center',
                    sortable: false,
                    width: '15%',
                    title: '创建时间'
                }, {
                    field: 'updateTime',
                    align: 'center',
                    sortable: false,
                    width: '15%',
                    title: '修改时间'
                }]],
            onLoadSuccess: function (data) {
                $(this).datagrid("fixRownumber");/* 表格行数宽度随内容变化 */
                $('#memberlist').datagrid('clearSelections');
            }
        });
        $('[id^=memberlist-qry]').on('click', function () {
            $('#memberlist').datagrid('load', {
                name: $.trim($('#m-name').val()),
                telphone: $.trim($('#m-telphone').val()),
                userCode: $.trim($('#m-userCode').val()),
                email: $.trim($('#m-email').val()),
                userFullName: $.trim($('#m-userFullName').val()),
                status: $('#m-status').val()
            });
        });
    });

    /**
     *  编辑成员 赋权
     * @param idx
     */
    function editForMember(idx) {
        var url = '/member/toOperation/2'+idx;
        var title = '修改个人信息';
        //页面层
        parent.layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['保存', '关闭'],
            resize:false,
            area: ['710px', '502px'], //宽高
            content: [url, 'no'],
            yes: function (index, layero) {
                var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                iframeWin.editFormInfo($('#memberlist'));
                return false;
            }
        });
    }

    /**
     *  编辑成员 赋权
     * @param idx
     */
    function showForMember(idx) {
        var url = '/member/toOperation/3/'+idx;
        var title = '查看个人信息';
        //页面层
        parent.layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['关闭'],
            resize:false,
            area: ['510px', '502px'], //宽高
            content: [url, 'no']
        });
    }
    /* 修改密码 */
    function editPassWord(idx) {
        parent.layer.open({
            type: 2,
            title: '修改密码',
            area: ['510px', '380px'],
            btn: ['保存', '取消'],
            resize:false,
            content: ['/member/toOperation/2/'+idx, 'no'],
            yes: function (index, layero) {
                parent.layer.load(2, {shade: 0.01, time: 5000});
                var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                iframeWin.editMemmberPassword($('#memberlist'));
                return false;
            }
        });
    }
    /* 新增成员信息 */
    function addInformation() {
        parent.layer.open({
            type: 2,
            title: '新增成员信息',
            area: ['710px', '500px'],
            btn: ['保存', '取消'],
            resize:false,
            content: ['/member/toOperation/add/1', 'no'],
            yes: function (index, layero) {
                var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                iframeWin.addFormInfo($('#memberlist'));
                return false;
            }
        });
    }
</script>
</body>
</html>

