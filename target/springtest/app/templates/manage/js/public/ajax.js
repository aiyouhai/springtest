
function NewTable(TableId, TableCol, url, height, limit, funcName) {
    /*
     TableId    表格ID,
     TableCol  	表头数组,
     url       	表内容数据来源,
     height    	表格高度,
     limit     	每页记录数,
     funcName  	表格的双击处理事件,传入按钮ID.click事件
     */
    var oTable = layui.table.render({
        elem: '#' + TableId, 
        height: height, 
        //,width:'600'
        id: TableId, 
        even: true, 
        url: url,
        request: {
            pageName: 'page',  //页码的参数名称，默认：page
            limitName: 'limit' //每页数据量的参数名，默认：limit
        }, 
        response: {
            statusName: 'code', //数据状态的字段名称，默认：code
            statusCode: 0 ,//成功的状态码，默认：0
            msgName: 'msg',//状态信息的字段名称，默认：msg
            countName: 'count',//数据总数的字段名称，默认：count
            dataName: 'data' //数据列表的字段名称，默认：data
        }, 
        method: 'GET', 
        // data: TableData,
        skin: 'row', //表格风格
        size: 'sm', //sm小尺寸的表格 lg
        cols: [TableCol], 
        page: true, //是否显示分页
        hash: true, //
        limits: [limit], 
        limit: limit,  //每页默认显示的数量
        done: function (res, curr, count) {
            var timer = null;
            //定义表格单击事件
            $(document).on("click", ".layui-table-body table.layui-table tbody tr", function () {
                var _this = $(this);
                clearTimeout(timer);
                timer = setTimeout(function () {
                    //console.log('这里是单击');
                    //这里写你的响应事件
                    //选择当前行
                    var index = _this.attr('data-index');
                    var tableBox = _this.parents('.layui-table-box');

                    //存在固定列
                    if (tableBox.find(".layui-table-fixed.layui-table-fixed-l").length > 0) {
                        tableDiv = tableBox.find(".layui-table-fixed.layui-table-fixed-l");
                    } else {
                        tableDiv = tableBox.find(".layui-table-body.layui-table-main");
                    }
                    var checkCell = tableDiv.find("tr[data-index=" + index + "]").find("td div.laytable-cell-checkbox div.layui-form-checkbox I");

                    if (checkCell.length > 0) {
                        checkCell.click();
                    }
                }, 300);


            });
            //对td的单击事件进行拦截停止，防止事件冒泡再次触发上述的单击事件5、将此代码在页面初始化后执行一次即可以。
            $(document).on("click", "td div.laytable-cell-checkbox div.layui-form-checkbox", function (e) {
                e.stopPropagation();
            });


            //定义表格双击事件
            $(document).on("dblclick", ".layui-table-body table.layui-table tbody tr", function () {

                clearTimeout(timer);
                //console.log('这里是双击');
                //选择当前行
                var index = $(this).attr('data-index');
                var tableBox = $(this).parents('.layui-table-box');

                //存在固定列
                if (tableBox.find(".layui-table-fixed.layui-table-fixed-l").length > 0) {
                    tableDiv = tableBox.find(".layui-table-fixed.layui-table-fixed-l");
                } else {
                    tableDiv = tableBox.find(".layui-table-body.layui-table-main");
                }

                var trs = tableDiv.find(".layui-unselect.layui-form-checkbox.layui-form-checked").parent().parent().parent();
                for (var i = 0; i < trs.length; i++) {
                    var ind = $(trs[i]).attr("data-index");
                    if (ind != index) {
                        var checkCell = tableDiv.find("tr[data-index=" + ind + "]").find("td div.laytable-cell-checkbox div.layui-form-checkbox I");
                        if (checkCell.length > 0) {
                            checkCell.click();
                        }
                    }
                }


                var checkCell = tableDiv.find("tr[data-index=" + index + "]").find("td div.laytable-cell-checkbox div.layui-form-checkbox I");

                if (checkCell.length > 0) {
                    checkCell.click();
                }
                //调用默认操作
                //函数存在执行,不存在跳过
                funcName && funcName();
            });

            //对td的单击事件进行拦截停止，防止事件冒泡再次触发上述的单击事件5、将此代码在页面初始化后执行一次即可以。
            $(document).on("dblclick", "td div.laytable-cell-checkbox div.layui-form-checkbox", function (e) {
                e.stopPropagation();
            });
        }
    });
    return oTable;
}
//建立表格
//var layerloginindex=0 ;
function NewTableTop(TableId, TableCol, url, height, limit) {

    var oTable = top.layui.table.render({
        elem: '#' + TableId
        , height: height
        , id: TableId
        , even: true
        , url: url
        , request: {
            pageName: 'page' //页码的参数名称，默认：page
            , limitName: 'limit' //每页数据量的参数名，默认：limit
        }
        , response: {
            statusName: 'code' //数据状态的字段名称，默认：code
            , statusCode: 0 //成功的状态码，默认：0
            , msgName: 'msg' //状态信息的字段名称，默认：msg
            , countName: 'count' //数据总数的字段名称，默认：count
            , dataName: 'data' //数据列表的字段名称，默认：data
        }
        , method: 'GET'
        //, data: TableData
        , skin: 'row' //表格风格
        , size: 'sm' //小尺寸的表格
        , cols: [TableCol]
        , page: true //是否显示分页
        , hash: true //
        , limits: [limit]
        , limit: limit //每页默认显示的数量
        , done: function (res, curr, count) {
            //数据渲染完的回调

        }
    });
    return oTable;
}

function ajax(method, url, data, success) {
	var xhr = new XMLHttpRequest();
	if(method == 'get' && data) {
		url += '?' + data;
	}

	xhr.open(method, url, true);
	if(method == 'get') {
		xhr.send();
	} else {
		xhr.setRequestHeader('content-type', 'application/x-www-form-urlencoded');
		xhr.send(data);
	}

	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4) {
			if(xhr.status == 200) {
				success && success(xhr.responseText);
				
			} else {
				alert('出错了,错误代码:' + xhr.status)
			}
		}
	}
}

//弹窗
function tanchuang(adress,width,height,title) {
	var index1=layer.open({
		type: 2,
		title: title,
		shadeClose: true,//点击空白消失
		/*maxmin: true,
		fix: true,*/
		shade: 0.3,
		area: [width, height],
		scrollbar: false,
		move: false,
		resize: false,
		offset: '3px',
		id: 'kuaisu', //设定一个id，防止重复弹出
		content: adress,
        end: function(layero){location.reload()} //iframe的url
	});
};
			
			
			
			
//获取cookies
function getCookie(c_name)
{
if (document.cookie.length>0)
  {
  c_start=document.cookie.indexOf(c_name + "=")
  if (c_start!=-1)
    { 
    c_start=c_start + c_name.length+1 
    c_end=document.cookie.indexOf(";",c_start)
    if (c_end==-1) c_end=document.cookie.length
    return unescape(document.cookie.substring(c_start,c_end))
    } 
  }
return ""
}

//创建cookies
function setCookie(c_name,value,expiredays){
	var exdate=new Date()
	exdate.setDate(exdate.getDate()+expiredays)
	document.cookie=c_name+ "=" +escape(value)+((expiredays==null) ? "" : ";expires="+exdate.toGMTString())
}


//过滤非法字符
function stripscript(s) {
    if (s == null || s == undefined || s == "") {
        rs = s;
        return rs;
    } else {
        var pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]")
        var rs = "";
        for (var i = 0; i < s.length; i++) {
            rs = rs + s.substr(i, 1).replace(pattern, '');
        }
        return rs;
    }
} 

//框架模式下 系统加载/取消 屏蔽层 函数 防止二次提交
function TopLayerLoading(index, Switch) {
    (Switch == true) ? Switch = true : Switch = false;
    if (Switch == false) {
        var index = top.layer.load(1, {
            shade: [0.1, '#000'] //0.1透明度的 白色
        });
        //console.log(index);
        return index;
    } else {
        if (index == null) {
            return false;
        } else {
            top.layer.close(index);
            return true;
        }
    }
}

//根据name,删除一条cookie(设置立即过期)
function delCookie(name) {
    setCookie(name, "", -1);
}

/*function heightcc() {
	var aa = document.body.clientHeight;
}*/

var herf = window.location.host;
var http = window.location.protocol;
var str = window.location.href;
var regUrl = str.replace(http +"//" + herf + "/","");
var ajxaUrl = regUrl.split("/");
var urlHome = ajxaUrl[0];