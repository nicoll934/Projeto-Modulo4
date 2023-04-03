const hamburguer = document.querySelector('.hamburguer');

hamburguer.addEventListener('click', menuOn);

function menuOn() {
    const container = document.querySelector('.container')
    container.classList.toggle('show-menu');
    const hasShowMenu = container.classList.contains('show-menu');
    hamburguer.setAttribute('aria-expanded', hasShowMenu);

    if(hasShowMenu) {
        hamburguer.setAttribute('aria-label', 'Fechar menu');
    } 
    
    else {
        hamburguer.setAttribute('aria-label', 'Abrir menu');
    }
}

function fecharModal() {
    const mostrarModalOn = mostrarModal.classList.contains('mostrar-modal');

    if(mostrarModalOn) {
        mostrarModal.classList.remove('mostrar-modal');
        inputNome.value = '';
        inputEmail.value = '';
    }
}

// SCROLL ANIMAÇÃO
const target = document.querySelectorAll('[data-soscamp]');
const animationFade = 'fadeOn';

function soscampScroll() {
    const windowTop = window.pageYOffset + ((window.innerHeight * 3) / 4);

    target.forEach(function(item) {
        if(windowTop > item.offsetTop) {
            item.classList.add(animationFade);
        } else {
            item.classList.remove(animationFade);
        }
    })
}

if(target.length) {
    window.addEventListener('scroll', function() {
        soscampScroll();
    });
}

let abrirResposta = document.querySelectorAll('.button-resposta');

for(let count = 0; count < abrirResposta.length; count++) {
    abrirResposta[count].addEventListener('click', () => {
        let divRespostas = document.querySelectorAll('.box-resposta');
        divRespostas[count].classList.toggle('active-resposta');
    })

    console.log(abrirResposta[count]);
}