/**
 * 广州市两棵树网络科技有限公司版权所有
 * DT Group Technology & commerce Co., LtdAll rights reserved.
 *
 * 广州市两棵树网络科技有限公司，创立于2009年。旗下运营品牌洋葱小姐。
 * 洋葱小姐（Ms.Onion） 下属三大业务模块 [洋葱海外仓] , [洋葱DSP] , [洋葱海外聚合供应链]
 * [洋葱海外仓]（DFS）系中国海关批准的跨境电商自营平台(Cross-border ecommerce platform)，
 * 合法持有海外直邮保税模式的跨境电商营运资格。是渠道拓展，平台营运，渠道营运管理，及客户服务等前端业务模块。
 * [洋葱DSP]（DSP）系拥有1.3亿消费者大数据分析模型。 是基于客户的消费行为，消费轨迹，及多维度云算法(MDPP)
 * 沉淀而成的精准消费者模型。洋葱DSP能同时为超过36种各行业店铺 及200万个销售端口
 * 进行多店铺高精度配货，并能预判消费者购物需求进行精准推送。同时为洋葱供应链提供更前瞻的商品采买需求模型 。
 * [洋葱海外聚合供应链]（Super Supply Chain）由中国最大的进口贸易集团共同
 * 合资成立，拥有20余年的海外供应链营运经验。并已入股多家海外贸易企业，与欧美澳等9家顶级全球供应商达成战略合作伙伴关系。
 * 目前拥有835个国际品牌直接采买权，12万个单品的商品供应库。并已建设6大海外直邮仓库，为国内客户提供海外商品采买集货供应，
 * 跨境 物流，保税清关三合一的一体化模型。目前是中国唯一多模式聚合的海外商品供应链 。
 *
 * 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1
 *
 * 洋桃商城：http://www.yunyangtao.com
 *
 * @Description: 登录注册js验证
 * @Company: http://www.yunyangtao.com
 * @author lemon
 * @e-mail address 2590586729@qq.com
 * @date 2017年3月10日
 * @version V1.0
 *
 */

/**
* 时间选择器
* WdatePicker({maxDate:'#F{$dp.$D(\'withdraw-endTime\')||\'%y-%M-%d\'}',maxDate:'%y-%M-%d-%H-%m-%s',skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})
*/
var YT = {
    /**
     * 刷新列表数据方法
     * currentTab为要刷新的选择对象 获取方法：currentTab = parent.$('#tabs').tabs('getTab', '这里选择标签名称')
     * tabTitle为菜单名称
     */
    RefreshTab:function (currentTab,tabTitle) {
        if(parent.$('#tabs').tabs("exists", tabTitle)){
            parent.$('#tabs').tabs("select", tabTitle);
            var url = $(currentTab.panel('options')).attr('href');
            parent.$('#tabs').tabs('update', {
                tab: currentTab,
                options: {
                    href: url
                }
            });
            currentTab.panel('refresh');
        }
    },
    /**
     * 打开菜单栏目
     * url为要打开的地址
     * title为菜单的标题
     * height表示要打开的栏目的高度，不传默认抓取浏览器高度
     */
    openTabs:function (url,title,height) {
        var height_ = height?height:$(window).height();
        var content = '<iframe onload="Ms.closeLoading()" scrolling="auto" frameborder="0"  src="' + url + '"  style="width:100%;height:' + height_ + 'px;"></iframe>';
        parent.$('#tabs').tabs('add', {
            title: title,
            content: content,
            closable: true
        });
    },
    /**
     * 上传表格成功后打开：excel导入记录
     */
    openExeclRecord:function () {
        if (parent.$('#tabs').tabs('exists', 'excel导入记录')) {
            this.RefreshTab(parent.$('#tabs').tabs('getTab', 'excel导入记录'),'excel导入记录');
        }else {
            this.openTabs('/scheduling/excelImport/list','excel导入记录');
        }
    },
    /**
     * 表单验证方法
     */
    verification:function () {

    },
    /** 写cookies */
    setCookie:function(c_name, value, expiredays){
        var exdate=new Date();
        exdate.setDate(exdate.getDate() + expiredays);
        document.cookie=c_name+ "=" + escape(value) + ((expiredays==null) ? "" : ";expires="+exdate.toGMTString())+";path=/";
    },
    /** 读取cookies */
    getCookie:function(name){
        var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
        if(arr=document.cookie.match(reg))
            return (arr[2]);
        else
            return null;
    },
    /** 删除cookies */
    delCookie:function(name){
        var exp = new Date();
        exp.setTime(exp.getTime() - 1);
        var cval=this.getCookie(name);
        if(cval!=null)
            document.cookie= name + "="+cval+";expires="+exp.toGMTString()+";path=/";
    },
    /**
     * 图片放大预览功能
     * clickObj为要点击的图片对象class或者id或者标签名
     */
    imgMagnifyPreview:function (clickObj) {
        $('body').on('click',clickObj,function () {
            var screenImage = $(this);
            var theImage = new Image();
            theImage.src = screenImage.attr("src");
            var imageWidth = theImage.width>$(window.parent.document).width()?$(window.parent.document).width()-200:theImage.width;
            var imageHeight = theImage.height>$(window.parent.document).height()?$(window.parent.document).height()-200:theImage.height;
            parent.layer.open({
                type: 1,
                title: false,
                closeBtn: 0,
                area:[imageWidth,imageHeight],
                skin: 'layui-layer-nobg', //没有背景色
                shadeClose: true,
                resize:false,
                content: '<div id="preview-big-img" style="overflow: auto;width: '+ imageWidth +'px;height:'+ imageHeight +'px;"> <img src="'+theImage.src+'" /> </div>'
            });
        });
    },
    /**
     *  获取手机验证码倒计时验证
     *  ob为点击的对象元素，存储的cookie名称为点击对象的data-cookie属性加'cookietime'
     *  注：点击对象必备data-cookie属性
     *  fun为倒计时结束时候要执行的方法，不传则不执行
     */
    codeCountDown:function(ob,fun) {
        var cookie_nave = ob.attr('data-cookie'),
            th_s = YT.getCookie(cookie_nave+'cookietime')?YT.getCookie(cookie_nave+'cookietime'):new Date().getTime()+120000,
            date2 = new Date(),
            date3 = (th_s-date2.getTime())/1000;//相差秒数
        ob.css({
            backgroundColor: '#b4b5b5',
            cursor: 'default'
        }).html(Math.round(date3)+'s');
        var tcode = setInterval(function () {
            date2 = new Date();
            date3 = (th_s-date2.getTime())/1000;//相差秒数
            if(Math.round(date3)<0){
                clearInterval(tcode);
                YT.delCookie(cookie_nave+'cookietime');
                ob.attr('style','').html('获取验证码');
                if(fun){
                    fun();/* 倒计时结束执行方法 */
                }
            }else {
                ob.html(Math.round(date3)+'s');
                YT.delCookie(cookie_nave+'cookietime');
                YT.setCookie(cookie_nave+'cookietime', th_s);
                th_s--;
            }
        },1000);
    },
    /**
     * input输入筛选
     * data为要筛选遍历的数据,格式需要为数组
     * text为遍历数据对象的文字标签 key为遍历数据的键值便签
     * 说明：用户选择后，key会以data-id赋值，要获取key请用attr去获取,展开的列表会以新增元素<div class="word-key-acquisition-list"><ul></ul></div>
     * objParent参数为传递的inpu对象，如果不传，则默认使用class为word-key-acquisition来进行dom对象操作
     */
    wordKeyAcquisition:function (data,text,key,objParent) {
        var operateObj = objParent?objParent:$('.word-key-acquisition input');
        function eachFn(val,editObj){
            var htmlArr = [];
            /* 排序数组方法 */
            function sortFn(name){
                return function(o,p){
                    var a,b;
                    if(o && p && typeof o === 'object' && typeof p ==='object'){
                        a = o[name];
                        b = p[name];
                        if(typeof a === typeof b){
                            return a < b ? -1:1;
                        }
                        return typeof a < typeof b ? -1 : 1;
                    }
                }
            }
            /* 先筛选选中的元素 */
            $.each(val!=''?data.sort(sortFn(text)):data,function (i,n) {
                if(val == '' || n.display.indexOf(val)>=0){
                    var ketVal = n[key]?n[key]:'',
                        textVal = n[text]?n[text]:'';
                    htmlArr.push('<li data-id="' + ketVal + '" title="' + textVal + '">'+ textVal.replace(new RegExp(val?val:false,"ig"), '<span>' + val + '</span>') +'</li>');
                    operateObj.attr('data-id',n.display == val && val!=''?ketVal:''); /* 输入的过程中将id赋值过去 */
                }
            });
            $('.word-key-acquisition-list').remove();
            $('body').append('<div class="word-key-acquisition-list" style="top:'+ (editObj.offset().top + (editObj.height()-1)) +'px;left:'+ editObj.offset().left +'px"><ul></ul></div>');
            if(htmlArr.length<=0){
                $('.word-key-acquisition-list ul').html('<li><span>' + val + '</span> 查询无结果</li>');
            }else {
                $('.word-key-acquisition-list ul').html(htmlArr.join(''));
            }
            $(window).unbind().resize(function () {
                $('.word-key-acquisition-list').css({top:(editObj.offset().top + editObj.height()-1),left:editObj.offset().left});
            });
        }
        $(document).on('click','.word-key-acquisition-list li',function () {
            $('.js-eachFn').val($(this).text()).attr('data-id',$(this).attr('data-id'));
        });
        var acquisitionInputVal = '',/* 输入框的值 */
            keyCodeEq = 0; /* 判断选中的标签的下标 */
        function listUlScrollTop(num){
            $('.word-key-acquisition-list ul li').eq(num).addClass('cur').siblings().removeClass('cur');
            if(num>6){
                $('.word-key-acquisition-list ul').animate({scrollTop: (num - 6) * 30}, 0);
            }else {
                $('.word-key-acquisition-list ul').animate({scrollTop: 0}, 0);
            }
        }
        operateObj.click(function (event) {
            event.stopPropagation();
            acquisitionInputVal = $(this).val();
            keyCodeEq = 0;
            $(this).addClass('js-eachFn');/* 绑定class来用户赋值 */
            /* 用户重新点击重新加载列表 */
            eachFn(acquisitionInputVal,$(this));
        }).keyup(function (e) {
            e = e || window.event;
            var keycode = e.which ? e.which : e.keyCode,
                thisVal = $(this).val(),
                listDataId = $('.word-key-acquisition-list ul li').attr('data-id');
            if(keycode == 13 && $('.word-key-acquisition-list ul .cur').attr('data-id')){
                var curObj = $('.word-key-acquisition-list ul .cur');
                if(curObj.text()){
                    $('.word-key-acquisition-list').remove();
                    $(this).val(curObj.text()).attr('data-id',curObj.attr('data-id'));
                }
            } else if (keycode == 38 && listDataId) {
                keyCodeEq = keyCodeEq <=0 ? 0:keyCodeEq-1;
                listUlScrollTop(keyCodeEq);/* 调用选择方法 */
            } else if (keycode == 40 && listDataId) {
                var liLength = $('.word-key-acquisition-list li').length;
                keyCodeEq = (keyCodeEq+1 >= liLength)?liLength-1:keyCodeEq+1;
                listUlScrollTop(keyCodeEq);/* 调用选择方法 */
            }else if(acquisitionInputVal != thisVal){
                eachFn(thisVal,$(this));
                acquisitionInputVal = thisVal;
            }
        });
        $(document).click(function () {
            $('.word-key-acquisition-list').remove();
        });
    },
    /**
     * 获取当前时间
     */
    fetchCurrentTime:function (){
        var date = new Date();
        var seperator1 = "-";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
        return currentdate;
    },
    /**
     * 该方法用来解决浏览器记住密码的功能，用户获取焦点不提示出现使用过的密码
     * input的密码框的type设置为text
     * pawObj为密码框对象
     */
    editTypePaw:function (pawObj) {
        pawObj.on('keyup',function(){
            $(this).attr('type',$(this).val()!=''?'password':'text');
        });
    },
    /**
     * 用户操作弹窗输入用户密码验证是否当前用户
     * tit弹窗的标题
     * ajaxUrl操作的ajax的url
     * ajaxData操作的ajax的参数
     * yesFunction用户点击确定的执行方法,调用的时候，传递两个方法，第一个关闭方法，第二个错误提示方法
     */
    userEdit_paw_verify:function (tit,ajaxUrl,ajaxData,yesFunction) {
        parent.layer.open({
            title: tit,
            type: 1,
            btnAlign: 'c',
            btn: ['确定','取消'],
            area: ['410px', '300px'], //宽高
            resize:false,
            content: '<div class="word-verify-paw"><h1 class="tit">请输入您的密码</h1><p class="info">确认密码用来判断是否为当前用户操作</p><div class="edit-input"><input class="type-paw" type="text" value="" placeholder="请输入用户密码"></div><p class="warn"></p></div>',
            success: function(layero){
                /* 修改密码框状态 */
                YT.editTypePaw(parent.$('.word-verify-paw .type-paw'));
            },
            yes: function(index, layero){
                var pawVal = parent.$('.word-verify-paw .edit-input .type-paw').val();
                if(pawVal == ''){
                    parent.$('.word-verify-paw .warn').html('请输入您的用户密码作为验证');
                    return false;
                }
                ajaxData.permitKey = CryptoJS.enc.Base64.stringify(CryptoJS.enc.Utf8.parse(pawVal));/* 将用户密码赋值给参数并加密 */
                parent.layer.load(2, {shade: 0.01});
                $.ajax({
                    type: "POST",
                    url: ajaxUrl,
                    data: ajaxData,
                    dataType: 'json'
                }).done(function (data) {
                    if (data.status == 200) {
                        parent.layer.close(index);
                        /* 调用成功后要执行的事件 */
                        yesFunction(data);
                    } else {
                        parent.$('.word-verify-paw .warn').html(data.msg);
                    }
                    parent.layer.closeAll('loading');
                }).fail(function () {
                    parent.$('.word-verify-paw .warn').html('服务器繁忙，请稍后重试');
                });
            }
        });
    }
};
/** easyui扩展插件 */
$(function () {
    /** 扩展easyui让表格随着数量递增宽度调整 */
    $.extend($.fn.datagrid.methods, {
        fixRownumber : function (jq) {
            return jq.each(function () {
                var panel = $(this).datagrid("getPanel");
                //获取最后一行的number容器,并拷贝一份
                var clone = $(".datagrid-cell-rownumber", panel).last().clone();
                //由于在某些浏览器里面,是不支持获取隐藏元素的宽度,所以取巧一下
                clone.css({
                    "position" : "absolute",
                    top : -1000
                }).appendTo("body");
                var width = clone.width("auto").width();
                //多加5个像素,保持一点边距
                $(".datagrid-header-rownumber,.datagrid-cell-rownumber", panel).width(width + 10);
                //修改了宽度之后,需要对容器进行重新计算,所以调用resize
                $(this).datagrid("resize");
                //一些清理工作
                clone.remove();
                clone = null;
            });
        }
    });
    /** 搜索栏收起展开 */
    function wordScreenSh() {
        $('.word-screen').each(function () {
            if($('.word-screen-cont').height() - $(this).height() <= 10){
                $(this).removeClass('word-screen-hide').find('.click-show-hide').hide();
            }else {
                $(this).find('.click-show-hide').show();
            }
        });
    }
    /* 确认按钮是否显示隐藏 */
    wordScreenSh();
    $(window).resize(function () {
        if(!$('.click-show-hide').hasClass('hide')){
            wordScreenSh();
        }
    });
    /* 点击收起隐藏方法 */
    $('.word-screen .click-show-hide').click(function () {
        var wordScreen = $(this).parent().parent();
        if($(this).hasClass('hide')){
            $(this).removeClass('hide');
            wordScreen.addClass('word-screen-hide');
        }else {
            $(this).addClass('hide');
            wordScreen.removeClass('word-screen-hide');
        }
    });
});