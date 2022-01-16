<?php
require_once 'dbconfig.php';


if(isset($_POST['user_id']))   { 	$user_id =$_POST['user_id'];	}	else	{	$user_id="";	}
if(isset($_POST['user_type']))   { 	$user_type =$_POST['user_type'];	}	else	{	$user_type="";	}
 
$output = array();

if($user_id!="" )
{
	
		$query = "SELECT user_id , name , email , mobile , photo FROM tbl_user WHERE user_id = '$user_id' ";
								$result = mysqli_query($conn,$query);

								if (mysqli_num_rows($result) > 0) 
								{
									$row = mysqli_fetch_array($result, MYSQLI_ASSOC);
									$output['user_id'] =$row['user_id'];		
									$output['name'] =$row['name'];		
									$output['user_type'] ='User';
									$output['user_image'] =$row['photo'];
									$output['mobile'] =$row['mobile'];
									$output['email'] =$row['email'];
									
									$output['result'] ='Yes';
									$output['msg'] ='Sucess';		
								}	
								else
								{
									$output['result'] ='No';
									$output['msg'] ='Error';
								}
	
}
else
{
	$output['result'] ='No';
	$output['msg'] ='NoData';
}
$output1['data'][] = $output;
	
	if($output!=null)
	{
		print(json_encode($output1));
	}
	
mysqli_close($conn);
?>