<?php
require_once 'dbconfig.php';



// mysql_set_charset($conn, "utf8");
date_default_timezone_set("Asia/Calcutta");
$today = date("d-m-Y H:i:s");


if(isset($_POST['name']))  			{ 	$name =$_POST['name'];		}	else	{	$name="";	}
if(isset($_POST['email']))   		{ 	$email =$_POST['email'];	}	else	{	$email="";	}
if(isset($_POST['mobile']))  	 	{ 	$mobile =$_POST['mobile'];	}	else	{	$mobile="";	}

if(isset($_POST['password']))   	{ 	$password =$_POST['password'];	}	else	{	$password="";	}


$output= array();

	if($name!="" && $email!="" && $mobile!=""  && $password!="")
	{	
		$password = encryptIt( $password ); 

		$sql = "SELECT * FROM tbl_user WHERE email ='$email'";
		$result = mysqli_query($conn,$sql);
		if(mysqli_num_rows($result) > 0)
		{
			$output['result'] ='No';
			$output['msg'] ='Email';
		}
		else
		{
			$sql = "SELECT * FROM tbl_user WHERE mobile ='$mobile'";
				$result = mysqli_query($conn,$sql);
				if(mysqli_num_rows($result) > 0)
				{
					$output['result'] ='No';
					$output['msg'] ='Mobile';
				}
				else
				{
				
							$sql = "INSERT INTO tbl_user(name,email, mobile , password , created , photo) VALUES ('$name','$email','$mobile','$password','$today','uploads/dp/user_default.png')";				
							if(mysqli_query($conn,$sql))
							{
								$query = "SELECT user_id , name , email , mobile , photo FROM tbl_user WHERE email ='$email' AND password='$password' ";
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