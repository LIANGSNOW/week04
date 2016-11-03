/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(function() {
            if ("WebSocket" in window) {
                var socket = new WebSocket("ws://" + document.location.host + getContectPath() + "notews/all");
                socket.onopen = function() {
                    console.log("connected");
                }

                socket.onclose = function() {
                    console.log("disconnected");
                }

                socket.onmessage = function(evt) {
                    var data = JSON.parse(evt.data);
                    var html = "";
                    html += "<tr>";
                    html += "<td>"+data.title+"</td>";
                    html += "<td>"+data.create_date+"</td>";
                    html += "<td>"+data.category+"</td>";
                    html += "<td>"+data.content+"</td>";
                    html += "</tr>";
                    $("#notes > tbody").before(html);
//                    alert(html);
                }
            }
        });
        function getContectPath() {
            var path = document.location.pathname;
            var contextPath = path;
            if (path.indexOf("face") > 0) {
                contextPath = path.substring(0, path.indexOf("face"));
            }
            return contextPath;
        }