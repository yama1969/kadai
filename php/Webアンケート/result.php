<html>
<head>
	<title>ザ・アンケート集計結果</title>
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
		
		//性別集計
		$sql = "SELECT sei.name,count(enquate.sei) FROM enquate INNER JOIN sei ON enquate.sei = sei.code GROUP BY sei.name";
		$result = mysql_query($sql,$con);
		$rows = mysql_num_rows($result);
		$columns = mysql_num_fields($result);
		echo("性別集計");
		echo("<table border=1>\n");
		for($i=0; $i<$rows; $i++){
			echo("<tr>");
			for($j=0; $j<$columns; $j++){
				$dat = mysql_result($result,$i,$j);
				echo("<td>$dat</td>");
			}
			echo("</tr>\n");
		}
		echo("</table><br>\n");
		
		//年齢集計
		$sql = "SELECT CONCAT(FLOOR(enquate.age / 5.0) * 5, '代') as nenrei, count(enquate.age) FROM enquate GROUP BY nenrei";
		$result = mysql_query($sql,$con);
		$rows = mysql_num_rows($result);
		$columns = mysql_num_fields($result);
		echo("年齢集計");
		echo("<table border=1>\n");
		for($i=0; $i<$rows; $i++){
			echo("<tr>");
			for($j=0; $j<$columns; $j++){
				$dat = mysql_result($result,$i,$j);
				echo("<td>$dat</td>");
			}
			echo("</tr>\n");
		}
		echo("</table><br>\n");
		
		//希望勤務地集計
		$sql = "SELECT kinmuchi.name, count(enquate.kimuchi) FROM enquate INNER JOIN kinmuchi ON enquate.kimuchi = kinmuchi.code GROUP BY kinmuchi.name";
		$result = mysql_query($sql,$con);
		$rows = mysql_num_rows($result);
		$columns = mysql_num_fields($result);
		echo("希望勤務地集計");
		echo("<table border=1>\n");
		for($i=0; $i<$rows; $i++){
			echo("<tr>");
			for($j=0; $j<$columns; $j++){
				$dat = mysql_result($result,$i,$j);
				echo("<td>$dat</td>");
			}
			echo("</tr>\n");
		}
		echo("</table><br>\n");
		
		//希望職種集計
		$sql = "SELECT syokusyu.name, count(enquate.syokusyu) FROM enquate INNER JOIN syokusyu ON enquate.syokusyu = syokusyu.code GROUP BY enquate.syokusyu";
		$result = mysql_query($sql,$con);
		$rows = mysql_num_rows($result);
		$columns = mysql_num_fields($result);
		echo("希望職種集計");
		echo("<table border=1>\n");
		for($i=0; $i<$rows; $i++){
			echo("<tr>");
			for($j=0; $j<$columns; $j++){
				$dat = mysql_result($result,$i,$j);
				echo("<td>$dat</td>");
			}
			echo("</tr>\n");
		}
		echo("</table><br>\n");
		
		//希望年収集計
		$sql = "SELECT CONCAT(FLOOR(enquate.nensyu / 10.0) * 10, '万円台') as rank, count(enquate.nensyu) FROM enquate GROUP BY rank";
		$result = mysql_query($sql,$con);
		$rows = mysql_num_rows($result);
		$columns = mysql_num_fields($result);
		echo("希望年収集計");
		echo("<table border=1>\n");
		for($i=0; $i<$rows; $i++){
			echo("<tr>");
			for($j=0; $j<$columns; $j++){
				$dat = mysql_result($result,$i,$j);
				echo("<td>$dat</td>");
			}
			echo("</tr>\n");
		}
		echo("</table><br>\n");
		
		//残業集計
		$sql = "SELECT CONCAT(FLOOR(enquate.zangyo), '時間') as rank, count(enquate.zangyo) FROM enquate GROUP BY rank";
		$result = mysql_query($sql,$con);
		$rows = mysql_num_rows($result);
		$columns = mysql_num_fields($result);
		echo("残業集計");
		echo("<table border=1>\n");
		for($i=0; $i<$rows; $i++){
			echo("<tr>");
			for($j=0; $j<$columns; $j++){
				$dat = mysql_result($result,$i,$j);
				echo("<td>$dat</td>");
			}
			echo("</tr>\n");
		}
		echo("</table><br>\n");
	}
?>
</body>
</html>
