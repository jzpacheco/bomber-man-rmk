const mapContainer = document.getElementById("map")
const playerDiv = document.createElement('div')

let game;
let player;
let connectionType;
playerDiv.id = 'player'
mapContainer.appendChild(playerDiv)


const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/bomberman-ws'
})

stompClient.onConnect = (frame) => {
    console.log('Connected: '+frame)

    stompClient.subscribe('/topic/game', (message) => {
        
        
        game = JSON.parse(message.body)
       // player = game.player
        console.log(game)
        renderMap(game)
    })
    

    if (connectionType === 'JOIN_GAME'){
        joinGame()
    }else{
        console.log("entrou")
        createGame()
    }
}



function renderMap(game) {
    
    let map = game.map
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
    const name = document.getElementById('user').value
    var player = {
        name: name,
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

// createMap()
// renderMap();
// updatePlayerPosition();

