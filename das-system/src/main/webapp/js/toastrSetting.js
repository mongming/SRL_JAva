$(function(){
    //参数设置，若用默认值可以省略以下面代
    toastr.options = {
    	progressBar: true, //进度条
        closeButton: true, //是否显示关闭按钮
        debug: false, //是否使用debug模式
        positionClass: "toast-top-center",//弹出窗的位置
        showDuration: "300",//显示的动画时间
        hideDuration: "1000",//消失的动画时间
        timeOut: "3000", //展现时间
        extendedTimeOut: "1000",//加长展示时间
        showEasing: "swing",//显示时的动画缓冲方式
        hideEasing: "linear",//消失时的动画缓冲方式
        showMethod: "fadeIn",//显示时的动画方式
        hideMethod: "fadeOut" //消失时的动画方式
    };
    
    /**
     * 位置信息，消息弹窗显示的位置，可以显示的位置对应的值

		toast-top-right
		toast-botton-right
		toash-bottom-left
		toast-top-left
		toast-top-full-width 这个是在网页顶端，宽度铺满整个屏幕
		toast-bottom-full-width
		toast-top-center顶端中间
		toast-bottom-center
     */
    /**
    //成功提示绑定
    toastr.success("祝贺你成功了");
     
    //信息提示绑定
    toastr.info("这是一个提示信息");
     
    //敬告提示绑定
    toastr.warning("警告你别来烦我了");
     
    //错语提示绑定
    toastr.error("出现错误，请更改");
     
    //清除窗口绑定
    toastr.clear();
    **/
});