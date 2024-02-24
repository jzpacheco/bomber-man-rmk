const mapContainer = document.getElementById("map")
const playerDiv = document.createElement('div')

const login = document.getElementById("login")
const canvas = document.getElementById("canvas")
const ctx = canvas.getContext("2d")
const userName = document.getElementById('user').value

const EMPTY = 0;
const WALL = 1;
const FIXED_OBSTACLE = 2;

let game;
let player;
let connectionType;
playerDiv.id = 'player'
mapContainer.appendChild(playerDiv)
//TO-DO: Organize , test player insert and dockerize

const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/bomberman-ws'
})

stompClient.onConnect = (frame) => {
    login.classList.add("hidden")
    canvas.classList.remove("hidden")

    console.log('Connected: '+frame)

    stompClient.subscribe('/topic/game', (message) => {
        
        game = JSON.parse(message.body)
        renderMap(game)
    })

    fetch('https://localhost:8080/players',{
        method: 'POST',
        body: userName
    }).then((response) =>{
        if (response.ok){
            return response.json()
        }else {
            return Promise.reject(response);
        }
    }).then((data) => player = data.body)
    .catch((err)=>console.warn("Não foi possível cadastrar o usuário: "+ err))
    

    if (connectionType === 'JOIN_GAME'){
        joinGame()
    }else{
        console.log("entrou")
        createGame()
    }
}



function renderMap(game) {
    console.log(game.password)
    let map = game.map
    let cellSize = canvas.width / game.x
    console.log("infos")
    console.log("cellSize: "+cellSize)
    console.log("game.x "+game.y)
    console.log("game.y "+game.x)
    

    for (let x = 0; x <game.x; x++) {
        for (let y = 0; y < game.y; y++) {
            console.log("entrou?"+ x+" "+y)
            ctx.strokeRect(x * cellSize, y * cellSize, cellSize, cellSize);
        }  
    }

    map.forEach(row => {
        row.forEach(cellType => {
            var cell = document.createElement('div')
            cell.classList.add('cell')
            

            if (cellType === EMPTY){
                cell.classList.add('empty')
            } else if (cellType === WALL) {
                cell.classList.add('wall')
                
            } else if (cellType === FIXED_OBSTACLE) {
                cell.classList.add('fixed-obstacle')
            }

            mapContainer.appendChild(cell);
        })
        })
}

function drawObstacle(x, y, width, height, cornerRadius, type) {
    ctx.beginPath();
    ctx.moveTo(x + cornerRadius, y);
    ctx.arcTo(x + width, y, x + width, y + height, cornerRadius);
    ctx.arcTo(x + width, y + height, x, y + height, cornerRadius);
    ctx.arcTo(x, y + height, x, y, cornerRadius);
    ctx.arcTo(x, y, x + width, y, cornerRadius);
    ctx.closePath();
  
    ctx.fillStyle = 'orange';  // Define a cor de preenchimento como laranja
    ctx.stroke();
    ctx.fill();  // Preenche o interior com a cor laranja
  }

function createGame(){
    
    stompClient.publish({
        destination: "/app/createGame",
        body:JSON.stringify(player)
    })
}

function joinGame(){
    stompClient.publish({
        destination: "/app/joinGame",
        body:JSON.stringify(user)
    })
}



playerPosition = {x:0,y:0}


function updatePlayerPosition(){
    playerDiv.style.transform = `translate(${playerPosition.x *27}px, ${playerPosition.y *27}px)`
}

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket ', error)
}

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ', + frame.headers('message'))
    console.error('Additional details: '+ frame.body)
}

function connect(){
        stompClient.activate();
}



function getUser(){
    const user = document.getElementById("user")
    return user.value
}

/*

document.addEventListener('keydown', function (event){
    if (event.key === 'ArrowUp' && map[playerPosition.y - 1][playerPosition.x] === 0 ){
        playerPosition.y--
        updatePlayerPosition()
    }
    if (event.key === 'ArrowDown' && map[playerPosition.y + 1][playerPosition.x] === 0 ){
        playerPosition.y++
        updatePlayerPosition()
    }
    if (event.key === 'ArrowRight' && map[playerPosition.y][playerPosition.x  + 1] === 0 ){
        playerPosition.x++
        updatePlayerPosition()
    }
    if (event.key === 'ArrowLeft' && map[playerPosition.y][playerPosition.x -1 ] === 0 ){
        playerPosition.x--
        updatePlayerPosition()
    }
})
*/
$(function(){
    $("form").on('submit', (e) => {
        e.preventDefault()
         connect()
         connectionType = "CREATE_GAME"
         
    });
    //$("#connect").click(() => )
    $("#joinGame").click(() => {
        connect()
        connectionType = "JOIN_GAME"
    })
})


