const formE1 = document.querySelector('.form');
formE1.addEventListener('submit',event=>{
    event.preventDefault();

    const formData = new FormData(formE1);
    const payload = Object.fromEntries(formData);
    console.log(JSON.stringify(payload));
    fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*'
        },
        body: JSON.stringify(payload)

    }).then(response =>{
        return response.json();
    }).then(
        // alert("Login succesfuly.")
        data => {
             console.log(data.response);
            console.log(data);

            if(data.response === "Successful Log in for client "+payload.username){
                sessionStorage.setItem('loginStatus', 'true');
                //console.log(sessionStorage.getItem('loginStatus'));
                sessionStorage.setItem('username', payload.username.toString());
                let username = sessionStorage.getItem('username');
                fetch('http://localhost:8080/getUserInfo/'+username,{
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                        'Access-Control-Allow-Origin': '*'
                    }
                }).then(response2 =>{
                    return response2.json();
                }).then(data2 => {
                    sessionStorage.setItem('roleId',data2.roleId);
                    sessionStorage.setItem('_id',data2.id);
                    sessionStorage.setItem('email',data2.email.toString());
                    sessionStorage.setItem('name',data2.name.toString());
                    if (sessionStorage.getItem('loginStatus') === "true") {
                        if(sessionStorage.getItem('roleId')==="2") {
                            alert("Hello, " + sessionStorage.getItem('username') + ". Welcome!");
                            window.location.href = 'home.html';
                        }
                        if(sessionStorage.getItem('roleId')==="1"){
                            window.location.href = 'admin.html';
                        }
                    }
                })
            }else {
                if (data.response === "Error during Log in") {
                    sessionStorage.setItem('loginStatus', 'false');
                    if(sessionStorage.getItem('loginStatus')==="false"){
                        window.location.href  = 'login.html';
                    }
                }
            }
        })
});






