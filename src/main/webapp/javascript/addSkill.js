// I haven't rum the codes it may have bug
var XMLHttpRequest = require("xmlhttprequest").XMLHttpRequest;
const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

var addskillreq = new XMLHttpRequest();
addskillreq.onreadystatechange = function(){
    if (this.readyState === 4 && this.status === 200) {
        console.log(addskillreq.status);
        console.log(addskillreq.responseText);
    }
};
// i dont know what tou put here for now
// addskillreq;
//
rl.question('name of skill', (skill) => {
    addskillreq.open("POST","http://localhost:8080/addSkill" ,true);
    addskillreq.setRequestHeader("id",skill);
    addskillreq.send();
    // console.log(`Thank you for your valuable feedback: ${skill}`);
});
    // addskillreq.send();

