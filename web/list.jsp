<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<% String path = request.getContextPath(); %>
<% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";%>

<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>"/>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>用户信息管理系统</title>

    <link rel='stylesheet' href='css/bootstrap.min.css'/>
    <script src='js/jquery-3.3.1.min.js'></script>
    <script src='js/bootstrap.min.js'></script>

    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>
        function delContact(contactId,currentPage,pageSize) {
            var flag = window.confirm("确定要删除么");
            if (flag) {
                location.href = "delete_contact_info?id=" + contactId+"&currentPage="+currentPage+"&pageSize="+pageSize;
            }
        }

        function changePageSize(pageSize) {
            location.href = "change_page_size?pageSize="+ pageSize;
        }

    </script>
</head>

<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>
    <table border="1" class="table table-bordered table-hover">
        <tr>
            <td colspan="8" align="center"><a class="btn btn-primary" href="add.jsp">添加联系人</a></td>
        </tr>
        <tr class="success">
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>籍贯</th>
            <th>手机</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${contacts}" var="contact" varStatus="status">
            <tr>
                <td>${contact.id}</td>
                <td>${contact.name}</td>
                <td>${contact.gender=='m'?'男':'女'}</td>
                <td>${contact.age}</td>
                <td>${contact.birthplace}</td>
                <td>${contact.mobile}</td>
                <td>${contact.email}</td>
                <td>
                    <a class="btn btn-default btn-sm" href="update_query?id=${contact.id}">修改</a>
                    <a class="btn btn-default btn-sm" href="javascript:;" onclick="delContact(${contact.id},${currentPage},${pageSize})">删除</a>
                </td>
            </tr>
        </c:forEach>

        <tr>
            <td colspan="8" align="center" class="form-inline">
                <c:forEach begin="1" end="${pages}" var="index">
                <a class="btn btn-default ${currentPage==index ? 'btn-success' :''}"  href="change_page_size?currentPage=${index}&pageSize=${pageSize}">${index}</a>
                </c:forEach>

                <select class="form-control" id="form-control" onchange="changePageSize(this.value)">
                    <option value="5"  ${pageSize==5?'selected':''} >5条/页</option>
                    <option value="10" ${pageSize==10?'selected':''} >10条/页</option>
                    <option value="15" ${pageSize==15?'selected':''}>15条/页</option>
                </select>


            </td>
        </tr>
    </table>
</div>
</body>
</html>
