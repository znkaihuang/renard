/**
 * 
 */

var companyList;
(async function init() {
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

function showInstantInfo(companyId) {	
	fetch("http://localhost:8081/renardServer/instantInfo_" + companyId)
		.then(function(response) {
	    	return response.json();
	  	})
	  	.then(function(instantIndexInfo) {
			console.log(instantIndexInfo);
	    	document.getElementById("instantIndex").innerHTML = instantIndexInfo[0].currentPrice;
	    	document.getElementById("todayIndexHighest").innerHTML = instantIndexInfo[0].highestPrice;
	    	document.getElementById("todayIndexLowest").innerHTML = instantIndexInfo[0].lowestPrice;
	    	document.getElementById("todayOpeningIndex").innerHTML = instantIndexInfo[0].openingPrice;
	    	document.getElementById("yesterdayClosingIndex").innerHTML = instantIndexInfo[0].yesterdayClosingPrice;
	    	doOnceFlag = true;
	    });
}

function showCompanyHistory(companyId) {
	return false;
}