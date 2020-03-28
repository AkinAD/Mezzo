/**
 * Kinny Wrote this
 */

function validate()
{ 
	console.log("validating...");
	var fname = document.getElementById('fname').value;
	var lname = document.getElementById('lname').value;
	var email = document.getElementById('email').value;
	var username = document.getElementById('username_reg').value; 
	var password =document.getElementById('password_reg').value;
	var conpassword= document.getElementById('re_pass').value;

	if (fname==null || fname =="")
	{ 
		alert("First Name can't be blank"); 
		return false; 
	}
	if (lname==null || lname =="")
	{ 
		alert("Last Name can't be blank"); 
		return false; 
	}
	else if (email==null || email=="")
	{ 
		alert("Email can't be blank"); 
		return false; 
	}
	else if (username==null || username=="")
	{ 
		alert("Username can't be blank"); 
		return false; 
	}
	else if(password.length<6)
	{ 
		alert("Password must be at least 6 characters long."); 
		return false; 
	} 
	else if (password!=conpassword)
	{ 
		alert("Confirm Password should match with the Password"); 
		return false; 
	} 
} 

function loginValidate()
{ 
	console.log("validating login ...");

	var username = document.getElementById('username').value; 
	var password = document.getElementById('password').value;

	if (username==null || username=="")
	{ 
		alert("Username cannot be blank"); 
		return false; 
	}
	else if(password==null || password=="")
	{ 
		alert("Password cannot be blank"); 
		return false; 
	} 
}