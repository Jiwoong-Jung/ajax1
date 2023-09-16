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
    <input type="text" name="memoText" id="id2"/>
    <button id="btn2">입력</button>
  </p>
</label>
<script>

    let btn = document.querySelector("#btn");
    let id = document.querySelector("#memoId");
    let here = document.querySelector("#here");
    let btn2 = document.querySelector("#btn2");
    btn.addEventListener("click", function () {
        let val = id.value;
        console.log(val);
        fetch("/memo/"+val, {
            method: "GET",
        }).then((response) => response.json()).then((data) => {
                console.log(data);
                here.innerHTML = "<p>"+data.mno+"<br/>"+data.memoText+"</p>";
            });
    });
    btn2.addEventListener("click", function () {
        const val = document.querySelector("#id2").value;
        let memo = {
            memoText: val,
        };
        fetch("/input/", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(memo),
        })
            .then((response) => response.json())
            .then((data) => console.log(data));
    });
</script>
</body>
</html>
