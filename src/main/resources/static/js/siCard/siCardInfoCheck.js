$(function(){
	$("#check").click(function() {
		var idNo = $("#idNo").val();
		var name =$("#name").val();
		var siCardNo =$("#siCardNo").val();
		var data={"name":idNo,"idNo":name,"siCardNo":siCardNo};
		$.ajax({
				type: 'post',
				url: '/jsp/sicardInfoCheck.do',
				contentType:'application/json',
	    		dataType:'json',
	    		 data:JSON.stringify(data),
					success:function(data) {
						console.log(data);
					}
			});
		});
})

 