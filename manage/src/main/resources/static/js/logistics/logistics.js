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
 * @Description: 杨桃后台管理中心 js
 * @Company: http://www.yunyangtao.com
 * @author lemon
 * @e-mail address 2590586729@qq.com
 * @date 2017年3月20日
 * @version V1.0
 *
 */

/* 弹出框 */
function deliverRegion(title){
    layer.open({
        type: 1,
        title: title,
        area: ['490px', '540px'],
        btn: ['保存', '取消'],
        resize:false,
        shadeClose: false,
        content: $('#deliverRegion')
    });
}

/* 弹出区域管理框，查询全部区域,objTable为用户刷新数据的对象,proProvince为页面选择框的对象 */
function addLogistics(title,objTable,proProvince){
    var idx = "1";
    var type = "1";
    var url ='/logistics/area/list/'+ idx + "/"+ type;
    parent.layer.open({
        type: 2,
        title: title,
        area: ['590px', '520px'],
        btn: ['保存', '取消'],
        resize:false,
        /*shadeClose: false,*/
        content: [url,'no'],
        yes: function (index, layero) {
            parent.Ms.saveLoading();
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            iframeWin.submitForm(objTable,proProvince);
            return false;
        },
        btn2: function () {
            Ms.clearGridSelections('logisticsAreaList');
        },
        cancel: function () {
            Ms.clearGridSelections('logisticsAreaList');
        }

    });
}

/**
 * 保存刷新列表
 */
function submitForm(objTable,proProvince) {
    Ms.saveLoading();
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    parent.layer.msg('操作成功！', {icon: 6});
    parent.layer.close(index);
    //var proProvince = parent.$("#proProvince");
    // 定义重复获取parent.$ ,可能由于其它原因有时候获取parent.$为null的异常，所以重复获取
   /* for(var i = 0; i < 5; i++) {
        alert(2);
        if (parent.$ != null) {*/
            parent.$.ajax({
                type: "POST",
                url: "/logistics/area/provincelist",
                dataType: 'json'
            }).done(function (data) {
                if (data.status == 200) {
                    var data = data.data;
                    var htmlstr = [];
                    var html = '';
                    htmlstr.push("<option value=''>请选择</option>");
                    proProvince.html("");
                    for (var i = 0; i < data.length; i++) {
                        html = "<option value=" + data[i].code + "," + data[i].secondCode + ">" + data[i].name + "</option>";
                        htmlstr.push(html);
                    }
                    proProvince.html(htmlstr.join(","));
                    objTable.datagrid('reload', {});
                } else {
                    parent.layer.msg(data.msg, {icon: 2});
                }
                // 如果一开始就获取到，直接关闭重试获取parent.$
                //break;
            }).fail(function (result) {
                parent.layer.msg(MS.ERROR_MSG, {icon: 2});
            });
       /* }*/
    /*}*/
    parent.$('#logisticsAreaList').datagrid('reload', {
        statusStr: $.trim(parent.$("#status option:selected").val()),
        codetype: $.trim(parent.$("#type option:selected").val())
    });
    //parent.$('#logisticsUnitTableList').datagrid('reload', {});
}
/* 点击取消添加 */
function cancelAdd(str) {
    $(str).parent().parent().parent().remove();
}

/* 点击取消添加 */
function cancelAdd(str) {
    $(str).parent().parent().parent().remove();
}

/* 添加省份 */
function addProvince(){
    var provinceName = $('.addProvinceText .provinceName-txt').val();
    var provinceCode = $('.addProvinceText .provinceName-Code').val();
    var evenNode = '<li class="province addProvinceText"><div class="item"><div class="provinceCont"><input type="text" class="provinceName-txt" placeholder="地址" /><input type="text" class="provinceName-Code" placeholder="默认编码" onkeyup="MS.onlyNumber(this)" onblur="MS.onlyNumber(this)" /><input type="text" class="provinceName-Code" placeholder="第二编码" onkeyup="MS.onlyNumber(this)" onblur="MS.onlyNumber(this)" /><input type="text" id="provinceOrderNum" class="provinceName-Code" placeholder="序号" onkeyup="MS.onlyNumber(this)" onblur="MS.onlyNumber(this)" /><span class="confirm" onclick="confirmProvinceAdd(this)">确定</span><span class="cancel" onclick="cancelAdd(this)">取消</span></div></div></li>';
    if($('.addProvinceText').length <= 0){
        $('.regionList .province:nth-child(1)').before(evenNode);
    }else{
        if(provinceName == "" || provinceCode == ""){
            layer.msg('请先填写完再添加！', {icon: 2});
        }else{
            $('.regionList .province:nth-child(1)').before(evenNode);
        }
    }
}


/**
 * 点击修改省
 * @param str 当前对象
 * @param code 省市区编码
 * @param name  省市区名称
 * @param zindex 省市区序号
 */
function editProvince(str, code, idxCode){
    var name = $("#name"+code).val();
    var zindex = $("#zindex"+code).val();
    var evenNode ='<div class="provinceCont cont-width"><input type="text" value="'+name+'" class="provinceName-txt" placeholder="地址" /><!--<input type="text" class="provinceName-Code" placeholder="编码" onkeyup="MS.onlyNumber(this)" onblur="MS.onlyNumber(this)" />--><input type="text" id="provinceOrderNum" value="'+zindex+'" class="provinceName-Code" placeholder="序号" onkeyup="MS.onlyNumber(this)" onblur="MS.onlyNumber(this)" /><span class="confirm" onclick="confirmEditProvince(this, '+ code +', '+idxCode+')">确定</span><span class="cancel" onclick="cancelEdit(this)">取消</span></div>';
    $(str).parent().siblings().hide();
    var num = $(str).parent().siblings().children(".provinceName-txt").length;
    if(num < 1){
        $(str).parent().before(evenNode);
    }
}

/* 点击确认修改省 */
function confirmEditProvince(str, code, idxCode){

    var provinceName = $.trim($(str).prev().prev().val());
    var num = $.trim($(str).prev().val());
    if(provinceName == "" ){
        layer.msg('省不能为空！', {icon: 2});
        return;
    }
    if(provinceName.length > 20) {
        layer.msg('省名称不能大于20字符！', {icon: 2});
        return;
    }

    if(num > 5000) {
        layer.msg('省排序号必须小于5000！', {icon: 2});
        return;
    }
    Ms.saveLoading();
    $.ajax({
        type: "POST",
        url: "/logistics/area/savaOrEdit",
        data: {
            idxCode : idxCode,
            name : provinceName,
            code : code,
            zindex: num,
            level : 1,
            ext : "2"
        },
        dataType: 'json'
    }).done(function (data) {
        if (data.status == 200) {
            /*var idx = data.data["idx"];*/
            var name = data.data["name"];
            var code = data.data["code"];
            var secondCode = data.data["secondCode"];
            var zindex = data.data["zindex"];
            $("#name"+code).val(name);
            $("#zindex"+code).val(zindex);
            $(str).parent().prev().children(".provinceName").html(name+"-"+code+"-"+secondCode);
            $(str).parent().siblings().show();
            $(str).parent().remove();
        } else {
            parent.layer.msg(data.msg, {icon: 2});
        }
    }).fail(function (result) {
        parent.layer.msg(MS.ERROR_MSG, {icon: 2});
    });


}

/* 点击添加市 */
function addCity(str,pidxCode, provinceCode,secondProvinceCode){
    var node = $(str).parent().parent().next().children(".addCityText");
    var addCityName = node.children().children().children(".provinceName-txt").val();
    var addCityCode = node.children().children().children(".provinceName-Code").val();
    var evenNode = '<li class="city addCityText"><div class="item itemCity"><div class="cityCont"><input type="text" class="provinceName-txt" placeholder="地区"  /><input type="text" class="provinceName-Code" placeholder="默认编码" onkeyup="MS.onlyNumber(this)" onblur="MS.onlyNumber(this)" /><input type="text" class="provinceName-Code" placeholder="第二编码" onkeyup="MS.onlyNumber(this)" onblur="MS.onlyNumber(this)" /><input type="text" id="provinceOrderNum" class="provinceName-Code" placeholder="序号" onkeyup="MS.onlyNumber(this)" onblur="MS.onlyNumber(this)" /><span class="confirm" onclick="confirmAddCity(this,'+pidxCode+', '+provinceCode+','+secondProvinceCode+')">确定</span><span class="cancel" onclick="cancelAdd(this)">取消</span></div></div></li>';
    var childrenNode = $(str).parent().parent().next().children(".city:nth-child(1)");
    $(str).parent().parent().next().show();
    if(node.length <= 0){
        if(childrenNode.length <= 0){
            $(str).parent().parent().next().prepend(evenNode);
        }
        else{
            childrenNode.before(evenNode);
        }
    }else{
        if(addCityName == "" || addCityCode == ""){
            layer.msg('请先填写完再添加！', {icon: 2});
        }else{
            childrenNode.before(evenNode);
        }
    }
}

/**
 * 修改城市
 * @param str 当前对象
 * @param code  区域编码
 * @param level 区域级别 1：省 2：市 3：区县
 * @param name  区域名称
 * @param zindex    区域序号
 */
function editCity(str, code,idxCode, level){
    var name = $("#name"+code).val();
    var zindex = $("#zindex"+code).val();
    var evenNode ='<div class="cityCont cont-width"><input type="text" class="provinceName-txt" value="'+name+'" placeholder="地区" /><!--<input type="text" class="provinceName-Code" placeholder="编码" onkeyup="MS.onlyNumber(this)" onblur="MS.onlyNumber(this)" />--><input type="text" id="provinceOrderNum" class="provinceName-Code" placeholder="序号" value="'+zindex+'" onkeyup="MS.onlyNumber(this)" onblur="MS.onlyNumber(this)" /><span class="confirm" onclick="confirmEditCity(this, '+ code +','+idxCode+','+ level +')">确定</span><span class="cancel" onclick="cancelEdit(this)">取消</span></div>';
    $(str).parent().siblings().hide();
    var num = $(str).parent().siblings().children(".provinceName-txt").length;
    if(num < 1){
        $(str).parent().before(evenNode);
    }
}

/* 点击确认修改城市 */
function confirmEditCity(str, code,idxCode, level){
    var cityName = $.trim($(str).prev().prev()/*.prev()*/.val());
    /*var cityCode = $.trim($(str).prev().prev().val());*/
    var num = $.trim($(str).prev().val());
    if(cityName == "" /*|| cityCode == ""*/ /*|| num == ""*/){
        layer.msg('市区或者区县不能为空！', {icon: 2});
        return;
    }
    if(cityName.length > 20) {
        layer.msg('市区或者区县名称不能大于20字符！', {icon: 2});
        return;
    }

    if(num > 5000) {
        layer.msg('市区或者区县排序号必须小于5000！', {icon: 2});
        return;
    }
    Ms.saveLoading();
    $.ajax({
        type: "POST",
        url: "/logistics/area/savaOrEdit",
        data: {
            idxCode: idxCode,
            name : cityName,
            zindex : num,
            code : code,
            level : level,
            ext : "2"
        },
        dataType: 'json'
    }).done(function (data) {
        if (data.status == 200) {
            /*var idx = data.data["idx"];*/
            var name = data.data["name"];
            var code = data.data["code"];
            var secondCode = data.data["secondCode"];
            var zindex = data.data["zindex"];
            $("#name"+code).val(name);
            $("#zindex"+code).val(zindex);
            $(str).parent().prev().children(".cityName").html(name+"-"+code+"-"+secondCode);
            $(str).parent().siblings().show();
            $(str).parent().remove();
        } else {
            parent.layer.msg(data.msg, {icon: 2});
        }
    }).fail(function (result) {
        parent.layer.msg(MS.ERROR_MSG, {icon: 2});
    });

}

/* 添加区域 */
function addDistrict(str,idxCode, cityCode, topProvinceCode, topCitySecondCode, topProvinceSecondCode){
    var node = $(str).parent().parent().next().children(".addDistrictText");
    var addDistrictName = node.children().children().children(".provinceName-txt").val();
    var addDistrictCode = node.children().children().children(".provinceName-Code").val();
    var evenNode = '<li class="city addDistrictText"><div class="item itemDistrict"><div class="cityCont"><input type="text" class="provinceName-txt" placeholder="地区"  /><input type="text" class="provinceName-Code" placeholder="默认编码" onkeyup="MS.onlyNumber(this)" onblur="MS.onlyNumber(this)" /><input type="text" class="provinceName-Code" placeholder="第二编码" onkeyup="MS.onlyNumber(this)" onblur="MS.onlyNumber(this)" /><input type="text" id="provinceOrderNum" class="provinceName-Code" placeholder="序号" onkeyup="MS.onlyNumber(this)" onblur="MS.onlyNumber(this)" /><span class="confirm" onclick="confirmAddDistrict(this,'+idxCode+','+cityCode+','+topProvinceCode+','+topCitySecondCode+','+topProvinceSecondCode+')">确定</span><span class="cancel" onclick="cancelAdd(this)">取消</span></div></div></li>';
    var childrenNode = $(str).parent().parent().next().children(".city:nth-child(1)");
    $(str).parent().parent().next().show();
    if(node.length <= 0){
        if(childrenNode.length <= 0){
            $(str).parent().parent().next().prepend(evenNode);
        }
        else{
            childrenNode.before(evenNode);
        }
    }else{
        if(addDistrictName == "" || addDistrictCode == ""){
            layer.msg('请先填写完再添加！', {icon: 2});
        }else{
            childrenNode.before(evenNode);
        }
    }
}


/* 给伪元素添加鼠标经过的时候展示添加编辑删除操作 */
function toggle(str){
    $(str).mouseenter(function(){
        $(str).children(".handleCont").show();
    });
    $(str).mouseleave(function(){
        $(str).children(".handleCont").hide();
    });
}

/* 删除节点 */
function deleteNode(str, code){
    layer.confirm('您确定要删除吗？', {
        shade: 0,
        title: false,
        btn: ['确定', '关闭'],
        yes: function (index) {
            Ms.saveLoading();
            $.ajax({
                type: "POST",
                url: "/logistics/area/delArea",
                data: {
                    code : code
                },
                dataType: 'json'
            }).done(function (data) {
                if (data.status == 200) {
                    $(str).parent().parent().parent().remove();
                } else {
                    parent.layer.msg(data.msg, {icon: 2});
                }
            }).fail(function (result) {
                parent.layer.msg(MS.ERROR_MSG, {icon: 2});
            });
            layer.close(index);
        },
        btn2: function (index) {
            layer.close(index);
        }
    });
}

/* 鼠标经过的时候展示添加编辑删除操作 */
$(function(){
    $('.containerPOP .item').mouseenter(function(){
        $(this).children(".handleCont").show();
    });
    $('.containerPOP .item').mouseleave(function(){
        $(this).children(".handleCont").hide();
    });
});

/* 点击取消编辑 */
function cancelEdit(str) {
    $(str).parent().prev().show();
    $(str).parent().remove();
}










