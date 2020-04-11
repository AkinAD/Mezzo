var buttons = document.querySelectorAll('form button:not([type="submit"])');
for (i = 0; i < buttons.length; i++) {
  buttons[i].addEventListener('click', function(e) {
    e.preventDefault();
  });
}

function doAjaxPriceUpdate(address)
{
	//multiply current price by cart quantity
	// send back to server
	// update view (cart price for user)
}