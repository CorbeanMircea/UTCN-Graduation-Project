window.onload=function() {
    let today = new Date().toISOString().split('T')[0];
    document.getElementsByName("availability")[0].setAttribute('min', today);
}

let menu = document.querySelector("#menu-icon");
let navbar = document.querySelector(".navbar");

menu.addEventListener("click",function (){
    navbar.classList.toggle("active");
});

window.onclose = ()=>{
    navbar.classList.remove("active");
};

window.onload=function(){
    let today = new Date().toISOString().split('T')[0];
    document.getElementsByName("date")[0].setAttribute('min', today);
}

function openForm() {
    document.getElementById("myForm").style.display = "block";
}

function closeForm() {
    document.getElementById("myForm").style.display = "none";
}

function openForm2() {
    document.getElementById("myForm2").style.display = "block";
}

function closeForm2() {
    document.getElementById("myForm2").style.display = "none";
}

function openForm3() {
    document.getElementById("myForm3").style.display = "block";
}

function closeForm3() {
    document.getElementById("myForm3").style.display = "none";
}

function openForm4() {
    document.getElementById("myForm4").style.display = "block";
}

function closeForm4() {
    document.getElementById("myForm4").style.display = "none";
}

function openForm5() {
    document.getElementById("myForm5").style.display = "block";
}

function closeForm5() {
    document.getElementById("myForm5").style.display = "none";
}

fetch("http://localhost:8080/users",{
    method: 'GET',
    headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
    }
}).then(response =>{
    return response.json();
}).then(data =>{
    let tableData="";
    data.map(values =>{
       tableData +=  `<tr>
           <td>${values.id}</td>
           <td>${values.username}</td>
           <td>${values.password}</td>
           <td>${values.roleId}</td>
           <td>${values.productId}</td>
           <td>${values.name}</td>
           <td>${values.email}</td>
           <td>${values.phoneNumber}</td>
       </tr>`
    });
    document.getElementById("table-body").innerHTML=tableData;
})

fetch("http://localhost:8080/listAllProducts",{
    method: 'GET',
    headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
    }
}).then(response =>{
    return response.json();
}).then(data =>{
    let tableData="";
    data.map(values =>{
        tableData +=  `<tr>
           <td>${values.id}</td>
           <td>${values.productId}</td>
           <td>${values.productName}</td>
           <td>${values.productDetails}</td>
           <td>${values.category}</td>
           <td>${values.availability}</td>
           <td>${values.quantity}</td>
           <td>
           <a href="https://www.google.com/maps/search/?api=1&query=${values.latitude},${values.longitude}+${values.location}">
            Open on Google Maps
            </a>
           </td>   
       </tr>`
    });
    document.getElementById("table-body-products").innerHTML=tableData;
})

fetch("http://localhost:8080/donations",{
    method: 'GET',
    headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
    }
}).then(response =>{
    return response.json();
}).then(data =>{
    let tableData="";
    data.map(values =>{
        tableData +=  `<tr>
           <td>${values.name}</td>
           <td>${values.email}</td>
           <td>${values.mode}</td>
           <td>${values.amount}</td>
       </tr>`
    });
    document.getElementById("table-body-donations").innerHTML=tableData;
})


fetch("http://localhost:8080/messages",{
    method: 'GET',
    headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
    }
}).then(response =>{
    return response.json();
}).then(data =>{
    let tableData="";
    data.map(values =>{
        tableData +=  `<tr>
           <td>${values.name}</td>
           <td>${values.email}</td>
           <td>${values.message}</td>
       </tr>`
    });
    document.getElementById("table-body-message").innerHTML=tableData;
})

fetch("http://localhost:8080/events",{
    method: 'GET',
    headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
    }
}).then(response =>{
    return response.json();
}).then(data =>{
    let tableData="";
    data.map(values =>{
        tableData +=  `<tr>
           <td>${values.eventCode}</td>
           <td>${values.name}</td>
           <td>${values.description}</td>
           <td>${values.location}</td>
           <td>${values.address}</td>
           <td>${values.date}</td>
           <td>${values.program}</td>
           <td>${values.spotsAvailable}</td>
       </tr>`
    });
    document.getElementById("table-body-events").innerHTML=tableData;
})



const form = document.querySelector('.form-container');

form.addEventListener('submit',event=> {
    event.preventDefault();
    const formData = new FormData(form);
    const payload = Object.fromEntries(formData);
    let productId = payload.productId.valueOf();

    fetch('http://localhost:8080/deleteUser/'+ productId, {
        method: 'DELETE',
        headers: {
            'Access-Control-Allow-Headers': '*',
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin':'*'
        }
    }).then(response =>{
        return response.json();
    }).then(
        data => {
            console.log(data.response);
            console.log(data);
            if (data.response === 'User deleted successfully') {
                alert("User Deleted")
                window.location.href = '../html/admin.html';
            }else{
                alert("User failed to be deleted")
            }
        })
})


const form2 = document.querySelector('.form-container2');

form2.addEventListener('submit',event=> {
    event.preventDefault();
    const formData2 = new FormData(form2);
    const payload2 = Object.fromEntries(formData2);
    let id  = payload2.productId.valueOf();

    fetch('http://localhost:8080/deleteProduct/'+ id, {
        method: 'DELETE',
        headers: {
            'Access-Control-Allow-Headers': '*',
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin':'*'
        }
    }).then(response =>{
        return response.json();
    }).then(
        data => {
            console.log(data.response);
            console.log(data);
            if (data.response === 'Product successfully deleted') {
                alert("Product Deleted")
                window.location.href = '../html/admin.html';
            }else{
                alert("Product failed to be deleted")
            }
        })
})

const form3 = document.querySelector('.form-container3');

form3.addEventListener('submit',event=> {
    event.preventDefault();
    const formData3 = new FormData(form3);
    const payload3 = Object.fromEntries(formData3);
    let id  = payload3.id.valueOf();
    console.log(JSON.stringify(payload3));

    fetch('http://localhost:8080/updateProduct/'+ id, {
        method: 'POST',
        headers: {
            'Access-Control-Allow-Headers': '*',
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin':'*'
        },
        body: JSON.stringify(payload3)
    }).then(response =>{
        return response.json();
    }).then(
        data => {
            console.log(data.response);
            console.log(data);
            if (data.response === 'Product successfully updated') {
                alert(data.response);
                window.location.href = '../html/admin.html';
            }else{
                alert(data.response);
            }
        })
})

const form4 = document.querySelector('.form-container4');

form4.addEventListener('submit',event=> {
    event.preventDefault();
    const formData4 = new FormData(form4);
    const payload4 = Object.fromEntries(formData4);


    fetch('http://localhost:8080/addEvent', {
        method: 'POST',
        headers: {
            'Access-Control-Allow-Headers': '*',
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin':'*'
        },
        body: JSON.stringify(payload4)
    }).then(response =>{
        return response.json();
    }).then(
        data => {
            console.log(data.response);
            console.log(data);
            if (data.response === 'Event added') {
                alert(data.response);
                window.location.href = '../html/admin.html';
            }else{
                alert(data.response);
            }
        })
})

const form5 = document.querySelector('.form-container5');

form5.addEventListener('submit',event=> {
    event.preventDefault();
    const formData5 = new FormData(form5);
    const payload5 = Object.fromEntries(formData5);
    let eventCode  = payload5.eventCode.valueOf();

    fetch('http://localhost:8080/deleteEvent/'+ eventCode, {
        method: 'DELETE',
        headers: {
            'Access-Control-Allow-Headers': '*',
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin':'*'
        }
    }).then(response =>{
        return response.json();
    }).then(
        data => {
            console.log(data.response);
            console.log(data);
            if (data.response === 'Event successfully deleted') {
                alert(data.response)
                window.location.href = '../html/admin.html';
            }else{
                alert(data.response)
            }
        })
})


