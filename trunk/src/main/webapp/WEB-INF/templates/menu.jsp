<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="side-bar">
    <a href="<c:url value="/"/>">Home</a>
    
    <p><fmt:message key="urls.form.title"/></p>
        <a href="<c:url value="/urls/form.html"/>"><fmt:message key="button.check"/></a>
        <a href="<c:url value="/urls/search.html"/>"><fmt:message key="button.showResults"/></a>
</div>
