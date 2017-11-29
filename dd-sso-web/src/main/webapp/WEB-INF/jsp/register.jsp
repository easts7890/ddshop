<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache,must-revalidate">
<title>注册-个人用户</title>
<link rel="stylesheet" type="text/css" href="css/headerfooter.css" />
<link rel="stylesheet" type="text/css" href="css/jquery.alerts.css" />
<link rel="stylesheet" type="text/css" href="css/headerfooterindex.css" />

<!-- sweetalert -->
<link rel="stylesheet" type="text/css" href="js/sweetalert/sweetalert.css">
<style>
	.reg_main li label.error{
		color: red;
		text-align: left;
    	padding-left: 15px;
    	width: 200px;
	}
</style>	
		
</head>
<body>
	<!-- header -->
	<div class="header">
		<a href="http://www.ttshop.com"><img src="images/logo.png"
			border="0" /><span>欢迎注册</span></a>
	</div>

	<!--mainStart-->
	<link rel="stylesheet" type="text/css" href="css/reg.css?v20140432" />
	<!-- reg_main -->
	<div class="tabBox clear">
		<span class="reg_hide reg_show regMr5" id="regper">个人用户</span> <span
			class="reg_hide" id="regcom" style="">企业用户</span>
		<div class="login">
			已有账号，立即 <a href="login">登录</a>
		</div>
	</div>
	<!--个人用户-->
	<div class="reg_main reg_border regTab" id="perDiv">
		<ul class="individualUserBg">
			<form id="regForm_mod" name="regForm_mod" method="post">

				<li class="regMb30">
					<label><font>*</font> 账户名：</label> 
					<span class="regM defaultBorder"> 
						<input id="regName" name="username" class="regInput" type="text" maxlength="80" />
					</span> 
				</li>
				<li class="regMb30">
					<label><font>*</font> 登录密码：</label> 
					<span class="regM defaultBorder"> 
						<input id="pwd" name="password" class="regInput" type="password"/>
					</span>
				</li>
				<li class="regMb30">
					<label><font>*</font> 确认密码：</label> 
					<span class="regM defaultBorder"> 
						<input id="pwdRepeat" class="regInput" name="password2" type="password" />
					</span>
				</li>
				<li class="regMb30">
					<label><font>*</font> 验证手机：</label> 
					<span class="regM defaultBorder"> 
						<input id="phone" name="phone" class="regInput" type="text" maxlength="11"/>
					</span>
				</li>

				<li class="regPl88">
					<span class="regM" style="margin-left: 29px"> 
					<input id="AgreeId" name="AgreeId" type="checkbox" checked>
					<a href="https://passport.ttshop.com/xy.html" target="_blank" class="checkTitle">我已阅读并同意<font style="font-size: 12px;">《天天商城用户注册协议》</font></a></span>
				</li>
				<li class="register regPl88 regMt10" id="sub_per" style="margin-left: 29px">
					<button class="registerNow" style="border:0;">立即注册</button>
				</li>
				<br />
				<br />
			</form>
		</ul>
		<div class="ui-reg-tip">
			<a href="http://help.ttshop.com/140/6788.html" target="_blank">了解更多>></a>
		</div>
	</div>

	<!--mainOver-->

	<!--footerStart-->
	<div class="footer">
		<span> <a href="http://www.ttshop.com/www/379/5109.html"
			rel="nofollow" class="footerlink1">关于我们</a> | <a
			href="http://www.ttshop.com/www/380/5116.html" rel="nofollow"
			class="footerlink1">联系我们</a> | <a
			href="http://www.ttshop.com/www/381/5117.html" rel="nofollow"
			class="footerlink1">招聘人才</a> | <a
			href="http://www.ttshop.com/www/330/2705.html" rel="nofollow"
			class="footerlink1">友情链接</a> | <a
			href="http://supplier.ttshop.com/supplierApply" rel="nofollow"
			class="footerlink1">供应商申请</a>
		</span>
		<p>
			北京天天电子商务有限公司<br /> 北京市公安局顺义分局备案11011302000890号|<a
				href="http://www.miibeian.gov.cn" target="_blank" rel="nofollow"
				class="footerlink1">京ICP备12011349号</a>|<a
				href="http://www.ttshop.com/www/174/461.html" target="_blank"
				rel="nofollow" class="footerlink1">企业营业执照</a><br /> Copyright© 天天商城
			ttshop.com 版权所有<br />
		</p>
	</div>
	<!-- /footer -->
	
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<!-- jquery validate -->
	<script type="text/javascript" src="js/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript" src="js/jquery-validation/localization/messages_zh.min.js"></script>
	<script type="text/javascript" src="js/jquery-validation/jquery.form.js"></script>
	
	<!-- sweetalert -->
	<script type="text/javascript" src="js/sweetalert/sweetalert.min.js"></script>

	<script>
	
	//改变默认的错误信息输出位置
	$.validator.setDefaults({
		errorPlacement:function(error,element) {  
			error.appendTo(element.parent().parent());
	   }
	})
	
	// 在键盘按下并释放及提交后验证提交表单
	$("#regForm_mod").validate({
	    rules: {
	    	username: {
	    		required: true,
	    		minlength:6,
	    		remote: 'check/username'
	    	},
	    	password: {
	    		required: true,
	    		minlength:6
	    	},
	    	password2: {
	    		required: true,
	    		equalTo:'#pwd'
	    	},
	    	phone: {
	    		required: true,
	    		maxlength:11,
	    		minlength:11,
	    		digits:true
	    	}
	    } ,
	    messages: {
	    	username: {
	    		required: '请输入用户名',
	    		minlength: '输入至少{0}个字符',
	    		remote: '用户名已存在'
	    	},
	    	password: {
	    		required: '请输入密码',
	    		minlength: '输入至少{0}个字符'
	    	},
	    	password2: {
	    		required: '请再次输入密码',
	    		equalTo: '两次输入不一致，请重新输入'
	    	},
	    	phone: {
	    		required: '请再次输入您的手机号',
	    		maxlength: '手机号码格式不正确',
	    		minlength: '手机号码格式不正确',
	    		digits: '手机号码格式不正确'
	    	}
	    },
	    submitHandler:function(form){
	    	$(form).ajaxSubmit({
	    		type:'post',
	    		url: 'user/register',
		 		success: function(data){
		 			
		 			if(data.success){
		 				swal({
	 					  title: '提示',
	 					  text: data.message,
	 					  confirmButtonText: "确定"
	 					},function(){
	 						window.location.href='login';
		 				});
		 			}else{
		 				swal(data.message);
		 			}
		 		}
	    	});  
        }    
	});
	
	</script>
</body>
</html>
