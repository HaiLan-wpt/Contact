<%--
  Created by IntelliJ IDEA.
  User: 31939
  Date: 2019/11/25
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="css/bootstrap.min.css">
    <script src="js/jquery-3.3.1.min.js" charset="utf-8"></script>
    <script src="js/bootstrap.min.js"></script>

    <script>

    </script>
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
    <style>
        .stuTable {
            width: 70%;
            margin: 20px auto;
        }
    </style>
    <script>
        $(function () {
            $.get("query_by_pages_redis_json", function (data) {
                $(data).each(function (index, element) {
                    element.gender = element.gender == 'm' ? '男' : '女';
                    $("<tr> <th>" + element.id + "</th> <th>" + element.name + "</th> <th>" + element.gender + "</th> <th>" + element.age + "</th> <th>" + element.birthplace + "</th> <th>" + element.mobile + "</th> </tr>").appendTo($("#stuTable"));
                })

            });
        })
    </script>
</head>
<body>
<table class="stuTable table table-bordered " id="stuTable">
    <caption class="text-center h2">学员列表</caption>

    <tr>
        <th>ID</th>
        <th>姓名</th>
        <th>学号</th>
        <th>性别</th>
        <th>班级</th>
        <th>学科</th>
    </tr>

</table>


</body>
</html>
