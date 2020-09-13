let status = document.getElementById("status");

let interval = setInterval(loop, 200);

async function switchListener(){
    await fetch(`http://localhost:8080/room_check/?id=${roomId}`, {
        method: "POST"
    })
}

function loop(){
    let request = new XMLHttpRequest();
    request.open("GET", `http://localhost:8080/room_check/?id=${roomId}`, true);
    request.addEventListener('readystatechange', function() {
        if ((request.readyState==4) && (request.status==200)) {
            console.log(JSON.parse(request.response));
            status.innerText = (JSON.parse(request.response)).data;
        }
    });
    request.send();
}

async function shutdown(){
    await fetch("http://localhost:8080/actuator/shutdown", {
        method: "POST"
    });
    clearInterval(interval);
}