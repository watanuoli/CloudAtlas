<?php
include('WebHDFS.php');
include('config.php');
if(!empty($_POST['check_list'])) {
    foreach ($_POST['check_list'] as $check) {
        $result = mysql_query("SELECT thumbnail_location FROM photos WHERE id = '$check'");

        $row = mysql_fetch_row($result);
        //delete thumbnail
        unlink($row[0]);
        if ($enablehdfs) {
            //delete hdfs
            $hdfs->delete("/".$row[0]);
        }
        mysql_query("DELETE FROM photos WHERE id = $check");
    }

    header("location: index.php");
    exit();
}
?>