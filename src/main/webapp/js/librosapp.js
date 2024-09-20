let el = selector => document.querySelector(selector);
class LibrosCtrl {
    constructor() {
        this.srvUrl="api/libro"; //REST service url
//view-model
        this.libros = [];
    }
    init() {
        el('#fAlta').addEventListener('submit', event => {
            this.alta(event);
        });
        this.cargaLibros();
    }
    cargaLibros() {
        return fetch( this.srvUrl )
            .then(response => response.json())
            .then( libros => {
                this.libros=libros;
                this.visualizaLibros();
                return true;
            })
            .catch(() => { //Network error
                el('#errores').innerHTML="Error en conexión";
                console.error("Error en conexión");
                return false;
            });
    }

    visualizaLibros() {
        let ul = el('#lista');
        ul.innerHTML = '';
        this.libros.forEach(libro => {
            let li = document.createElement('li');
            li.innerHTML = `<b>${libro.isbn}</b> <b>${libro.titulo}</b> <b>${libro.descripcion}</b> ${libro.url}`;
            ul.appendChild(li);
        });
    }


    alta (event) {
        event.preventDefault();
        let isbn = el('[name="isbn"]').value;
        let titulo = el('[name="titulo"]').value;
        let descripcion = el('[name="descripcion"]').value;
        let url = el('[name="url"]').value;
        console.log('alta de libro %s: %s, %s, %s', isbn, titulo,descripcion,url);
        let libro = {
            isbn: isbn,
            titulo: titulo,
            descripcion: descripcion,
            url: url
        };
        this.enviaLibro( libro )
            .then( enviado => {
                if (enviado) {
                    el('#fAlta').reset();
                    this.cargaLibros();
                }
            });
    }
    enviaLibro(libro) { //ajax request
        let enviado = false;
        return fetch( this.srvUrl, {
            method: 'POST',
            body: JSON.stringify(libro),
            headers: {
                'Content-type': 'application/json',
                'accept': 'application/json'
            }
        }).then( response => {
            if (response.ok) {
                enviado = true; //libro accepted in server
            } //else bean-validation errors!
            return response.json();
        }).then( response => {
            let error="";
            if ( enviado === true ) {
                console.log(`Confirmada alta de libro: ${response.isbn}`);
            } else { //show bean-validation errors
                console.warn(response);
                error=response[0].message;
            }
            el('#errores').innerHTML=error;
            return enviado;
        })
            .catch( ex => { //Network error
                el('#errores').innerHTML="Error en conexión";
                console.error("Error en conexión");
                return enviado;
            });
    }


}
window.addEventListener('load', () => {
//Create and initialize controller
    window.ctrl = new LibrosCtrl();
    console.log('Inicializando controlador libros');
    ctrl.init();
});
