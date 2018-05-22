<!DOCTYPE html>
<head>
    <title>洋桃跨境供应链后台管理中心-主页</title>
    <#include "../../common/memberCommon.jsp"/>
</head>
<body id="easyUILayoutBody">
<div data-options="region:'north'" style="height:90px">
    <!--头部logo和核心导航 start-->
    <div class="yt-head">
        <div class="home"><a class="yt-logo" href="/"></a></div>
        <div class="yt-nav">
            <ul>
            </ul>
            <div class="yt-nav-move">
                <div class="likeLink">
                    <div id="ycWWWTitle" class="list ycWWWTitle">
                    </div>
                    <div id="ycindexTitle" class="list ycindexTitle">
                    </div>
                    <div id="indexTitle" class="list indexTitle">
                    </div>
                </div>
                <div id="personMouseover" class="personMouseover">
                    <div class="yt-personal">
                        <span class="personal-icon"></span>
                        <span class="personal-name"><i></i></span>
                    </div>
                    <div class="set-center">
                        <i class="set-center-icon"></i>
                        <div class="set-help">
                            <ol>
                                <li> <a class="set" id="editMember" url=""><i></i>个人设置</a></li>
                                <li> <a class="password" id="editPassword" url=""><i></i>修改密码</a></li>
                                <li> <a class="out" href="/sys/logout"><i></i>退出</a></li>
                            </ol>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div data-options="region:'west',title:'导航菜单'" style="width:240px;">
    <div class="yt-left-container">
        <div class="yt-left-menu">
            <div class="menu-c" >
                <ul id="menu" class="easyui-tree" >

                </ul>
            </div>
        </div>
    </div>
</div>
<div data-options="region:'center'" style="overflow:hidden;">
    <!--右边内容框 start-->
    <div class="yt-right-container">
        <!--tabs 窗口 start-->
        <div class="win-nav">
            <div data-options="region:'center',fit:true,title:''">
                <div id="tabs" data-options="tools:'#tab-tools',tabHeight:31" style="margin: 0;padding: 0;width: 100%;" class="easyui-tabs win-nav-cont">
                    <div title="主页" style="margin: 0;padding: 0 0 30px;width: 100%;background-color: white;box-sizing: border-box;">
                            <div class="index-img-info" style="text-align: center">
                                <img src="http://yantaodev-1253852034.cosgz.myqcloud.com/ueditor/20170703/20170703115052529_558.png" title="洋桃商城首页简介_01.png">
                            </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--右边内容框 end-->
</div>
<!--底部版权信息 start-->
<div class="yt-footer">
    <div class="yt-footer-container" >
        <div class="versions-time"><i></i><span id="servicerTime_T"></span></div>
        <div class="versions-info"><i></i>${runVersion!}</div>
    </div>
</div>
<!--底部版权信息 end-->
<div class="yt-right-hand">
    <div id="mm" class="easyui-menu" style="width: 140px;">
        <div id="mm-tabclose" name="6">刷新</div>
        <div id="Div1" name="1">关闭</div>
        <div id="mm-tabcloseall" name="2">关闭全部</div>
        <div id="mm-tabcloseother" name="3">关闭其他标签页</div>
        <div id="mm-tabcloseright" name="4">关闭右侧标签页</div>
        <div id="mm-tabcloseleft" name="5">关闭左侧标签页</div>
    </div>
</div>
<script>
    /* 时间 */
    function addTime() {
        //var servicerTime = ${nowTimeStamp!};
        var servicerTime = "";
        function add0(m){return m<10?'0'+m:m }
        //时间戳转化成时间格式
        function timeFormat(timestamp){
            //timestamp是整数，否则要parseInt转换,不会出现少个0的情况
            var time = new Date(timestamp);
            var year = time.getFullYear();
            var month = time.getMonth()+1;
            var date = time.getDate();
            var hours = time.getHours();
            var minutes = time.getMinutes();
            var seconds = time.getSeconds();
            return year+'-'+add0(month)+'-'+add0(date)+' '+add0(hours)+':'+add0(minutes)+':'+add0(seconds);
        }
        setInterval(function () {
            servicerTime += 1000;
            $('#servicerTime_T').text(timeFormat(servicerTime));
        },1000);
    }

    var treeJson = ${session_menuJson!};
    $(function() {
        //画面加载完成之后就调用layout()方法将body变成easy的layout
        $('#easyUILayoutBody').layout();
        addTime(); /* 调用时间方法 */
        /* tabs 标签 */
        var $tree = $('#menu');
        $tree.tree({
            data : treeJson,
            animate:true,
            onClick: function (node) {
                var height = $(window).height()-130,
                    tmp = node ,nodeId = node.id;
                while ($tree.tree('getParent',tmp.target) != null) {
                    tmp=$tree.tree('getParent',tmp.target);
                    nodeId = tmp.id;
                }
                $("#" + nodeId).length > 0 ? $("#" + nodeId).addClass("active").siblings().removeClass("active"):$('.yt-nav .nav-list').removeClass("active");
                $tree.tree('toggle', node.target).tree('select', node.target);
                if ($tree.tree("isLeaf", node.target)) {
                    var tabs = $("#tabs");
                    var tab = tabs.tabs("getTab", node.text);
                    if (tab) {
                        tabs.tabs("select", node.text);
                        //tab.panel('refresh', node.attributes[0].url);
                        var tabSel = tabs.tabs('getSelected');
                        tabs.tabs('update', {
                            tab: tabSel,
                            options: {
                                title: node.text,
                                content: '<iframe  scrolling="auto" src="'+node.attributes.url+'" frameborder="0" style="width:100%;height:'+height+'px;"></iframe>',
                                closable: true,
                                selected:true
                            }
                        });
                    } else {
                        tabs.tabs('add', {
                            title: node.text,
                            //href: node.attributes[0].url,
                            content:'<iframe  scrolling="auto" src="'+node.attributes.url+'" frameborder="0" style="width:100%;height:'+height+'px;"></iframe>',
                            closable: true,
                            bodyCls: "content"
                        });
                    }
                }
            }
        });
        /* 页面窗口大小改变，修改iframe的高度 */
        $('.index-img-info').height($(this).height()-130);
        $(window).resize(function () {
            $('#tabs iframe,.index-img-info').height($(this).height()-130);
        });
        /* 点击头部的核心菜单关联左边菜单栏 */
        $('.nav-list').click(function(){
            $(this).addClass("active").siblings().removeClass("active");
            var node = $tree.tree('find', $(this).attr("id"));
            $tree.tree('collapseAll').tree('expand', node.target).tree('select', node.target);
            // $('#menu').tree('scrollTo', node.target);
        });
        $('.nav-list') && $('.nav-list:eq(0)').click();
    });
</script>
<script type="text/javascript" src="${static$domain!}/js/common/index.js?_v=${js$version!}"></script>
</body>
</html>