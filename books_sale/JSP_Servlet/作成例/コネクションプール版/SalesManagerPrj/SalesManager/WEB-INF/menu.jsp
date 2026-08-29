<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="utf-8">
  <link rel="stylesheet" href="style.css">
  <title>トップメニュー：書籍販売業務システム</title>
</head>
<body>
  <section id="container">
    <h1>書籍販売業務システム</h1>
    <h2>トップメニュー</h2>
    
    <c:if test="${not empty message}">
    <p class="message">
      ${message}
    </p>
    </c:if>
    
    <form action="orderSearch.html" method="post">
      <input class="menu_btn" type="submit" value="注文検索">
    </form>
    <form action="itemSearch.html" method="post">
      <input class="menu_btn" type="submit" value="注文入力">
    </form>
  </section>
</body>
</html>
