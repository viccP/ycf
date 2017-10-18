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