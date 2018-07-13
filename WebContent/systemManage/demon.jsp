<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../easyui/themes/default/easyui.css" rel="stylesheet" />
<link href="../easyui/themes/icon.css" rel="stylesheet" />
<link href="css/style.css"" rel="stylesheet" />

<script src="../easyui/jquery.min.js"></script>
<script src="../easyui/jquery.easyui.min.js"></script>
<script src="../easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
<table id="custromList"></table>
<input type="button" value="qwe" onclick="bu()"/>
</body>

<script type="text/javascript">
function bu(){alert("sss")}

        	
         $("#custromList").datagrid({
                fit: true,
                title: '顾客信息表',
                striped: true, //下划线
                url: 'json/boostate.json',
                rownumbers: true,//行数
                border: false, //边框
                columns: [[
             
              {
                  field: 'book_state', title: '顾客姓名', hailgn: 'center', align: 'center', width: 200, editor: { type: "validatebox", options: { required: true } }
              },
              {
                  field: 'text', title: '顾客年龄', hailgn: 'center', align: 'center', width: 200, editor: { type: "numberbox", options: { required: true } },
              },
              {
                  field: 'action', title: '操作', hailgn: 'center', align: 'center', width: 200,
                  formatter: function (value, row, index) {
                      if (row.editing == true) {
                          return "<a href='#' onclick='saveRow(" + index + ")'>确认</a>&nbsp;<a href='#' onclick='reRow(" + index + ")'>取消</a>"
                      }
                      else {
                          return "<a href='#' onclick='editRow(" + index + ")'>编辑</a>&nbsp;<a href='#' onclick='deleteRow(" + index + ")'>删除</a>"
                      }
                  }
              },
                ]],
                onBeforeEdit: function (index, row)
                {
                 row.editing = true;
                $('#custromList').datagrid('refreshRow', index);
                },
                //完成编辑后触发
                onAfterEdit: function (index, row)
                {
                row.editing = false;
                $('#custromList').datagrid('refreshRow', index);
                },
                //取消编辑后触发
                 onCancelEdit:function(index,row)
                 {
                   row.editing = false;
                  $('#custromList').datagrid('refreshRow', index);
                 }
                
            });
       
        function editRow(index) {
            $('#custromList').datagrid('beginEdit', index);//开始编辑行。
        }

		 function saveRow(index){
		            $('#custromList').datagrid('endEdit', index);
		        }
		/* function reRow(index)
		        {
		            $('#custromList').datagrid('cancelEdit', index);
		        } */
            </script>
</html>