<?php
require_once 'dbconfig.php';



if(isset($_POST['user_id']))   { 	$user_id =$_POST['user_id'];	}	else	{	$user_id="";	}

if(isset($_POST['name']))   	{ 	$name =$_POST['name'];		}	else	{	$name="";	}
if(isset($_POST['email']))   	{ 	$email =$_POST['email'];	}	else	{	$email="";	}
if(isset($_POST['mobile']))   	{ 	$mobile =$_POST['mobile'];	}	else	{	$mobile="";	}

 
$output = array();

if($name!="" && $email!="" && $mobile!="" &&  $user_id!="" )
{	
		$sql = "SELECT * FROM tbl_user WHERE email ='$email' AND user_id != '$user_id'";
		
		$result = mysqli_query($conn,$sql);
		if(mysqli_num_rows($result) > 0)
		{
			$output['result'] ='No';
			$output['msg'] ='Email';
		}
		else
		{
		
			$sql = "SELECT * FROM tbl_user WHERE mobile ='$mobile' AND user_id != '$user_id'";
				
				$result = mysqli_query($conn,$sql);
				if(mysqli_num_rows($result) > 0)
				{
					$output['result'] ='No';
					$output['msg'] ='Mobile';
				}
				else
				{
				
					
							$sql = "UPDATE  tbl_user SET name = '$name', email = '$email', mobile = '$mobile' WHERE user_id = '$user_id'";				
							if(mysqli_query($conn,$sql))
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
								$output['msg'] ='Error';
							}
						
											
				}
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