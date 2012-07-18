<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div align="right">
    <div>
        <fmt:message key="button.locale"/>:
            <c:url var="englishLocaleUrl" value="/index.html">
                <c:param name="locale" value="en" />
            </c:url>
            <c:url var="russianLocaleUrl" value="/index.html">
                <c:param name="locale" value="ru" />
            </c:url>
             <c:url var="germanLocaleUrl" value="/index.html">
                <c:param name="locale" value="de" />
            </c:url>
            <a href='<c:out value="${germanLocaleUrl}"/>'><fmt:message key="locale.german"/></a>
            <a href='<c:out value="${englishLocaleUrl}"/>'><fmt:message key="locale.english"/></a>
            <a href='<c:out value="${russianLocaleUrl}"/>'><fmt:message key="locale.russian"/></a>

    </div>
    
    <div>&nbsp;</div>
    
    <div><fmt:message key="site.footer"/></div>
</div>