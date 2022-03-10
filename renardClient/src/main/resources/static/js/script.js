/**
 * Global Field
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

/**
 * Daily Info
 */

let doOnceFlag = false;
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
		if(doOnceFlag === false) {
			showInstantInfo();
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

function showInstantInfo() {	
	fetch("http://localhost:8081/renardServer/instantAggreInfo")
		.then(function(response) {
	    	return response.json();
	  	})
	  	.then(function(instantIndexInfo) {
	    	document.getElementById("instantIndex").innerHTML = instantIndexInfo[0].currentPrice;
	    	document.getElementById("todayIndexHighest").innerHTML = instantIndexInfo[0].highestPrice;
	    	document.getElementById("todayIndexLowest").innerHTML = instantIndexInfo[0].lowestPrice;
	    	document.getElementById("todayOpeningIndex").innerHTML = instantIndexInfo[0].openingPrice;
	    	document.getElementById("yesterdayClosingIndex").innerHTML = instantIndexInfo[0].yesterdayClosingPrice;
	    	doOnceFlag = true;
	    });
}

/**
 * Monthly Info
 */
let indexHistoryArray = [];
(function getIndexHistoryInfo() {
	
	fetch("http://localhost:8081/renardServer/totalIndexHistory_" 
		+ currentYear + currentMonth + "01")
		.then(function(response) {
			return response.json();
		})
		.then(async function(indexHistory) {
			//console.log(indexHistory.length);
			//console.log(indexHistory);
			for(var i = 0; i < indexHistory.length; i++) {
				indexHistoryArray.push(
					new Stock(transformDate(indexHistory[i].date), indexHistory[i].tradeVolume,
						indexHistory[i].tradeValue, indexHistory[i].openingPrice,
						indexHistory[i].highestPrice, indexHistory[i].lowestPrice,
						indexHistory[i].closingPrice, indexHistory[i].change,
						indexHistory[i].transaction));
			}
			plotChart(indexHistoryArray);
			createIndexTable(indexHistoryArray);
		});
}) ();

function transformDate(date) {
	var dateSplit = date.split("/");
	dateSplit[0] = (parseInt(dateSplit[0]) + 1911).toString();
	return dateSplit.join("-");
}

function createIndexTable(data) {
	let indexTable = document.createElement("table");
	let tableHeader = document.createElement("thead");
	let tableBody = document.createElement("tbody");
	tableHeader.innerHTML = "<tr><th>日期</th><th>當日開盤</th><th>當日最高</th>"+
							"<th>當日最低</th><th>當日收盤</th></tr>"
	let bodyString = "";
	for(var i = 0; i < data.length; i++) {
		bodyString += createIndexRow(data[i]);
	}
	tableBody.innerHTML = bodyString;
	indexTable.append(tableHeader);
	indexTable.append(tableBody);
	document.getElementById("indexPlot").append(indexTable);
}

function createIndexRow(rowData) {
	return "<tr><td>" + rowData.date + "</td><td>" + rowData.openingPrice +"</td><td>"
		+ rowData.highestPrice + "</td><td>" + rowData.lowestPrice + "</td><td>"
		+ rowData.closingPrice + "</td></tr>";
}

function plotChart(dataObjectArray) {
	var arrayLength = dataObjectArray.length;
	
	var dateArray = [];
	var closingPriceArray = [];
	var highestPriceArray = [];
	var lowestPriceArray = [];
	var openingPriceArray = [];
	for(var i = 0; i < arrayLength; i++) {
		dateArray.push(dataObjectArray[i].date);
		closingPriceArray.push(stringToFloat(dataObjectArray[i].closingPrice));
		highestPriceArray.push(stringToFloat(dataObjectArray[i].highestPrice));
		lowestPriceArray.push(stringToFloat(dataObjectArray[i].lowestPrice));
		openingPriceArray.push(stringToFloat(dataObjectArray[i].openingPrice));
	}
	
	var trace1 = {
		//x: ['2017-01-17', '2017-01-18', '2017-01-19', '2017-01-20', '2017-01-23', '2017-01-24', '2017-01-25', '2017-01-26', '2017-01-27', '2017-01-30', '2017-01-31', '2017-02-01', '2017-02-02', '2017-02-03', '2017-02-06', '2017-02-07', '2017-02-08', '2017-02-09', '2017-02-10'], 
  		x: dateArray,
  		//close: [120, 119.989998, 119.779999, 120, 120.080002, 119.970001, 121.879997, 121.940002, 121.949997, 121.629997, 121.349998, 128.75, 128.529999, 129.080002, 130.289993, 131.529999, 132.039993, 132.419998, 132.119995], 
  		close: closingPriceArray,
  		decreasing: {line: {color: 'green'}}, 
		//high: [120.239998, 120.5, 120.089996, 120.449997, 120.809998, 120.099998, 122.099998, 122.440002, 122.349998, 121.629997, 121.389999, 130.490005, 129.389999, 129.190002, 130.5, 132.089996, 132.220001, 132.449997, 132.940002], 
		high: highestPriceArray,
		increasing: {line: {color: 'red'}}, 
		//line: {color: 'rgba(31,119,180,1)'}, 
		//low: [118.220001, 119.709999, 119.370003, 119.730003, 119.769997, 119.5, 120.279999, 121.599998, 121.599998, 120.660004, 120.620003, 127.010002, 127.779999, 128.160004, 128.899994, 130.449997, 131.220001, 131.119995, 132.050003],
		//open: [118.339996, 120, 119.400002, 120.449997, 120, 119.550003, 120.419998, 121.669998, 122.139999, 120.93, 121.150002, 127.029999, 127.980003, 128.309998, 129.130005, 130.539993, 131.350006, 131.649994, 132.460007], 
		low: lowestPriceArray,
		open: openingPriceArray,
		type: 'ohlc', 
		xaxis: 'x', 
		yaxis: 'y',
		line: {
			width: 5
		}
		//hovertext: ["日期", "開盤", "最高", "最低", "收盤"]
	};
	

	
	var data = [trace1];
	var layout = {
		dragmode: 'zoom', 
		margin: {
			r: 10, 
			t: 25, 
			b: 40, 
			l: 60
		}, 
		showlegend: false, 
		xaxis: {
    		autorange: true, 
			//rangeslider: {range: ['2017-01-17 12:00', '2017-02-10 12:00']}, 
			title: 'Date', 
			type: 'date'
		}, 
		yaxis: {
			autorange: true, 
			type: 'linear'
		}
	};
	Plotly.newPlot('plotChart', data, layout);
}

function stringToFloat(numberString) {
	return parseFloat(numberString.split(",").join(""));
}