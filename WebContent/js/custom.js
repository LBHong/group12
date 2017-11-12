var speciald=new Array();
speciald=["2016/12/5","2016/12/9","2017/11/6"];//此处为添加的特殊日期，也可以都设置为yyyy-mm-dd
$('#calendar').datepicker({
	beforeShowDay:function(date){
					                var d=date;
					                var curr_date=d.getDate();
					                var curr_month=d.getMonth()+1;
					                var curr_year=d.getFullYear();
					                var formatDate=curr_year+"/"+curr_month+"/"+curr_date;
					                //特殊日期的匹配
					                if($.inArray(formatDate,speciald)!=-1){
					                    return {classes:'specialdays'};
    }
					                return;
	 }    
});
$('#calendar').datepicker({
						  onSelect: gotoDate,
						  buttonImage: '/images/2.jpg'
							}).on('changeDate',gotoDate);

function gotoDate(ev){
		window.location.href="login.jsp";
}
!function ($) {
    $(document).on("click","ul.nav li.parent > a ", function(){          
        $(this).find('em').toggleClass("fa-minus");      
    }); 
    $(".sidebar span.icon").find('em:first').addClass("fa-plus");
}

(window.jQuery);
	$(window).on('resize', function () {
  if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
})
$(window).on('resize', function () {
  if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
})

$(document).on('click', '.panel-heading span.clickable', function(e){
    var $this = $(this);
	if(!$this.hasClass('panel-collapsed')) {
		$this.parents('.panel').find('.panel-body').slideUp();
		$this.addClass('panel-collapsed');
		$this.find('em').removeClass('fa-toggle-up').addClass('fa-toggle-down');
	} else {
		$this.parents('.panel').find('.panel-body').slideDown();
		$this.removeClass('panel-collapsed');
		$this.find('em').removeClass('fa-toggle-down').addClass('fa-toggle-up');
	}
})