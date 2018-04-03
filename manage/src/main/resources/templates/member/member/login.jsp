<!DOCTYPE html>
<html>
<head>
	<title>洋桃跨境供应链后台管理中心-登录</title>
	<#include "../../common/memberCommon.jsp"/>
	<link rel="stylesheet" href="${static$domain!}/style/login.css?_v=${css$version!}" />
</head>
<body>
<div class="wrapper wrapper-login-bg">
    <div class="login-com">
        <div class="login-bg">
            <div class="login-container">
                <div class="login-logo"><img src="/images/login-logo.png" /></div>
                <div class="cont">
                    <i class="user-close"></i>
                    <input id="userName" class="userName" type="text"  placeholder="成员名／手机号码／邮箱" />
                    <i class="pass-close"></i>
                    <input id="passWord" class="passWord" type="password"  placeholder="密码"/>
                    <div id="_captcha_img_content" style="display:none;"></div>
                    <div class="verification-code">
                        <input type="text" value="" maxlength="6" placeholder="请输入手机验证码">
                        <a data-cookie="verification-code" href="javascript:;">获取验证码</a>
                    </div>
                </div>
                <div class="login-row">
                    <span class="checkbox" id="rememberme"></span>
                    <span class="avoid">1天内免登录</span>
                    <a class="forget" href="/sys/to-forget" target="_blank">忘记密码？</a>
                </div>
                <button class="login-btn-active" type="submit" id="login-btn">登录</button>
                <div class="hint-d"><i class="icon icon-warn"></i><span class="hint"></span> </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${static$domain!}/js/member/login.js?_v=${js$version!}" ></script>
<script type="text/javascript" src="${static$domain!}/plugins/picture-code/jquery-picture-code.js?_v=${js$version!}" ></script>
<link rel="stylesheet" href="${static$domain!}/plugins/picture-code/css/picture-code.css?_v=${css$version!!}"/>
<script type="text/javascript">
    /*var _captcha_img_content;
    var isSmsSend = '${isSmsSend!}';
	$(function(){
	    if(isSmsSend == 'false'){
	        $('.verification-code').remove();
        !}
        var verifyCodePictureSize="${verifyCodePictureSize!}";
        var verifyCodePictureNum="${verifyCodePictureNum!}";
        var verifyCodePictureRing="${verifyCodePictureRing!}";
        _captcha_img_content = $("#_captcha_img_content").pictureCode({
            url: verifyCodeDomin + "/validateCode?type=1&version=${js$version!}",
            accessCodeUrl: verifyCodeDomin + "/getValidateAccessCode?version=${js$version!}",
            picSize: verifyCodePictureSize,
            picNum: verifyCodePictureNum,
            ring:verifyCodePictureRing
        });
        // 当登录页面在主页里面或者在弹层里面，需要重新加载一下...
        if($('.win-nav').length > 0){
            window.location.reload();
        }
        if(self != top){
            parent.window.location.reload()
        }
        /!* enter绑定登录事件 *!/
        $(document).on("keydown", function(e){
            if (e.keyCode == 13) {
                doLogin();
            }
        });
        $('#passWord').on("focus", function(){
            var userName = $('#userName').val();
            if(userName.length > 1){
                // 没有加载成功 重试
                if(!window.probc){
                    _captcha_img_content_reg.pictureCode("refresh");
                }
                $('#_captcha_img_content,.verification-code ').show();
            }
        });
	});*/
</script>
</body>
</html>