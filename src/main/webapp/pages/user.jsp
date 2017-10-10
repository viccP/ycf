<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="<%=request.getContextPath()%>/pages/js/user.js"></script>
<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<div class="well well-sm">
			<form class="form-inline" id="searchUserForm">
				<input class="input-large" placeholder="请输入登录用户名" type="text" name="loginId">
				<input class="input-large" placeholder="请输入用户姓名" type="text" name="userName">
				<select class="chosen-select fix-select" name="sex" data-placeholder="请选择性别...">
					<option value=""></option>
					<option value="">全部</option>
					<option value="0">女</option>
					<option value="1">男</option>
				</select>
				<select class="chosen-select fix-select" name="pwdStatus" data-placeholder="请选择密码状态...">
					<option value=""></option>
					<option value="">全部</option>
					<option value="0">初始密码</option>
					<option value="1">已经修改</option>
				</select>
				<button type="button" class="btn btn-info btn-sm" id="searchUser">
					<i class="ace-icon fa fa-search bigger-110"></i>
					查询
				</button>
			</form>
		</div>
		<table id="grid-table"></table>
		<div id="grid-pager"></div>
	</div>
</div>