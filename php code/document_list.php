<?php

require_once 'dbconfig.php';


		$sql = "SELECT upload_id, tbl_uploads.user_id , title , upload_path , tbl_user.name , tbl_user.mobile FROM tbl_uploads
			INNER JOIN tbl_user ON tbl_user.user_id = tbl_uploads.user_id";

		$result = mysqli_query($conn,$sql);

		if(mysqli_num_rows($result) > 0)
		{
			while( $row = mysqli_fetch_assoc($result)) 
			{	
				$output['data'][] = $row;
			}
			print(json_encode($output));
		}
		else
		{
			echo "";
		}

?>

