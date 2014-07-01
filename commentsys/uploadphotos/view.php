<?php
//Not work yet
include('WebHDFS.php');
include('config.php');
$photo_id = $_GET['id'];
error_log("id:".$photo_id."\n",3,"3.log");
$result = mysql_query("SELECT thumbnail_location FROM photos WHERE id = '$photo_id'");

$row = mysql_fetch_row($result);

error_log("aaaaid:".$row[0]."\n",3,"3.log");

$response = $hdfs->open("/".$row[0]);
error_log("response:".$response,3,"3.log");
echo $response;
    //header("location: index.php");
    exit();

?>