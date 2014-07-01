<style type="text/css">
<!--
.ed{
border-style:solid;
border-width:thin;
border-color:#00CCFF;
padding:5px;
margin-bottom: 4px;
}
#button1{
text-align:center;
font-family:Arial, Helvetica, sans-serif;
border-style:solid;
border-width:thin;
border-color:#00CCFF;
padding:5px;
background-color:#00CCFF;
height: 34px;
}
#imagelist{
border: thin solid silver;
float:left;
padding:5px;
width:auto;
margin: 0 5px 0 0;
}
p{
margin:0;
padding:0;
text-align: center;
font-style: italic;
font-size: smaller;
text-indent: 0;
}
#caption{
margin-top: 5px;
}
img{
height: 225px;
}
#rightImage
{
	border: thin solid silver;
	padding:5px;
	width:auto;
	margin: 0 5px 0 0;
    height:275px;
    float:left;
    position:relative;
    
}
#rightImage:hover img
{
	border: thin solid silver;
	float:left;
	padding:5px;
	width:auto;
	margin: 0 5px 0 0;
    height: auto;
    
}
-->
</style>

<script src="js/lightbox/js/jquery-1.11.0.min.js"></script>
<script src="js/lightbox/js/lightbox.min.js"></script>
<link href="js/lightbox/css/lightbox.css" rel="stylesheet" />

<form action="addexec.php" method="post" enctype="multipart/form-data" name="addroom">
 Select Image: <br />
 <input type="file" name="image" class="ed"><br />
 Caption<br />
 <input name="caption" type="text" class="ed" id="brnu" />
 <br />
 <input type="submit" name="Submit" value="Upload" id="button1" />
 </form>
<br />
Photo Archieve
<br />
<br />
<form action="delete.php" method="post" name="deletephoto">
<?php
include('WebHDFS.php');
include('config.php');
$result = mysql_query("SELECT * FROM photos");
while($row = mysql_fetch_array($result))
{
    echo '<div id="imagelist">';
    echo '<input type="checkbox" name="check_list[]" value="'.$row['id'].'"/>';
    //echo '<p><a href="view.php?id='.$row['id'].'" data-lightbox="gallery" data-title="'.$row['caption'].'"><img src="'.$row['thumbnail_location'].'"></a></p>';
    echo '<p><a href="'.$row['thumbnail_location'].'" data-lightbox="gallery" data-title="'.$row['caption'].'"><img src="'.$row['thumbnail_location'].'"></a></p>';
    echo '<p id="caption">'.$row['caption'].' </p>';
    echo '</div>';
}
?>
<br/>
<input type="submit" name="delete" value="Delete" class="ed"/>
</form>