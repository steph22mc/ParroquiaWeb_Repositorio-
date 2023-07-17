/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*=============== SHOW HIDDEN - PASSWORD ===============*/
const showHiddenPass = (loginPassClass, loginEyeClass) => {
    const input = document.querySelector(`.${loginPassClass}`),
        iconEye = document.querySelector(`.${loginEyeClass}`)

    iconEye.addEventListener('click', () => {
        // Cambiar password a text
        if (input.type === 'password') {
            input.type = 'text'

            iconEye.classList.add('ri-eye-line')
            iconEye.classList.remove('ri-eye-off-line')
        } else {
            input.type = 'password'

            iconEye.classList.remove('ri-eye-line')
            iconEye.classList.add('ri-eye-off-line')
        }
    })
}

showHiddenPass('reset__pass', 'reset__eye');

const showHiddenPass2 = (loginPassClass, loginEyeClass) => {
    const input = document.querySelector(`.${loginPassClass}`),
        iconEye = document.querySelector(`.${loginEyeClass}`)

    iconEye.addEventListener('click', () => {
        // Cambiar password a text
        if (input.type === 'password') {
            input.type = 'text'

            iconEye.classList.add('ri-eye-line')
            iconEye.classList.remove('ri-eye-off-line')
        } else {
            input.type = 'password'

            iconEye.classList.remove('ri-eye-line')
            iconEye.classList.add('ri-eye-off-line')
        }
    })
}

showHiddenPass('reset__pass2', 'eye2');