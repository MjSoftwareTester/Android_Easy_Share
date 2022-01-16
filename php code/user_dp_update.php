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
		
		
	if($user_id !="" )
	{
		
			$location = "uploads/dp/";
			$filename1= "dp".$user_id.'.'.$ext;
			move_uploaded_file($temp_name,$location.$filename1);
			
			$sql = "UPDATE tbl_user SET photo = '$location$filename1' WHERE user_id='$user_id'";
	 
					if(mysqli_query($conn,$sql))
					{
						echo $file_name;
					}
					else
					{
						echo "error124";
					}
		
		
			 
	}
}

?>