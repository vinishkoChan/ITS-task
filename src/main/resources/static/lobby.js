async function searchListener(){
    let url = "http://localhost:8080/rooms";

    let response = await fetch(url);
    let json = await response.json();

    let roomList = document.getElementById("roomList");
    roomList.innerHTML = "";
    
    let btn = document.createElement("button");
    btn.style.width = "80px";
    btn.style.height = "25px";
    btn.style.marginLeft = "15px";

    json.forEach(element => {

        let room = document.createElement("li");
        room.style.paddingBottom = "15px";

        room.innerText = `Name: ${element.name}` + '\n'+ `Country: ${element.country}`

        let newButton = btn.cloneNode();
        newButton.addEventListener("click", enterRoomListener);
        newButton.innerText = "Enter";
        newButton.roomId = element.id;
        room.appendChild(newButton);

        roomList.appendChild(room);

    });
}

async function createListener() {
    let name = document.getElementById("roomName").value;
    let country = document.getElementById("country").value;
    let url = `http://localhost:8080/rooms/?name=${name}&country=${country}`;

    let response = await fetch(url, {
        method: "POST"
    });
    let json = await response.json();

    console.log(json);
    await searchListener();
}

async function enterRoomListener(e) {
    window.location.href = `http://localhost:8080/room/?id=${e.target.roomId}`;
}

async function shutdown(){
    await fetch("http://localhost:8080/actuator/shutdown", {
        method: "POST"
    });
}