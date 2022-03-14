/**
 * 
 */

var companyList;
let indexHistoryArray = [];
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
		showInstantInfo("http://localhost:8081/renardServer/instantInfo_" + companyId);
		document.getElementById("companyIndex").style.display = "block";
	}
	return false;
}

function showCompanyHistory() {
	var companyId = document.getElementById("companyHistoryIndexSearch").value.substr(0, 4);
	getIndexHistoryInfo("http://localhost:8081/renardServer/historyInfo_" 
		+ currentYear + currentMonth + "01_" + companyId);
	return false;
}
