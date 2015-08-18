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