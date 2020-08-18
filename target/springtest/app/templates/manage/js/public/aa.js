/**
 * Created by lwkj on 2018/6/29.
 */
//系统加载/取消 屏蔽层 函数 防止二次提交
function LayerLoading(index, Switch) {
    (Switch == true) ? Switch = true : Switch = false;
    if (Switch == false) {
        var index = layer.load(1, {
            shade: [0.1, '#000'] //0.1透明度的 白色
        });
        return index;
    } else {
        if (index == null) {
            return false;
        } else {
            layer.close(index);
            return true;
        }
    }
}

//过滤非法字符
function stripscript(s) {
    if (s == null || s == undefined || s == "") {
        rs = s;
        return rs;
    } else {
        var pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
        var rs = "";
        for (var i = 0; i < s.length; i++) {
            rs = rs + s.substr(i, 1).replace(pattern, '');
        }
        return rs;
    }
}

//选择当前行
function Table_checkCell(dataindex) {
    //layui的table单击行勾选checkbox功能
    $(document).on("click", ".layui-table-body table.layui-table tbody tr", function () {
        var index = $(this).attr('data-index');
        var tableBox = $(this).parents('.layui-table-box');
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
    });

    $(document).on("click", "td div.laytable-cell-checkbox div.layui-form-checkbox", function (e) {
        e.stopPropagation();
    });

    /*
     实现原理：找到table的div绑定单击事件到表格的行：
     1、取得行的索引data-index，为后面查找checkbox的控件作准备
     2、根据是否有固定列查找checkbox所在的表格table（当存在固定列时，固定列是另一个table，checkbox控件就在这上面，因此要优先取这个）
     3、通过table和data-index查找checkbox控件”td div.laytable-cell-checkbox div.layui-form-checkbox I”，如果存在，则执行单击
     4、对td的单击事件进行拦截停止，防止事件冒泡再次触发上述的单击事件
     5、将此代码在页面初始化后执行一次即可以。
     */
}

//建立表格
//var layerloginindex=0 ;

function NewTable(TableId, TableCol, url, height, limit, funcName) {
    /*
     TableId    表格ID
     ,TableCol  表头数组
     ,url       表内容数据来源
     ,height    表格高度
     ,limit     每页记录数
     ,funcName  表格的双击处理事件,传入按钮ID.click事件
     */
    var oTable = layui.table.render({
        elem: '#' + TableId
        , height: height
        //,width:'600'
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
        , size: 'sm' //sm小尺寸的表格 lg
        , cols: [TableCol]
        , page: true //是否显示分页
        , hash: true //
        , limits: [limit]
        , limit: limit //每页默认显示的数量
        , done: function (res, curr, count) {
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
//获得当前时间是年份和月份，形如：201208
// 获取完整的日期
/**
 * 注意，year.toString()+month.toString()不能写成year+month。
 * 不然如果月份大于等于10，则月份为数字，会和年份相加，
 * 如201210，则会变为2022，需要加.toString()*
 * */
function get_date(t_lb, t_date) {
    var f_date = t_date;
    var f_year = f_date.getFullYear();
    var f_month = f_date.getMonth() + 1;
    var f_day = f_date.getDay() + 1;

    f_month = (f_month < 10 ? "0" + f_month : f_month);
    f_day = (f_day < 10 ? "0" + f_day : f_day);

    var mydate = '';
    //类别为 1 返回 201701 格式
    if (t_lb == '1') {
        mydate = (f_year.toString() + f_month.toString());
        return mydate;
    } else if (t_lb = '2') { //返回 20170306 格式
        mydate = (f_year.toString() + f_month.toString() + f_day.toString());
        return mydate;
    } else { //返回 2017-01-02 格式
        mydate = (f_year.toString() + '-' + f_month.toString() + '-' + f_day.toString());
        return mydate;
    }
}
//将日期型参数格式化为 年月字符串 : 201809
function get_date_YM(date) {
    //n_n=date.indexOf("-");
    //c_year=
    date_arr = date.split("-");
    c_out_str = "";
    c_out_str = date_arr[0].toString();

    if (Number(date_arr[1]) < 10) {
        c_out_str = c_out_str + "0" + Number(date_arr[1]).toString();
    } else {
        c_out_str = c_out_str + Number(date_arr[1]).toString();
    }
    return c_out_str;
}

// 框架模式下 系统加载/取消 屏蔽层 函数 防止二次提交
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

//树状 DIV 动态调整高度
function treeDIVresize() {
    //取数据表格高度
    var gridHight = $(".layui-form.layui-border-box.layui-table-view").height();     //获取：表格高度
    //console.log(gridHight);
    //定义动态调整树状DIV高度
    $('#tree').height(gridHight + 12);
    $('#tree .layui-elem-field').height(gridHight + 10);
    $('#tree .layui-field-box').height(gridHight - 10);
    $('#tree_div').height(gridHight - 18);
}
//
function newTree(treeId, treeUrl, otherParamArr, TreeOnClick) {
    //var zTreeObj;
    var setting = {
        async: {
            enable: true,
            url: treeUrl,        //Ajax 获取数据的 URL 地址
            type: "get",                        //Ajax 的 http 请求模式
            //contentType: "application/json",    //Ajax 提交参数的数据类型
            contentType: "application/x-www-form-urlencoded",    //Ajax 提交参数的数据类型
            dataType: "text",                   //Ajax 获取的数据类型
            autoParam: ["id"],                   //异步加载时需要自动提交父节点属性的参数。
            otherParam: otherParamArr,   //Ajax 请求提交的静态参数键值对
            dataFilter: filter                  //用于对 Ajax 返回数据进行预处理的函数。

        },
        view: {
            dblClickExpand: false,              //关闭双击事件
            autoCancelSelected: false           //点击节点时，按下 Ctrl 或 Cmd 键是否允许取消选择操作。如果不需要此功能，请设置为 false。
        },
        callback: {
            onClick: TreeOnClick
        }
    };
    $.fn.zTree.init($("#" + treeId), setting);
}
function filter(treeId, parentNode, childNodes) {
    if (!childNodes) return null;
    //for (var i=0, l=childNodes.length; i<l; i++) {
    //    childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
    //}
    return childNodes;
}

function FormVerify(_form){
    //required（必填项）
    //phone（手机号）
    //email（邮箱）
    //url（网址）
    //number（数字）
    //date（日期）
    //identity（身份证）
    //自定义值

    _form.verify({
        minone:function(value){
            if(value.length < 1){
                return '输入不得小于1个字符';
            }
        }
        ,mintwo:function(value){
            if(value.length < 2){
                return '输入不得小于2个字符';
            }
        }
        ,maxtwo: function(value){
            if(value.length > 2){
                return '输入不得大于2个字符';
            }
        }
        ,maxone: function(value){
            if(value.length > 1){
                return '输入不得大于1个字符';
            }
        }
        ,phone: [/^1[3|4|5|7|8]\d{9}$/, '手机必须11位，只能是数字！']
        ,email: [/^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$|^1[3|4|5|7|8]\d{9}$/, '邮箱格式不对']
        ,validateMoney: function (value) {
            var result = validateMoney(value);
            if (result != "Y") {
                return result;
            }
        }
        ,username: function(value, item){ //value：表单的值、item：表单的DOM对象
            if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
                return '用户名不能有特殊字符';
            }
            if(/(^\_)|(\__)|(\_+$)/.test(value)){
                return '用户名首尾不能出现下划线\'_\'';
            }
            if(/^\d+\d+\d$/.test(value)){
                return '用户名不能全为数字';
            }
        }
        ,pass: [/^[\S]{6,12}$/,'密码必须6到12位，且不能出现空格']
    });
    /**
     * 金额校验
     * @param money
     * @returns {*}
     */
    function validateMoney(money) {
        var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
        if (reg.test(money)) {
            return "Y";
        }
        return "请输入正确的金额,且最多两位小数!";
    }
};



//layui ztree 下拉树
//id div 的id，isMultiple 是否多选，chkboxType 多选框类型{"Y": "ps", "N": "s"} 详细请看ztree官网
function initSelectTree(_$,id,treeUrl,otherParamArr,isMultiple,chkboxType) {
    var setting = {
        async: {
            enable: true,
            url: treeUrl,        //Ajax 获取数据的 URL 地址
            type: "get",                        //Ajax 的 http 请求模式
            //contentType: "application/json",    //Ajax 提交参数的数据类型
            contentType: "application/x-www-form-urlencoded",    //Ajax 提交参数的数据类型
            dataType: "text",                   //Ajax 获取的数据类型
            autoParam: ["id"],                   //异步加载时需要自动提交父节点属性的参数。
            otherParam: otherParamArr,   //Ajax 请求提交的静态参数键值对
            dataFilter: filter                  //用于对 Ajax 返回数据进行预处理的函数。
        },view: {
            dblClickExpand: false,
            showLine: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        check: {
            enable: false,
            chkboxType: {"Y": "ps", "N": "s"}
        },
        callback: {
            beforeClick:beforeClick,
            onClick: onClick,
            onCheck: onCheck
        }

    };
    if (isMultiple) {
        setting.check.enable = isMultiple;
    }
    if (chkboxType !== undefined && chkboxType != null) {
        setting.check.chkboxType = chkboxType;
    }
    var html = '<div class = "layui-select-title" >' +
        '<input id="' + id + 'Show"' + 'type = "text" placeholder = "请选择" value = "" class = "layui-input" readonly>' +
        '<i class= "layui-edge" ></i>' +
        '</div>';
    _$("#" + id).append(html);
    _$("#" + id).parent().append('<div class="tree-content scrollbar" hidden="">' +
        '<input hidden id="' + id + 'Hide" ' +
        'name="' + _$(".select-tree").attr("id") + '">' +
        '<ul id="' + id + 'Tree" class="ztree scrollbar" style="margin-top: 0px; -moz-user-select: none;position: absolute;z-index: 99999;height: 150px;width: 178px;overflow: auto;border: 1px solid #e6e6e6;background: #fff;"</ul>' +
        '</div>');
    _$("#" + id).bind("click", function () {
        if (_$(this).parent().find(".tree-content").css("display") !== "none") {
            hideMenu()
        } else {
            _$(this).addClass("layui-form-selected");
            var Offset = _$(this).offset();
            var width = _$(this).width() - 2;
            _$(this).parent().find(".tree-content").css({
                left: Offset.left + "px",
                top: Offset.top + _$(this).height() + "px"
            }).slideDown("fast");
            _$(this).parent().find(".tree-content").css({
                width: width
            });
            _$("body").bind("mousedown", onBodyDown);
        }
    });
    _$.fn.zTree.init(_$("#" + id + "Tree"), setting);

    function beforeClick(treeId, treeNode, clickFlag) {
        if (treeNode.id=="ROOT"){
            //alert("ddddd...");
            _$("#" + treeId + "Show").attr("value", "");
            _$("#" + treeId + "Show").attr("title", "");
            _$("#" + treeId + "Hide").attr("value", "");
            return false;
        }else{
            //var check = (treeNode && !treeNode.isParent);
            //if (!check) alert("只能选择城市...");
            //return check;
        }
    }

    function onClick(event, treeId, treeNode) {
        var zTree = _$.fn.zTree.getZTreeObj(treeId);
        if (zTree.setting.check.enable == true) {
            //treeObj.checkNode(nodes[i], true, true);
            zTree.checkNode(treeNode, !treeNode.checked, false);
            assignment(treeId, zTree.getCheckedNodes());
        } else {
            assignment(treeId, zTree.getSelectedNodes());
            hideMenu();
        }
    }

    function onCheck(event, treeId, treeNode) {
        var zTree = _$.fn.zTree.getZTreeObj(treeId);
        assignment(treeId, zTree.getCheckedNodes());
    }

    function hideMenu() {
        _$(".select-tree").removeClass("layui-form-selected");
        _$(".tree-content").fadeOut("fast");
        _$("body").unbind("mousedown", onBodyDown);
    }

    function assignment(treeId, nodes) {
        var names = "";
        var ids = "";
        for (var i = 0, l = nodes.length; i < l; i++) {
            names += nodes[i].name + ",";
            ids += nodes[i].id + ",";
        }
        if (names.length > 0) {
            names = names.substring(0, names.length - 1);
            ids = ids.substring(0, ids.length - 1);
        }
        treeId = treeId.substring(0, treeId.length - 4);
        _$("#" + treeId + "Show").attr("value", names);
        _$("#" + treeId + "Show").attr("title", names);
        _$("#" + treeId + "Hide").attr("value", ids);
    }

    function onBodyDown(event) {
        if (_$(event.target).parents(".tree-content").html() == null) {
            hideMenu();
        }
    }
}

//表格操作,批量提交操作,用于批量删除提交
//支持表格一页记录多选

//@_Table      要处理的TABLE 对像
//@_indexID    要提交表格的表格记次索引ID
//@_Url        提交后台处理地址
//@_GridID     要处理的表格ID
//@_promptMsg  要删除的记录提示字符串

function GridDeleteSubmit (_Table,_indexID,_Url,_GridID,_promptMsg){
    var checkStatus = _Table.checkStatus(_GridID);
    if (checkStatus.data.length == 0) {
        top.layer.msg('请选择要删除的'+_promptMsg+'记录!', {
            icon: 5,
            time: 3000 //3秒关闭（如果不配置，默认是3秒）
        });
    }else {
        top.layer.confirm('确认要删除选择的'+_promptMsg+'记录?', {icon: 3, title:'确认信息'}, function(index){
            top.layer.close(index);
            var idlist=new Array();
            //关闭弹层后 执行操作
            top.layer.close(index);
            $.each(checkStatus.data,function(index,value){
                //alert(index+"..."+value['bmbm']);
                idlist.push(value[_indexID]);
            });
            var strID=idlist.join(",");
            //开启屏蔽层,防止二次提交
            var layerloginindex=TopLayerLoading (0,false);
            $.ajax({
                url:_Url,
                type:"post",
                data:{
                    id:strID
                },
                success:function(data){
                    //console.log(data);
                    if ($.parseJSON(data).success == true) {
                        //提交成功处理
                        //取消屏蔽层
                        TopLayerLoading (layerloginindex,true);
                        //提示处理结果信息
                        top.layer.alert($.parseJSON(data).msg, {title:'提示信息'});
                        //表格重载
                        //执行重载
                        $(".layui-laypage-btn")[0].click();	//刷新当前页
                    }else{
                        //取消屏蔽层
                        TopLayerLoading (layerloginindex,true);
                        //提交失败处理
                        top.layer.alert($.parseJSON(data).msg, {title:'错误信息',area: ['400px', '300px']});
                    }
                }
            });
        });
    }
}

//弹窗
function tanchuang(adress,width,height,title) {
	layer.open({
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
//geturl ?
function GetRequest() {
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if(url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for(var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}