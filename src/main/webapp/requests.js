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
	$(".game-message").hide();
	$.ajax({
	  method  : "POST",
	  url     : "servlet/NewGame"
	});
}

function getTiles() {
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
		url     : "servlet/GetScore",
		success : function(score){
			parseScore(score);
		}
	});
}

function getGameState() {
	$.ajax({
		method  : "POST",
		url     : "servlet/GetState",
		success : function(state){
			updateGameState(state);
		}
	});
}


function parseTiles(JSONtiles) {
	var tileArray = JSON.parse(JSONtiles);

	for(var i=0; i<tileArray.length; i++){
			addTile(tileArray[i]);
	}

}

function parseScore(JSONscore) {

	var scoreObject = JSON.parse(JSONscore);
	var currentScore = scoreObject.currentScore;
	var bestScore = scoreObject.bestScore;
	updateScore(currentScore, bestScore);
}

function cleanTilesContainer() {
	var tileContainer = document.querySelector(".tile-container");
	tileContainer.innerHTML = "";
}

function updateScore(currentScore, bestScore) {
  $(".score-container").text(currentScore);
  $(".best-container").text(bestScore);
}

