<?php
include "login.php";
?>
<?php
//echo "is_admin: " . $_SESSION["is_admin"];
if ( $_SESSION["is_admin"] != true ) {
  header("Location: profile.php");
  exit();
}
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
  
    <table border="0" cellspacing="0" cellpadding="3">
      <tr>
        <th></th> 
        <th>Participant</th> 
        <th colspan="3">E-coins</th>
      </tr> 
  
<?php

  $sql = "SELECT `login`, `name`, `surname`, `ecoins` FROM `user` WHERE `is_admin` = false;";
  
  $result = $conn->query($sql);
    
  if ($result->num_rows > 0 ) {
  
    $row_number = 0;
  
    while($rowData = $result->fetch_array()) {
    
      $row_number = $row_number + 1;

      $login = $rowData['login'];
      $name = $rowData['name'];
      $surname = $rowData['surname'];
      $ecoins = $rowData['ecoins'];
        
      /*echo "Name: " . $name . "<br />";    
      echo "Surname: " . $surname . "<br />";    
      echo "Ecoins: " . $ecoins . "<br />"; */
       
      if ( ($row_number % 2) == 0 ) {
        echo "      <tr bgcolor=\"#CCEEFF\">\n";
      } else {
        echo "      <tr bgcolor=\"#AADDFF\">\n";
      } 
      echo "        <td>" . $row_number . "." . "</td>\n"; 
      echo "        <td>" . $name . " " . $surname . "</td>\n"; 
      echo "        <td align=\"right\"><div id=\"div_" . $login . "\">" . $ecoins . "</div></td>\n"; 
//      echo "        <td>" . "<button type=\"button\" onclick=\"alert('DODELAT! (+1)')\"><b>+</b></button>" . "</td>\n"; 
      echo "        <td>" . "<input class=\"button_" . $login . "_plus\" type=\"button\" value=\"+\" />" . "</td>\n"; 
//      echo "        <td>" . "<button type=\"button\" onclick=\"alert('DODELAT! (-1)')\"><b>&minus;</b></button>" . "</td>\n"; 
      echo "        <td>" . "<input class=\"button_" . $login . "_minus\" type=\"button\" value=\"&minus;\" />" . "</td>\n"; 
      echo "      </tr>\n"; 
      echo "      <tr height=\"5\" bgcolor=\"#FFFFFF\">\n";
      echo "        <td colspan=\"5\"></td>\n";
      echo "      </tr>\n";
      
      echo "      <script> ";
      echo "$(\".button_" . $login . "_plus\").click(function(e) { ";
      echo "var user_login = \"" . $login . "\"; ";
      echo "var user_operation = \"+\"; ";
      echo "$.ajax({url: \"db_user_plus_minus_1.php\", data: { input_login : user_login , input_operation : user_operation }, success: function(result){ ";
      echo "$(\"#div_" . $login . "\").html(result); ";
      echo "}}); ";
      echo "}); ";
      echo "</script>\n";
      
      echo "      <script> ";
      echo "$(\".button_" . $login . "_minus\").click(function(e) { ";
      echo "var user_login = \"" . $login . "\"; ";
      echo "var user_operation = \"-\"; ";
      echo "$.ajax({url: \"db_user_plus_minus_1.php\", data: { input_login : user_login , input_operation : user_operation }, success: function(result){ ";
      echo "$(\"#div_" . $login . "\").html(result); ";
      echo "}}); ";
      echo "}); ";
      echo "</script>\n";
      
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
    <br />
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
    
  </center>
  
  </body>
</html>
