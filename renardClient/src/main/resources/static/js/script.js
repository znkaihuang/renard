/**
 * Daily Info
 */

let doOnceFlag = false;
let indexHistoryArray = [];

(function init() {
	getInstantInfo(); 
	setInterval(getInstantInfo, 30000);
	getDateTime();
	getIndexHistoryInfo("http://localhost:8081/renardServer/totalIndexHistory_" 
		+ currentYear + currentMonth + "01");
}) ();

function getInstantInfo() {
	if(isCurrentTimeAvailForInstantInfo()) {
		showInstantInfo("http://localhost:8081/renardServer/instantAggreInfo");
	}
	else {
		if(doOnceFlag === false) {
			showInstantInfo("http://localhost:8081/renardServer/instantAggreInfo");
		}
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
