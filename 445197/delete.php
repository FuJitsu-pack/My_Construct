  <html>
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
     <link rel = "stylesheet" href = "mystyle.css">
      <title>ブログ記事の削除</title>
    </head>
    <body>
      <h1>ブログ記事の削除</h1>
      <?php
  	  try{

    	     //PDOクラスのオブジェクトの作成
             $dbh = new PDO('sqlite:blog.db','','');
           
          if(isset($_POST["id"]) && !isset($_POST["title"]) && !isset($_POST["contents"])){
             if(!isset($_POST["password"]) || $_POST["password"] != 'd'){
               echo '<p>パスワードが違います。</p>';
              }else{
              //実行するSQL文を$sqlに格納
              $sql='delete from posts where id=?';
              //prepareメソッドでSQL文の
              $sth = $dbh->prepare($sql);
                //prepareした$sthを実行 SQL文の?部に格納する変数を指定
              $sth->execute(array($_POST["id"]));
          }
  
          if ($sth) {
  	       echo "記事１件を削除しました";
  	         }else {
  	       echo "記事１件の削除に失敗しました";
  	         }

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