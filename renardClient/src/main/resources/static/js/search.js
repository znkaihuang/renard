/**
 * 
 */

var companyList;
(async function init() {
	getDateTime();
	companyList = await getAllCompaniesInfo();
	addDataListOptions();
}) ();

async function getAllCompaniesInfo() {
	return fetch("http://localhost:8081/renardServer/allCompanies")
		.then(function(response) {
			return response.json()
		})
		.then(function(data) {
			return data;
		});
}

function addDataListOptions() {
	let dataList = document.getElementById("companyAndId");
	for(var company of companyList) {
		let option = document.createElement("option");
		option.setAttribute("value", company.companyId + " " + company.companyAbbrev);
		dataList.append(option);
	}
}

function showCompanyInstantIndex() {
	var companyId = document.getElementById("companyInstantIndexSearch").value.substr(0, 4);
	if(companyId !== "") {
		showInstantInfo(companyId);
		document.getElementById("companyIndex").style.display = "block";
	}
	return false;
}

function showInstantInfo(companyId) {	
	fetch("http://localhost:8081/renardServer/instantInfo_" + companyId)
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

function showCompanyHistory() {
	var companyId = document.getElementById("companyInstantIndexSearch").value.substr(0, 4);
	getIndexHistoryInfo(companyId);
	return false;
}

let indexHistoryArray = [];
function getIndexHistoryInfo(companyId) {
	
	fetch("http://localhost:8081/renardServer/historyInfo_" 
		+ currentYear + currentMonth + "01_" + companyId)
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
