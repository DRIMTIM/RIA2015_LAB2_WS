		var imagenCargada = null;
		var imagenesCargadas = new Array();	
		var active = false;
		var selectedItemListaJugadores = null;		

   		$('[data-toggle="tooltip"]').tooltip();

		function mostrarImagen2(input) {
 			if (input.files && input.files[0]) {
  				var reader = new FileReader();
  				reader.onload = function (e) {
   					$('#miniatura').attr('src', e.target.result);
   					imagenCargada = e.target.result;
  				}
  				reader.readAsDataURL(input.files[0]);
 			}
		}

		function mostrarImagen(titulo, imagen, descripcion){
			bootbox.dialog({
			  message: "<div><img src='" + imagen + "' class='imagenDetalle'/><h4>" + descripcion + "</h4></div>",
			  title: titulo,
			  buttons: {
		    	aceptar: {
		    		label: "Cerrar",
		      		className: "btn-primary",
		      		callback: function() {}
		    	}
			  }
			});
		}

		function cargarImagen(event) {
			var files = event.target.files;
			var reader = new FileReader();
			var miniatura = document.getElementById("miniatura");
			for (var i = 0, file; file = files[i]; i++) {
				miniatura.title = file.name;
				reader.onload = function(event) {
					miniatura.src = event.target.result;
					imagenCargada = event.target.result;
				};
				reader.readAsDataURL(file);
			}
		}	

		function mostrarFuncionalidad(funcionalidad) {
			if (funcionalidad == "INGRESAR") {
				$("#formIngresar").show();
				$("#formVer").hide();
				$("#linkIngresar").attr("class", "active");
				$("#linkVer").attr("class", "");
				$("#vistaBtns").hide();
				$('#listaImgs').hide();
			} else {
				$("#formIngresar").hide();
				$("#formVer").show();
				if(imagenesCargadas.length > 0){
					$("#vistaBtns").show();	
				}
				$("#linkIngresar").attr("class", "");
				$("#linkVer").attr("class", "active");
				mostrarImagenesCargadas();
			}

		}	
		
		function mostrarConfirmacion(titulo, mensaje, funcionAceptar){
			bootbox.dialog({
			  message: mensaje,
			  title: titulo,
			  buttons: {
			  	cancelar: {
				  label: "Cancelar",
			      className: "btn-primary",
			      callback: function() {}
		    	},
		    	aceptar: {
		    		label: "Aceptar",
		      		className: "btn-primary",
		      		callback: function() {
		        		funcionAceptar();
		      		}
		    	}
			  }
			});
		}

		function mostrarError(mensaje){
			bootbox.dialog({
			  message: mensaje,
			  title: "Error!",
			  buttons: {
		    	aceptar: {
		    		label: "Cerrar",
		      		className: "btn-primary",
		      		callback: function() {}
		    	}
			  }
			});
		}		

		function limpiarCampos(){
			$("#archivoImagen").val(null);
			$("#tituloImagen").val(null);
			$("#descripcionImagen").val(null);
			$("#miniatura").attr("src", "../images/upload.png");
			$(":file").filestyle('clear');
		}

		$("#btnSubirImg").click(function(){
			guardarImagen();
 		});	

 		function aleatorio(inferior,superior){
    		var numPosibilidades = superior - inferior;
    		var aleat = Math.random() * numPosibilidades;
    		aleat = Math.round(aleat);
    		return parseInt(inferior) + aleat;
		};
		/*Funciones Diegosincas*/
		
		function getJugadoresEquipo(equipo) {
			var id= equipo.id;
		}
		
		function mostrarModal(source) {
			var id = source.getAttribute('data-identity');
			findByIdCallback(id, printListJugadores);
			$('#modal-contenedor-jugadores').modal('show');
		}
		
		function actualizarDetalleJugador(_this) {
			
			var jugador = _this.data("jugador");
			if(selectedItemListaJugadores != null) {
				selectedItemListaJugadores.attr("class", "list-group-item ");
			}
			_this.attr("class", "list-group-item active");
			selectedItemListaJugadores = _this;
			$("#nombre-jugador").text('');
			$("#posicion-jugador").text('');
			$("#edad-jugador").text('');
			$("#fecha-nacimiento-jugador").text('');
			$("#nacionalidad-jugador").text('');
			$("#rojas-jugador").text('');
			$("#goles-jugador").text('');
			$("#amarillas-jugador").text('');
			$("#faltas-jugador").text('');
			$("#contenedor-imagen-jugador").attr("src", "");
			
			$("#nombre-jugador").text(jugador.nombre);
			$("#posicion-jugador").text(jugador.posicion);
			$("#edad-jugador").text(jugador.edad);
			$("#fecha-nacimiento-jugador").text(formatFecha(jugador.fechaNacimiento));
			$("#nacionalidad-jugador").text(jugador.nacionalidad);
			$("#rojas-jugador").text(jugador.rojas);
			$("#goles-jugador").text(jugador.goles);
			$("#amarillas-jugador").text(jugador.amarillas);
			$("#faltas-jugador").text(jugador.faltas);
			$("#contenedor-imagen-jugador").attr("src", "data:image/jpg; base64, "+jugador.urlImagen);
		}

		function printListJugadores(data) {
			var divListJugadores = $('#ul-list-jugadores');
			var firstElement = null;
			divListJugadores.html('');
			for(i=0; i<data.jugador.length; i++) {
				var element = document.createElement("li");
				element.setAttribute("class", "list-group-item");
				element.setAttribute("id", data.jugador[i].id);
				element.textContent = data.jugador[i].nombre;
				divListJugadores.append(element);
				//Por jquery guardamos el objeto
				var createdElement = $('#'+data.jugador[i].id);

				var jugador = data.jugador[i];
				createdElement.data("jugador",jugador);
				createdElement.css("cursor", "pointer");
				//Bindeamos el onClick
				createdElement.on("click", function() {
					actualizarDetalleJugador($(this));
				});
				if(i==0)
					firstElement = createdElement;
			}
			firstElement.click();
		}
	