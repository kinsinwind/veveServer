<%--
  Created by IntelliJ IDEA.
  User: kinsin
  Date: 2020/3/27
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="srcs/jquery.min.js"></script>
    <title>Java后端WebSocket的Tomcat实现</title>

</head>

<body>
Welcome
<br/><input id="names" type="text"/>

<button id="connect">连接服务器</button>
<br/><input id="text" type="text"/>

<button id="sendBtn">发送消息</button>

<hr/>

<button οnclick="closeWebSocket()">关闭WebSocket连接</button>

<hr/>

<div id="message"></div>

</body>

<script type="text/javascript">
    var websocket = null;
    var names = null;
    //判断当前浏览器是否支持WebSocket


    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        closeWebSocket();
    }

    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        document.getElementById('message').innerHTML += innerHTML + '<br/>';
    }

    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
    }

    //发送消息
    function send(msg) {
        var message = names+",all#@#@#@#"+msg
        websocket.send(message);
    }

    $(document).ready(function () {
        $("#connect").click(function () {
            names = $("#names").val();
            if ('WebSocket' in window) {
                websocket = new WebSocket("ws://192.168.1.102:8080/websockets/websocket/"+names);//url_describe:ws://ip:port/objectName/webSocketName
                alert("连接成功")
            } else {
                alert('当前浏览器 Not support websocket')
            }

            //连接发生错误的回调方法
            websocket.onerror = function () {
                setMessageInnerHTML("WebSocket连接发生错误");
            };

            //连接成功建立的回调方法
            websocket.onopen = function () {
                setMessageInnerHTML("WebSocket连接成功");
            }

            //接收到消息的回调方法
            websocket.onmessage = function (event) {
                setMessageInnerHTML(event.data);
            }

            //连接关闭的回调方法
            websocket.onclose = function () {
                setMessageInnerHTML("WebSocket连接关闭");
            }

        })


        $("#sendBtn").click(function () {
            var message = $("#text").val();
            send(message)
        });
    });
</script>
</html>
