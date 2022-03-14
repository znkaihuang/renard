/**
 * 
 */
 
let currentDateTime = new Date();
let currentYear = currentDateTime.getFullYear().toString();
let currentMonth = (currentDateTime.getMonth() >= 9) ? (currentDateTime.getMonth() + 1).toString()
												: "0" + (currentDateTime.getMonth() + 1).toString();
let currentDate = (currentDateTime.getDate() >= 10) ? currentDateTime.getDate().toString()
												: "0" + currentDateTime.getDate().toString();

class Stock {
	constructor(date, tradeVolume, tradeValue, openingPrice,
		highestPrice, lowestPrice, closingPrice, change, transaction) {
		this.date = date;
		this.tradeVolume = tradeVolume;
		this.tradeValue = tradeValue;
		this.openingPrice = openingPrice;
		this.highestPrice = highestPrice;
		this.lowestPrice = lowestPrice;
		this.closingPrice = closingPrice;
		this.change = change;
		this.transaction = transaction;
	}
}

function getDateTime() {
	let dateTimeWorker;
	if(typeof(dateTimeWorker) == "undefined") {
		dateTimeWorker = new Worker("js/time_worker.js");
	}
	dateTimeWorker.onmessage = function(event) {
		document.getElementById("currentDate").innerHTML = event.data[0];
		document.getElementById("currentTime").innerHTML = event.data[1];
	};
}


