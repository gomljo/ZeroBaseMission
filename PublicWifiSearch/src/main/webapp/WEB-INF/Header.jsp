<%--
  Created by IntelliJ IDEA.
  User: gomlj
  Date: 2023-06-11
  Time: 오전 1:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title></title>
</head>
<body>

<div id="navigator">
    <button type="button" id="homeButton" onclick="location.href=`index.jsp`"><%="홈" %></button>
    <button type="button"
            id="history-button"
            onclick=""
    >
      <%="위치 히스토리" %>
    </button>
    <button type="button"
            id="bookMark-button"
<%--            onclick="location.href=`History.jsp`"--%>
            >
      <%="북마크" %>
    </button>
    <button
            type="button"
            id="requestPublicWifiButton"
            onclick=
                    "window.open('JsonRequest', `공공 와이파이 데이터 가져오기 결과`,
                    'width=400,height=150,location=no,status=no,scrollbars=yes')">
        <%="공공 와이파이 정보 갱신" %>
    </button>

</div>


<script type="text/javascript" src="../js/SearchHistory.js"></script>
</body>
</html>
