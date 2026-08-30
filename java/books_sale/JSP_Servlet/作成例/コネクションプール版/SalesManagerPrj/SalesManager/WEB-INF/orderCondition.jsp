<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="utf-8">
  <link rel="stylesheet" href="style.css">
  <title>注文検索：書籍販売業務システム</title>
</head>
<body>
  <section id="container">
    <h1>書籍販売業務システム</h1>
    <h2>注文検索</h2>
    
    <c:if test="${not empty message}">
    <p class="message">
      ${message}
    </p>
    </c:if>
    
    <p>
      検索する注文の情報を入力してください。<br>
      省略する項目は空欄のままで結構です。
    </p>
    <form action="orderList.html" method="post">
      <ul>
        <li><span class="column">注文番号</span>： <input type="text" style="width:4em" name="orderno"></li>
        <li><span class="column">注文日</span>：
          <input type="text" style="width:3em" name="startyear">年
          <input type="text" style="width:2em" name="startmonth">月
          <input type="text" style="width:2em" name="startday">日 ～
          <input type="text" style="width:3em" name="endyear">年
          <input type="text" style="width:2em" name="endmonth">月
          <input type="text" style="width:2em" name="endday">日
        </li>
        <li><span class="column">購入者氏名(部分可)</span>： <input type="text" style="width:20em" name="custname"></li>
        <li><span class="column">購入者電話番号</span>： <input type="text" style="width:20em" name="tel"></li>
        <li><span class="column">商品名(部分可)</span>： <input type="text" style="width:20em" name="itemname"></li>
        <li><span class="column"></span>　 <input type="submit" value="検索実行"></li>
      </ul>
    </form>
    <form class="to_top" action="index.html">
      <input type="submit" value="メニューへ戻る">
    </form>
  </section>
</body>
</html>
