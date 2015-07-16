function moveTiles(direction) {
	$.ajax({
	  method  : "POST",
	  url     : "/2048/servlet/MoveTiles",
	  data    : {
	  	direction: direction
	  }
	});
}

function newGame() {
	$.ajax({
	  method  : "POST",
	  url     : "/2048/servlet/NewGame"
	});
}

function getTiles() {
        // IS IT RIGHT TO USE AJAX, IF WE USE NO XML ?
	$.ajax({
	  method  : "POST",
	  url     : "/2048/servlet/GetTiles",
	  data    : {	  },
	  success : function(JSONtiles){
		cleanTilesContainer();
		parseTiles(JSONtiles);
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

