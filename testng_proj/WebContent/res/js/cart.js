var buttons = document.querySelectorAll('form button:not([type="submit"])');
for (i = 0; i < buttons.length; i++) {
  buttons[i].addEventListener('click', function(e) {
    e.preventDefault();
  });
}

function ajaxCart(address)
{
	var request = new XMLHttpRequest();
	var data= '';
	
	
	data += "updateCart=true";
	request.open("GET", (address + "?" + data), true);
	request.onreadystatechange = function() {
	handler(request);
	};
	request.send(null);
}
function handler(request){
	if ((request.readyState == 4) && (request.status == 200)){
		var target = document.getElementById("cartDisp");
		target.innerHTML = request.responseText;
	}
}
function doAjaxPriceUpdate(address)
{ 
	
	//multiply current price by cart quantity
	// send back to server
	// update view (cart price for user)
}