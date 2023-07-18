function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}

function display(){
    window.onclick = e => {
        let eventName = e.target.textContent;
        /*console.log(e.target.textContent);*/
        fetch("http://localhost:8080/getEvent/"+eventName, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            }
        }).then(response =>{
            return response.json();
        }).then(data =>{
            let obj = Object.entries(data);

            let event_data=`<div class="overlay"></div>

                <div id="event-info" class="event-info">
                    <p class="title">${obj[1][1]}</p>
                    <div class="separator"></div>
                    <p class="info">${obj[2][1]}</p>
                    <p class="spots">Spots Available: ${obj[7][1]}</p>

                    <div class="additional-info">
                        <p class="info">
                            <i class="fas fa-map-marker-alt"></i>
                            ${obj[4][1]}
                        </p>
                        <p class="info">
                            <i class="far fa-calendar-alt"></i>
                            ${obj[5][1]} ${obj[3][1]}
                        </p>
                        <p class="info description">
                            ${obj[6][1]}
                        </p>
                    </div>
                </div>
                <a class="action" id="action" href="../html/volunteerForm.html">Take action</a>`

            document.getElementById("body-container").innerHTML=event_data;

            sessionStorage.setItem("eventCode",obj[0][1].toString());
        })

    }
}

fetch("http://localhost:8080/events", {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
    }
}).then(response =>{
    return response.json();
}).then(data =>{
    let sidebar_data="<a href=\"javascript:void(0)\" class=\"closebtn\" onclick=\"closeNav()\">&times;</a>";
    data.map(values =>{
        sidebar_data +=  `<a onclick="display()">${values.name}</a>`

    });
    document.getElementById("mySidenav").innerHTML=sidebar_data;
})









