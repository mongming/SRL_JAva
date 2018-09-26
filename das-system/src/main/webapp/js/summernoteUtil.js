/**
 * 重新summernote插件的上传图片方法，上传到阿里云，返回阿里云URL
 * @param file
 * @param editor
 * @param $editable
 */
function sendFile(file,editor,$editable) {
    var fileReader = new FileReader();
    fileReader.readAsDataURL(file);
    fileReader.onload = function () {
        $.ajax({
            url : '<%=basePath%>v1/common/uploadImage.do',
            method : 'POST', 
            dataType : 'json',
            data : {image:this.result},
            success : function(obj) {
                if( obj.stateCode == 0 ) {
                    editor.insertImage($editable, obj.data);  
                }else{
                    toastr.info( obj.stateMsg ) ;
                }
            },
            error : function(){
            }
        });
    }; 
}