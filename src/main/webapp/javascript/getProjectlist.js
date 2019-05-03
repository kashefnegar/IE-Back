// // I haven't rum the codes it may have bug
// var XMLHttpRequest = require("xmlhttprequest").XMLHttpRequest;
// var getprojects = new XMLHttpRequest();
// getprojects.onreadystatechange = function(){
//     if (this.readyState === 4 && this.status === 200) {
//         console.log(addskillreq.status);
//         console.log(addskillreq.responseText);
//     }
// };
// // i dont know what tou put here for now
// getprojects.open("GET","http://localhost:8080/project", true );
// // getprojects.setRequestHeader();
// getprojects.send();
// console.log("hi I finished");
// console.log(getprojects.status);
// console.log(getprojects.responseText);
var XMLHttpRequest = require("xmlhttprequest").XMLHttpRequest;
var HttpClient = function() {
    this.get = function(aUrl, aCallback) {
        var anHttpRequest = new XMLHttpRequest();
        anHttpRequest.onreadystatechange = function() {
            if (anHttpRequest.readyState === 4 && anHttpRequest.status === 200) {
                aCallback(anHttpRequest.responseText);
                console.log(anHttpRequest.status);
                console.log(anHttpRequest.responseText);
            }
        }

        anHttpRequest.open( "GET", aUrl, true );
        anHttpRequest.send( null );
    }
}
var client = new HttpClient();
client.get('http://localhost:8080/projects', function(response) {
    // do something with response
});