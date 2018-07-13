$(function(){
	console.log("ss");
	//创建菜单
	createMenu();
	createTree();
});

//创建菜单
function createMenu(){
	$.ajax({
		url: 'json/menu_data.json',
		dataType: 'json',
		type: 'post',
		async: true,
		success: function(data){
			//alert(data);
			//遍历json中的对象，index是对象的下标,element表示对象
			$.each(data, function(index, element){
				
				//在分类中添加面板
				$('#menu').accordion('add', {
					id: element.id,
					title: element.title,
					content: '<ul name="'+element.title+'" class="easyui-tree"></ul>',
					iconCls: element.iconCls,
					selected: index
				});
			})
		}
	});
}

//通过Accordion的onSelect事件动态绑定Tree组件数据
function createTree(){
	$('#menu').accordion({
		onSelect:function(title, index){
			$("ul[name='"+title+"']").tree({
				url:'json/tree_data.json',
				lines:true,
				//节点展开事件
				onExpand:function(node){
					console.log("onExpand:"+node);
				},
				//节点折叠事件
				onCollapse:function(node){
					console.log("onCollapse:"+node);
				},
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
function createTab(title,url){   //title=用户管理
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
/*


//创建树菜单
function createTree(){
	$('#menu').accordion({
		//当分类触发选中事件，将选中的分类项的title及index
		onSelect : function(index, title){
			$("ul[name='"+title+"']").tree({
				url: 'json/tree_data.json',
				lines : true,
				onExpand : function(node){
					console.log("onExpand:"+node);
				},
				onCollapse : function(node){
					console.log("onCollapse:"+node);
				},
				onSelect:function(node){
					//判断当前选中节点是否是子节点（叶节点）
					if($(this).tree("isLeaf", node.target)){
						//创建tab标签
						createTab(node.text, node.attribute.url)
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

*/
