<?php
$servername = "sql2.webzdarma.cz";
$username = "slsbrno.euwe9572";
$password = "Elsabrno123";
$dbname = "slsbrno.euwe9572";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
//echo "Connected successfully" . "<br />";

//echo $_GET['input_login']."\n";
$target_login = $_GET['input_login'];
//echo "Login: " . $target_login . "\n";
$target_operation = $_GET['input_operation'];
//echo "Operation: " . $target_operation . "\n";

$ecoins = -1;
//echo "Ecoins1: " . $ecoins . "\n";

$sql = "SELECT `ecoins` FROM `user` WHERE login = \"" . $target_login . "\"";
$result = $conn->query($sql);
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        //echo "Ecoins: " . $row["ecoins"]; // . "<br>" . "\n";
        $ecoins = $row["ecoins"];
        if ( $target_operation == "+" ) {
          $ecoins = $ecoins + 1;
        }
        if ( $target_operation == "-" ) {
          if ( $ecoins > 0 ) {
            $ecoins = $ecoins - 1;
          }
        }
        //echo "Ecoins2: " . $ecoins . "<br>" . "\n";
    }
} else {
    //echo "0 results";
    echo "ERROR";
}


$sql = "UPDATE `user` SET ecoins=" . $ecoins . " WHERE login = \"" . $target_login . "\"";
$result = $conn->query($sql);


$sql = "SELECT `login`, `name`, `surname`, `ecoins` FROM `user` WHERE login = \"" . $target_login . "\"";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        //echo "Login: " . $row["login"]. ", name: " . $row["name"]. " " . $row["surname"]. ", ecoins: " . $row["ecoins"]. "<br>";
        echo $row["ecoins"];
    }
} else {
    //echo "0 results";
    echo "ERROR";
}

$conn->close();
?>