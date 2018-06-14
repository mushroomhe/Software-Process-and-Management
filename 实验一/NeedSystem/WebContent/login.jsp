 <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>登录界面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.css" />
<title>河北省重大技术需求征集系统</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url(images/loginbg.jpg);
	background-repeat: repeat-x;
}

.code {
	background-image: url(images/code.jpg);
	font-family: Arial;
	font-style: italic;
	color: Red;
	border: 0;
	padding: 2px 3px;
	letter-spacing: 3px;
	font-weight: bolder;
}

.unchanged {
	border: 0;
}
-->
</style>
<script src="js/login.js"></script>
<script type="text/javascript">
	function check() {
		//姓名
		var username = document.getElementById('username');
		var pwd = document.getElementById('password');
		var codeCheck=document.getElementById('codeCheck');
		var checkCode=document.getElementById('checkCode');

		if (username.value == "") {
			alert("用户名不能为空");
			username.focus();
			return false;
		}

		//密码验证
		if (pwd.value == "") {
			alert("密码不能为空");
			pwd.focus();
			return false;
		}

		//验证码验证
		if(codeCheck.value==""){
			alert("验证码不能为空");
			codeCheck.focus();
			return false;
		}
		
		if(codeCheck.value!=checkCode.value){
			alert("验证码输入错误");
			codeCheck.focus();
			document.getElementById("codeCheck").value="";
			return false;
		}
		return true;
	}


	var code; //在全局 定义验证码   
	function createCode() {
		code = "";
		var codeLength = 6;//验证码的长度   
		var checkCode = document.getElementById("checkCode");
		var selectChar = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C',
				'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
				'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');//所有候选组成验证码的字符，当然也可以用中文的   

		for (var i = 0; i < codeLength; i++) {
			var charIndex = Math.floor(Math.random() * 36);
			code += selectChar[charIndex];
		}
		//  alert(code);   
		if (checkCode) {
			checkCode.className = "code";
			checkCode.value = code;
		}

	}
</script>

<script type="text/javascript">
<!--
	function MM_swapImgRestore() { //v3.0
		var i, x, a = document.MM_sr;
		for (i = 0; a && i < a.length && (x = a[i]) && x.oSrc; i++)
			x.src = x.oSrc;
	}
	function MM_preloadImages() { //v3.0
		var d = document;
		if (d.images) {
			if (!d.MM_p)
				d.MM_p = new Array();
			var i, j = d.MM_p.length, a = MM_preloadImages.arguments;
			for (i = 0; i < a.length; i++)
				if (a[i].indexOf("#") != 0) {
					d.MM_p[j] = new Image;
					d.MM_p[j++].src = a[i];
				}
		}
	}

	function MM_findObj(n, d) { //v4.01
		var p, i, x;
		if (!d)
			d = document;
		if ((p = n.indexOf("?")) > 0 && parent.frames.length) {
			d = parent.frames[n.substring(p + 1)].document;
			n = n.substring(0, p);
		}
		if (!(x = d[n]) && d.all)
			x = d.all[n];
		for (i = 0; !x && i < d.forms.length; i++)
			x = d.forms[i][n];
		for (i = 0; !x && d.layers && i < d.layers.length; i++)
			x = MM_findObj(n, d.layers[i].document);
		if (!x && d.getElementById)
			x = d.getElementById(n);
		return x;
	}

	function MM_swapImage() { //v3.0
		var i, j = 0, x, a = MM_swapImage.arguments;
		document.MM_sr = new Array;
		for (i = 0; i < (a.length - 2); i += 3)
			if ((x = MM_findObj(a[i])) != null) {
				document.MM_sr[j++] = x;
				if (!x.oSrc)
					x.oSrc = x.src;
				x.src = a[i + 2];
			}
	}
//-->
</script>
</head>
<body bgcolor="#FFFFFF" onload="createCode()"
	onLoad="MM_preloadImages('images/login000_06.jpg','images/loging000_07.jpg')">
	<!-- Save for Web Slices (待切.psd) -->
	<form
		action="${pageContext.request.contextPath}/AdminServlet?method=login"
		method="post" onsubmit="return check()">
		<table width="795" height="475" border="0" align="center"
			cellpadding="0" cellspacing="0" id="__01">

			<tr>
				<td colspan="5"><img src="images/login_01.jpg" width="795"
					height="159" alt="系统名称"></td>
			</tr>
			<tr>
				<td rowspan="2"><img src="images/login_02.jpg" width="269"
					height="174" alt=""></td>
				<td bgcolor="#CFE5F2"><img src="images/login_03.jpg" width="66"
					height="115" alt=""></td>
				<td colspan="2" bgcolor="#D0E6F3"><table width="100%"
						height="116" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td colspan="2" align="left" valign="bottom"><INPUT
								id="username" NAME="username" TYPE="text" placeholder="Username" CLASS="STYLE1"
								STYLE="width: 180px; height: 17px; border: #336699 1px solid"
								tabindex="1" MAXLENGTH="26"></td>
						</tr>
						<tr>
							<td height="32" colspan="2" align="left" valign="bottom"><INPUT
								id="password" NAME="password" TYPE="password" placeholder="Password" CLASS="STYLE1"
								STYLE="width: 180px; height: 17px; border: #336699 1px solid"
								tabindex="1" MAXLENGTH="26"></td>
						</tr>
						<tr>
							<td width="50%" height="29" align="left" valign="bottom"><INPUT
								id="codeCheck" NAME="codeCheck" TYPE="text" placeholder="Code"
								CLASS="STYLE1"
								STYLE="width: 100px; height: 17px; border: #336699 1px solid"
								tabindex="1" MAXLENGTH="26"></td>
							<td width="50%" align="left" valign="bottom"><input
								type="text" oncopy="return false" onclick="createCode()" readOnly="true"
								id="checkCode" class="unchanged" style="width: 80px" /></td>
						</tr>
						<tr>
							<td height="30" colspan="1" align="left" valign="bottom">&nbsp;</td>
						</tr>
					</table></td>
				<td rowspan="2"><img src="images/login_05.jpg" width="262"
					height="174" alt=""></td>
			</tr>
			<tr>
				<td colspan="2"><a href="" id="checkForm"
					onMouseOut="MM_swapImgRestore()"
					onMouseOver="MM_swapImage('Image12','','images/loging000_06.jpg',1)">
						<input onsubmit="return check()" type="image"
						src="images/login_06.jpg" name="Image12" width="135" height="59"
						border="0">
				</a></td>
				<td><a href="${pageContext.request.contextPath }/AdminServlet?method=ProvinceView" onsubmit="return check()"
					onMouseOut="MM_swapImgRestore()"
					onMouseOver="MM_swapImage('Image13','','images/loging000_07.jpg',1)"><img
						src="images/login_07.jpg" name="Image13" width="129" height="59"
						border="0"></a></td>

			</tr>
			<tr>
				<td colspan="5"><img src="images/login_08.jpg" width="795"
					height="141" alt=""></td>
			</tr>
			<tr>
				<td><img src="images/&#x5206;&#x9694;&#x7b26;.gif" width="269"
					height="1" alt=""></td>
				<td><img src="images/&#x5206;&#x9694;&#x7b26;.gif" width="66"
					height="1" alt=""></td>
				<td><img src="images/&#x5206;&#x9694;&#x7b26;.gif" width="69"
					height="1" alt=""></td>
				<td><img src="images/&#x5206;&#x9694;&#x7b26;.gif" width="129"
					height="1" alt=""></td>
				<td><img src="images/&#x5206;&#x9694;&#x7b26;.gif" width="262"
					height="1" alt=""></td>
			</tr>
		</table>
		<c:if test="${param.status.equals('1')}">
			<div class="alert alert-danger" role="alert">用户名不存在</div>
		</c:if>
		<c:if test="${param.status.equals('2')}">
			<div class="alert alert-danger" role="alert">密码输入错误</div>
		</c:if>
		<c:if test="${param.status.equals('3')}">
			<div class="alert alert-danger" role="alert">注册成功！请登录</div>
		</c:if>
	</form>
	<!-- End Save for Web Slices -->
	<script
		src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/jquery.validate.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/myValidate.js"
		type="text/javascript"></script>
</body>
</html>