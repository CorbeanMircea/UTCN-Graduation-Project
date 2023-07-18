const formE2 = document.querySelector(".contact-form");
const autoName = document.getElementById("name");
const autoEmail = document.getElementById("email");

if(sessionStorage.getItem('loginStatus')==='true'){
    autoName.value = sessionStorage.getItem("name");
    autoEmail.value = sessionStorage.getItem("email");
}

formE2.addEventListener('submit',event=> {
    event.preventDefault();
    if(sessionStorage.getItem('loginStatus')==='true') {
        const formData = new FormData(formE2);
        const payload = Object.fromEntries(formData);
        console.log(JSON.stringify(payload));
        fetch('http://localhost:8080/addMessage', {
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
                    if (data.response === "We will contact you soon") {
                        alert(data.response);
                        window.location.href = '../html/home.html';
                    }else{
                        alert(data.response);
                    }
                })

    }else{
        alert("Login required");
        window.location.href = '../html/login.html';
    }
})