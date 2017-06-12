<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Admin</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
</head>
<body>
<div >
    <table class="table table-hover">
        <thead class="text-center">
        <td>ID</td>
        <td>URL</td>
        <td>Cooldown, s</td>
        <td>Time OK, ms</td>
        <td>Time WARN, ms</td>
        <td>Code</td>
        <td>Min Response Length, b</td>
        <td>Max Response Length, b</td>
        <td>Substring</td>
        <td>Action</td>
        </thead>
        <tbody class="form-group">
        <c:forEach items="${questions}" var="item">
            <tr>
                <td><input class="form-control" type="number" value="${item.id}" disabled ></td>
                <td><input class="form-control" type="text" value="${item.URL}"></td>
                <td><input class="form-control" type="number" value="${item.cooldown}"></td>
                <td><input class="form-control" type="number" value="${item.responseTimeOK}"></td>
                <td><input class="form-control" type="number" value="${item.responseTimeWARNING}"></td>
                <td><input class="form-control" type="number" value="${item.expectedResponseCode}"></td>
                <td><input class="form-control" type="number" value="${item.minResponseLength}"></td>
                <td><input class="form-control" type="number" value="${item.maxResponseLength}"></td>
                <td><input class="form-control" type="text" value="${item.substring}"></td>
                <td><input class="form-control btn btn-primary" type="button" value="Update"></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>

<script>
    $(document).ready(function () {
        $(":button").click(function () {
            var question = {
                id: "",
                URL: "",
                cooldown: "",
                responseTimeOK: "",
                responseTimeWARNING: "",
                expectedResponseCode: "",
                minResponseLength: "",
                maxResponseLength: "",
                substring: ""
            };

            var cells = [];
            $(this).closest("tr").find("td").each(function (idx, itm) {
                cells.push(itm.childNodes[0].value);
            });

            var index = 0;
            for (key in question) {
                question[key] = cells[index++];
            }

            $.ajax({
                type: "POST",
                url: "admin",
                contentType: "application/json",
                data: JSON.stringify(question),
                success: function(response) {
                    console.log(response);
                }
            });
        });
    });

</script>
</html>
