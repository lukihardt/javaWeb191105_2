<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>动态添加文件上传列表</title>
<script type="text/javascript">
	var num = 0;
	function addFile(event) {
		//创建一个div标签，用以包含一个input标签和删除按钮
		var innerdiv = document.createElement("div");

		//创建一个input标签
		var inputNode = document.createElement("input");
		inputNode.name = "fileName";
		inputNode.type = "file";

		//创建一个删除按钮
		var delNode = document.createElement("input");
		delNode.name = "del";
		delNode.type = "button";
		delNode.value = "删除";

		var submit = document.getElementById("submit");

		//删除当前删除按钮所在的标签,为此按钮点击事件创建一个处理函数
		delNode.onclick = function d() {
			this.parentNode.parentNode.removeChild(this.parentNode); //删除此div区域
			var fileNodes = document.getElementsByName("fileName");
			//当没有上传文件时，隐藏submit按钮
			if (fileNodes.length == 0) {
				submit.style.display = "none";
			}
		};

		innerdiv.appendChild(inputNode);
		innerdiv.appendChild(delNode);

		var div = document.getElementById("file");
		div.appendChild(innerdiv);

		submit.style.display = "block";

	}
</script>

</head>

<body>
	<form id="upload"
		action="<%= request.getContextPath() %>/UploadServlet"
		enctype="multipart/form-data" method="post">
		上传文件:<input type="button" value="添加文件"
			onclick="addFile(this.parentNode)" /> <br />
		<table>
			<tr>
				<td><div id="file"></div></td>
			</tr>
		</table>
		<input id="submit" type="submit" value="上传" style="display: none" />
	</form>
</body>
</html>