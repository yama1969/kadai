<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="utf-8">
  <link rel="stylesheet" href="style.css">
  <title>商品検索：書籍販売業務システム</title>
</head>
<body>
  <section id="container">
    <h1>書籍販売業務システム</h1>
    <h2>注文入力 － 商品検索</h2>
    
    <c:if test="${not empty message}">
    <p class="message">
      ${message}
    </p>
    </c:if>
    
    <p>
      検索する書籍名を入力してください。書籍名の一部分でも検索できます。
    </p>
    <form action="itemList.html" method="post">
      書籍名：<input type="text" name="itemname"><input type="submit" value="検索">
    </form>
    
    <form class="to_top" action="index.html">
      <input type="submit" value="メニューへ戻る">
    </form>
  </section>
</body>
</html>
