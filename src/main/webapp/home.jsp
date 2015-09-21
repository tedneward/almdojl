<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
  <title>Employee Fares</title>
</head>

<body>

    <h1>Employee Fares: <c:out value="${employee.username}"></c:out></h1>
    
    <table>
        <tbody>
            <tr>
                <th>ID</th>
                <th>Start</th>
                <th>Pickup</th>
                <th>End</th>
                <th>Dropoff</th>
            </tr>
        <c:forEach items="${requestScope.employeeList}" var="fare">
            <tr>
                <td><c:out value="${fare.id}"></c:out></td>
                <td><c:out value="${fare.start}"></c:out></td>
                <td><c:out value="${fare.pickup}"></c:out></td>
                <td><c:out value="${fare.end}"></c:out></td>
                <td><c:out value="${fare.dropoff}"></c:out></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</body>

</html>
