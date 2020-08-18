
var tab;
layui.define(['element', 'nprogress', 'form', 'loader', 'tab', 'navbar', 'laytpl'], function(exports) {
    var $ = layui.jquery,
        element = layui.element,
        layer = layui.layer,
        _win = $(window),
        _doc = $(document),
        _body = $('.kit-body'),
        form = layui.form,
        loader = layui.loader,
        navbar = layui.navbar,
    tab = layui.tab
    var herf = window.location.host;
    var http = window.location.protocol;
    var str = window.location.href;
    var regUrl = str.replace(http +"//" + herf + "/","");
    var ajxaUrl = regUrl.split("/");
    var urlHome = ajxaUrl[0]; 
    var app = {
        hello: function(str) {
            layer.alert('Hello ' + (str || 'test'));
        },
        config: {
            type: 'iframe'
        },
        set: function(options) {
            var that = this;
            $.extend(true, that.config, options);
            return that;
        },
        init: function() {
            var that = this,
                _config = that.config;
            if (_config.type === 'spa') {
                navbar.bind(function(data) {
                    spa.render(data.url, function() {
                        console.log('渲染完成..');
                    });
                });
            }

            if (_config.type === 'iframe') {
                tab.set({
                    elem: '#container',
                    onSwitch: function(data) { //选项卡切换时触发
                    },
                    closeBefore: function(data) { //关闭选项卡之前触发
                        return true; //返回true则关闭
                    }
                }).render();
                 navbar.set({
                     remote: {
                         /*url: 'app/templates/manage/datas/navbar2.json'*/	//本地json数据
                    	 url:'/' + urlHome +'/manage/menuInfo'
                     }
                 }).render(function(data) {
                     tab.tabAdd(data);
                     
                 });
            }

            // ripple start
            var addRippleEffect = function(e) {
                // console.log(e);
                layui.stope(e)
                var target = e.target;
                if (target.localName !== 'button' && target.localName !== 'a') return false;
                var rect = target.getBoundingClientRect();
                var ripple = target.querySelector('.ripple');
                if (!ripple) {
                    ripple = document.createElement('span');
                    ripple.className = 'ripple'
                    ripple.style.height = ripple.style.width = Math.max(rect.width, rect.height) + 'px'
                    target.appendChild(ripple);
                }
                ripple.classList.remove('show');
                var top = e.pageY - rect.top - ripple.offsetHeight / 2 - document.body.scrollTop;
                var left = e.pageX - rect.left - ripple.offsetWidth / 2 - document.body.scrollLeft;
                ripple.style.top = top + 'px'
                ripple.style.left = left + 'px'
                ripple.classList.add('show');
                return false;
            }
            document.addEventListener('click', addRippleEffect, false);
            // ripple end
            return that;
        }
    };

    //输出test接口
    exports('app', app);
});