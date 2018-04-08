<!doctype html>
<head>
    <title>洋桃跨境供应链后台管理中心-角色操作相关</title>
    <#include "../../common/memberCommon.jsp"/>
</head>
<body>
<link rel="stylesheet" href="/css/common/current.css?_v="/>

<!-- start-->
<input type="hidden" id="idx" value=""/>
<div id="resourceCapacity" style="height: 319px;overflow-y: auto;overflow-x: hidden;">
    <div class="winCont">
        <div class="winRow">
            <label class="brandName"><i>*</i>角色名称：</label>
            <input id="name" class="brandTxt required" type="text" value="">
            <p class="palce-hint hint1">*角色名称必填</p>
        </div>
        <div class="winRow">
            <label class="brandName"><i>*</i>编码：</label>
            <input id="code" class="brandTxt required" type="text" onkeyup="MS.onlyEnOrNumber(this);" onblur="MS.onlyEnOrNumber(this);"
                   value=""  >
            <p class="palce-hint hint2">*编码必填</p>
        </div>
        <div class="winRow2" style="margin-bottom: 20px;">
            <label class="brandName">备注：</label>
            <textarea class="brandTxt" id="remark"></textarea>
        </div>
        <div class="row-set">
            <div class="row-set-lf">
                <label class="ms-userName">资源组设置：</label>
                <button class="ms-set" type="button" onclick="chooseResourceGroup(${role.idx});">设置</button>
            </div>
            <div class="row-set-lr" id="resource-group-text">
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        var _type = '${type!}';
        if(_type != 'view') {
            $('#resourceCapacity .required').on({
                'click': function () {
                    $(this).next().hide();
                    $(this).css("border", "1px solid #ffac00");
                },
                'blur': function () {
                    $(this).css("border", "1px solid #e4e4e4");
                }
            });
        }
        if(_type == 'view'){
            $('input,textarea').prop('readonly',true).on({
                'focus': function () {
                    $(this).css("border", "1px solid #e4e4e4");
                }
            });
            $("span[id^='resource-']").each(function(index, element) {
                $(this).removeAttr("onclick");
            });
        }
    });
    /**
     *  选择资源组
     * @param idx 角色idx
     */
    function chooseResourceGroup(idx) {
        // 获取资源组idx
        var resourceGroupIdx = [];
        $("span[id^='resource-']").each(function(index, element) {
            var idx = $(this).attr("id");
            var idxArr = idx.split("-");
            resourceGroupIdx.push(idxArr[1]);
        });
        var url = '/role/chooseResourceGroup/'+idx + '?resourceGroupIdx=' + resourceGroupIdx.join(',');
        var title ='选择资源组';
        //页面层
        parent.layer.open({
            title: title,
            type: 2,
            btnAlign: 'c',
            btn: ['保存','关闭'],
            resize:false,
            area: ['605px', '663px'], //宽高
           /* offset: [        //随机坐标
                Math.random()*($(window).height()-300),
                Math.random()*($(window).width()+390)
            ],*/
            content: [url, 'no'],
            yes: function(index, layero){
                var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                var result = iframeWin.chooseResult();
                // 回现
                var spanHtml = '',$resourceDiv = $('#resource-group-text');
                if (result){
                    var resultArr = result.split(",");
                    for (var i =0 ; i < resultArr.length ; i++){
                        var resourceArr = resultArr[i].split("|");
                        var idx = resourceArr[0], name = resourceArr[1];
                        if($('#resource-'+idx).length > 0 && !name){
                            name = $('#resource-'+idx).text();
                        }
                        spanHtml +='<span class="ms-institution" id="resource-'+idx+'" onclick="closeResouceGroupName(this)"><i></i>'+name+'</span>';
                    }
                }
                $resourceDiv.html(spanHtml);
                return false;
            }
        });
    }
    // 点击X 资源组
    function closeResouceGroupName(obj) {
        $(obj).remove();
    }
    // 提交表单 objTable保存成功后要刷新的列表对象
    function submitForm(objTable) {
        if (!$.trim($('#name').val())) {
            $('#resourceCapacity .hint1').show();
            $('#name').css("border", "1px solid #ff2c41");
            return false;
        }
        if (!$.trim($('#code').val())) {
            $('#resourceCapacity .hint2').show();
            $('#code').css("border", "1px solid #ff2c41");
            return false;
        }
        // 获取资源组idx
        var resourceGroupIdxArr = [];
        $("span[id^='resource-']").each(function(index, element) {
            var idx = $(this).attr("id");
            var idxArr = idx.split("-");
            resourceGroupIdxArr.push(idxArr[1]);
        });
        var params = {};
        params.idx = $("#idx").val();
        params.name = $("#name").val();
        params.code = $("#code").val();
        params.remark = $("#remark").val();
        params.resourceGroupIdxs = resourceGroupIdxArr.join(",");
        parent.layer.load(2, {shade : 0.01});
        $.ajax({
            type: "POST",
            url: "/role/do-saveOrEdit",
            data: {
                idx: params.idx,
                name: params.name,
                code: params.code,
                remark: params.remark,
                resourceGroupIdxs : params.resourceGroupIdxs
            },
            dataType: 'json'
        }).done(function (data) {
            if (data.status == 200) {
                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                parent.layer.msg('操作成功！', {icon: 6});
                parent.layer.close(index);
                objTable.datagrid('reload', {});
            } else {
                parent.layer.msg(data.msg, {icon: 2});
            }
        }).fail(function (result) {
            parent.layer.msg(result.msg, {icon: 2});
        });
    }

</script>

</body>
</html>