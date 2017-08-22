window.sessionStorage.removeItem("ngStorage-CurrentLevel");

function login() {
	showLoadPanel();
	$.ajax({
		type : "POST",
		url : ctx + "/loginAuthentication/identityAuthentication",
		data : {
			userName : $("#userName").val(),
			password : $("#password").val()
		},
		dataType : "json",
		async : false,
		success : function(result) {
			if (result.flag=='OK') {
				suc();
			} else if(result.flag=='USERNAME_ERROR'){
				hideLoadPanel(function(){
					$.notify('<i class=\"fa fa-lg fa-exclamation-triangle fload-l mt5\"></i> 用户名错误，请重新输入',
						{status: 'warning'});
				});
			} else if(result.flag=='PASSWORD_ERROR'){
				hideLoadPanel(function(){
					$.notify('<i class=\"fa fa-lg fa-exclamation-triangle fload-l mt5\"></i> 密码错误，请重新输入',
						{status: 'warning'});
				});
			}else if(result.flag=='CS_DEL_ERROR'){
				hideLoadPanel(function(){
					$.notify('<i class=\"fa fa-lg fa-exclamation-triangle fload-l mt5\"></i> 此账号已被删除，请重新输入',
						{status: 'warning'});
				});
			}else if(result.flag=='STATUS_ERROR'){
				hideLoadPanel(function(){
					$.notify('<i class=\"fa fa-lg fa-exclamation-triangle fload-l mt5\"></i> 此账号已被停用，请重新输入',
						{status: 'warning'});
				});
			}
			else {
				// alert('登录失败，请重新登录');
				hideLoadPanel(function(){
					$.notify('<i class=\"fa fa-lg fa-exclamation-triangle fload-l mt5\"></i> 登录失败，请重新登录',
							{status: 'warning'});
				});
				// wrong();
			}
		},
		error : function(result) {
			console.log("result==",result);
			// wrong();
			hideLoadPanel(function(){
				$.notify('<i class=\"fa fa-lg fa-exclamation-triangle fload-l mt5\"></i> 登录失败，请重新登录',
						{status: 'warning'});
			});
			//alert('登录失败，请重新登录');

		}
	});
}

function showLoadPanel(){
	$("#loadPanel").show();
	$('#loadPanel').removeClass().addClass('fadeIn animated');
}

function hideLoadPanel(loaded){
	setTimeout(function(){
		$('#loadPanel').removeClass().addClass('fadeOut animated').one('animationend', function(){
			$("#loadPanel").hide();
			if(loaded){
				loaded();
			}
		});
	},500)
}

function success() {
	var ui = document.getElementById("suc");
	ui.style.display = "block";
}

function wrong() {
	var ui = document.getElementById("wor");
	ui.style.display = "block";
}

function suc() {
	$.get(ctx + '/loginAuthentication/getContextPath').success(function(result){
		window.location.href = result.data;
	})
	return;
	//window.location.href = ctx;
	//if(window.event){
	//	window.event.returnValue=false;
	//}
}
function wor() {
	var ui = document.getElementById("wor");
	ui.style.display = "none";
}
function removeUserName() {
	$('#userName').val('');
}
function removePassword() {
	$('#password').val('');
}