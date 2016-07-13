<?php
include "login.php";
?>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Summer Law School 2016</title>
  </head>
  <body>
    <center>
      <img src="logo2mini.png">
      <h1 class="page-header">Summer Law School 2016</h1>
      <?php
        //echo "is_admin: " . $_SESSION["is_admin"];
        if ( $_SESSION["is_admin"] == true ) {
          header("Location: admin_table.php");
          exit();
        } else {
          header("Location: profile.php");
          exit();
        }
      ?>
    </center>
  </body>
</html>
