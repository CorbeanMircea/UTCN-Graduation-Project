const autoName = document.getElementById("name");
const autoEmail = document.getElementById("email");
let code = window.sessionStorage.getItem("eventCode");


if(sessionStorage.getItem('loginStatus')==='true'){
    autoName.value = sessionStorage.getItem("name");
    autoEmail.value = sessionStorage.getItem("email");
}
const form = document.querySelector('.volunteer-form');

form.addEventListener('submit',event=> {
    event.preventDefault();
    if(sessionStorage.getItem('loginStatus')==='true') {
        const formData = new FormData(form);
        const payload = Object.fromEntries(formData);
        console.log(JSON.stringify(payload));
            fetch('http://localhost:8080/bookVolunteerSpots/'+code, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    'Access-Control-Allow-Origin': '*'
                },
                body: JSON.stringify(payload)

            }).then(response => {
                return response.json();
            }).then(
                data => {
                   if(data.response==="There are not enough spots"){
                       alert("Too many people");
                   }
                    if(data.response==="Spots booked"){
                        alert(data.response);
                        window.location.href='../html/home.html'
                    }
                })

    }else{
        alert("Login required");
        window.location.href = '../html/login.html';
    }
})