<?php

require 'PHPMailerAutoload.php';
require_once 'dbconfig.php';



if(isset($_POST['user_email']))   { 	$user_email =$_POST['user_email'];	}	else	{	$user_email="";	}
if(isset($_POST['email_msg']))   { 	$email_msg =$_POST['email_msg'];	}	else	{	$email_msg="";	}
 
$output = array();

$password ="";
$name ="";

if($user_email!="" && $email_msg != "")
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
		$mail->AddAddress($user_email, "User");

		$mail->Subject = "Your OTP for Easy Share";
		$mail->Body = $email_msg;

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
else
{
	$output['result'] ='No';
	$output['msg'] ='Error';
}

$output1['data'][] = $output;
	
	if($output!=null)
	{
		print(json_encode($output1));
	}
	
mysqli_close($conn);
?>