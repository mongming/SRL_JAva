<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%	
	//项目名称
	String path=request.getContextPath();
	String basePath=request.getScheme()+"://"
			+request.getServerName()+":"+request.getServerPort()
			+path+"/";
	System.out.println("========"+basePath);
%>
<!DOCTYPE html>
<html>
	<base href="<%=basePath %>"/>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>数据分析管理系统- 登录</title>
    <meta name="keywords" content="数据分析管理系统">
    <meta name="description" content="数据分析管理系统，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="hplus/favicon.ico"> 
    <link href="hplus/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="hplus/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="hplus/css/animate.css" rel="stylesheet">
    <link href="hplus/css/style.css?v=4.1.0" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>

<body class="gray-bg">

    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div>

                <h1 class="logo-name">H+</h1>

            </div>
            <h3>欢迎使用 H+</h3>

            <form name="form1" id="form1" class="m-t" role="form" action="index.html">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="请输入用户名" name="loginName" required="" value="admin">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="请输入密码" name="password" required="" value="1">
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>


                <p class="text-muted text-center"> <a href="login.html#"><small>忘记密码了？</small></a> | <a href="register.html">注册一个新账号</a>
                </p>

            </form>
        </div>
    </div>

    <!-- 全局js -->
    <script src="hplus/js/jquery.min.js?v=2.1.4"></script>
    <script src="hplus/js/bootstrap.min.js?v=3.3.6"></script>
    
     <!-- jQuery Validation plugin javascript-->
    <script src="hplus/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="hplus/js/plugins/validate/messages_zh.min.js"></script>

	<script type="text/javascript">
        $(document).ready(function() {
            // $(".m-t .btn").click(function(event) {
            //     loginSubmit();
            // });
            
            
            //$("#form1").validate();
            // validate signup form on keyup and submit
            var icon = "<i class='fa fa-times-circle'></i> ";
            $("#form1").validate({
                rules: {
                    loginName: "required",
                    password: "required"   
                },
                messages: {
                    loginName: icon+"请输入您的账号",
                    password: icon+"请输入您的密码"
                }
            });
        }); 
       

        $.validator.setDefaults({
            submitHandler: function() {
                loginSubmit();     
            }
        });
        

        function loginSubmit(){
            var formData=$(".m-t").serialize();
            $.ajax({
                url : "<%=basePath%>loginServlet?action=loginServlet&"+formData,
                type : "post",
                dataType:"json", 
                //param :formData,//{userName:"",loginName:""}
                param :null,
                success:function(data){
                    if(data!=null && data.success){
                        window.location.href="<%=basePath%>index_bak.jsp";
                    }else if(data!=null){
                         alert(data.message);
                    }else{
                        alert("登录失败!"+formData);
                    }
                   
                },
                error:function(){
                    alert("登录失败!"+formData+formData);
                }
            });
        }
    </script>
</body>

</html>
