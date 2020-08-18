$(".nav").on("click","li",function(){
	$(this).siblings().removeClass("current");
	$(this).siblings().find('li').removeClass("current");
	$(this).parent().parent().siblings().find('li').removeClass("current");
	var hasChild = !!$(this).find(".subnav").size();
	if(hasChild){
		if($(this).hasClass("hasChild")){
			$(this).removeClass("hasChild");
		}else{
			$(".nav > li").removeClass("hasChild");
			$(this).addClass("hasChild");
		}
	}else{
		if(this.id=='first'){
			$(".nav > li").removeClass("hasChild");
		}
	}
	$(this).addClass("current");
});


$(window).resize(function(e) {
    $("#bd").height($(window).height() - $("#hd").height() - $("#ft").height()-6);
	$(".wrap").height($("#bd").height()-3);
	$(".nav").css("minHeight", $(".sidebar").height() - $(".sidebar-header").height()-1);
	$("#iframe").height($(window).height() - $("#hd").height() - $("#ft").height()-12);
}).resize();

//$(".nav>li").css({"borderColor":"#dbe9f1"});
//$(".nav>.current").prev().css({"borderColor":"#7ac47f"});
$(".nav").on("click","li",function(e){
	var aurl = $(this).find("a").attr("date-src");
	$("#iframe").attr("src",aurl);
	//$(".nav>li").css({"borderColor":"#dbe9f1"});
	//$(".nav>.current").prev().css({"borderColor":"#7ac47f"});
	return false;
});

$('.exitDialog').Dialog({
	title:'提示信息',
	autoOpen: false,
	width:400,
	height:200
	
});

$('.exit').click(function(){
	$('.exitDialog').Dialog('open');
});



$('.exitDialog input[type=button]').click(function(e) {
    $('.exitDialog').Dialog('close');
	
		if($(this).hasClass('ok')){
				$.ajax({
				url : 'j_spring_security_logout',
				async : false,
				success : function(data) {
				}
			});
			window.location.href = "manage/login"	;

		}
	});