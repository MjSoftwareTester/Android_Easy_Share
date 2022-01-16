<?php
require_once 'dbconfig.php';





if($_SERVER['REQUEST_METHOD']=='POST')
{
		$file_name = $_FILES['myFile']['name'];
		$file_size = $_FILES['myFile']['size'];
		$file_type = $_FILES['myFile']['type'];
		$temp_name = $_FILES['myFile']['tmp_name'];
		$ext = pathinfo($file_name, PATHINFO_EXTENSION);
		
		$user_id = $_POST['user_id'];
		$title = $_POST['title'];
		
		
	if($user_id !="" & $title != "" )
	{
		
			$location = "uploads/documents/";
			//$filename1= "document".$user_id.'.'.$ext;
			$filename1= $file_name;
			move_uploaded_file($temp_name,$location.$filename1);
			
			$sql = "INSERT INTO  tbl_uploads(user_id, title ,upload_path) VALUES ('$user_id' , '$title' , '$location$filename1')";
	 
					if(mysqli_query($conn,$sql))
					{
						echo $file_name;
					}
					else
					{
						echo "error124";
					}
	
	}
	else
	{
		echo "error";
	}
}

?>