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

		function mostrarImagen3(titulo, imagen, descripcion){
			modal({
				type: 'inverted', //Type of Modal Box (alert | confirm | prompt | success | warning | error | info 
					//| inverted | primary)
				title: titulo, //Modal Title
				text: "<div><img src='" + imagen + "' class='img-thumbnail' width='900' height='750'/><h4>" 
												+ descripcion + "</h4></div>", //Modal HTML Content
				size: 'normal', //Modal Size (normal | large | small)
				/*buttons: [{
					text: 'Aceptar', //Button Text
					val: 'ok', //Button Value
					eKey: true, //Enter Keypress
					addClass: 'btn-light-blue', //Button Classes (btn-large | btn-small | btn-green | btn-light-green 
					//| btn-purple | btn-orange | btn-pink | btn-turquoise | btn-blue | btn-light-blue | btn-light-red 
					//| btn-red | btn-yellow | btn-white | btn-black | btn-rounded | btn-circle | btn-square | btn-disabled)

					onClick: function(argument) {
						console.log(argument);
						testing();
					}
				}, ],*/
				center: true, //Center Modal Box?
				autoclose: false, //Auto Close Modal Box?
				callback: null, //Callback Function after close Modal (ex: function(result){alert(result);})
				onShow: function(r) {}, //After show Modal function
				closeClick: true, //Close Modal on click near the box
				closable: true, //If Modal is closable
				theme: 'atlant', //Modal Custom Theme
				animate: false, //Slide animation
				background: 'rgba(0,0,0,0.35)', //Background Color, it can be null
				zIndex: 1050, //z-index
				/*buttonText: {
					ok: 'Aceptar',
					yes: 'Yes',
					cancel: 'Cancel'
				},*/
				template: '<div class="modal-box"><div class="modal-inner"><div class="modal-title">' +
							'<a class="modal-close-btn"></a></div><div class="modal-text"></div>' +
							'<div class="modal-buttons"></div></div></div>',
				_classes: {
					box: '.modal-box',
					boxInner: ".modal-inner",
					title: '.modal-title',
					content: '.modal-text',
					buttons: '.modal-buttons',
					closebtn: '.modal-close-btn'
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

		function cargarItemListaImagenes(indice, titulo, imagen, descripcion){

			var lista = "";
			itemLista = "<li id='itemList_" + indice + "' class='list-group-item' >" +
								"<div class='panel-group' >" +
									"<div id='itemListImg_" + indice + "' style='display: inline;'>" + 	
										"<img id='itemImg_" + indice + "'" +
										"src='" + imagen + "' class='img-thumbnail imagen' >" +
									"</div>" +
									"<div id='itemListData_" + indice + "' style='display: inline;'>" + 
										"<h3 style='display: inline;'> " + titulo + " </h3>" +
										"<p style='display: inline;'> { " + descripcion + " } </p>" +
									"</div>" +
								"</div>" +		
							"</li>";

			$('#listaImagenes').append(itemLista);			

			var obj = $("#itemImg_" + indice);
			obj.on("click", {titulo : titulo, imagen : imagen, descripcion: descripcion}, function(event) {
				mostrarImagen3(event.data.titulo,event.data.imagen,event.data.descripcion);
			});

		}

		function mostrarImagenesCargadas(){

			$("#myCarousel").show();
			$(".carousel-inner").html(null);
			$('.carousel-indicators').html(null);
			$('#listaImagenes').html(null);

			if(imagenesCargadas.length > 0){
				$("#sinImagenes").hide();
				var indicator = "";
				var item = "";

				for(var i = 0; i < imagenesCargadas.length; i++){
					var titulo = imagenesCargadas[i].titulo;
					var imagen = imagenesCargadas[i].archivo;
					var descripcion = imagenesCargadas[i].descripcion;

					if(i == 0){
						item = "<div class='item active'>" +
						        "<img id='item_" + i + "'" + "class='img-rounded img-thumbnail' src='" + imagen 
						        + "'" + "alt='" + titulo + "'" 
						        + "width='500' height='350'>" + "<div class='carousel-caption'>" +
						        	"<h3>" + titulo + "</h3>" + 
						        	"<p>" + descripcion + "</p>" +
						        "</div>" +
						    "</div>";

						indicator = "<li data-target='#myCarousel' data-slide-to='" + i + "'" + "class='active'></li>";

					}else{

						item = "<div class='item'>" +
						        "<img id='item_" + i + "'" + "class='img-rounded img-thumbnail' src='" + imagen + "'" 
						        + "alt='" + titulo + "'" 
						        + "width='500' height='350'>" + "<div class='carousel-caption'>" +
						        	"<h3>" + titulo + "</h3>" + 
						        	"<p>" + descripcion + "</p>" +
						        "</div>" +
						    "</div>";

						indicator = "<li data-target='#myCarousel' data-slide-to='" + i + "'" + "></li>";

					}		

					cargarItemListaImagenes(i, titulo, imagen, descripcion);

					$('.carousel-indicators').append(indicator);
					$('.carousel-inner').append(item);

					item = null;				

				}
			}else{
				$("#sinImagenes").show();
				$("#myCarousel").hide();
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

		function guardarImagen(){
			var tituloImagen = $("#tituloImagen").val();
			var descripcionImagen = $("#descripcionImagen").val();
			if(imagenCargada != null && imagenCargada != "" && imagenCargada != undefined){
				if(tituloImagen != null && tituloImagen != "" && tituloImagen != undefined){
					if(descripcionImagen != null && descripcionImagen != "" && descripcionImagen != undefined){
						mostrarConfirmacion("Confirmación", "Esta seguro que desea guardar la imagen?", function (){
							var imagen = new Object();
							imagen.archivo = imagenCargada;
							imagen.titulo = tituloImagen;
							imagen.descripcion = descripcionImagen;
							imagenesCargadas[imagenesCargadas.length] = imagen;
							limpiarCampos();
						});
					}else{
						mostrarError("Debe ingresar una descripción!");
					}
				}else{
					mostrarError("Debe ingresar un titulo!");
				}
			}else{
				mostrarError("Debe seleccionar una imagen!");
			}		
		}		

		function limpiarCampos(){
			$("#archivoImagen").val(null);
			$("#tituloImagen").val(null);
			$("#descripcionImagen").val(null);
			$("#miniatura").attr("src", "../images/upload.png");
			$(":file").filestyle('clear');
		}
 
		/*$("#archivoImagen").change(function(){
 			cargarImagen(event);
		});*/

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
	