$(function() {
	initDataGrid();
});

// 初始化DataGrid
function initDataGrid() {
	$("#booklist").datagrid({
		
		url:'bookAction.action?methodName=selectBook',
//		queryParams:{methodName:'selectBook'},
		fitColumns:true,
		striped:true,			//斑马线效果
		checkOnSelect:false,	//选中行时复选框无操作
		 rownumbers: true,
		//初始化分页
		pagination:true,	//底部显示分页工具栏
	    pageNumber:1,		//初始化页码，默认第1页
	    pageSize:4,			//初始化每页显示记录条数，注意：该数必须包含在pageList参数中
	    pageList:[4,10,20,30],
//		toolbar:"#search",
		 toolbar: [{
				iconCls: 'icon-add',
				text: '新增书籍',
				handler: function(){
//					bookCategory();
					addbookPopover();
						}
			},'-',{
				iconCls: 'icon-remove',
				text: '批量删除',
				handler: function(){				
					bookDel();
						}
			},'-',{
				iconCls: 'icon-undo',
				text: '批量上架',
				handler: function(){
							alert('上架操作')
						}
			},'-',{
				iconCls: 'icon-redo',
				text: '批量下架',
				handler: function(){
							alert('下架操作')
						}
			}],
		columns:[[
			{field:'book_id',checkbox:true},
			{field:'book_name',title:'书名',width:'23%',editor:{ type: "validatebox",options: { required: true }}},
			{field:'book_author',title:'作者',width:'23%',editor:{ type: "validatebox",options: { required: true }}},
			{field:'book_price',title:'价格',width:'10%',sortable:true,editor:{ type: "numberbox",options: { required: true }}},
			{field:'publishing',title:'出版社',width:'23%',editor:{ type: "validatebox",options: { required: true }}},
			{field:'operation',title:'操作',width:'20%',
						formatter: function(value,row,index){
							var imauploead="";
							var booksta="";
							console.log(row);
							if(row.book_image==0){
								imauploead="<a href='javaScript:void(0);' id="+row.book_id+" onclick='imaup(this)' class='easyui-linkbutton' data-options='iconCls:'\"icon-search\"'>图片上传</a>&nbsp;&nbsp;";
							}else{
								imauploead="<a href='javaScript:void(0);' id="+row.book_id+" onclick='imaup(this)' class='easyui-linkbutton' data-options='iconCls:'\"icon-search\"'>图片更改</a>&nbsp;&nbsp;";
								if(row.book_state==1){
									booksta="<a href='javaScript:void(0);' onclick='bookputaway("+row.book_id+")' class='easyui-linkbutton' data-options='iconCls:'\"icon-search\"'>上架</a>&nbsp;&nbsp;";
								}else{
									booksta="<a href='javaScript:void(0);' onclick='bookputaway("+row.book_id+")' id="+row.book_id+" class='easyui-linkbutton' data-options='iconCls:'\"icon-search\"'>下架</a>";
									if(row.book_state==3){
										booksta="";
									}
									imauploead="";
								}
							}
					  		   if (row.editing == true) {
					  			 return "<a href='#' onclick='saveRow(" + index + ")'>确认</a>&nbsp;<a href='#' onclick='reRow(" + index + ")'>取消</a>"				                      }
		                      else {
		                       return  "<a href='javaScript:void(0);' id="+row.book_id+" onclick='editRow(" + index + ")' class='easyui-linkbutton' data-options='iconCls:'\"icon-search\"'>编辑</a>&nbsp;&nbsp;"+
				                       	"<a href='javaScript:void(0);' id="+row.book_id+" class='easyui-linkbutton' onclick='bookDelone(this)' data-options='iconCls:'\"icon-search\"'>删除</a>&nbsp;&nbsp;" +
				                       	imauploead+booksta;
		                      }
						}
			}
		]],
		
			
		  //触发前触发
		    onBeforeEdit: function (index, row)
		   {
		    row.editing = true;
		   $('#booklist').datagrid('refreshRow', index);
		   },
		   //完成编辑后触发
		   onAfterEdit: function (index, row)
		   {
		   row.editing = false;
		   $('#booklist').datagrid('refreshRow', index);
		   },
		   //取消编辑后触发
		    onCancelEdit:function(index,row)
		    {
		      row.editing = false;
		     $('#booklist').datagrid('refreshRow', index);
		    }
	});


	$('#bookstate').combobox({    					
		url:'json/boostate.json', 
		valueField:'book_state',  
	    textField:'text',
	    width:'100px',
	    panelHeight:'auto',
	    editable:false,
	    limitToList:true,
	    showItemIcon:true,
	    onChange : function(pa){	
	    	console.log("onChange");//上下架下拉框发生改变时
	    	$("#booklist").datagrid({
				url:'bookAction.action?methodName=selectBook',
				queryParams:{searchValue:$("#searchValue").val(),book_state:$("#bookstate").val()}
		});
	    }
	}); 
	
	$('#searchBtn').bind('click', function(){ 
		console.log("searchBtn");//点击搜索时
		$("#booklist").datagrid({
			url:'bookAction.action?methodName=selectBook',
			queryParams:{searchValue:$("#searchValue").val(),book_state:$("#bookstate").val()}
	});

    });
}

function addbookPopover(){	

	$("#popover").dialog({			//增加页面弹出框
		title: '新增书籍',
		width: 400,
		height :360,
		draggable:false,
		resizable:false,
		modal:true,
		href:'addBook.jsp',
			buttons:[{
				iconCls:'icon-add',
				text:'确认添加',
				onClick: function(){
					addBook();
				}
			}],			 
	});	
}

function addBook(){
	$("#addbookform").form('submit',{					//添加书籍到后台
		url:'bookAction.action?methodName=addbook',
		success:function(){
			$("#popover").window('close');  
			$("#booklist").datagrid("reload");		
		}
	});
}

function bookDel(){												//批量删除
	var row = $("#booklist").datagrid("getSelections");			//伤处书籍
	if(row.length!=0){
		$.messager.confirm('确认','您确认想要删除所选记录吗？',function(r){    
		    if (r){    
		    	var bookid = {};//定义一个数组
		    	var ind=0;
		    	$.each(row,function(index,rows){
		    		bookid[index]=rows.book_id;
		    		index=index;
		    		ind=index;
		    	});
		    	$.ajax({
		    		type:"POST",//为post请求
		            url:"bookAction.action?methodName=delbook",//这是我在后台接受数据的文件名
		            data:{book_id:bookid,inde:ind},//将该表单序列化
		            async:false,
		            success:function(){
		            	       	$("#booklist").datagrid("reload");
		    		}
		    	});  
		    } 
		});
	}else{
		$.messager.alert("提示","请选择要删除的项")
	}
}


function bookDelone(obj){
	$.messager.confirm('确认','您确认想要删除本条记录吗？',function(r){    
	    if (r){    
	    	var bookid = {};//定义一个数组
	    	bookid[0]=obj.id;
	    	$.ajax({
	    		type:"POST",//为post请求
	            url:"bookAction.action?methodName=delbook",//这是我在后台接受数据的文件名
	            data:{book_id:bookid,inde:0},//将该表单序列化
	            async:false,
	            success:function(){
	            	       	$("#booklist").datagrid("reload");
	    		}
	    	});  
	    } 
	});
}
function editRow(index){
	
    $('#booklist').datagrid('beginEdit', index);//开始编辑行。
}

function saveRow(index){
	
    $('#booklist').datagrid('endEdit', index);
    var updated = $("#booklist").datagrid('getChanges',"updated");//获取修改的行
    $.ajax({													  //提交数据到后台
		type:"POST",//为post请求
        url:"bookAction.action?methodName=editbook",//这是我在后台接受数据的文件名
        data:book=updated[0],
        async:false,
        success:function(data){
        	console.log(data);
//        	alert(i);
//        	       	$("#booklist").datagrid("reload");
		}
	});  
}
function reRow(index){							//取消编辑
    $('#booklist').datagrid('cancelEdit', index);
}

/**
 * 图片上传
 * @param row
 * @returns
 */
function imaup(row){
	console.log(row.id);
	$("#popover").dialog({			//增加页面弹出框
		title: '图片上传',
		width: 400,
		height :360,
		draggable:false,
		resizable:false,
		modal:true,
		href:'uploadBookImage.jsp',
			buttons:[{
				iconCls:'icon-add',
				text:'确认上传',
				onClick: function(){
					upBookImage(row.id);
				}
			}],			 
	});	

}
function upBookImage(imaid){
	$("#upload_form").form('submit',{					//图片上传到后台
		url:'bookAction.action?methodName=upBookImage&book_id='+imaid,
		success:function(){
			$("#popover").window('close');  
			$("#booklist").datagrid("reload");		
		}
	});
}

function bookputaway(book_id){
	 $.ajax({													  //提交数据到后台
			type:"POST",//为post请求
	        url:"bookAction.action?methodName=bookPutaway&book_id="+book_id,//这是我在后台接受数据的文件名
	        async:false,
	        success:function(data){
	        	$("#booklist").datagrid("reload");
			}
		});  
}

	