<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<!DOCTYPE HTML>
<head>

<title>洋桃跨境供应链后台管理中心-个人设置</title>
	<jsp:include page="../common/memberCommon.jsp"></jsp:include>
</head>
<body>
<div class="wrapper form-operation">
	<div class="person-set form-operation-com">
		<input id="idx" type="hidden" value="${member.idx}">
        <ul>
            <li>
                <label class="tit">成员名称：</label>
                <input class="type-text" id="name" type="text"  value="${member.name}" disabled/>
                <p class="warn" id="hint1"></p>
            </li>
            <li>
                <label class="tit">全称：</label>
                <input class="type-text" id="allName" type="text" onblur="checkAllName()" value="${member.fullName}" />
                <p class="warn" id="hint2"></p>
            </li>
            <li>
                <label class="tit">成员编码：</label>
                <input class="type-text" id="coding" type="text" value="${member.code}" onblur="checkCode()"/>
                <p class="warn" id="hint3"></p>
            </li>
            <li>
                <label class="tit">性别：</label>
                <p class="gender-man">
                    <span class="sex-man ${member.sex eq 1 ? 'sex-manCur' : ''}"></span>
                    <label class="sex">男</label>
                </p>
                <p class="gender-woman">
                    <span class="sex-woman ${member.sex eq 2 ? 'sex-womanCur' : ''}"></span>
                    <label class="sex">女</label>
                </p>
                <p class="warn" id="hint_sex"></p>
            </li>
            <li>
                <label class="tit">手机号码：</label>
                <input class="type-text" id="phone" type="text" onblur="checkPhone()" maxlength="11" value="${member.phone}"/>
                <p class="warn" id="hint6"></p>
            </li>
            <li>
                <label class="tit">电话号码：</label>
                <input class="type-text" id="telephone" type="text" onblur="checkTelephone()" value="${member.tel}"/>
                <span class="user-hints" id="hint7"></span>
            </li>
            <li>
                <label class="tit">电子邮箱：</label>
                <input class="type-text" id="email" type="text" onblur="checkEmail()" value="${member.email}"/>
                <p class="warn" id="hint8"></p>
            </li>
            <li>
                <label class="tit">备注：</label>
                <textarea class="type-textarea" id="remark" onblur="checkRemark()" >${member.remark}</textarea>
                <p class="warn" id="hint10"></p>
            </li>
        </ul>
	</div>
</div>
</body>

<%--<link rel="stylesheet" href="${static$domain}/css/member/login.css?_v=${css$version}" />--%>
<script type="text/javascript" src="${static$domain}/js/member/login.js?_v=${js$version}" ></script>

</html>