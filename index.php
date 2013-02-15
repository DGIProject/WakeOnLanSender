<?php
$mac1 = str_replace ( "-", "",$_GET['MAC'] ) ;
$mac = str_replace ( ":", "",$mac1 ) ;
$IP_ADDRESS=$_GET['IP'];
$MAC_ADDRESS=$mac;
$PORT = $_GET['PORT'];
 
class Wol{
  private $nic;
  public function wake($mac,$ip,$port){
    $this->nic = fsockopen("udp://$ip", "$port");
    if( !$this->nic ){
      fclose($this->nic);
      return false;
    }
    else{
      fwrite($this->nic, $this->pacquet($mac));
      fclose($this->nic);
      return true;
    }
  }
  private function pacquet($Mac){
    $packet = "";
    $packet = "\xFF\xFF\xFF\xFF\xFF\xFF";
    for ($j = 0; $j < 16; $j++){
      for($i = 0; $i < 12; $i=$i + 2){$packet .= chr(hexdec(substr($Mac, $i, 2)));}
    }
    return $packet;
  }
}
 
$wol = new Wol();
 
$php_wal=$_GET['WAL'];
 
if ($php_wal!="OK") {
 
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Wake On Lan</title>
  </head>
  <body>
    <form action="<?php echo $_SERVER['SCRIPT_NAME'];?>" method="get">
      <input type="hidden" name="WAL" value="OK" >
		<label for="ipa">IP :</label><input type="text" name="IP" id="ipa" value="0.0.0.0">
	  <label for="maca">MAC :</label><input type="text" name="MAC" id="maca" value="00000AAAAVVR">
	  <label for="porta">PORT :</label><input type="text" name="PORT" id="porta" value="9">
      <input type="submit" value="Démarrer">
    </form>
	  <h2>Vous voulez réveillez un pc sur votre reseau local ? Utilisez cette page ! <a href="local.html">Cliquez ICI !</a></h2>
  </body>
</html>
 
<?php
    exit();
  }
  $wol->wake("$MAC_ADDRESS","$IP_ADDRESS", "$PORT");
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Wake On Lan</title>
  </head>
  <body>
Démarrage en cours...<br>
	  Vous revenez souvent ? Mettez cette page en racourci ! Quand vous la chargerez, elle lancera automatiquement l'ordinateur correspondant au ardresses IP, MAC entré.
  </body>
</html>