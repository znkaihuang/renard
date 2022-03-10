/**
 * 
 */
 
 function showSomething() {
	var myInit = {
		method: 'GET',
		mode: 'cors',
		cache: 'default'
	};
	var myRequest = new Request('http://localhost:8080/js/test.json',
		myInit);
	fetch(myRequest)
	  .then(function(response) {
	    return response.json();
	  })
	  .then(function(myJson) {
	    document.getElementById("demo").innerHTML = 
	    	myJson.firstName + " " + myJson.lastName;
	  });
}

function loadFromRenardServer() {
		var myInit = {
		method: 'GET',
		mode: 'cors',
		cache: 'default'
	};
	var myRequest = new Request('http://localhost:8081/renardServer/holidaySchedule',
		myInit);
	fetch(myRequest)
	  .then(function(response) {
	    return response.json();
	  })
	  .then(function(myJson) {
	    console.log(myJson);
	    document.getElementById("serverReturn").innerHTML = myJson[0].event + " " + myJson[0].date;
	  });
}
