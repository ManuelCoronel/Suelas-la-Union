function cambiarProductoDev() {
    let value = parseInt(document.getElementById("producto").value);
    var producto = "";
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
            producto = tiras();
            break;
        case 5:
            producto = salpas();
            break;
        default:
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
          <!-- ID pedido proveedor-->
    <div class="col-sm-6" style="margin-top: 40px;">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">ID</div>
                <input type="number" id="idproveedor" name="idproveedor" class="form-control">
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
          <!-- ID pedido proveedor-->
    <div class="col-sm-6" style="margin-top: 40px;">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">ID</div>
                <input type="number" id="idproveedor" name="idproveedor" class="form-control">
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
          <!-- ID pedido proveedor-->
    <div class="col-sm-6" style="margin-top: 40px;">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">ID</div>
                <input type="number" id="idproveedor" name="idproveedor" class="form-control">
            </div>
        </div>
    </div>

    `;
    return suela;

}
function plantillas() {
    let pantilla = `
        <!-- MODELO -->
    
    <div class="col-sm-6" style="margin-top: 40px;">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">Modelo</div>
                <input type="text" id="modelo" name="modelo" class="form-control">
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
    <!-- Precio-->
    <div class="col-sm-6" style="margin-top: 40px;">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">Precio</div>
                <input type="number" id="precio" name="precio" class="form-control">
            </div>
        </div>
    </div>
      <!-- ID pedido proveedor-->
    <div class="col-sm-6" style="margin-top: 40px;">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">ID</div>
                <input type="number" id="idproveedor" name="idproveedor" class="form-control">
            </div>
        </div>
    </div>

    `;
    return pantilla;
}
