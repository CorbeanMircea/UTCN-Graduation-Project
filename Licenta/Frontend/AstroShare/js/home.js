let log = document.getElementById("log");
if(sessionStorage.getItem("loginStatus")==='true'){
    log.textContent="Log out";
}
log.addEventListener('submit', ev => {
    sessionStorage.setItem("loginStatus","false");
})
let menu = document.querySelector("#menu-icon");
let navbar = document.querySelector(".navbar");

menu.addEventListener("click",function (){
    navbar.classList.toggle("active");
});

window.onclose = ()=>{
    navbar.classList.remove("active");
};