<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <%--引入样式--%>
    <!--引入EasyUI的样式文件 -->
    <link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.5/themes/bootstrap/easyui.css"/>
    <!-- 图标样式 -->
    <link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.5/themes/icon.css"/>
    <!-- 引入easyui的脚本文件 -->
    <script type="application/javascript" src="/js/jquery-easyui-1.5/jquery.min.js"></script>
    <script type="application/javascript" src="/js/jquery-easyui-1.5/jquery.easyui.min.js"></script>
    <!-- 语言脚本 -->
    <script type="application/javascript" src="/js/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
生日：<input  id="birthday" name="birthday" type= "text" class= "easyui-datebox" required ="required"> </input>

<table class="easyui-datagrid" style="width:400px;height:250px"
       data-options="url:'datagrid_data.json',fitColumns:true,singleSelect:true">
    <%--表头--%>
    <thead>
    <tr>
        <th data-options="field:'code',width:100">编码</th>
        <th data-options="field:'name',width:100">名称</th>
        <th data-options="field:'price',width:100,align:'right'">价格</th>
    </tr>
    </thead>
</table>

<ul id="ttshop"></ul><%--要放在tree的js代码前--%>
<script type="application/javascript">
    $('#ttshop').tree({
        url:'tree_data.json'
    });
</script>
</body>
</html>
