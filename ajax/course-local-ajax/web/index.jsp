
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>局部刷新-ajax</title>
    <script type="text/javascript">
      //使用内存中的异步对象，代替浏览器发起请求。异步对象使用js创建和管理的

      function doAjax() {
        //1、创建异步对象
        var xmlHttp = new XMLHttpRequest();
        //2、绑定事件
        xmlHttp.onreadystatechange = function () {
          //处理服务器端返回的数据，更新至当前页面
          //alert("readyState属性值="+xmlHttp.readyState+"|"+xmlHttp.status)
          if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
            //alert(xmlHttp.responseText);
            var data = xmlHttp.responseText;
            //更新dom对象，更新页面数据
            document.getElementById("mydata").innerText = data;
          }
        }
        //3、初始请求数据

        //获取dom对象的value属性值
        var name = document.getElementById("name").value;
        var w = document.getElementById("w").value;
        var h = document.getElementById("h").value;

        var param = "name="+name+"&w="+w+"&h="+h;
        //alert(param)
        xmlHttp.open("get","bmiAjax?"+param,true)

        //4、发起请求
        xmlHttp.send();
      }


    </script>
  </head>
  <body>
  <p>局部刷新ajax-计算Bmi</p>
       <div>
         <%--这里没有使用form--%>
         姓名：<input type="text" id="name" /><br/>
         体重(公斤)：<input type="text" id="w" /><br/>
         身高(米)：<input type="text" id="h" /><br/>
         <input type="button" value="计算Bmi" onclick="doAjax()"/>
         <br/>
         <br/>
         <div id="mydata">等待加载数据....</div>

       </div>
  </body>
</html>
