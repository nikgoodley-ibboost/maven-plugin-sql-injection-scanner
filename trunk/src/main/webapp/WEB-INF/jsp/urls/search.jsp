<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1><fmt:message key="urls.form.title"/></h1>
<c:choose>
    <c:when test="not empty ${urls}">

<table class="search">
    <tr>
        <th><fmt:message key="url.form.urlString"/></th>
        <th><fmt:message key="url.form.created"/></th>
        <th><fmt:message key="url.form.actions"/></th>
    </tr>
    <c:forEach var="url" items="${urls}" varStatus="status">
        <tr>
            <c:set var="urlFormId" value="url${status.index}"/>

            <c:url var="editUrl" value="/urls/form.html">
                <c:param name="id" value="${url.id}" />
            </c:url>
            <c:url var="deleteUrl" value="/urls/delete.html"/>

            <form id="${urlFormId}" action="${deleteUrl}" method="POST">
                <input name="id" type="hidden" value="${url.id}"/>
            </form>

            <td><c:choose>
                <c:when test="not empty ${url.created}">
                    ${url.created}
                </c:when>
                <c:otherwise><fmt:message key="unknown"/></c:otherwise>
            </c:choose> </td>
            <td>
                <c:choose>
                    <c:when test="not empty ${url.logFileName}">
                        ${url.logFileName}
                    </c:when>
                    <c:otherwise>---</c:otherwise>
                </c:choose> </td>
            <td>
                <a href='<c:out value="${editUrl}"/>'><fmt:message key="button.showLog"/></a>
                <a href="javascript:document.forms['${urlFormId}'].submit();"><fmt:message key="button.delete"/></a>
            </td>
        </tr>
    </c:forEach>
</table>

    </c:when>
    <c:otherwise>
        <fmt:message key="no.results"/>
    </c:otherwise>
</c:choose>