var servidor = $(location).attr('origin');
var graficoUnoEtiqueta = [], graficoUnoDato = [], graficoUnoId = []
var graficoDosEtiqueta = [], graficoDosDato = [], graficoDosId = []
var graficoTresEtiqueta = [], graficoTresDato = [], graficoTresId = []
var graficoCuatroEtiqueta = [], graficoCuatroDato = [], graficoCuatroId = []

//Gráfico 1

async function graficoUno() {

	await obtenerGraficoUno()

	var ctx = document.getElementById('myChart').getContext('2d');

	var myChart = new Chart(ctx, {
		type: 'bar',
		data: {
			//labels: ['ASEPCO', 'SODIMAC', 'En blanco'],
			labels: graficoUnoEtiqueta,
			datasets: [{
				label: '# de entrevistas',
				//data: [21, 483, 174],
				data: graficoUnoDato,
				backgroundColor: [
					'rgba(255, 99, 132, 0.2)',
					'rgba(54, 162, 235, 0.2)',
					'rgba(255, 206, 86, 0.2)',
					'rgba(75, 192, 192, 0.2)',
					'rgba(153, 102, 255, 0.2)',
					'rgba(255, 159, 64, 0.2)'
				],
				borderColor: [
					'rgba(255, 99, 132, 1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)'
				],
				borderWidth: 1
			}]
		},
		options: {
			scales: {
				y: {
					beginAtZero: true
				}
			}
		}
	});
}

graficoUno()

//Gráfico 2

async function graficoDos() {

	await obtenerGraficoDos()

	var ctx2 = document.getElementById('myChart2').getContext('2d');
	
	var myChart2 = new Chart(ctx2, {
		type: 'line',
		data: {
			//labels: ['07-09', '08-09', '09-09', '10-09', '13-09', '14-09', '15-09', '16-09', '20-09', '21-09'],
			labels: graficoDosEtiqueta,
			datasets: [{
				label: '# de entrevistas',
				//data: [27, 22, 16, 19, 26, 24, 25, 26, 15, 32],
				data: graficoDosDato,
				fill: false,
				borderColor: 'rgb(75, 192, 192)',
				tension: 0.1
			}]
		},
		options: {
			scales: {
				y: {
					beginAtZero: true
				}
			}
		}
	});

}

graficoDos()

//Gráfico 3

async function graficoTres() {

	await obtenerGraficoTres()

	var ctx3 = document.getElementById('myChart3').getContext('2d');

	var myChart3 = new Chart(ctx3, {
		type: 'line',
		data: {
			//labels: ['David', 'Emiliano', 'Jesús', 'Juan Carlos', 'Judith', 'Romina', 'En blanco'],
			labels: graficoTresEtiqueta,
			datasets: [{
				label: '# de entrevistas',
				//data: [27, 177, 5, 226, 43, 26, 174],
				data: graficoTresDato,
				fill: false,
				borderColor: 'rgb(75, 192, 192)',
				tension: 0.1
			}]
		},
		options: {
			scales: {
				y: {
					beginAtZero: true
				}
			}
		}
	}); 
}

graficoTres()

//Gráfico 4

async function graficoCuatro() {

	await obtenerGraficoCuatro()

	var ctx4 = document.getElementById('myChart4').getContext('2d');

	var myChart4 = new Chart(ctx4, {
		type: 'bar',
		data: {
			//labels: ['No validado', 'Nombre no corresponde', 'RUT duplicado', 'RUT Erróneo', 'Validado', 'En blanco'],
			labels: graficoCuatroEtiqueta,
			datasets: [{
				label: '# de entrevistas',
				//data: [68, 5, 2, 14, 385, 204],
				data: graficoCuatroDato,
				backgroundColor: [
					'rgba(255, 99, 132, 0.2)',
					'rgba(54, 162, 235, 0.2)',
					'rgba(255, 206, 86, 0.2)',
					'rgba(75, 192, 192, 0.2)',
					'rgba(153, 102, 255, 0.2)',
					'rgba(255, 159, 64, 0.2)'
				],
				borderColor: [
					'rgba(255, 99, 132, 1)',
					'rgba(54, 162, 235, 1)',
					'rgba(255, 206, 86, 1)',
					'rgba(75, 192, 192, 1)',
					'rgba(153, 102, 255, 1)',
					'rgba(255, 159, 64, 1)'
				],
				borderWidth: 1
			}]
		},
		options: {
			scales: {
				y: {
					beginAtZero: true
				}
			}
		}
	});

}

graficoCuatro()

//Funciones extra

async function obtenerGraficoUno() {
	const apiUrl = servidor + "/api/v1/graficos/graficouno"

	const response = await fetch(apiUrl)
	const barChatData = await response.json()
	//console.log(barChatData.map((x) => x.cantidadEntrevistas))

	const cantidad = barChatData.map((x) => x.cantidadEntrevistas)
	//console.log(salary)
	const identificador = barChatData.map((x) => x.idCliente)
	const nombrecliente = barChatData.map((x) => x.nombreCliente)

	graficoUnoDato = cantidad
	graficoUnoId = identificador
	graficoUnoEtiqueta = nombrecliente
}

async function obtenerGraficoDos() {
	const apiUrl = servidor + "/api/v1/graficos/graficodos"

	const response = await fetch(apiUrl)
	const barChatData = await response.json()
	//console.log(barChatData.map((x) => x.cantidadEntrevistas))

	const cantidad = barChatData.map((x) => x.cantidadEntrevistas)
	//console.log(salary)
	const identificador = barChatData.map((x) => x.fechaIngreso)
	const nombrecliente = barChatData.map((x) => x.fechaIngreso)

	graficoDosDato = cantidad
	graficoDosId = identificador
	graficoDosEtiqueta = nombrecliente
}

async function obtenerGraficoTres() {
	const apiUrl = servidor + "/api/v1/graficos/graficotres"

	const response = await fetch(apiUrl)
	const barChatData = await response.json()
	//console.log(barChatData.map((x) => x.cantidadEntrevistas))

	const cantidad = barChatData.map((x) => x.cantidadEntrevistas)
	//console.log(salary)
	const identificador = barChatData.map((x) => x.nombreReclutador)
	const nombrecliente = barChatData.map((x) => x.nombreReclutador)

	graficoTresDato = cantidad
	graficoTresId = identificador
	graficoTresEtiqueta = nombrecliente
}

async function obtenerGraficoCuatro() {
	const apiUrl = servidor + "/api/v1/graficos/graficocuatro"

	const response = await fetch(apiUrl)
	const barChatData = await response.json()
	//console.log(barChatData.map((x) => x.cantidadEntrevistas))

	const cantidad = barChatData.map((x) => x.cantidadEntrevistas)
	//console.log(salary)
	const identificador = barChatData.map((x) => x.detalleEstado)
	const nombrecliente = barChatData.map((x) => x.detalleEstado)

	graficoCuatroDato = cantidad
	graficoCuatroId = identificador
	graficoCuatroEtiqueta = nombrecliente
}
