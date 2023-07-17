/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


const passwordInput = document.querySelector(".reset__box");
const requirementList = document.querySelectorAll(".requirement-list li");
const errorDisplay = document.getElementById("message2"); // Agregamos un elemento para mostrar el mensaje de error

const requirements = [
  { regex: /.{8,}/, index: 0 },
  { regex: /[0-9]/, index: 1 },
  { regex: /[a-z]/, index: 2 },
  { regex: /[!@#$%^&*()-=_+]/, index: 3 },
  { regex: /[A-Z]/, index: 4 },
];

passwordInput.addEventListener("keyup", (e) => {
  let isValidPassword = true; // Variable para rastrear si la contraseña es válida

  requirements.forEach((item) => {
    const isValid = item.regex.test(e.target.value);
    const requirementItem = requirementList[item.index];

    if (isValid) {
      requirementItem.classList.add("valid");
      requirementItem.firstElementChild.className = "fa-solid fa-check";
    } else {
      requirementItem.classList.remove("valid");
      requirementItem.firstElementChild.className = "fa-solid fa-circle";
      isValidPassword = false; // Actualizamos la variable a false si algún requisito no se cumple
    }
  });

  // Mostramos el mensaje de error si la contraseña no es válida
  if (!isValidPassword) {
    errorDisplay.textContent = "La contraseña no cumple con los requisitos.";
  } else {
    errorDisplay.textContent = ""; // Si la contraseña es válida, limpiamos el mensaje de error
  }
});
