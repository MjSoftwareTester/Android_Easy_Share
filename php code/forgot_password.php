<?php

require 'PHPMailerAutoload.php';
require_once 'dbconfig.php';



if(isset($_POST['user_name']))   { 	$user_name =$_POST['user_name'];	}	else	{	$user_name="";	}
 
$output = array();

$password ="";
$name ="";

if($user_name!="")
{
	$query = "SELECT password,name , email FROM tbl_user WHERE ((email = '$user_name') OR (mobile = '$user_name'))";
	$result = mysqli_query($conn,$query);

	if (mysqli_num_rows($result) > 0) 
	{
		$row = mysqli_fetch_array($result, MYSQLI_ASSOC);
		$password =$row['password'];
		$name =$row['name'];
		$email =$row['email'];
		
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

if($password != "")
{


	$mail = new PHPMailer;

		//Send mail using gmail

		$mail->IsSMTP(); // telling the class to use SMTP
		$mail->Host = "smtp.gmail.com"; // sets GMAIL as the SMTP server
		$mail->SMTPAuth = true; // enable SMTP authentication
		$mail->Username = "Mj@gmail.com"; // Your GMAIL username
		$mail->Password = "llnmstxsalkudzbw"; //Your GMAIL password
		$mail->SMTPSecure = "tls"; // sets the prefix to the servier
		$mail->Port  = 587 ; // set the SMTP port for the GMAIL server
    

		//Typical mail data
		$mail->SetFrom("Mj@gmail.com","Easy Share");
		$mail->AddAddress($email, $name);

		$mail->Subject = "Your Password for Easy Share";
		$mail->Body = "Hello ".$name.",\nYour Password is ".$password."\nThank you.";

		try{
			$mail->Send();
					$output['result'] ='Yes';
					$output['msg'] ='Sucess';		

			}
			catch(Exception $e){
			//Something went bad
			$output['result'] ='No';
			$output['msg'] ='Error';	
			}
}

$output1['data'][] = $output;
	
	if($output!=null)
	{
		print(json_encode($output1));
	}
	
mysqli_close($conn);
?>