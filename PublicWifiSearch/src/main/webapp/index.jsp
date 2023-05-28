<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="./css/home/navigator.css">
    <link rel="stylesheet" href="./css/home/locator.css">
</head>
<body>
<h1><%= "내 주변 와이파이 탐색" %></h1>
<br/>

<div class="navigator">
    <button><%="홈" %></button>
    <button><%="위치 히스토리" %></button>
    <button><%="북마크" %></button>
    <button ><%="공공 와이파이 정보 갱신" %></button>
</div>

<div class="locator">
    <form name="coordinate_X">
        <fieldset>
            x 좌표: <input type="text" name="x_coordinate" value="0.0"/>
        </fieldset>
    </form>
    <form name="coordinate_Y">
            <fieldset>
                y 좌표: <input type="text" name="y_coordinate" value="0.0"/>
            </fieldset>
    </form>

    <button>
        <%="내 위치 찾기" %>
    </button>
</div>

<a href="hello-servlet">Hello Servlet</a>
</body>
</html>