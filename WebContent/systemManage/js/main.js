$(function(){
	console.log("ss");
	//创建菜单
	createMenu();
	createTree();
});

//通过Ajax动态添加一级菜单Accordion
function createMenu(){
	console.log("qq");
	//使用Ajax模拟Java后台数据交互方式
	$.ajax({
		url:'userAction.action',
		data:"methodName=getfun",
		type:'post',
		dataType:'json',
		async:true,
		success:function(result){
			console.log("result"+result);
			/*var da=eval("("+result+")");*/
			//循环输出结果对象
			$.each(result,function(index,elem){
				$('#menu').accordion('add',{
					title:elem.fun_name,
					content:'<ul style="margin:10px;" name="'+elem.fun_name+'" class="easyui-tree"></ul>'
				});
			});
		}
	});
}

//通过Accordion的onSelect事件动态绑定Tree组件数据
function createTree(){
	$('#menu').accordion({
		onSelect:function(title, index){
			$("ul[name='"+title+"']").tree({
				url:'userAction.action',
				queryParams:{methodName:'gettree',title:title},
				lines:false,
				//节点选中事件
				onSelect:function(node){
					//console.log($(this).tree("isLeaf",node.target));
					//判断当前选中节点是否是子节点（叶节点）
					if($(this).tree("isLeaf",node.target)){
						//console.log("onSelect:"+JSON.stringify(node));
						//$.messager.alert('消息',node.text,'info');
						createTab(node.text,node.attribute.url)
					}
				}
			});
		}
	});
}

//选中Tree组件中的子节点动态创建Tab页签
function createTab(title,url){
	//如果#tab选项卡中存在title相同的
	if($('#tab').tabs('exists',title)){
		//则选中
		$('#tab').tabs('select',title);
	}else{ //否则，表示#tab选项卡中不存在，则在#tab中创建一个新的tab
		$('#tab').tabs('add',{
			title:title,
			//注：在动态加载Url页面时，请注意JSP页面中不要包括body/html/head/title/meta等标签
			href:url,
			closable:true
		});
	}
}

