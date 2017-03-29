  <html>
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
      <title>ブログ記事へのコメント</title>
      <link rel = "stylesheet" href = "mystyle.css">()
    </head>
    <body>
      <h1>ブログ記事のコメント</h1>

     
  <?php

    try{
      $dbh = new PDO('sqlite:blog.db','',''); //PDOクラスのオブジェクトの作成
      
      //タイムゾーンの指定
     ini_set("date.timezone", "Asia/Tokyo");
     //$timeへ成形した年月日および時刻データを格納
     $time=date("Y.m.d - H:i");
    
    $sql='insert into comments(name,pid,contents,date) values (?,?,?,?)';
    $sth = $dbh->prepare($sql);
     //prepareした$sthを実行　SQL文の？部に格納する変数を指定
    $sth->execute(array($_POST["name"],$_POST["pid"],$_POST["contents"],$time));
      
      if($sth){
           echo "コメント１件を追加しました";
             }else {
           echo "コメント１件の追加に失敗しました";
      }

      $dbh = null;
      
    }Catch (PDOException $e) {
       print "エラー!: " . $e->getMessage() . "<br/>";
       die();
       }


  ?>
      

    <p><a href="index.php">blog閲覧ページはこちら</a></p>

  </body>
  </html>