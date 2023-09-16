<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2023-09-13
  Time: 오전 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="here">
</div>
<input type="text" id="memoId"/>
<button id="btn">클릭</button>

<label>
  <p>
    <input type="text" name="memoText" />
    <button id="btn2">입력</button>
  </p>
</label>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
    $(document).ready(function () {
        $("#btn").on("click", function () {
            let id = $('#memoId').val();
            $.getJSON('/memo/'+id, function (data) {
                console.log(data);
                //$("#here").text(data);
                let str = "<p>"+data.mno+"<br/>"+data.memoText+"</p>";
                $("#here").html(str);
            });
        });

        $('#btn2').on("click", function () {
            let memo = {
                memoText: $('input[name="memoText"]').val(),
            };
            console.log(memo);

            $.ajax({
                url: '/input/',
                method: 'post',
                data: JSON.stringify(memo),
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                success: function (data) {
                    console.log(data);

                }
            })
        });

    });
</script>
</body>
</html>
