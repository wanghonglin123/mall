<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>洋桃跨境供应链后台管理中心</title>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <script type="text/javascript" charset="utf-8" >
        // 文件资源中心url全局变量
        var fileResourceApi = '${fileResourceApi}';
        var imgurl = '${picimgurl}';
        var verifyCodeDomin = '${verifyCodeDomin}';
    </script>

    <link rel="icon" href="${static$domain}/images/favicon.ico?_v=${img$version}" type="image/x-icon" />
    <script type="text/javascript" src="/resources/plugins/jquery-easyui-1.5.1/jquery.min.js?_v=${js$version}" ></script>
    <!-- member 模块公共 -->
    <!-- 公共css -->
    <link rel="stylesheet" href="/resources/plugins/jquery-easyui-1.5.1/themes/gray/easyui.css?_v=${css$version}"/>
    <link rel="stylesheet" href="${static$domain}/css/common/common.css?_v=${css$version}"/>
    <link rel="stylesheet" href="${static$domain}/css/common/current.css?_v=${css$version}"/>

    <!-- 公共js -->
    <script type="text/javascript" src="${static$domain}/js/common/core-min.js?_v=${js$version}" ></script>
    <script type="text/javascript" src="${static$domain}/js/common/enc-base64-min.js?_v=${js$version}" ></script>
    <script type="text/javascript" src="/resources/plugins/layer/layer.js?_v=${js$version}" ></script>
    <script type="text/javascript" src="${static$domain}/js/common/common.js?_v=${js$version}" ></script>
    <script type="text/javascript" src="${static$domain}/js/common/constans.js?_v=${js$version}" ></script>
    <script type="text/javascript" src="/resources/plugins/jquery-easyui-1.5.1/jquery.easyui.min.js?_v=${js$version}"></script>
    <script type="text/javascript" src="/resources/plugins/jquery-easyui-1.5.1/locale/easyui-lang-zh_CN.js?_v=${js$version}"></script>

    <script type="text/javascript" src="${static$domain}/js/common/center.js?_v=${js$version}" ></script>
    <script type="text/javascript" charset="utf-8" src="/resources/plugins/layui/layui.js?_v=${js$version}" charset="utf-8"></script>
    <script type="text/javascript" src="${static$domain}/plugins/My97DatePicker/WdatePicker.js?_v=${js$version}"></script>

</head>
<body>


<div id="verifyCodef-div" style="display: block;">
    <div class="winCont">
        <div class="winRow">
            <label class="brandName"><i>*</i>类型：</label>
            <p class="relative">
                <select class="brandTxt" id="verifyCodef-type">
                    <c:forEach items="${verifyCodePictureTypeMap}" var="p">
                        <option value="${p.key}">${p.value}</option>
                    </c:forEach>
                </select>
                <i class="downIcon"></i>
            </p>
            <p class="palce-hint hint4">*类型必填</p>
        </div>
        <!-- 图片上传 start -->
        <div class="uploadImg-col">
            <span class="img-title pd"><em class="left"><i>*</i>验证码图片：</em><em class="ts">（支持jpg/png，宽 x 高)</em></span>
            <div class="upload-cont wtdm fr">
                <div class="upload-col">
                    <div class="uploadBtn" id="verifyCodef-up-display">
                        <a href="javascript:;" class="upload-icon"></a>
                        <span class="font-btn">上传图片</span>
                    </div>
                    <input id="verifyCodef-fileupload" type="file" name="files" style="display: none" />
                    <p class="size-ts">${verifyCodePictureSize} x ${verifyCodePictureSize}</p>
                    <div class="upload-img" id="verifyCodef-upload-img">
                        <p class="img-cont">
                            <c:if test="${not empty itemOrigin.nationalFlagImageBigDynamicS}">
                                <img src="${itemOrigin.nationalFlagImageBigDynamicS}"
                                     messageid='${itemOrigin.nationalFlagImageBig}' alt="">
                            </c:if>
                        </p>
                        <span class="close"><i></i> </span>
                        <span class="img-name"></span>
                    </div>
                </div>
            </div>
        </div>
        <div class="winRow2">
            <label class="brandName">备注：</label>
            <textarea class="brandTxt" id="verifyCodef-remark">${contentKeyword.data.remark}</textarea>
        </div>
        <!-- 图片上传 end -->
    </div>
</div>
<div id="sssss"></div>

<script src="${static$domain}/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js?_v=${js$version}"></script>
<script src="${static$domain}/plugins/jquery-file-upload/js/jquery.iframe-transport.js?_v=${js$version}"></script>
<script src="${static$domain}/plugins/jquery-file-upload/js/jquery.fileupload.js?_v=${js$version}"></script>

<script src="${static$domain}/plugins/jquery-file-upload/ajaxfileupload.js?_v=${js$version}"></script>
<script src="${static$domain}/plugins/json2.js"></script>
<script type="text/javascript">

    function submitForm(obj) {
        var pass = true;

        var imagePath = $("#verifyCodef-upload-img").find('.img-cont').find('img:first').attr("messageid");
        if (imagePath == "" || !imagePath) {
            layer.msg('请上传验证码图片！', {icon: 2});
            Ms.closeLoading();
            pass = false;
        }
        if (pass) {
            $.ajax({
                type: "post",
                url: "/system/verifyCode/list/doSave",
                data: {
                    type: $("#verifyCodef-type").val(),
                    remark: $("#verifyCodef-remark").val(),
                    imagePath: $("#verifyCodef-upload-img").find('.img-cont').find('img:first').attr("messageid") || null,
                },
                dataType: 'json',
                success: function (d) {
                    if (d.status == 200) {
                        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                        parent.layer.msg('操作成功！', {icon: 1});
                        parent.layer.close(index);
                        obj.datagrid('reload', {});
                    } else {
                        parent.layer.msg(d.msg == null ? MS.ERROR_MSG : d.msg, {icon: 2});
                    }
                },
                error: function (d) {
                    parent.layer.msg(d.msg == null ? MS.ERROR_MSG : d.msg, {icon: 2});
                }
            });
        }
    }

    $("#verifyCodef-up-display").click(function () {
        $("#verifyCodef-fileupload").trigger("click");
    });
    // 上传表格
    $("#verifyCodef-fileupload").click(function () {
        var formData = {
            "filePath":"verifyCode",
            "remark":""
        };
        $(this).fileupload({
            url: fileResourceApi + '/cos/uploadFiles',
            type: "post",
            forceIframeTransport:true,
            formData: formData,
            dataType: "json",
            start: function (e) {
                layer.msg('正在上传数据...', {
                    icon: 16,
                    shade: [0.1, '#fff'],
                    time: 6000000
                });
            },
            done: function (e, data) {
                if(data && data.status ==200){
                    if(data.data.length == 0){
                        layer.msg(MSYT.UPLOAD_FAIL_MSG, {icon: 2, time:1500,offset: '100px'});
                        return;
                    }
                    if(data.data[0].uploadStatus ==1){
                        var path = imgurl + data.data[0].path;
                        var dataurl = data.data[0].path;
                        $("#verifyCodef-upload-img").find('.img-cont').html('<img src="'+path+'" messageid='+dataurl+' />');
                        layer.msg('上传成功！', {icon: 6});
                    }else {
                        layer.msg(MSYT.UPLOAD_FAIL_MSG, {icon: 2, time:1500,offset: '100px'});
                    }
                }else {
                    layer.msg(MSYT.UPLOAD_FAIL_MSG, {icon: 2, time:1500,offset: '100px'});
                }
            },
            fail: function (e, data) {
                // 处理上传失败后处理
                layer.msg('上传失败！', {icon: 2});
            }
        });
    });
</script>

<jsp:include page="../../footer.jsp"/>