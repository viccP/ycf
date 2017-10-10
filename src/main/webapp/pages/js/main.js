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