const formE2 = document.querySelector('.form');
formE2.addEventListener('submit',event=>{
    event.preventDefault();

    const formData = new FormData(formE2);
    const payload = Object.fromEntries(formData);
    console.log(JSON.stringify(payload));
    console.log(payload.username.length+"\n"+ payload.password.length +"\n"+ payload.roleId+"\n"+payload.name.length+"\n"+payload.phoneNumber.length+"\n"+payload.email.length)
    if(payload.username.length > 0 && payload.password.length >0 && (payload.roleId == 1 || payload.roleId == 2) && payload.name.length >0 && payload.email.length > 0 && payload.phoneNumber.length>0) {
        fetch('http://localhost:8080/signup', {
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
                alert("Account created!")
                window.location.href  = 'login.html';
            })
    }else{
        alert("Wrong credentials!")
        window.location.href  = 'signup.html';
    }
});