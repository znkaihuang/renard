/**
 * 
 */

var companyList;
var companyInfo;
var industryMap;

(async function init() {
	companyList = await getAllCompaniesInfo();
	industryMap = await getIndustryMap();
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

function showCompanyInfo() {
	var companyId = document.getElementById("companyInfoSearch").value.substr(0, 4);
	fillInCompanyInfoTable(companyId);
	document.getElementById("companyInfoTable").style.display = "table";
	return false;
}

async function getCompanyInfo(companyId) {
	return fetch("http://localhost:8081/renardServer/companyId_" + companyId)
		.then(function(response) {
			return response.json()
		})
		.then(function(data) {
			companyInfo = data;
			return data;
		});
}

async function fillInCompanyInfoTable(companyId) {
	companyInfo = await getCompanyInfo(companyId);
	document.getElementById("companyFullName").innerHTML = companyInfo[0].companyName;
	document.getElementById("companyId").innerHTML = companyInfo[0].companyId;
	document.getElementById("companyName").innerHTML = companyInfo[0].companyAbbrev;
	document.getElementById("listedDate").innerHTML = formatDateString(companyInfo[0].listingDate);
	document.getElementById("address").innerHTML = companyInfo[0].address;
	document.getElementById("website").innerHTML = companyInfo[0].website;
	document.getElementById("industryType").innerHTML = 
		industryMap[parseInt(companyInfo[0].industryType)];
	document.getElementById("capital").innerHTML = companyInfo[0].capital;
	document.getElementById("chairman").innerHTML = companyInfo[0].chairman;
	document.getElementById("manager").innerHTML = companyInfo[0].generalManager;
	document.getElementById("spokesman").innerHTML = companyInfo[0].spokesman;
	document.getElementById("telephone").innerHTML = companyInfo[0].telephone;
	document.getElementById("email").innerHTML = companyInfo[0].email;
}

function formatDateString(date) {
	return date.substr(0, 4) + "-" + date.substr(4, 2) + "-" + date.substr(6);
}

async function getIndustryMap() {
	return fetch("http://localhost:8081/renardServer/industryType")
			.then(function(response) {
				return response.text();
			})
			.then(function(data) {
				data = data.substring(1, data.length - 2);
				let returnDataArray = [];
				for(var i of data.split(",")) {
					iSplit = i.split("=");
					returnDataArray[parseInt(iSplit[0])] = iSplit[1];
				}
				return returnDataArray;
			});
}
