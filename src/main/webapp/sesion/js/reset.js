/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function checkPassword(){
    let password = document.getElementById("pass").value;
    let confirm = document.getElementById("pass2").value;
    console.log(password, confirm);
    let message =  document.getElementById("message");

    if(password.length !=0){
        if(password == confirm){
            message.textContent = "Las contraseñas coinciden";
            message.style.color = "#1dcd59";
        }
        else{
            message.textContent = "Las contraseñas no coinciden";
            message.style.color = "#ff4d4d";
        }
    }
    else{
        alert("Password can't be empty!");
        message.textContent = "";
    }
}
