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

    <center>
      
<?php
include "zahlavi.txt";
?>
      
      <!-- <br /> -->

      <h1>Important Contacts</h1>
      <h2>Organization team SLS</h2>		
      
      <!--		
      <table>
        <tr>
          <th align="left">Name</th> 
          <th align="left">Position</th> 
          <th align="left">Phone number</th>
        </tr> 
        <tr>
          <td>Veronika Kulichová</td>
          <td>Head of HEADS</td>
          <td>(+420) 724 796 190</td>
        </tr>
        <tr>
          <td>Josef Zelinka</td>
          <td>Contact Person</td>
          <td>(+420) 732 468 803</td>
        </tr>
        <tr>
          <td>Jan Burda</td>
          <td>Head of Academic Program</td>
          <td>(+420) 728 429 993</td>
        </tr>
        <tr>
          <td>Lukáš Tománek</td>
          <td>Head of Logistics</td>
          <td>(+420) 724 413 049</td>
        </tr>
        <tr>
          <td>Lukáš Weiss</td>
          <td>Head of Marketing</td>
          <td>(+420) 721 084 712</td>
        </tr>
        <tr>
          <td>Nikol Nevečeřalová</td>
          <td>Head of Social Program</td>
          <td>(+420) 777 870 052</td>
        </tr>
      </table> -->
      
      <table border="0" cellspacing="0" cellpadding="3">
        <tr>
          <th align="left">Name / Position</th> 
          <th align="left" width="135">Phone number</th>
        </tr> 
        <tr height="5" bgcolor="#FFFFFF">
          <td colspan="5"></td>
        </tr>
        <tr bgcolor="#AADDFF">
          <td>Veronika Kulichová<br /><b>Head of HEADS</b></td>
          <td><u><font color="#000099">+420 724 796 190</font></u></td>
        </tr>
        <tr height="5" bgcolor="#FFFFFF">
          <td colspan="5"></td>
        </tr>
        <tr bgcolor="#CCEEFF">
          <td>Josef Zelinka<br /><b>Contact Person</b></td>
          <td><u><font color="#000099">+420 732 468 803</font></u></td>
        </tr>
        <tr height="5" bgcolor="#FFFFFF">
          <td colspan="5"></td>
        </tr>
        <tr bgcolor="#AADDFF">
          <td>Jan Burda<br /><b>Head of Academic Program</b></td>
          <td><u><font color="#000099">+420 728 429 993</font></u></td>
        </tr>
        <tr height="5" bgcolor="#FFFFFF">
          <td colspan="5"></td>
        </tr>
        <tr bgcolor="#CCEEFF">
          <td>Lukáš Tománek<br /><b>Head of Logistics</b></td>
          <td><u><font color="#000099">+420 724 413 049</font></u></td>
        </tr>
        <tr height="5" bgcolor="#FFFFFF">
          <td colspan="5"></td>
        </tr>
        <tr bgcolor="#AADDFF">
          <td>Lukáš Weiss<br /><b>Head of Marketing</b></td>
          <td><u><font color="#000099">+420 721 084 712</font></u></td>
        </tr>
        <tr height="5" bgcolor="#FFFFFF">
          <td colspan="5"></td>
        </tr>
        <tr bgcolor="#CCEEFF">
          <td>Nikol Nevečeřalová<br /><b>Head of Social Program</b></td>
          <td><u><font color="#000099">+420 777 870 052</font></u></td>
        </tr>
      </table>
                
      <br />
      
      <h2>Other important contacts</h2>		
      
      <table border="0" cellspacing="0" cellpadding="3">
        <tr>
          <th align="left">Name</th> 
          <th align="left" width="135">Phone number</th> 
        </tr> 
        <tr height="5" bgcolor="#FFFFFF">
          <td colspan="5"></td>
        </tr>
        <tr bgcolor="#AADDFF">
          <td>Hotel Sono Centrum - reception</td>
          <td><u><font color="#000099">+420 511 189 790</font></u></td>
        </tr>
        <tr height="5" bgcolor="#FFFFFF">
          <td colspan="5"></td>
        </tr>
        <tr bgcolor="#CCEEFF">
          <td>Top 1 Taxi</td>
          <td><u><font color="#000099">+420 734 574 110</font></u></td>
        </tr>
        <tr height="5" bgcolor="#FFFFFF">
          <td colspan="5"></td>
        </tr>
        <tr bgcolor="#AADDFF">
          <td>Emergency telephone number</td>
          <td><u><font color="#000099">112</font></u></td>
        </tr>
      </table>

      <br />
      
      <h2>Transportation</h2>
      		
      <table border="0" cellspacing="0" cellpadding="3">
        <tr>
          <th align="left">Transport</th> 
          <th align="left">Terminal Station</th> 
          <th align="left">Station</th>
        </tr> 
        <tr height="5" bgcolor="#FFFFFF">
          <td colspan="5"></td>
        </tr>
        <tr bgcolor="#AADDFF">
          <td>Tram n. 3</td>
          <td>Tábor</td>
          <td>Tábor</td>
        </tr>
        <tr height="5" bgcolor="#FFFFFF">
          <td colspan="5"></td>
        </tr>
        <tr bgcolor="#CCEEFF">
          <td>Night bus n. N92</td>
          <td>Černého</td>
          <td>Tábor</td>
        </tr>
      </table>
      
      <br />
      <br />
      
      <a href="profile.php">Back to profile</a>  
      
      <br />
      
<?php
include "zapati.txt";
?>

    </center>
  </body>
</html>
