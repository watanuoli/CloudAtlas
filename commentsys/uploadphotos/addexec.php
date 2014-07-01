<?php
include('WebHDFS.php');
include('config.php');
if (!isset($_FILES['image']['tmp_name'])) {
	echo "";
} else {
	$file=$_FILES['image']['tmp_name'];
	$image= addslashes(file_get_contents($_FILES['image']['tmp_name']));
	$image_name= addslashes($_FILES['image']['name']);
	move_uploaded_file($_FILES["image"]["tmp_name"],"photos/" . $_FILES["image"]["name"]);
	$location="photos/" . $_FILES["image"]["name"];
	$caption=$_POST['caption'];

    if ($enablehdfs) {
        //upload to hdfs
        $response = $hdfs->getContentSummary('/photos/');
        $result = json_decode($response);
        if (strcmp("FileNotFoundException",$result->{'RemoteException'}->{'exception'}) == 0) {
            $hdfs->mkdirs('/photos');
        }

	    $hdfs_location = $hdfs->create($location, $_FILES["image"]["name"]);
    }
    $save=mysql_query("INSERT INTO photos (thumbnail_location, hdfs_location, caption) VALUES ('$location','$hdfs_location','$caption')");

	header("location: index.php");
	exit();					
}
?>
