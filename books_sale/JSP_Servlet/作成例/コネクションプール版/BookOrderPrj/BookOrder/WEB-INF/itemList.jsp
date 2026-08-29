<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="utf-8">
  <link rel="stylesheet" href="style.css">
  <title>商品一覧：書籍注文システム</title>
</head>
<body>
  <section id="container">
    <h1>書籍販売システム</h1>
    <h2>商品一覧</h2>
    
    <c:if test="${not empty message}">
    <p class="message">
      ${message}
    </p>
    </c:if>
    
    <table>
      <tr>
        <th>番号</th><th>書籍名</th><th>価格</th><th>注文</th>
      </tr>
      
      <c:forEach var="item" items="${itemlist}" varStatus="st">
      <tr>
        <td class="no">${st.count}</td>
        <td>${item.name}</td>
        <td>${item.price}円</td>
        <td>
          <form action="order.html" method="post">
            <input type="hidden" name="itemcode" value="${item.code}">
            <input type="submit" value="注文">
          </form>
        </td>
      </tr>
      </c:forEach>
      
    </table>
    <form class="to_top" action="index.html">
      <input type="submit" value="トップへ戻る">
    </form>
  </section>
</body>
</html>
