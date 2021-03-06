<!DOCTYPE html >
<html>
<head>
	<title>洋桃跨境供应链后台管理中心-登录</title>
	<#include "../../common/memberCommon.jsp"/>
	<link rel="stylesheet" href="/style/login.css?_v=" />
</head>
<body onload="loadTopWindow()">
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
                </div>
                <div class="login-row">
                    <span class="checkbox" id="rememberme"></span>
                    <span class="avoid">1天内免登录</span>
                    <a class="forget" href="/sys/to-forget" target="_blank">忘记密码？</a>
                </div>
                <button class="login-btn-active"  id="login-btn" onclick="doLogin()">登录</button>
                <div class="hint-d"><i class="icon icon-warn"></i><span class="hint"></span> </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/member/login.js?_v=" ></script>
<script type="text/javascript" src="/plugins/picture-code/jquery-picture-code.js?_v=" ></script>
<link rel="stylesheet" href="/plugins/picture-code/css/picture-code.css?_v="/>
<script type="text/javascript">
    function doLogin() {
        var userName = $.trim($('#userName').val());
        if(!userName){
            showLoginInfo("账户不能为空");
            return false;
        }
        var passWord = $.trim($('#passWord').val());
        if(!passWord){
            showLoginInfo("密码不能为空");
            return false;
        }
        var param={};
        param.username =base64_encode(userName);
        param.password = base64_encode(passWord);

        $('#login-btn').html("登录中...").addClass("login-btn").removeClass("login-btn-active");
        $.ajax({
            type : "POST",
            url : "/sys/doLogin",
            cache : false,
            data :param
        }).done(function (result) {
            if(parseInt(Ms.SUC_CODE) == parseInt(result.status)){
                window.location.href="/";  // /sys/index
            }else{
                showLoginInfo(result.msg);
                $("#verification").val("");
                $('#login-btn').html("登录").removeClass("login-btn").addClass("login-btn-active");
            }
        }).fail(function(result) {
            showLoginInfo(Ms.ERROR_MSG);
            $("#verification").val("");
            $('#login-btn').html("登录").removeClass("login-btn").addClass("login-btn-active");
        });
    }

    //判断当前窗口是否有顶级窗口，如果有就让当前的窗口的地址栏发生变化，
    function loadTopWindow(){
        if (window.top!=null && window.top.document.URL!=document.URL){
            window.top.location= document.URL; //这样就可以让登陆窗口显示在整个窗口了
        }
    }
</script>
</body>
</html>