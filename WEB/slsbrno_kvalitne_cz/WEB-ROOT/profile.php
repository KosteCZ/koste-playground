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

  $sql = "SELECT `id`, `name`, `surname`, `ecoins` FROM `user` WHERE `login` = '" . $_SESSION["auth_login"] . "' AND `password` = '" . $_SESSION["auth_password"] . "';";
  
  $result = $conn->query($sql);
    
  if ($result->num_rows > 0 ) {
  
    $row = $result->fetch_assoc();
    
    $id = $row['id'];
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
      
  <center>
<?php
include "zahlavi.txt";
?>
  </center>
      
      <br />
      <?php echo "<img src=\"photos\\" . $id . ".jpg\" height=\"205\" width=\"160\" border=\"4\" style=\"border-style:double\">" . "\n"; ?>
      <br />
      <br />
      <p><b><?php echo "" . $name . " " . $surname . "<br />"; ?></b></p>  
      <p><b><?php echo "E-coins: " . $ecoins . "<br />"; ?></b></p> 
      <br />
      <br />
      <table width="100%">
        <tr>
          <td width="33%" align="left"  ><a href="statistics.php">Statistics</a></td>
          <td width="*"   align="center"><a href="#">Programme</a></td>
          <td width="33%" align="right" ><a href="contacts.php">Contacts</a></td>
        </tr>
      </table>
      <br />
      <br />

  <center>
<?php
include "zapati.txt";
?>
  </center>

    <!--</center>-->
  </body>
</html>
