const formE2 = document.querySelector('.form');
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
        if (payload.name.length > 0 && payload.email.length > 0 && payload.amount.length > 0) {
            fetch('http://localhost:8080/addDonation', {
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
                    console.log(data.response);
                    console.log(data);
                    alert("Thank you for your donation")
                    window.location.href = '../html/home.html';
                })
        } else {
            alert("Wrong credentials!")
            window.location.href = 'donate.html';
        }
    }else{
        alert("Login required");
        window.location.href = '../html/login.html';
    }
})