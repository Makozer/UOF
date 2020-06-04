
function loader() {
	raise();
}

function raise() {
	var res = ["iron", "rare", "water", "tritium"];
	res.forEach(raiseElement);
}

function raiseElement(item, index) {
	var rps = document.getElementById(item + "persecond").value;
	var increase = 1;
	var timems = (1000 / rps);

	var lastchar = document.getElementById(item).innerHTML.slice(-1);

	if (lastchar == "k") {
		timems = timems * 1000;
		setInterval(function() {
			document.getElementById(item).innerHTML = parseInt(document.getElementById(item).innerHTML) + increase + lastchar
		}, timems);
	} else if (lastchar == "m") {
		// Do nothing cuz numbers r 2 high :D
	} else {
		setInterval(function() {
			document.getElementById(item).innerHTML = parseInt(document.getElementById(item).innerHTML) + increase
		}, timems);
	}
	
}