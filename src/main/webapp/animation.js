jQuery(document).on('keydown',function(event){

	if(event.keyCode == 38 || event.keyCode == 40)
		event.preventDefault();
	var direction;
	if(event.keyCode == 27){
		direction = "NewGame";
		<!--direction = 0;-->
	}
	else if(event.keyCode == 38){
		direction = "UP";
		<!--direction = 1;-->
	}
	else if(event.keyCode == 39){
		direction = "RIGHT";
		<!--direction = 2;-->
	}
	else if(event.keyCode == 40){
		direction = "DOWN";
		<!--direction = 3;-->
	}
	else if(event.keyCode == 37){
		direction = "LEFT";
		<!--direction = 4;-->
	}

	if(direction == "UP" || direction == "RIGHT" || direction == "DOWN" || direction == "LEFT" || direction == "NewGame") {
	<!--if(direction >= 0 && direction <= 4){-->
		runGame(direction);
	}
});


function parseAnswer(JSONString) {

	var answerObject = JSON.parse(JSONString);
	var tileArray 		= answerObject.tiles;
	var currentScore 	= answerObject.currentScore;
	var bestScore 		= answerObject.bestScore;

	for(var t=0; t<tileArray.length; t++){

			var tile = {
						"x"     : tileArray[t].x,
						"y"     : tileArray[t].y,
						"value" : tileArray[t].value,
	  					"prevX" : tileArray[t].prevX,
	  					"prevY" : tileArray[t].prevY,
	  					"merged": tileArray[t].merged
					   };

			addTile(tile);
	}

}

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


function addTile(tile){
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




$(document).ready(runGame("NewGame"));

function runGame(direction){
	$.ajax({
	  method  : "POST",
	  url     : "/2048/start",
	  data    : {
	  	direction: direction
	  },
	  success : function(answer){
	  	var tileContainer = document.querySelector(".tile-container");
		tileContainer.innerHTML = "";
	  	parseAnswer(answer);
	  }

	});
}