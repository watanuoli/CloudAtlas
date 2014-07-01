<?php
$mysql_hostname = "localhost";
$mysql_user = "root";
$mysql_password = "";
//database name, don't change
$mysql_database = "photoupload";
$prefix = "";
$bd = mysql_connect($mysql_hostname, $mysql_user, $mysql_password) or die("Could not connect database");
mysql_select_db($mysql_database, $bd) or die("Could not select database");
//if false, then only upload to local directory
//if true, then upload to hdfs
$enablehdfs = false;
if ($enablehdfs) {
    //wto : The name of the hdfs namdenode
    //50070: default port of namenode
    //hadoop: hdfs user, it should be the same as the user who install hadoop
    $hdfs = new WebHDFS('wto', '50070', 'hadoop');
}


?>