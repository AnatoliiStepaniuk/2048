function moveTiles(direction) {
	$.ajax({
	  method  : "POST",
	  url     : "servlet/MoveTiles",
	  data    : {
	  	direction: direction
	  }
	});
}

function newGame() {
	$.ajax({
	  method  : "POST",
	  url     : "servlet/NewGame"
	});
}

function getTiles() {
        // IS IT RIGHT TO USE AJAX, IF WE USE NO XML ?
	$.ajax({
	  method  : "POST",
	  url     : "servlet/GetTiles",
	  data    : {	  },
	  success : function(JSONtiles){
		cleanTilesContainer();
		parseTiles(JSONtiles);
	  }
	});
}

function getScore() {
	$.ajax({
		method  : "POST",
		url     : "servlet/Score",
		success : function(score){
			updateScore(score);
		}
	});
}


function parseTiles(JSONtiles) {

//jQuery("#mes").html(JSONtiles);

	var tileArray = JSON.parse(JSONtiles);

	for(var i=0; i<tileArray.length; i++){
			addTile(tileArray[i]);
	}

}

function cleanTilesContainer() {
	var tileContainer = document.querySelector(".tile-container");
	tileContainer.innerHTML = "";
}

