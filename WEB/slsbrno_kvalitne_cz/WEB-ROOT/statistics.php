<?php
include "login.php";
?>
<?php /*
//echo "is_admin: " . $_SESSION["is_admin"];
if ( $_SESSION["is_admin"] != true ) {
  header("Location: profile.php");
  exit();
} */
?><!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Summer Law School 2016</title>
  </head>
  <body>
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  
  <center>
  
<?php
include "zahlavi.txt";
?>

<?php

  $sql = "SELECT `ecoins`, count(`login`) as `count` FROM `user` WHERE `is_admin` = false GROUP BY `ecoins` ORDER BY `ecoins` DESC;";

  $result = $conn->query($sql);

  $array = array();

  if ($result->num_rows > 0 ) {
  
    while($rowData = $result->fetch_array()) {
    
      $ecoins = $rowData['ecoins'];
      $count  = $rowData['count'];
      
//      echo "---" . $ecoins . " - " . $count . "---\n"; 

      $array[$ecoins] = $count;   
  
    }
    
  }

?>

    <table border="0" cellspacing="0" cellpadding="3">
      <tr>
        <th></th> 
        <th align="left">Participant</th> 
        <th align="left" colspan="1">E-coins</th>
      </tr> 

<?php

  $sql = "SELECT `login`, `name`, `surname`, `ecoins` FROM `user` WHERE `is_admin` = false ORDER BY `ecoins` DESC;";
  
  $result = $conn->query($sql);
    
  if ($result->num_rows > 0 ) {
  
    $rowNumber = 0;
    $userPlace = 0;
    $lastUserEcoins = -1;
  
    while($rowData = $result->fetch_array()) {
    
      $rowNumber = $rowNumber + 1;

      $login = $rowData['login'];
      $name = $rowData['name'];
      $surname = $rowData['surname'];
      $ecoins = $rowData['ecoins'];
        
      /*echo "Name: " . $name . "<br />";    
      echo "Surname: " . $surname . "<br />";    
      echo "Ecoins: " . $ecoins . "<br />"; */
      
      if ( $ecoins <> $lastUserEcoins ) {
      
         $lastUserEcoins = $ecoins;
         $userPlace = $rowNumber;
      
      }
       
      if ( ($rowNumber % 2) == 0 ) {
        echo "      <tr bgcolor=\"#CCEEFF\">\n";
      } else {
        echo "      <tr bgcolor=\"#AADDFF\">\n";
      }                                                
      if ( $array[$ecoins] == 1 ) {
        echo "        <td>" . $userPlace . "." . "</td>\n";
      } else if ( $array[$ecoins] > 1 ) {
        echo "        <td>" . $userPlace . "-" . ($userPlace+$array[$ecoins]-1) . "." . "</td>\n";
      } else {
        echo "        <td>" . $userPlace . "." . "</td>\n";
      }   
      if ( $_SESSION["auth_login"] == $login ) {
        echo "        <td><b>" . $name . " " . $surname . "</b></td>\n"; 
      } else {
        echo "        <td>" . $name . " " . $surname . "</td>\n"; 
      }  
      echo "        <td align=\"right\"><div id=\"div_" . $login . "\"><b>" . $ecoins . "</b></div></td>\n"; 
      echo "      </tr>\n"; 
      echo "      <tr height=\"5\" bgcolor=\"#FFFFFF\">\n";
      echo "        <td colspan=\"5\"></td>\n";
      echo "      </tr>\n";
      
    }
    
/*    echo "Name: " . $name . "<br />";    
    echo "Surname: " . $surname . "<br />";    
    echo "Ecoins: " . $ecoins . "<br />";    */
        
    /* if ( $count > 0 ) {
      
    }    */
        
  }

?>

    </table>
    <br />
    <a href="profile.php">Back to profile</a>  
    <br />
    <br />

    <!--<center>
      <h3 class="page-header">Summer Law School 2016</h3>
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
    </center>-->
    
<?php
include "zapati.txt";
?>
    
  </center> 
  
  </body>
</html>
