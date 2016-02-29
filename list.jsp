<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Prog.kiev.ua</title>
    </head>
    <body>
        <div align="center">
            <h1>The list of all photos</h1>
            <c:if test="${onlyList eq 1}">
                <c:forEach items="${viewList}" var="ma">
                    <br/>Photo ID: ${ma}
                    <br/><img src="/spr2/photo/${ma}">
                </c:forEach>
            </c:if>
            <c:if test="${onlyList eq 2}">
                <form action="/spr2/deleteselected" method="POST">
                    <c:forEach items="${viewList}" var="ma">
                        <br/><input type="checkbox" name="photo_id" value="${ma}">${ma}
                    </c:forEach>
                    <br/><input type="submit" value="Delete selected"/>
                </form>
                <input type="submit" value="Return to main" onclick="window.location='/spr2';" />
            </c:if>

        </div>
    </body>
</html>
