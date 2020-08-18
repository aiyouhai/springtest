/**
 * 扩展easyui的validator插件rules，支持更多类型验证
 */
$.extend($.fn.validatebox.defaults.rules, {
	chinese : {// 验证中文
		validator : function(value) {
			return /^[\u0391-\uFFE5]+$/i.test(value);
		},
		message : '请输入中文'
	},
	unnormal : {// 验证是否包含空格和非法字符
		validator : function(value) {
			value = value.replace("'_", "*").replace("[charlist]", "*");
			return !(new RegExp("[\'\"“”，、《》？￥&#｛｝【】!！……]+").test(value))
					&& /^[\u0391-\uFFE5a-zA-Z0-9_:-]+$/i.test(value);
		},
		message : '请勿输入特殊字符!'
	},
	dateTimeValid : {
		validator : function(value, param) { // 参数value为当前文本框的值
			startTime = $(param[0]).datetimebox('getValue');// 获取起始时间的值
			endTime = $(param[1]).datetimebox('getValue');// 获取结束时间的值
			if (startTime && endTime) {
				return startTime < endTime;
			} else {
				return true;
			}
		},
		message : '开始时间要小于结束时间!'
	},
	checkIP : {
		validator : function(value, param) {
			var re = /^(\d+)\.(\d+)\.(\d+)\.(\d+)$/;
			if (re.test(value)) {
				if (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256
						&& RegExp.$4 < 256)
					return true;
			} else {
				return false;
			}
		},
		message : '请输入由数字和.组成的IP地址！'
	},
	dateTimeValids : {
		validator : function(value, param) { // 参数value为当前文本框的值
			startTime = $(param[0]).datetimebox('getValue');// 获取起始时间的值
			endTime = $(param[1]).datetimebox('getValue');// 获取结束时间的值
			if (startTime && endTime) {
				return startTime < endTime;
			} else {
				return true;
			}
		},
		message : '提出时间要小于期望完成时间!'
	},
	checkUrl : {
		validator : function(value, param) {
			var reg = /[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62}|(:[0-9]{1,4}))+\.?/;
			
			 var re=new RegExp(reg); 
	            if (!re.test(value)){
	                return false;
	            }else{
	            	return true;
	            }
			/*if (re.test(value)) {
				if (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256
						&& RegExp.$4 < 256)
					return true;
			} else {
				return false;
			}*/
		},
		message : '请输入正确的Url地址！'
	},
});