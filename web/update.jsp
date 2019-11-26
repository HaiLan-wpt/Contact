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
    <title>修改用户</title>

    <link rel='stylesheet' href='css/bootstrap.min.css'/>
    <script src='js/jquery-3.3.1.min.js'></script>
    <script src='js/bootstrap.min.js'></script>
</head>

<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改联系人</h3>
    <form action="update_contact" method="post">
        <input type="hidden"  name="id" value="${contact.id}">
        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name" readonly="readonly" value="${contact.name}"
                   placeholder="请输入姓名"/>
        </div>

        <div class="form-group">
            <label>性别：</label>
            <input type="radio" name="gender" value="m"  ${contact.gender=="m"?"checked":"" } />男
            <input type="radio" name="gender" value="f"  ${contact.gender=="f"?"checked":"" }  />女
        </div>

        <div class="form-group">
            <label for="formatBirthday">生日：</label>
            <input type="text" class="form-control" id="formatBirthday" name="formatBirthday"
                   value="${contact.formatBirthday}" placeholder="请输入生日(yyyy-MM-dd)"/>
        </div>

        <div class="form-group">
            <label for="birthplace">籍贯：</label>
            <select name="birthplace" class="form-control">
                <option value="广州"   ${contact.gender=="广州"?"checked":"" } >广州</option>
                <option value="上海"   ${contact.gender=="上海"?"checked":"" } >上海</option>
                <option value="北京"   ${contact.gender=="北京"?"checked":"" } >北京</option>
            </select>
        </div>

        <div class="form-group">
            <label for="mobile">手机：</label>
            <input type="text" class="form-control" name="mobile" value="${contact.mobile}" placeholder="请输入手机号码"/>
        </div>

        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" class="form-control" name="email" value="${contact.email}" placeholder="请输入邮箱地址"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交"/>
            <input class="btn btn-default" type="reset" value="重置"/>
            <input class="btn btn-default" type="button" value="返回"/>
        </div>
    </form>
</div>
</body>
</html>
