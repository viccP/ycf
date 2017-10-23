/**
 * 
 */
$(function() {

	//初始化菜单点击事件
	$(".menu-anchor").on("click", function() {

		//清空容器
		$("#pageContent").empty();

		//加载页面
		$.get($(this).attr("path"), function(data) {
			$("#pageContent").html(data);//初始化加载界面  
		});
	});

	//初始加载
	initFirstPage();
	
	//初始化个人资料修改
	$("#userInfo").on("click",function(){
		openModal();
	});
	
	//初始化修改密码
	initPwd();
	
	//初始化修改基本信息
	//initBasicInfo();
	
	//密码状态检验
	validatePwd();
	
	//初始化登出按钮
	$("#logout").on("click",function(){
		//提示框打开
	    bootbox.confirm({
	        title: "消息提示",
	        message: "确定退出么?",
	        size:"small",
	        buttons: {
	            cancel: {
	                label: '<i class="fa fa-times"></i> 取消'
	            },
	            confirm: {
	                label: '<i class="fa fa-check"></i> 确定'
	            }
	        },
	        callback: function (result) {
	        		if(result){
	        			$.ajax({
	        				url : $.cxt + '/index/logout',
	        				type : "POST",
	        				dataType:"json",
	        				success : function(json) {
	        					if (json.code == '0') {
	        						location.href = $.cxt+"/pages/login.jsp";
	        					}
	        				}
	        			});
	        		}
	        }
	    });
	});

});

/**
 * 初始加载第一页
 * @returns
 */
function initFirstPage() {
	//获取链接地址
	var path = $(".menu-anchor:first").attr("path");

	//清空容器
	$("#pageContent").empty();

	//加载页面
	$.get(path, function(data) {
		$("#pageContent").html(data);//初始化加载界面  
	});
}

/**
 * 打开模态框
 * @returns
 */
function openModal(customMsg){
	$("#selfModal").modal({
		backdrop : 'static',
		keyboard : false,
		show : true
	});
	
	// 清除form的验证状态
	$('#editPwdfForm').bootstrapValidator('resetForm', 'true');
	document.getElementById("editPwdfForm").reset();
	
	$('#editSelfForm').bootstrapValidator('resetForm', 'true');
	document.getElementById("editSelfForm").reset();
	if(customMsg){
		$("#selfModal .modal-title").empty().append($("<i></i>").addClass("ace-icon fa fa-pencil-square-o")).append(customMsg);
	}
}

/**
 * 初始化修改密码
 * @returns
 */
function initPwd(){
	$('#editPwdfForm').bootstrapValidator({
		fields : {
			oldPwd : {
				validators : {
					notEmpty : {message : '请输入旧的密码'},
					regexp : {regexp : /^.*$/,message : '旧密码验证错误，请重新输入旧密码'}
				}
			},
			newPwd : {
				validators : {
					notEmpty : {message : '请输入新的密码'},
					regexp : {regexp : /^.{8,}$/,message : '请输入超过7位字符'}
				}
			},
			confirmPwd : {validators : {
					notEmpty : {message : '请输入确认密码'},
					regexp : {regexp : /^.*$/,message : '新密码两次输入不一致，请重新输入'}
				}
			}
		},
		submitHandler : function(validator, form, submitButton) {
			var oldPwd = $.md5($("#editPwdfForm input[name=oldPwd]").val());
			var newPwd = $.md5($("#editPwdfForm input[name=newPwd]").val());
			var confirmPwd = $.md5($("#editPwdfForm input[name=confirmPwd]").val());
			$.ajax({
				url : $.cxt + '/index/modifyPwd',
				data : {"oldPwd":oldPwd,"newPwd":newPwd,"confirmPwd":confirmPwd},
				type : "POST",
				dataType:"json",
				async:false,
				loading:true,
				success : function(data) {
					if (data.code == "1") {
						validator.updateStatus("oldPwd",'INVALID', "regexp");
					} else if (data.code == "2") {
						validator.updateStatus("confirmPwd",'INVALID', "regexp");
					} else{
						// 关闭窗口
						$('#selfModal').modal('hide');
					}
			}});
		}
	});
}

/**
 * 检查密码状态
 * @returns
 */
function validatePwd(){
	$.ajax({
		url : $.cxt + '/index/vlidatePwd',
		type : "POST",
		dataType:"json",
		async:false,
		loading:true,
		success : function(data) {
			if (data.code == "1") {
				openModal("您的密码为初始密码，建议您尽快修改");
			}
	}});
}