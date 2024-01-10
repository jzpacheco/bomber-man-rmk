const mapSize = {x: 40, y:20}


const mapContainer = document.getElementById('map')

const player = document.createElement('div')
player.id = 'player'
mapContainer.appendChild(player)

playerPosition = {x:1,y:1}

map = []
function createMap(){
    for (let i = 0; i < mapSize.x; i++) {
        map[i] = []
        for (let j = 0; j < mapSize.y; j++) {
             map[i][j] = Math.floor(Math.random() * 2);
        }
        
    }
}
createMap()
console.log(map)

function updatePlayerPosition(){
    player.style.transform = `translate(${playerPosition.x *27}px, ${playerPosition.y *27}px)`
}

function renderMap() {
    map.forEach(row => {
        row.forEach(cellType => {
            var cell = document.createElement('div')
            cell.classList.add('cell')

            if (cellType === 0){
                cell.classList.add('empty')
            } else {
                cell.classList.add('wall')
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

renderMap();
updatePlayerPosition();

