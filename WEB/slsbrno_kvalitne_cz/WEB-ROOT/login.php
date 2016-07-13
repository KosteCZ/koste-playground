<?php
session_start();
?>

<?php
function page_header($title) {
?>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><?php echo $title; ?></title>
  </head>
  <body> 

<?php
}
?>


<?php
function page_footer() {
?>

  </body>
</html>
<?php
}
?>


<?php

$incorrect_credentials = false;

$servername = "sql2.webzdarma.cz";
$username = "slsbrno.euwe9572";
$password = "Elsabrno123";
$dbname = "slsbrno.euwe9572";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {

    page_header();
    die("Connection failed: " . $conn->connect_error);
    page_footer();
    exit;
    
} 
//echo "Connected successfully" . "<br />";
  
if (isset($_POST["auth_login"])) {
  
  $sql = "SELECT COUNT(*) AS count FROM `user` WHERE `login` = '" . $_POST["auth_login"] . "' AND `password` = '" . $_POST["auth_heslo"] . "';";
  
  $result = $conn->query($sql);
    
  if ($result->num_rows > 0 ) {
  
    $row = $result->fetch_assoc();
    
    $count = $row['count'];
        
    if ( $count > 0 ) {
  
      session_regenerate_id(); // ochrana před Session Fixation
      $_SESSION["logged"] = true;
      $_SESSION["auth_login"] = $_POST["auth_login"];
      $_SESSION["auth_password"] = $_POST["auth_heslo"];
    
    } 
       
  }
  
  
  
  $sql = "SELECT `is_admin` FROM `user` WHERE `login` = '" . $_POST["auth_login"] . "' AND `password` = '" . $_POST["auth_heslo"] . "';";
  
  $result = $conn->query($sql);
    
  if ($result->num_rows > 0 ) {
  
    $row = $result->fetch_assoc();
    
    $is_admin = $row['is_admin'];
        
    $_SESSION["is_admin"] = $is_admin;
    
    /*echo "is_admin: " . $is_admin;
      
    if ( $is_admin == true ) {
  
      echo "Admin";
    
    } else {
    
      echo "Not admin";
    
    } */
    
  }
  
}
if (!isset($_SESSION["logged"])) {
    
    page_header("Summer Law School - Log in");
    
    // echo "<form action='' method='post'>\n";
    // echo "<p>Login: <input name='auth_login' maxlength='30' /></p>\n";
    // echo "<p>Heslo: <input type='password' name='auth_heslo' /></p>\n";
    // echo "<p><input type='submit' value='Přihlásit' /></p>\n";
    // echo "</form>\n";
    
    echo "<center>";
    echo "<img src=\"logo2mini.png\">";
    echo "<form class=\"form-signin\" action='' method='post'>\n";
    
    if (isset($_POST["auth_login"])) {
        echo "<div class=\"alert alert-danger\" role=\"alert\"><span class=\"glyphicon glyphicon-exclamation-sign\"></span> Neplatné přihlašovací údaje.</div>\n";
    }
    
    /*echo "<h2 class=\"form-signin-heading\">Přihlášení</h2>\n";  */
    echo "<br />\n";
    echo "<br />\n";
    echo "<table>\n";
    echo "<tr>\n";
    echo "<td>\n";
    echo "<label for=\"inputLogin\" class=\"sr-only\">Username: </label>\n";
    echo "</td>\n";
    echo "<td>\n";
    echo "<input name='auth_login' id=\"inputLogin\" class=\"form-control\" placeholder=\"Username\" required autofocus>\n";
    echo "</td>\n";
    /*echo "<br />\n";*/
    echo "</tr>\n";
    echo "<tr>\n";
    echo "<td>\n";
    echo "<label for=\"inputPassword\" class=\"sr-only\">Password: </label>\n";
    echo "</td>\n";
    echo "<td>\n";
    echo "<input type=\"password\" name='auth_heslo' id=\"inputPassword\" class=\"form-control\" placeholder=\"Password\" required>\n";
    /*echo "<div class=\"checkbox\">\n";
    echo "  <label>\n";
    echo "    <input type=\"checkbox\" value=\"remember-me\"> Zapamatovat si mě\n";
    echo "  </label>\n";
    echo "</div>\n"; */
    echo "</td>\n";
    echo "</tr>\n";
    echo "</table>\n";
    echo "<button class=\"btn btn-lg btn-primary btn-block\" type=\"submit\">Log in</button>\n";
    echo "</form>\n";
    echo "<br />\n";
    echo "<br />\n";
    echo "<br />\n";
    echo "<br />\n";
    echo "<br />\n";
    echo "<br />\n";
    echo "<br />\n";
    echo "<br />\n";
    echo "<p>Advertisment:</p>\n";
    echo "</center>";

    
    page_footer();
    exit;
}

?>
