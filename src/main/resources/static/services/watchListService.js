App.factory('watchListService', function($http) {
	return {
		getWatchList : function(watchListId) {
			return $http.get('/api/watchlist/' + watchListId);
		}, 
		addStockToWatchList: function(watchListId, symbol) {
			return $http.put('/api/watchlist/' + watchListId + '/stocks/' + symbol);
		}, 
		removeStockFromWatchList: function(watchListId, symbol) {
			return $http.delete('/api/watchlist/' + watchListId + '/stocks/' + symbol);
		}
	};
});