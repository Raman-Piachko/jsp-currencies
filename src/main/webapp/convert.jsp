<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*, com.epam.rd.jsp.currencies.CurrenciesOfCurrentTestCase" %>

<jsp:useBean id="currencies" class="com.epam.rd.jsp.currencies.CurrenciesOfCurrentTestCase" scope="request"/>
<html>
<c:set var="from" value="from" scope="request"/>
<c:set var="to" value="to" scope="request"/>
<c:set var="fromCurrency" value="${param.get(from)}" scope="request"/>
<c:set var="toCurrency" value="${param.get(to)}" scope="request"/>
<c:set var="amount" value="amount" scope="request"/>
<c:set var="amountValue" value="${param.get(amount)}" scope="request"/>
<c:set var="result" value="${currencies.convert(amountValue,fromCurrency,toCurrency)}"/>

<head><title>Converting ${fromCurrency} to ${toCurrency}</title></head>
<body>
<h1>Converting ${fromCurrency} to ${toCurrency}</h1>
Conversion amount of 1000 ${fromCurrency} to ${toCurrency} =${result}
</body>
</html>