<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@include file="Header.jsp"%>--%>
<%!
    private static final String coordinateX = "coordinateX";
    private static final String coordinateY = "coordinateY";
    private double currentX;
    private double currentY;
    private String nearestPublicWifi;
    private String nearestPublicWifiFeature;
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
    <link href="../css/home/navigator.css?after" rel="stylesheet" >
    <link href="../css/home/locator.css?after" rel="stylesheet" >

</head>
<body>
<h1><%= "내 주변 와이파이 탐색" %></h1>
<br/>

<div id="navigator">
    <button type="button" id="homeButton" onclick="location.href=`index.jsp`"><%="홈" %></button>
    <button type="button"
            id="history-button"
            onclick="location.href=`History.jsp`"
    >
        <%="위치 히스토리" %>
    </button>
    <button type="button"
            id="bookMark-button"

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
<%
    mappingRequestParameter(request);
    if (request.getAttribute("NearestTwentyPublicWifiFeatures")!= null && nearestPublicWifiFeature==null){
        nearestPublicWifiFeature = request.getAttribute("NearestTwentyPublicWifiFeatures").toString();
    }
    if(request.getAttribute("NearestTwentyPublicWifi")!=null){
        nearestPublicWifi = request.getAttribute("NearestTwentyPublicWifi").toString();
        System.out.println(nearestPublicWifi);
    }
%>

<%--<h1><%= "내 주변 와이파이 탐색" %></h1>--%>
<%--<br/>--%>

<%--<div id="navigator">--%>
<%--    <button type="button" id="homeButton" onclick="`/`"><%="홈" %></button>--%>
<%--    <button type="button" id="history-button"><%="위치 히스토리" %></button>--%>
<%--    <button type="button" id="bookMark-button"><%="북마크" %></button>--%>
<%--    <button--%>
<%--            type="button"--%>
<%--            id="requestPublicWifiButton"--%>
<%--            onclick=--%>
<%--                    "window.open('JsonRequest', `공공 와이파이 데이터 가져오기 결과`,--%>
<%--                    'width=400,height=150,location=no,status=no,scrollbars=yes')">--%>
<%--        <%="공공 와이파이 정보 갱신" %>--%>
<%--    </button>--%>

<%--</div>--%>

<div id="coordinateViewer">
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

<div id="nearestPublicWifiTable"></div>
<script type="text/javascript" src="../js/findMyCoordinate.js"></script>
<script type="text/javascript" src="../js/findNearestPublicWifi.js"></script>
<script type="text/javascript" src="../js/NearestWifiTableMaker.js"></script>
<script>

    renderTable(<%= nearestPublicWifi %>, <%= nearestPublicWifiFeature%>)

</script>
</body>

</html>