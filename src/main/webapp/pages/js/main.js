/**
 * 
 */
$(function(){
	
	//初始化滚动条
	$('#uploads').ace_scroll({
		size:300,
		mouseWheelLock: true,
		alwaysVisible : true
	});
	
	// 初始化checkBox
	initCheckBox();
	
	// 初始化动态填写框
	initBootBox();
	
	// 初始化保存按钮
	initSaveBtn();
	
	// 初始化上传
	initUploads();
});

/**
 * 初始化checkBox(设置为单选模式)
 * 
 * @returns
 */
function initCheckBox(){
	$("input[type=checkbox]").on("click",function(){
		if(!$(this).prop("checked")){
			return;
		}
		var ckName=$(this).attr("name");
		var simlarCk=$("input[name="+ckName+"]");
		simlarCk.each(function(){
			$(this).prop("checked",false);
		});
		$(this).prop("checked",true);
	});
}

/**
 * 初始化输入框
 * 
 * @returns
 */
function initBootBox(){
	$(".easy-pie-chart").on("click",function(){
		var _this=$(this);
		bootbox.prompt({ 
			size: "small",
			title: "请输入"+_this.siblings("span").text(),
			callback: function(name){
				if(name!=null){
					_this.children(".percent").text(name);
				}
			}
		});
	});
}

/**
 * 初始化保存按钮
 * 
 * @returns
 */
function initSaveBtn(){
	
	//构造Json
	$("#savebtn").on("click",function(){
		
		//1.构造基础信息
		var json = new FormData();
		
		//获取文本框
		$.each($("input[type=text]"),function(){
			json.append($(this).attr("name"),$(this).val());
		});
		
		//获取单选框
		$.each($("input[type=checkbox]"),function(){
			if($(this).prop("checked")){
				json.append($(this).attr("name"),$(this).next().text());
			}
		});
		
		//获取输入框
		$.each($("span.percent"),function(){
			json.append($(this).attr("name"),$(this).text());
			
		});
		
		//获取上传文件
		$.each($("body").data("uploads")||{},function(key,val){
			for(var i=0;i<val[0].files.length;i++){
				json.append(key,val[0].files[i]);
			}
		});
		
		//提交后台
		$.ajax({
			url : $.cxt + "/apply/doApply",
			data : json,
			type : "POST",
			dataType : "json",
			contentType : false,
			processData : false,
			loading:true
		});
	});
}

/**
 * 初始化上传按钮
 * 
 * @returns
 */
function initUploads(){
	$("a.upload").on("click",function(e){
		var _this=$(this);
		e.preventDefault();
		var fileObj=$("<input id='fileField'></input>").attr({"type":"file","multiple":"multiple"}).on("change",function(){
			var data = $("body").data("uploads")||{};
			data[_this.attr("name")]=fileObj;
			$("body").data("uploads",data);
			_this.parent().prev().children("span").remove();
			_this.parent().prev().append($("<span></span>").addClass("badge badge-pink").append(fileObj[0].files.length));
			
		});
		fileObj.trigger("click");
	});
}