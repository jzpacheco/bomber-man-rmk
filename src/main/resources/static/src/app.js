let game;

const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/bomberman-ws'
})

stompClient.onConnect = (frame) => {
    console.log('Connected: '+frame)

    stompClient.subscribe('/topic/game', (message) => {
        game = JSON.parse(message.body)
        console.log(game)
        renderMap(game)
    })

  console.log(game)  
}

function renderMap(game) {
    let mapContainer = document.getElementById("map")
    let map = game.map

    console.log(typeof game)

    map.forEach(row => {
        row.forEach(cellType => {
            var cell = document.createElement('div')
            cell.classList.add('cell')

            if (cellType === 0){
                cell.classList.add('empty')
            } else if (cellType === 1) {
                cell.classList.add('wall')
            } else {
                cell.classList.add('fixed-obstacle')
            }

            mapContainer.appendChild(cell);
        })
        })
}

function createGame(){
    let name = document.getElementById('user').value
    var player = {
        name: name,
      };

      console.log(player)
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
const mapContainer = document.getElementById('map')

const player = document.createElement('div')
player.id = 'player'
mapContainer.appendChild(player)

playerPosition = {x:0,y:0}


function updatePlayerPosition(){
    player.style.transform = `translate(${playerPosition.x *27}px, ${playerPosition.y *27}px)`
}




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
    $("form").on('submit', (e) => e.preventDefault());
    $("#connect").click(() => connect())
    $("#createGame").click(() => createGame())
})

// createMap()
// renderMap();
// updatePlayerPosition();

