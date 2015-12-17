<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>

<head>
  <title>Employee Fares - <c:out value="${employee.username}"></c:out></title>
</head>

<body>

    <h1>Employee Fares for <c:out value="${employee.username}"></c:out></h1>
    
    <table>
        <tbody>
            <tr>
                <th>ID</th>
                <th>Start</th>
                <th>Pickup</th>
                <th>End</th>
                <th>Dropoff</th>
                <th>Fare</th>
                <th>Driver</th>
                <th>Pass Rtg</th>
                <th>Drvr Rtg</th>
            </tr>
        <c:forEach items="${employeeList}" var="fare">
            <tr>
                <td>${fare.id}</td>
                <td><fmt:formatDate value="${fare.start}" pattern="HH:mm:ss yyyy-MM-dd" /></td>
                <td>${fare.pickup}</td>
                <td><fmt:formatDate value="${fare.end}" pattern="HH:mm:ss yyyy-MM-dd" /></td>
                <td>${fare.dropoff}</td>
                <td>$ ${fare.fareInDollars}</td>
                <td>$ ${fare.driverFeeInDollars}</td>
                <td>${fare.passengerRating}</td>
                <td>${fare.driverRating}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</body>

</html>
