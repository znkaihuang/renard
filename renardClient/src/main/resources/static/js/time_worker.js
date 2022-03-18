
(function timedCount() {
	let dateTime = new Date().toLocaleString();
	postMessage([dateTime.split(" ")[0], dateTime.split(" ")[1]]);
	setTimeout("timedCount()",1000);
}) ();
