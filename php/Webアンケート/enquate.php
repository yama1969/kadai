<html>
<head>
	<title>ご協力ありがとうございました。</title>
</head>
<body>
<?php
	$con = mysql_connect("localhost","root","pass");
	if(!$con){
		echo("MySQLに接続できませんでした。<br>\n");
	}else{
		//データベースの選択
		$sql = "use enquate";
		$result = mysql_query($sql,$con);
		
		//データ挿入
		$sql  = "INSERT INTO enquate VALUES(";
		$sql .= $_POST["age"].",";
		$sql .= $_POST["sei"].",";
		$sql .= $_POST["haigu"].",";
		$sql .= $_POST["huyo"].",";
		$sql .= $_POST["kinmuchi"].",";
		$sql .= $_POST["syokusyu"].",";
		$sql .= $_POST["nensyu"].",";
		$sql .= $_POST["zangyo"];
		$sql .= ")";
		$result = mysql_query($sql,$con);
	}
?>
	<center>
		<form>
			<input type="button" value="ご協力ありがとうございました。" onClick="window.close()">
		</form>
	</center>
</body>
</html>
