<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="table" uri="/WEB-INF/tld/tariff.tld" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>TariffTable</title>
</head>
<body>

<jsp:useBean id="tariffbean" class="by.stepanovich.xmlparsers.tablebuild.TariffSet" scope="request" />

<table:jspset set="${tariffbean}" />
<br/>
</body>
</html>
