<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<!DOCTYPE HTML>
<head>

<title>洋桃跨境供应链后台管理中心-修改密码</title>
	<jsp:include page="../common/memberCommon.jsp"></jsp:include>
</head>
<body>

<div class="wrapper form-operation">
	<div class="reg-container form-operation-com">
		<input id="idx" type="hidden" value="${idx}">
        <ul>
            <li>
                <label class="tit">原密码：</label>
                <input class="type-text" id="password_old" type="password" onblur="checkOldPassword()"/>
                <p class="warn" id="hint_old"></p>
            </li>
            <li>
                <label class="tit">新密码：</label>
                <input class="type-text" id="password" type="password" onblur="checkPassword()" />
                <p class="info" id="passHint">密码建议使用字母，数字和符号两种及以上的组合，6-20位字符!</p>
                <span class="warn" id="hint4"></span>
            </li>
            <li>
                <label class="tit">确认密码：</label>
                <input class="type-text" id="password2" type="password" onblur="checkAgainPwd()" />
                <span class="warn" id="hint5"></span>
            </li>
        </ul>
	</div>
</div>
</body>
<%--<link rel="stylesheet" href="${static$domain}/css/member/login.css?_v=${css$version}" />--%>
<script type="text/javascript" src="${static$domain}/js/member/login.js?_v=${js$version}" ></script>
</html>