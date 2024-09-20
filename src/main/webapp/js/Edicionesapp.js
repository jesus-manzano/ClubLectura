let el = selector => document.querySelector(selector);
class EdicionesCtrl {
    constructor() {
        this.srvURL="api/edicion";
        this.ediciones = [];
    }

    init() {
        el('#fAlta').addEventListener('submit', event => {this.alta(event)});
        this.cargaEdiciones();
    }

    cargaEdiciones() {
        return fetch(this.srvURL)
            .then(response => response.json())
            .then(ediciones => {
                this.ediciones=ediciones;
                this.visualizaEdiciones();
                return true;
            })
            .catch(() => {
            el('#errores').innerHTML="Error en conexion";
            console.error("Error en conexion");
            return false;
        });
    }

    visualizaEdiciones() {
        let ul = el('#lista');
        ul.innerHTML = '';
        this.ediciones.forEach(edicion => {
            let li = document.createElement('li');
            li.innerHTML = `<b>${edicion.isbn}</b> <b>${edicion.numero}</b> <b>${edicion.fecha}</b> <b>${edicion.editorial}</b> <b>${edicion.paginas}</b> <b>${edicion.tipo}</b>`
            ul.appendChild(li);
        });
    }

    alta(event) {
        event.preventDefault();
        let isbn = el('[name="isbn"]').value();
        let numero = el('[name="numero"]').value();
        let fecha = el('[name="fecha"]').value();
        let editorial = el('[name="editorial"]').value();
        let paginas = el('[name="paginas"]').value();
        let tipo = el('[name="tipo"]').value();
        console.log('alta de edicion %s - %s: %s, %s, %s, %s', isbn, numero, fecha, editorial, paginas, tipo);
        let edicion = {
            isbn:isbn,
            numero:numero,
            fecha:fecha,
            editorial:editorial,
            paginas:paginas,
            tipo:tipo
        };
        this.enviaEdicion(edicion)
            .then(enviado => {
                if(enviado) {
                    el('#fAlta').reset();
                    this.cargaEdiciones();
                }
            });
    }

    enviaEdicion(edicion) {
        let enviado = false;
        return fetch(this.srvURL, {
            method:'POST',
            body: JSON.stringify(edicion),
            headers: {
                'Content-type': 'application/json',
                'accept': 'application/json'
            }
        }).then(response => {
            if(response.ok){
                enviado=true;
            }
            return response.json();
        }).then(response => {
            let error="";
            if(enviado === true){
                console.log(`Confirmada alta de edicion: ${response.isbn}, - ${response.numero}`);
            }else{
                console.warn(response);
                error = response[0].message;
            }
            el('#errores').innerHTML=error;
            return enviado;
        }).catch(ex => {
            el('#errores').innerHTML="Error en conexion";
            console.error("Error en conexion");
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
