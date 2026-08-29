<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="utf-8">
  <link rel="stylesheet" href="style.css">
  <title>注文入力完了：書籍販売業務システム</title>
</head>
<body>
  <section id="container">
    <h1>書籍販売業務システム</h1>
    <h2>注文入力完了</h2>
    <p>
      以下の通りに注文入力が完了しました。
    </p>
    <ul>
      <li><span class="column">注文番号</span>：${order.no}</li>
      <li><span class="column">商品名</span>：${order.item.name}</li>
      <li><span class="column">価格</span>：${order.item.price}円</li>
      <li><span class="column">注文個数</span>：${order.quantity}個</li>
      <li><span class="column">注文者氏名</span>：${order.sei} ${order.mei}</li>
      <li><span class="column">注文者住所</span>：${order.pref}${order.add}</li>
      <li><span class="column">電話番号</span>：${order.tel}</li>
      <li><span class="column">メールアドレス</span>：${order.mail}</li>
    </ul>
    <p>
    </p>
    <form class="to_top" action="index.html">
      <input type="submit" value="メニューへ戻る">
    </form>
    <form action="itemSearch.html">
      <input type="submit" value="新たな注文入力へ">
    </form>
  </section>
</body>
</html>
