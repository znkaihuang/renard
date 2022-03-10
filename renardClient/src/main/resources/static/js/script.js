/**
 * Daily Info
 */
getInstantInfo(); 
setInterval(getInstantInfo, 30000);
(function getDateTime() {
	let dateTimeWorker;
	if(typeof(dateTimeWorker) == "undefined") {
		dateTimeWorker = new Worker("js/time_worker.js");
	}
	dateTimeWorker.onmessage = function(event) {
		document.getElementById("currentDate").innerHTML = event.data[0];
		document.getElementById("currentTime").innerHTML = event.data[1];
	};
})();

function getInstantInfo() {
	if(isCurrentTimeAvailForInstantInfo()) {
		showInstantInfo();
	}
	else {
		document.getElementById("instantIndex").innerHTML = "Not okay";
	}
};

function isCurrentTimeAvailForInstantInfo() {
	let currentDateTime = new Date();
	let currentDay = currentDateTime.getDay();
	let currentHour = currentDateTime.getHours();
	if(currentDay >= 1 && currentDay <= 5 && 
		currentHour >= 9 && currentHour <= 14) {
		return true;
	}
	else {
		return false;
	}
}

function showInstantInfo() {	
	var myRequest = new Request('http://localhost:8081/renardServer/instantAggreInfo');
	fetch(myRequest)
		.then(function(response) {
	    	return response.json();
	  	})
	  	.then(function(instantIndexInfo) {
	    	document.getElementById("instantIndex").innerHTML = instantIndexInfo[0].currentPrice;
	    	document.getElementById("todayIndexHighest").innerHTML = instantIndexInfo[0].highestPrice;
	    	document.getElementById("todayIndexLowest").innerHTML = instantIndexInfo[0].lowestPrice;
	    	document.getElementById("todayOpeningIndex").innerHTML = instantIndexInfo[0].openingPrice;
	    	document.getElementById("yesterdayClosingIndex").innerHTML = instantIndexInfo[0].yesterdayClosingPrice;
	    });
}

/**
 * Monthly Info
 */
 
 
 