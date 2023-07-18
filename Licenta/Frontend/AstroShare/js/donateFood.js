window.onload=function(){
    let today = new Date().toISOString().split('T')[0];
    document.getElementsByName("availability")[0].setAttribute('min', today);
    geoLocation();
}

const options = {
    enableHighAccuracy: true,
    timeout: 5000,
    maximumAge: 0,
};

function geoLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.watchPosition(showPosition, showError, options)
    }
}

function showPosition(position){
    document.getElementById("latitude").value = position.coords.latitude;
    document.getElementById("longitude").value = position.coords.longitude;
    sessionStorage.setItem("latitude",document.getElementById("latitude").value.toString());
    sessionStorage.setItem("longitude",document.getElementById("longitude").value.toString());
}

function showError(error){
    switch(error.code) {
        case error.PERMISSION_DENIED:
        alert("You must allow the request for geolocation to fill out this form");
        location.reload();
        break;
    }
}

const form = document.querySelector('.form');

form.addEventListener('submit',event=> {
    event.preventDefault();
    if(sessionStorage.getItem('loginStatus')==='true') {
        const formData = new FormData(form);
        formData.append("productDetails", document.getElementById("productDetails").value.toString());
        const payload = Object.fromEntries(formData);
        console.log(JSON.stringify(payload));

            fetch('http://localhost:8080/addProduct/' + sessionStorage.getItem('_id'), {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Access-Control-Allow-Origin': '*'
                },
                body: JSON.stringify(payload)

            }).then(response => {
                return response.json();
            }).then(
                data => {
                    if(data.response==='Product successfully added') {
                        console.log(data.response);
                        alert("Thank you for your donation")
                        window.location.href = '../html/home.html';
                    }else{
                        alert(data.response);
                    }
                })
    }else{
        alert("Login Required!")
        window.location.href="../html/login.html";
    }

})