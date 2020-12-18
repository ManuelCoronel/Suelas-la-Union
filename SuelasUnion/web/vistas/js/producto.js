function cambiarProducto() {
    let value = parseInt(document.getElementById("producto").value);
    var producto = "";
    console.log(typeof (value));
    switch (value) {
        case 1:
            producto = plantillas();
            console.log(producto);
            break;
        case 2:
            producto = suela();
            break;
        case 3:
            producto = suela();
            break;
        case 4:
            producto = tiras()
            break;
        case 5:
            producto = salpas()
            break;
        default:
            console.log("defaul");
            break;
    }
    document.getElementById("produc").innerHTML = producto;
}
function tiras() {
    var tira = `
    <!-- MODELO -->

    <div class="col-sm-6" style="margin-top: 40px;">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">Modelo</div>
                <input type="text" id="modelo" name="modelo" class="form-control">
            </div>
        </div>
    </div>
    <!-- Ancho -->

    <div class="col-sm-6" style="margin-top: 40px;">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">Ancho</div>
                <input type="number" id="ancho" name="ancho" class="form-control">
            </div>
        </div>
    </div>

    <!-- COLOR -->

    <div class="col-sm-6" style="margin-top: 40px;">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">Color</div>
                <input type="text" id="color" name="color" class="form-control">
            </div>
        </div>
    </div>
    
    <!-- Precio-->
    <div class="col-sm-6" style="margin-top: 40px;">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">Precio</div>
                <input type="number" id="precio" name="precio" class="form-control">
            </div>
        </div>
    </div>
    <!-- CANTIDAD -->

    <div class="col-sm-6" style="margin-top: 40px;">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">Cantidad</div>
                <input type="number" id="cantidad" name="cantidad" class="form-control">
            </div>
        </div>
    </div>
    

    `;
    return tira;
}
function salpas() {
    let salpa = `
    <!-- CANTIDAD -->

    <div class="col-sm-6" style="margin-top: 40px;">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">Cantidad</div>
                <input type="number" id="cantidad" name="cantidad" class="form-control">
            </div>
        </div>
    </div>
    <!-- Precio-->
    <div class="col-sm-6" style="margin-top: 40px;">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">Precio</div>
                <input type="number" id="precio" name="precio" class="form-control">
            </div>
        </div>
    </div>
    
    

    `;
    return salpa;

}
function suela() {
    let suela = `
    <!-- MODELO -->
    
    <div class="col-sm-6" style="margin-top: 40px;">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">Modelo</div>
                <input type="text" id="modelo" name="modelo" class="form-control">
            </div>
        </div>
    </div>
    <!-- TIPO -->

    <div class="col-sm-6" style="margin-top: 40px;">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">Tipo</div>
                <select name="tipo" id="tipo" class="form-control">
                    <option value="0" selected>Seleccionar Tipo</option>
                    <option value="1">Mujer</option>
                    <option value="2">Niña</option>
                </select>
            </div>
        </div>
    </div>
    
    <!-- TALLA -->

    <div class="col-sm-6" style="margin-top: 40px;">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">Talla</div>
                <input type="number" id="talla" name="talla" class="form-control">
            </div>
        </div>
    </div>
    <!-- COLOR -->

        <div class="col-sm-6" style="margin-top: 40px;">
            <div class="form-group">
                <div class="input-group">
                    <div class="input-group-addon">Color</div>
                    <input type="text" id="color" name="color" class="form-control">
                </div>
            </div>
        </div>
    <!-- Precio-->
    <div class="col-sm-6" style="margin-top: 40px;">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">Precio</div>
                <input type="number" id="precio" name="precio" class="form-control">
            </div>
        </div>
    </div>
    <!-- CANTIDAD -->

    <div class="col-sm-6" style="margin-top: 40px;">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">Cantidad</div>
                <input type="number" id="cantidad" name="cantidad" class="form-control">
            </div>
        </div>
    </div>
    

    
    `;
    return suela;

}
function plantillas() {
    let pantilla = `
    <!-- TALLA -->

    <div class="col-sm-6" style="margin-top: 40px;">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">Talla</div>
                <input type="number" id="talla" name="talla" class="form-control">
            </div>
        </div>
    </div>

    <!-- MODELO -->

    <div class="col-sm-6" style="margin-top: 40px;">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">Modelo</div>
                <input type="text" id="modelo" name="modelo" class="form-control">
            </div>
        </div>
    </div>
    
    <!-- TIPO -->

    <div class="col-sm-6" style="margin-top: 40px;">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">Tipo</div>
                <select name="tipo" id="tipo" class="form-control">
                    <option value="0" selected>Seleccionar Tipo</option>
                    <option value="1">Mujer</option>
                    <option value="2">Niña</option>
                </select>
            </div>
        </div>
    </div>
    
    <!-- Precio-->
    <div class="col-sm-6" style="margin-top: 40px;">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">Precio</div>
                <input type="number" id="precio" name="precio" class="form-control">
            </div>
        </div>
    </div>
    
    <!-- CANTIDAD -->

    <div class="col-sm-6" style="margin-top: 40px;">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">Cantidad</div>
                <input type="number" id="cantidad" name="cantidad" class="form-control">
            </div>
        </div>
    </div>
    
    

    `;
    return pantilla;
}

function Elimina(NodoBoton) {

    //recibimos el boton como parametro, obtendremos el tr que lo contiene de la siguiente manera
    //Como nuestro boton es hijo de un td, y este td de el tr, debemos invocar dos veces parentNode
    //Esto para llegar a tener el TR
    var TR = NodoBoton.parentNode.parentNode;

    //ahora que ya tenemos el padre TR, podemos eliminarlo de la siguiente manera
    //junto a todos sus hijos

    document.getElementById("tablaProductos").removeChild(TR);
}

function Agrega() {

    //obtenemos los valores de nuestra caja de texto
    let producto = parseInt(document.getElementById("producto").value);
    let descripcion = descripcionProducto(producto);
    let precio=document.getElementById("precio").value;
    //creamos un objeto tr que anexaremos a nuestra tabla llamada tableProductos
    var TR = document.createElement("tr");

    //creamos 4 elementos td en donde iran los datos y uno cuarto donde ira un boton para eliminar
    var TD1 = document.createElement("td");
    var TD2 = document.createElement("td");
    var TD3 = document.createElement("td");
    var TD4 = document.createElement("td");

    //asignamos los valores a nuestros td por medio del atributo innerHTML, el cual tiene el contenido HTML de un Nodo
    TD1.innerHTML = producto;
    TD2.innerHTML = descripcion;
    TD3.innerHTML = precio;

    //A continuación asignamos contenido html a nuestro cuarto td
    //esta es una forma de crear elementos tambien, dando el codigo html a un Nodo
    TD4.innerHTML = `
    <button onclick='Elimina(this)' class="white-color" data-toggle="tooltip"
    data-placement="top" title="Delete">
    <i class="zmdi zmdi-delete"></i>
    </button>`;

    //Ahora proseguimos a agregar los hijos TD al Padre TR
    //Esta es otra manera de crear elementos HTML, por medio de el metodo appendChild
    TR.appendChild(TD1);
    TR.appendChild(TD2);
    TR.appendChild(TD3);
    TR.appendChild(TD4);

    //Por ultimo asignamos nuestro TR a la tabla con id tablaProductos
    document.getElementById("tablaProductos").appendChild(TR)

    //limpiamos nuestros inputs para agregar ma datos, y ponemos el foco nuevamente en el input de codigo
    document.getElementById("txtCodigo").value = ""
    document.getElementById("txtNombre").value = "";
    document.getElementById("txtPrecio").value = "";
    document.getElementById("txtCodigo").focus();
}
function descripcionProducto(producto) {
    let descripcion="";
    switch (producto) {
        case 1:
            descripcion=document.getElementById("talla").value +" - "+document.getElementById("modelo").value;
            break;
        case 2:
        case 3:
            descripcion=document.getElementById("modelo").value+" - "+
                        document.getElementById("tipo").value+" - "+
                        document.getElementById("talla").value+" - "+
                        document.getElementById("color").value;
            break;
        case 4:
            descripcion=document.getElementById("modelo").value+" - "+
                        document.getElementById("ancho").value+" - "+
                        document.getElementById("color").value;
            break;
        case 5:
            descripcion=document.getElementById("cantidad").value;
            break;

        default:
            break;
    }
    return descripcion;

}
