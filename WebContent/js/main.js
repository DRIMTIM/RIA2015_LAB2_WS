// The root URL for the RESTful services
var rootURL = "http://localhost:8080/jatrik-reloaded/rest/equipos";

// Retrieve wine list when application starts 
findAll();

// Nothing to delete in initial application state
$('#btnDelete').hide();

// Register listeners
$('#btnSearch').click(function() {
	search($('#searchKey').val());
	return false;
});

// Trigger search when pressing 'Return' on search key input field
$('#searchKey').keypress(function(e){
	if(e.which == 13) {
		search($('#searchKey').val());
		e.preventDefault();
		return false;
    }
});

// Replace broken images with generic wine bottle
$("img").error(function(){
  $(this).attr("src", "pics/generic.jpg");

});

function search(searchKey) {
	if (searchKey == '') 
		findAll();
	else
		findByName(searchKey);
}

function findAll() {
	console.log('findAll');
	$.ajax({
		type: 'GET',
		url: rootURL + '/lista',
		dataType: "json", // data type of response
		success: renderList
	});
}

function findByName(searchKey) {
	console.log('findByName: ' + searchKey);
	$.ajax({
		type: 'GET',
		url: rootURL + '/' + searchKey,
		dataType: "json",
		success: renderList 
	});
}

function findById(id) {
	console.log('findById: ' + id);
	$.ajax({
		type: 'GET',
		url: rootURL + '/' + id,
		dataType: "json",
		success: function(data){
			$('#btnDelete').show();
			console.log('findById success: ' + data.name);
			currentWine = data;
			renderDetails(currentWine);
		}
	});
}

function renderList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data;
	console.log("Lista de Equipos: ");
	console.log(list);

	var tableFormat = 	"<thead>" +
					      	"<tr>" +
					        	"<th>Nombre</th>" +
					        	"<th>Fundado</th>" +
					        	"<th>Cant. Jugadores</th>" +
					      	"</tr>" +
					    "</thead>" +
					    "<tbody></tbody>";

	$('#tblEquipos').html(tableFormat);

	if(list.equipo.length > 1){
		$.each(list.equipo, function(index, e) {
			console.log("Equipo " + index +": ");
			console.log(e);
			formatFecha(e.fechaCreacion);
			$('#tblEquipos')
				.append('<tr><td><a href="#" data-identity="' + e.id + '">' + e.nombre + '</a></td>'
						+ '<td>' + formatFecha(e.fechaCreacion) + '</td><td>' + e.jugadores.length + '</td></tr>');
		});
	}else{

		$.each(list, function(index, e) {
			console.log("Equipo " + index +": ");
			console.log(e);
			$('#tblEquipos')
				.append('<tr><td><a href="#" data-identity="' + e.id + '">' + e.nombre + '</a></td>'
						+ '<td>' + e.fechaCreacion + '</td><td>' + e.jugadores.length + '</td></tr>');
		});

	}


}

function renderDetails(wine) {
	$('#wineId').val(wine.id);
	$('#name').val(wine.name);
	$('#grapes').val(wine.grapes);
	$('#country').val(wine.country);
	$('#region').val(wine.region);
	$('#year').val(wine.year);
	$('#pic').attr('src', 'pics/' + wine.picture);
	$('#description').val(wine.description);
}

// Helper function to serialize all the form fields into a JSON string
function formToJSON() {
	var wineId = $('#wineId').val();
	return JSON.stringify({
		"id": wineId == "" ? null : wineId, 
		"name": $('#name').val(), 
		"grapes": $('#grapes').val(),
		"country": $('#country').val(),
		"region": $('#region').val(),
		"year": $('#year').val(),
		"picture": currentWine.picture,
		"description": $('#description').val()
		});
}

function formatFecha(fecha){

	var fechaHora = fecha.split("T");
	var f = fechaHora[0].split("-");
	var fechaRet = f[2] + '/' + f[1] + '/' + f[0];
	return fechaRet;

}
