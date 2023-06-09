<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%!
    private static final String coordinateX = "coordinateX";
    private static final String coordinateY = "coordinateY";
    private double currentX;
    private double currentY;
    public void mappingRequestParameter(HttpServletRequest request){
        if(request.getParameter(coordinateX)!=null && request.getParameter(coordinateY)!=null){
            currentX = Double.parseDouble(request.getParameter(coordinateX));
            currentY = Double.parseDouble(request.getParameter(coordinateY));
        }
        else {
            currentX = 0.0;
            currentY = 0.0;
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>서울에서 내 주변 와이파이 찾기 서비스</title>
    <link rel="stylesheet" href="../css/home/navigator.css">
    <link rel="stylesheet" href="../css/home/locator.css">
</head>
<body>
<h1><%= "내 주변 와이파이 탐색" %></h1>
<br/>

<div class="navigator">
    <button type="button" id="homeButton" onclick="`/`"><%="홈" %></button>
    <button type="button" id="history-button"><%="위치 히스토리" %></button>
    <button type="button" id="bookMark-button"><%="북마크" %></button>
    <button
            type="button"
            id="requestPublicWifiButton"
            onclick=
                    "window.open('JsonRequest', `공공 와이파이 데이터 가져오기 결과`,
                    'width=400,height=150,location=no,status=no,scrollbars=yes')">
        <%="공공 와이파이 정보 갱신" %>
    </button>

</div>
<%
    System.out.println(request.getAttribute("coordinateX"));
    System.out.println(request.getAttribute("coordinateY"));
    System.out.println(request.getAttribute("searchTime"));
    mappingRequestParameter(request);

%>
<div class="locator" id="coordinate">
    <form id="coordinateForm">
        <label for="coordinateX">x 좌표: </label>
        <input id="coordinateX"
               type="text"
               name="coordinateX"
               value="<%= currentX %>"
        />
        <label for="coordinateY">y 좌표:</label>
        <input id="coordinateY"
               type="text"
               name="coordinateY"
               value="<%= currentY %>"
        />
        <label for="searchTime"></label>
        <input id="searchTime"
               type="hidden"
               name="searchTime"
        />
        <button type="button"  id="findMyCoordinateButton">
            <%="내 위치 찾기"%>
        </button>
        <button type="button" id="findNearByWifi">
            <%="근처 와이파이 찾기"%>
        </button>
    </form>
</div>

<div id="nearestPublicWifiTable">
    <table>
        <tr>

        </tr>

    </table>

</div>

</body>
<script type="text/javascript" src="../js/findMyCoordinate.js"></script>
<script type="text/javascript" src="../js/findNearestPublicWifi.js"></script>
</html>