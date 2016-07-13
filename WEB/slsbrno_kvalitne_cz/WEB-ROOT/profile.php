<?php
include "login.php";
?>
<?php /*
//echo "is_admin: " . $_SESSION["is_admin"];
if ( $_SESSION["is_admin"] == true ) {
  header("Location: admin_table.php");
  exit();
} else {
  header("Location: profile.php");
  exit();
} */
?>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="http://slsbrno.euweb.cz/favicon.ico" type="image/x-icon">
    <title>Summer Law School 2016</title>
    </head>
  <body>
  
<?php

  $sql = "SELECT `name`, `surname`, `ecoins` FROM `user` WHERE `login` = '" . $_SESSION["auth_login"] . "' AND `password` = '" . $_SESSION["auth_password"] . "';";
  
  $result = $conn->query($sql);
    
  if ($result->num_rows > 0 ) {
  
    $row = $result->fetch_assoc();
    
    $name = $row['name'];
    $surname = $row['surname'];
    $ecoins = $row['ecoins'];
        
/*    echo "Name: " . $name . "<br />";    
    echo "Surname: " . $surname . "<br />";    
    echo "Ecoins: " . $ecoins . "<br />";    */
        
    /* if ( $count > 0 ) {
      
    }    */
        
  }

?>
    <!--<center>-->
    <!--  <h3 class="page-header">Summer Law School</h3>
      <h3>IT Law</h3>
      <h3>Brno 2016</h3>  -->
      <table>
        <tr>
          <td><b>Summer Law School</b></td>
          <td rowspan="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
          <td rowspan="3"><img src="logo2micro_clear.png"></td>
        </tr>
        <tr>
          <td><b>IT Law</b></td>
        </tr>
        <tr>
          <td><b>Brno 2016</b></td>
        </tr>
      </table>
      <hr>
      <br />
      <img src="photos\2.jpg">
      <br />
      <br />
      <p><b><?php echo "" . $name . " " . $surname . "<br />"; ?></b></p>  
      <p><b><?php echo "E-coins: " . $ecoins . "<br />"; ?></b></p> 
      <br />
      <br />
      <table>
        <tr>
          <td><a href="#">Programme</a></td>
          <td><a href="#">Contacts</a></td>
        </tr>
      </table>
      <br />
      <br />
      <br />
      <br />
    <!--</center>-->
  </body>
</html>
