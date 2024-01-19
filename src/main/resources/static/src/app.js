const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/bomberman-ws'
})

stompClient.onConnect = (frame) => {
    //setConnected(true)
    console.log('Connected: '+frame)
    stompClient.subscribe('/topic/game', (game) => {
        renderMap(game)
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

const mapSize = {x: 21, y:41}


const mapContainer = document.getElementById('map')

const player = document.createElement('div')
player.id = 'player'
mapContainer.appendChild(player)

playerPosition = {x:0,y:0}

map = []
function createMap(){
    for (let i = 0; i < mapSize.x; i++) {
        map[i] = []
        for (let j = 0; j < mapSize.y; j++) {
            if (i % 2 != 0 && j % 2 != 0){
                map[i][j] = 2
            } else if (isCorner(i,j)) {
                map[i][j] = 0
              } else {
                map[i][j] = Math.floor(Math.random() * 2);
            }
        }
        
    }
}

function isCorner(i,j){
    return (i <= 1 && j <= 1) || (i <= 1 && j >= mapSize.y - 2) || (i >= mapSize.x - 2 && j <= 1) || (i >= mapSize.x - 2 && j >= mapSize.y - 2)
}

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

createMap()
renderMap();
updatePlayerPosition();

