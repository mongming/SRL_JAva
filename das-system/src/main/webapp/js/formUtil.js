/**
 * 根据元素name回填表单里的值
 * @param formId 表单id
 * @param data  json格式的数据
 */
function reSetFormVal(formId,data){
	//遍历表单的input
	var objName="";
	//循环所有的输入框、隐藏域、下拉框
	$.each($("#"+formId).find("input[type='text'],input[type='hidden'],select,textarea"), function(index, obj) {
		objName=$(obj).attr('name');
		$.each(data, function(name, jsonVal) {
			if(objName==name){
				$(obj).val(jsonVal||"");
			}
		});
	});

	//循环所有的单选框、互选框,选择它
	$.each($("#"+formId).find("input[type='radio'],input[type='checkbox']"), function(index, obj) {
		objName=$(obj).attr('name');
		$.each(data, function(name, jsonVal) {
			if(objName==name && $(obj).val()==""+jsonVal){
				$(obj).prop("checked",true);
			}
		});
	});
}

function loadDictOptionToSelect(selId,url,selval) {
	$.ajax({
		url: url,
		type: 'GET',
		dataType: 'json',
		data: null,
	})
	.done(function(data) {
		//返回List<DictOption>
		if(data!=null){
			var html="";
			$.each(data, function(index, val) {
				if(selval!=null && (val.value+"")==(selval+"")){
					html+="<option value='"+val.value+"' selected='selected'>"+val.text+"</option>";
				}else{
					html+="<option value='"+val.value+"'>"+val.text+"</option>";
				}
				
			});
			if(html!=""){
				$("#"+selId).append(html);
			}
		}
	})
	.fail(function() {
	})
	.always(function() {
	});
	
}

//生成树下拉菜单
function loadTreeToSelect(selId,url,selVal) {
    $.ajax({
      url: url,
      type: 'GET',
      dataType: 'json',
      data: null,
    })
    .done(function(data) {
      if(data!=null && data.result!=null){
    	  var html=creatSelectTree(data.result,selVal);
    	  if(html!=""){
    		  $("#"+selId).append(html);
    	  }
      }
    })
    .fail(function() {
    })
    .always(function() {
    });
    
  }

//生成树下拉菜单
//格式：{"id":18,"text":"帮助中心","url":null,"expanded":true,"children":[{"id":84,"text":"理财问题","url":null,"expanded":false,"children":null}
var j="-";//前缀符号，用于显示父子关系，这里可以使用其它符号
function creatSelectTree(d,selVal){
  var optionTree="";
  for(var i=0;i<d.length;i++){
     if(d[i].children!=null && d[i].children.length){//如果有子集
    	 if(selVal!=null && selVal==d[i].id){
    		 optionTree+="<option value='"+d[i].id+"' selected='selected'>"+j+d[i].text+"</option>";
    	 }else{
    		 optionTree+="<option value='"+d[i].id+"'>"+j+d[i].text+"</option>";
    	 }
    	 j="&nbsp;&nbsp;&nbsp;&nbsp;"+j;//前缀符号加一个符号
    	 optionTree+=creatSelectTree(d[i].children,selVal);//递归调用子集
    	 j=j.replace("&nbsp;&nbsp;&nbsp;&nbsp;","");
		//j=j.slice(0,j.length-1);//每次递归结束返回上级时，前缀符号需要减一个符号
     }else{//没有子集直接显示
    	 if(selVal!=null && selVal==d[i].id){
    		 optionTree+="<option value='"+d[i].id+"' selected='selected'>"+j+d[i].text+"</option>";
    	 }else{
    		 optionTree+="<option value='"+d[i].id+"'>"+j+d[i].text+"</option>";
    	 }
    	 
      }
  }
  return optionTree;//返回最终html结果
}

/**
 * summernote文本编辑器图片上传到本地服务器（上传到配置文件applicationContext.p2p.filepath.xml的真实项目路径）
 * @param file
 * @param editor
 * @param $editable
 * @param wd
 * @param hg
 * @returns {Boolean}
 */
function sendFileTServer(file, editor, $editable,wd,hg){  
	 var filename = false;  
	 try{  
	 	filename = file['name']; 
	 } catch(e){filename = false;}  
	 if(!filename){$(".note-alarm").remove();}  

    var fileReader = new FileReader();

    if (/^image\/\w+$/.test(file.type)) {
        fileReader.readAsDataURL(file);
        fileReader.onload = function () {
           $.ajax({
               url : 'summtImageUpload.do',
               method : 'POST', 
               dataType : 'json',
               data : {image:this.result},
               success : function(obj) {
                   if( obj!=null && obj.success) {
                	   var data=obj.result;
                       var $image1 = $('<img>').attr('src', data);
                       getImageWidth(data,function(w,h){
                           //console.log({width:w,height:h});
                           if(wd!=null && wd!="" && !isNaN(wd)){
                               $image1.css('width', wd+"%");
                           }else{
                               if(w>320){
                                   $image1.css('width', "100%");
                               }
                           }
                           if(hg!=null && hg!="" && !isNaN(hg)){
                               $image1.css('height', hg+"%");
                           }
                           
                       });
                       $editable.data('range').insertNode($image1[0]);
                       
                       toastr.info( "上传图片成功!") ; 
                   }else{
                       toastr.info( obj.stateMsg ) ;
                   }
                   //console.log( obj ) ;
               },
               error : function(){
               	toastr.info( "上传图片失败!") ; 
               }
           });
        };
    } else {
        toastr.info("Please choose an image file.");
    }
    return false;
}

/**
 * summernote文本编辑器图片上传
 * @param file
 * @param editor
 * @param $editable
 * @param wd
 * @param hg
 * @returns {Boolean}
 */
function sendFileTOss(file, editor, $editable,wd,hg){  
	 var filename = false;  
	 try{  
	 	filename = file['name']; 
	 } catch(e){filename = false;}  
	 if(!filename){$(".note-alarm").remove();}  

    var fileReader = new FileReader();

    if (/^image\/\w+$/.test(file.type)) {
        fileReader.readAsDataURL(file);
        fileReader.onload = function () {
           $.ajax({
               url : 'app/common/uploadImage.do',
               method : 'POST', 
               dataType : 'json',
               data : {image:this.result},
               success : function(obj) {
                   if( obj.stateCode == 0 ) {
                   	//editor.insertImage($editable, obj.data);
                       /*
                       $('#summernote').summernote('editor.insertImage', obj.data, function ($image) {
                           alert($image.width());
                           $image.css('width', '90%');
                       });
                       editor.insertImage($editable, obj.data);
                       */
                       
                       var $image1 = $('<img>').attr('src', obj.data);
                       getImageWidth(obj.data,function(w,h){
                           //console.log({width:w,height:h});
                           if(wd!=null && wd!="" && !isNaN(wd)){
                               $image1.css('width', wd+"%");
                           }else{
                               if(w>320){
                                   $image1.css('width', "100%");
                               }
                           }
                           if(hg!=null && hg!="" && !isNaN(hg)){
                               $image1.css('height', hg+"%");
                           }
                           
                       });
                       $editable.data('range').insertNode($image1[0]);
                       
                       toastr.info( "上传图片成功!") ; 
                   }else{
                       toastr.info( obj.stateMsg ) ;
                   }
                   //console.log( obj ) ;
               },
               error : function(){
               	toastr.info( "上传图片失败!") ; 
               }
           });
        };
    } else {
        toastr.info("Please choose an image file.");
    }
    return false;
}