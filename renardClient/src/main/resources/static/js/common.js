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

function showInstantInfo(url) {	
	fetch(url)
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
  		x: dateArray,
  		close: closingPriceArray,
  		decreasing: {line: {color: 'green'}}, 
		high: highestPriceArray,
		increasing: {line: {color: 'red'}}, 
		low: lowestPriceArray,
		open: openingPriceArray,
		type: 'ohlc', 
		xaxis: 'x', 
		yaxis: 'y',
		line: {
			width: 5
		}
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
			title: '日期', 
			type: 'date'
		}, 
		yaxis: {
			autorange: true, 
			type: 'linear'
		}
	};
	Plotly.newPlot("plotChart", data, layout);
}

function stringToFloat(numberString) {
	return parseFloat(numberString.split(",").join(""));
}

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
	for(var i = data.length - 1; i >= 0; i--) {
		bodyString += createIndexRow(data[i]);
	}
	tableBody.innerHTML = bodyString;
	indexTable.append(tableHeader);
	indexTable.append(tableBody);
	document.getElementById("indexTable").append(indexTable);
}

function createIndexRow(rowData) {
	return "<tr><td>" + rowData.date + "</td><td>" + rowData.openingPrice + "</td><td>"
		+ rowData.highestPrice + "</td><td>" + rowData.lowestPrice + "</td><td>"
		+ rowData.closingPrice + "</td></tr>";
}

function getIndexHistoryInfo(url) {
	
	fetch(url)
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
}