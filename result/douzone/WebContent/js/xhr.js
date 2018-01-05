	function sendmsg(url, req, data, whenSuccess, whenError) {
		$.ajax({
			type : "post",
			url : url, //".ajax.do"
			data : {
				req : req,
				data : data
			},
			success : whenSuccess,
			error : whenError
		});
	}