/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


let loader = document.querySelector('.loader-wrapper');
let logo = document.querySelector('.Logo-Text');
let logoSpan = document.querySelectorAll('.logo-parts');
let main = document.querySelector('header');
let main2 = document.querySelector('.divMain');


window.addEventListener('DOMContentLoaded', () => {
    setTimeout(() => {
        logoSpan.forEach((span, index) => {
            setTimeout(() => {
                span.classList.add('active');
            }, (index + 1) * 100);
        });

        setTimeout(() => {
            logoSpan.forEach((span, index) => {
                setTimeout(() => {
                    span.classList.remove('active');
                    span.classList.add('fade');
                }, (span + 1) * 50);
            });
        }, 2000);

        setTimeout(() => {
            loader.style.top = '-100vh';
        }, 2300);

        setTimeout(() => {
            main.style.display = 'block';
            main.style.overflow = 'visible';
            main2.style.display = 'block';
            main2.style.overflow = 'visible';
            setTimeout(() => (main.style.opacity = 1, main2.style.opacity = 1), 50);
        }, 2700);
    })
})