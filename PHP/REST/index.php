<?php

	// process client request (VIA URL)
	header("Content-type: application/json");
	include("functions.php");
	if(!empty($_GET['name']))
	{
		//
		$name = $_GET['name'];
		$price = get_price($name);

		if(empty($price))
		{
			// book not found
			deliver_response(200, "book not found", NULL);
			
		}

		else
		{
			// response book price
			deliver_response(200, "book found", $price);
		}
	}

	else
	{
		// throw invalid request
		deliver_response(400, "Invalid request", NULL);

	}

	function deliver_response($status, $status_message, $data)
	{
		header("HTTP/1.1 $status $status_message");

		$response['status'] = $status;
		$response['status_message'] = $status_message;
		$response['data'] = $data;

		$json_response = json_encode($response);
		echo $json_response;
	}
?>