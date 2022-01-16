<?php
require_once 'dbconfig.php';



if(isset($_POST['user_name']))   { 	$user_name =$_POST['user_name'];	}	else	{	$user_name="";	}
if(isset($_POST['password']))   { 	$password =$_POST['password'];	}	else	{	$password="";	}
 
$output = array();

if($user_name!="" && $password!="")
{
	
		$query = "SELECT user_id , name , email , mobile , photo FROM tbl_user WHERE ((email ='$user_name') OR (mobile ='$user_name')) AND password='$password' ";
		$result = mysqli_query($conn,$query);

		if (mysqli_num_rows($result) > 0) 
		{
			$row = mysqli_fetch_array($result, MYSQLI_ASSOC);
			$user_id =$row['user_id'];		
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
	

}	
mysqli_close($conn);
?>