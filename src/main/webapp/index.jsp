<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Monitoring</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <%--TODO Remove styles to separate file--%>
    <style>
        .bordered-top {
            margin-top: 20px;
        }
        .table-bordered {
            border: 1px solid black;
        }
        thead > td {
            font-weight: bold;
        }
    </style>
</head>
<body>
<div>
    <button class="btn btn-primary pull-right bordered-top" id="noSound" type="submit">Sound Off</button>
</div>
<div class="col-md-10 col-md-offset-1 table-bordered bordered-top">
    <table id="table" class="table table-hover table-condensed">
        <thead class="text-center">
        <tr>
            <td>ID</td>
            <td>URL</td>
            <td>Cooldown</td>
            <td>Response Time OK</td>
            <td>Response Time</td>
            <td>Response Time WARNING</td>
            <td>Expected Response Code</td>
            <td>Response Code</td>
            <td>Min Response Length</td>
            <td>Response Length</td>
            <td>Max Response Length</td>
            <td>Substring Entry</td>
            <td>Substring</td>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
</body>


<script>
    var ws = new WebSocket("ws://10.197.113.214:8280/monitor/echo");
    var critical = new Audio("CRITICAL.mp3");
    var warning = new Audio("WARNING.mp3");
    var isCritical = function (json) {
        var crit = false;
        for (var key in json) {
            if (json[key] == 'CRITICAL') {
                crit = true;
                break;
            }
        }
        return crit;
    };
    var isWarning = function (json) {
        var warn = false;
        for (var key in json) {
            if (json[key] == 'WARNING') {
                warn = true;
                break;
            }
        }
        return warn;
    };
    ws.onopen = function (event) {
    };
    ws.onclose = function (event) {
    };
    ws.onmessage = function (event) {
        var json = JSON.parse(event.data);

        if (isCritical(json)) {
            warning.pause();
            critical.play();
        } else if (isWarning(json)) {
            critical.pause();
            warning.play();
        }

        var newRow ="<tr id=" + json.id + ">" +
                "<td>" + json.id + "</td>" +
                "<td>" + json.URL + "</td>" +
                "<td>" + json.cooldown + "</td>" +
                "<td>" + json.responseTimeOK + "</td>" +
                "<td>" + json.responseTime + "</td>" +
                "<td>" + json.responseTimeWARNING + "</td>" +
                "<td>" + json.expectedResponseCode + "</td>" +
                "<td>" + json.responseCode + "</td>" +
                "<td>" + json.minResponseLength + "</td>" +
                "<td>" + json.responseLength + "</td>" +
                "<td>" + json.maxResponseLength + "</td>" +
                "<td>" + json.substringEntry + "</td>" +
                "<td>" + json.substring + "</td>" +
                "</tr>";

        if ($('tr').is("#" + json.id)) {
            $('#' + json.id).replaceWith(newRow);
        } else {
            $('#table > tbody:last').append(newRow);
        }
    };

    ws.onerror = function (event) {
        console.log(event);
    };
    window.onbeforeunload = function () {
        ws.close();
    };
    $(document).ready(function () {
        $(document).delegate("button#noSound", "click", function () {
            if ($("#noSound").text() == "Sound Off") {
                critical.volume = 0;
                warning.volume = 0;
                $("#noSound").text("Sound On")
            } else {
                critical.volume = 1;
                warning.volume = 1;
                $("#noSound").text("Sound Off")
            }
        })
    })
</script>

</html>