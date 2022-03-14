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
	var content = document.getElementById("companyInstantIndexSearch").value;
	if(content !== "") {
		window.alert(content);
	}
}