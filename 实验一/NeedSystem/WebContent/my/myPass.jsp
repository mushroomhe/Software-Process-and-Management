<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.css" />
<title>修改密码</title>

</head>
<body>
	<div class="page-header" align="center">
    	<h1>修改密码</h1>
	</div>
	<div class="row-fluid" style="margin-top: 50px;">
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<form class="form-horizontal" role="form" method="post"
				action="${pageContext.request.contextPath}/AdminServlet?method=updateP&id=${adminBean.username}" id="checkForm">
			<div class="form-group">
					<label class="col-md-3 control-label" for="password">旧密码</label>
					<div class="col-md-9" >
						<input class="form-control" name="password" type="password"
							id="password" placeholder="初始密码">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label" for="pwd1">新密码</label>
					<div class="col-md-9" >
						<input type="password" name="pwd1" class="form-control"
							id="pwd1" placeholder="新密码">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label" for="pwd2">确认密码</label>
					<div class="col-md-9">
						<input type="password" name="pwd2" class="form-control"
							id="pwd2" placeholder="确认密码">
					</div>
				</div>	
				<div class="col-md-offset-3" align="center">
					<button type="submit" class="btn btn-primary">修改</button>
					<button type="reset" class="btn btn-primary">重置</button>
				</div>
				
				<c:if test="${param.status.equals('1')}">
					<div class="alert alert-danger" role="alert">原始密码输入错误</div>
				</c:if>
				<c:if test="${param.status.equals('2')}">
					<div class="alert alert-danger" role="alert">密码修改成功</div>
				</c:if>
				<c:if test="${param.status.equals('3')}">
					<div class="alert alert-danger" role="alert">密码修改失败</div>
				</c:if>
			</form>
		</div>
	</div>
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
		src="${pageContext.request.contextPath}/static/js/myValidate1.js"
		type="text/javascript"></script>
</body>
</html>