

const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/bomberman-ws'
})

stompClient.onConnect = (frame) => {
    //setConnected(true)
    console.log('Connected: '+frame)
    stompClient.subscribe('/topic/game', (message) => {
        game = message.body
        console.log("game: " + message.body)
        //renderMap(game)
    })

    if (!game.players){
        createGame();
    }else{
        joinGame();
    }
    
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

function createGame(){
 
    var player = {
        id: 1,
        name: 'Exemplo',
        x: 10,
        y: 20
      };
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

function renderMap(game) {
    console.log("Game: "+ game)


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
    $("#createGame").click(() => connect())
})

// createMap()
// renderMap();
// updatePlayerPosition();

