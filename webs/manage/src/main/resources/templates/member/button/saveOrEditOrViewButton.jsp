<!doctype html>
<head>
    <title>洋桃跨境供应链后台管理中心-按钮操作相关</title>
    <#include "../../common/memberCommon.jsp"/>
</head>
<body>
<link rel="stylesheet" href="/css/common/current.css?_v="/>

<!-- start-->
<input type="hidden" id="idx" value=""/>
<input type="hidden" id="menuIdx" value="">
<div id="resourceCapacity">
    <div class="winCont">
        <div class="winRow">
            <label class="brandName">当前菜单：</label>
            <input id="menuName" class="brandTxt" type="text" value="" disabled="disabled">
        </div>
        <div class="winRow">
            <label class="brandName">当前菜单编码：</label>
            <input id="menuCode" class="brandTxt" type="text" value="" disabled="disabled">
        </div>

        <div class="winRow">
            <label class="brandName"><i>*</i>名称：</label>
            <input id="name" class="brandTxt required" type="text" value="">
            <p class="palce-hint hint1">*名称必填</p>
        </div>
        <div class="winRow">
            <label class="brandName"><i>*</i>编码：</label>
            <input id="code" class="brandTxt required" type="text" onkeyup="MS.onlyEnOrNumber(this);" onblur="MS.onlyEnOrNumber(this);"
                   value=""  >
            <p class="palce-hint hint2">*编码必填</p>
        </div>

        <div class="winRow2">
            <label class="brandName">备注：</label>
            <textarea class="brandTxt" id="remark"></textarea>
        </div>

    </div>
</div>


<script type="text/javascript">

    $(function () {
        var _type = '${type}';
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
        }
    });

    // 提交表单  obj为要刷新的数据对象
    function submitForm(obj) {
        if (!$.trim($('#name').val())) {
            $('#resourceCapacity .hint1').show();
            $('#name').css("border", "1px solid #ff2c41");
            parent.layer.closeAll('loading');
            return false;
        }
        if (!$.trim($('#code').val())) {
            $('#resourceCapacity .hint2').show();
            $('#code').css("border", "1px solid #ff2c41");
            parent.layer.closeAll('loading');
            return false;
        }
        var params = {};
        params.idx = $("#idx").val();
        params.name = $("#name").val();
        params.code = $("#code").val();
        params.menuIdx = $("#menuIdx").val();
        //params.iconUrl = $("#iconUrl").val();
        params.remark = $("#remark").val();
        $.ajax({
            type: "POST",
            url: "/button/operation/1",
            data: {
                idx: params.idx,
                name: params.name,
                code: params.code,
                menuIdx: params.menuIdx,
                //iconUrl:params.iconUrl,
                remark: params.remark
            },
            dataType: 'json'
        }).done(function (data) {
            if (data.status == 200) {
                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                parent.layer.msg('操作成功！', {icon: 6});
                parent.layer.close(index);
                obj.datagrid('reload', {});
            } else {
                parent.layer.msg(data.msg, {icon: 2});
            }
            parent.$('#buttonlist').datagrid('reload', {menuIdx: params.menuIdx});
        }).fail(function (result) {
            parent.layer.msg(result.msg, {icon: 2});
        });
    }
</script>
</body>
</html>