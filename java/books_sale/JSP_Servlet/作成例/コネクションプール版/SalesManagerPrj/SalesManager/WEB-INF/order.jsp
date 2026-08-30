<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="utf-8">
  <link rel="stylesheet" href="style.css">
  <title>商品注文：書籍販売業務システム</title>
</head>
<body>
  <section id="container">
    <h1>書籍販売業務システム</h1>
    <h2>注文入力</h2>
    
    <c:if test="${not empty message}">
    <p class="message">
      ${message}
    </p>
    </c:if>
    
    <p>
      注文情報を入力してください。<br>
      * 印は必須項目です。
    </p>
    <form action="orderResult.html" method="post">
      <input type="hidden" name="itemcode" value="${item.code}">
      <ul>
        <li><span class="column">商品名</span>：${item.name}</li>
        <li><span class="column">価格</span>：${item.price}円</li>
        <li><span class="column">* 注文個数</span>：<input type="text" style="width:2em" name="quantity">個</li>
        <li><span class="column">* 発注者氏名</span>：(姓)<input type="text" style="width:7em" name="sei">(名)<input type="text" style="width:7em" name="mei"></li>
        <li><span class="column">* 発注者住所</span>：(都道府県)<input type="text" style="width:5em" name="pref">(市区郡以降)<input type="text" style="width:25em" name="address"></li>
        <li><span class="column">電話番号</span>：<input type="text" style="width:20em" name="tel"></li>
        <li><span class="column">メールアドレス</span>：<input type="text" style="width:20em" name="mail"></li>
        <li><span class="column"></span>　<input type="submit" value="入力確定"></li>
      </ul>
    </form>
    <form class="to_top" action="index.html">
      <input type="submit" value="メニューへ戻る">
    </form>
    <form action="itemSearch.html">
      <input type="submit" value="新たな注文入力へ">
    </form>
  </section>
</body>
</html>
