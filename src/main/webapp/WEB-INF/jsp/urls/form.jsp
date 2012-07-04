<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1><fmt:message key="urls.form.title"/></h1>

<c:if test="${not empty statusMessageKey}">
    <p><fmt:message key="${statusMessageKey}"/></p>
</c:if>

<c:url var="currentUrl" value="/urls/form.html" />
<form:form action="${currentUrl}" commandName="urlToCheck">
    <form:hidden path="id" />

    <fieldset>
        <div class="form-row">
            <label for="urlString"><fmt:message key="url.form.name"/>:</label>
            <span class="input"><form:input path="urlString" /></span>
        </div>

        <div class="form-buttons">
            <div class="button"><input name="submit" type="submit" value="<fmt:message key="button.go"/>" /></div>
        </div>
    </fieldset>
</form:form>