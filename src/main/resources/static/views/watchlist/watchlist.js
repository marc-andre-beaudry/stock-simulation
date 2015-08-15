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

App.controller('watchListController', function($scope, $http, $location, $routeParams, watchListService) {		
	$scope.watchList = [];
	
	$scope.addStock = function(symbol) {
		watchListService.addStockToWatchList('1', symbol).success(handleAddStockSuccess);
	}
	
	$scope.removeStock = function(symbol) {
		watchListService.removeStockFromWatchList('1', symbol).success(handleRemoveStockSuccess);
	}
	
	var handleGetWatchListSuccess = function(data, status) {
		$scope.watchList = data;
	};
	
	var handleAddStockSuccess = function(data, status) {
	};
	
	var handleRemoveStockSuccess = function(data, status) {
	};
	
	watchListService.getWatchList('1').success(handleGetWatchListSuccess);
});