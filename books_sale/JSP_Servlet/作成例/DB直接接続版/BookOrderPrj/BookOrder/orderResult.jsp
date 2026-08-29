<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="utf-8">
  <link rel="stylesheet" href="style.css">
  <title>ご注文の完了：書籍注文システム</title>
</head>
<body>
  <section id="container">
    <h1>書籍販売システム</h1>
    <h2>ご注文の完了</h2>
    <p>
      ご注文ありがとうございます。
      以下の通り承りました。
    </p>
    <ul>
      <li><span class="column">ご注文番号</span>：${order.no}</li>
      <li><span class="column">商品名</span>：${order.item.name}</li>
      <li><span class="column">価格</span>：${order.item.price}円</li>
      <li><span class="column">ご注文個数</span>：${order.quantity}個</li>
      <li><span class="column">お名前</span>：${order.sei} ${order.mei}</li>
      <li><span class="column">ご住所</span>：${order.pref}${order.add}</li>
      <li><span class="column">お電話番号</span>：${order.tel}</li>
      <li><span class="column">メールアドレス</span>：${order.mail}</li>
    </ul>
    <p>
    </p>
    <form class="to_top" action="index.html">
      <input type="submit" value="トップへ戻る">
    </form>
  </section>
</body>
</html>
