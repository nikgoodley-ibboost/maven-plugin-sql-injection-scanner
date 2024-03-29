<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1><fmt:message key="urls.form.title"/></h1>

<c:if test="${not empty statusMessageKey}">
    <p><fmt:message key="${statusMessageKey}"/></p>
</c:if>
<c:if test="${not empty errorMessageKey}">
    <span style="color:red"><fmt:message key="${errorMessageKey}"/></span>
</c:if>

<c:url var="currentUrl" value="/urls/form.html" />
<form:form action="${currentUrl}" commandName="urlForm">


    <form:hidden path="id" />

    <fieldset>
        <div class="form-row">

            <label for="urlString"><fmt:message key="url.form.name"/>:</label>
            <span class="input"><form:input path="urlString" /></span>
            <span style="color:red"><form:errors path="urlString"/></span>
        </div>
        <div class="form-row">

            <label for="emailString"><fmt:message key="url.form.email"/>:</label>
            <span class="input"><form:input path="emailString" /></span>
            <span style="color:red"><form:errors path="emailString"/></span>
        </div>

        <div class="form-buttons">
            <div class="button"><input name="submit" type="submit" value="<fmt:message key="button.go"/>" /></div>
        </div>
    </fieldset>
</form:form>