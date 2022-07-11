<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>使用json格式的数据</title>
    <script type="text/javascript">
        function doSearch() {
            //1、创建异步对象
            var xmlHttp = new XMLHttpRequest();
            //2、绑定事件
            xmlHttp.onreadystatechange = function () {
                if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
                    var data = xmlHttp.responseText;
                    //eval是执行括号中的代码，把json字符串转换成json对象
                    var jsonobj = eval("("+data+")");
                    //更新dom对象
                    callback(jsonobj);

                }
            }
            //3、初始异步对象的参数
            var proid = document.getElementById("proid").value;
            xmlHttp.open("get","queryjson?proid="+proid,true);
            //4、发送请求
            xmlHttp.send();
        }

        //定义函数，处理服务器端返回的数据
        function callback(json) {
            document.getElementById("proname").value = json.name;
            document.getElementById("projancheng").value = json.jiancheng;
            document.getElementById("proshenghui").value = json.shenghui;

        }

    </script>
</head>
<body>
     <p>ajax请求使用json格式的数据</p>
     <table>
         <tr>
             <td>省份编号：</td>
             <td><input type="text" id="proid">
                 <input type="button" value="搜索" onclick="doSearch()">
             </td>
         </tr>
         <tr>
             <td>省份名称：</td>
             <td><input type="text" id="proname"></td>
         </tr>
         <tr>
             <td>省份简称：</td>
             <td><input type="text" id="projancheng"></td>
         </tr>
         <tr>
             <td>省会名：</td>
             <td><input type="text" id="proshenghui"></td>
         </tr>
     </table>
</body>
</html>
