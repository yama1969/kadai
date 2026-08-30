<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="utf-8">
  <link rel="stylesheet" href="style.css">
  <title>注文一覧：書籍販売業務システム</title>
</head>
<body>
  <section id="container">
    <h1>書籍販売業務システム</h1>
    <h2>注文一覧</h2>
    <p>
      検索された注文は以下の通りです。
    </p>
    <table>
      <tr>
        <th>番号</th><th>注文日時</th><th>商品コード</th><th>商品名</th><th>数量</th><th>お名前</th><th>ご住所</th><th>電話番号</th><th>メールアドレス</th>
      </tr>
      
      <c:forEach var="order" items="${orderlist}" varStatus="st">
      <tr>
        <td class="no">${order.no}</td>
        <td>
          <fmt:formatNumber value="${order.datetime.year}" type="NUMBER" groupingUsed="false" minIntegerDigits="4"/>/<fmt:formatNumber value="${order.datetime.monthValue}" type="NUMBER" minIntegerDigits="2"/>/<fmt:formatNumber value="${order.datetime.dayOfMonth}" type="NUMBER" minIntegerDigits="2"/>
          <fmt:formatNumber value="${order.datetime.hour}" type="NUMBER" minIntegerDigits="2"/>:<fmt:formatNumber value="${order.datetime.minute}" type="NUMBER" minIntegerDigits="2"/>:<fmt:formatNumber value="${order.datetime.second}" type="NUMBER" minIntegerDigits="2"/>
        </td>
        <td>${order.item.code}</td>
        <td>${order.item.name}</td>
        <td>${order.quantity}</td>
        <td>${order.sei} ${order.mei}</td>
        <td>${order.pref}${order.add}</td>
        <td>${order.tel}</td>
        <td>${order.mail}</td>
      </tr>
      </c:forEach>
      
    </table>
    <form class="to_top" action="index.html">
      <input type="submit" value="メニューへ戻る">
    </form>
    <form action="orderSearch.html">
      <input type="submit" value="新たな注文検索へ">
    </form>
  </section>
</body>
</html>
