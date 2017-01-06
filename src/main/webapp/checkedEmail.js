var http = require('http');
var mysql = require('mysql');
var url = require('url');


var connection = mysql.createConnection({
	host     : 'localhost',
	user     : 'hiba',
	password : 'hiba1234',
	database : 'hibadb'
});

connection.connect();

//2) HTTP 서버 준비
var httpServer = http.createServer(function(request, response) {
	response.setHeader('Access-Control-Allow-Origin', 'http://t3.java85.com:8080');
	response.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');
	response.setHeader('Access-Control-Allow-Headers', 'X-Requested-With,content-type');
	response.setHeader('Access-Control-Allow-Credentials', true);	
	
	var urlInfo = url.parse(request.url, true);
	var email = urlInfo.query.email;	
	console.log("inputEmail : " + email)
	response.writeHead(200, {
		'Content-Type' : 'text/html;charset=UTF-8' 
	});
	if (urlInfo.pathname == "/teamproject/index.html") {
		
		var returnEmail = ""
		connection.query("select email from hiba_membs where email=?", [email],	function(err, rows, fields) { 		
			if (err) throw err;

			for (var i = 0; i < rows.length; i++) {						
				returnEmail = rows[i].email;
				console.log("1 :" + returnEmail)
			}
			console.log("2 :" + returnEmail)
			response.end('callback(' + JSON.stringify(returnEmail) + ');');
		});
	}

});

//3) HTTP 서버 가동
httpServer.listen(8989);
console.log("서버 실행 중...");


