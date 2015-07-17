var gameState = "Game";
$(document).ready(function(){
  newGame();
  getTiles();
  getScore();
});

jQuery(document).on('keydown',function(event){
    // get status
  getGameState();
  if (gameState.trim() == "GameOver") {
      $(".game-message").show();
      $(".game-message p").text("Game over");
      return;
    } else if (gameState.trim() == "Twenty48") {
      $(".game-message").show();
      $(".game-message p").text("You win");
      return;
    } else {
      $(".game-message").hide();
      $(".game-message p").text = "";
    }
    // to avoid scrolling:
	if(event.keyCode == 38 || event.keyCode == 40)
		event.preventDefault();
	var direction;
	if(event.keyCode == 38){
		direction = "UP";
	}
	else if(event.keyCode == 39){
		direction = "RIGHT";
	}
	else if(event.keyCode == 40){
		direction = "DOWN";
	}
	else if(event.keyCode == 37){
		direction = "LEFT";
	}

	if(direction == "UP" || direction == "RIGHT" || direction == "DOWN" || direction == "LEFT") {
		moveTiles(direction);
		getTiles();
        getScore();
	}
});

window.requestAnimationFrame = function (callback) {
  var lastTime = 0;
  var currTime = new Date().getTime();
  var timeToCall = Math.max(0, 60 - (currTime - lastTime));
  var id = window.setTimeout(function () {
        callback(currTime + timeToCall);
      },
      timeToCall);
  lastTime = currTime + timeToCall;
  return id;
};
window.cancelAnimationFrame = function (id) {
  clearTimeout(id);
};

function positionClassF(position) {
  position = normalizePosition(position);
  return "tile-position-" + position.x + "-" + position.y;
};

function normalizePosition(position) {
  return { x: position.x + 1, y: position.y + 1 };
};

function applyClasses(element, classes) {
  element.setAttribute("class", classes.join(" "));
};


function addTile(tile) {
  var wrapper   = document.createElement("div");
  var inner     = document.createElement("div");

var position = {x:0,y:0};
if (tile.prevX !== -1 || tile.prevY !== -1) {
	position.x = tile.prevX;
	position.y = tile.prevY;
}else{
	position.x = tile.x;
	position.y = tile.y;
}
  var positionClass = positionClassF(position);
  var classes = ["tile", "tile-" + tile.value, positionClass];

  if (tile.value > 2048) classes.push("tile-super");

  applyClasses(wrapper, classes);

  inner.classList.add("tile-inner");
  inner.textContent = tile.value;

  if (tile.prevX === -1 || tile.prevY === -1) {
      classes.push("tile-new");
     applyClasses(wrapper, classes);
  }
    if (tile.prevX !== -1 || tile.prevY !== -1) {
    // Make sure that the tile gets rendered in the previous position first
    window.requestAnimationFrame(function () {
      classes[2] = positionClassF({ x: tile.x, y: tile.y });
      applyClasses(wrapper, classes); // Update the position
    });
  } else if (tile.merged) {

    classes.push("tile-merged");
    /*
    applyClasses(wrapper, classes);
    tile.mergedFrom.forEach(function (merged) {
      self.addTile(merged);
    });
	*/

  }

  // Add the inner part of the tile to the wrapper
  wrapper.appendChild(inner);
  // Put the tile on the board
  var tileContainer = document.querySelector(".tile-container");
  tileContainer.appendChild(wrapper);
};

function updateGameState(state) {
  gameState = state;
}

