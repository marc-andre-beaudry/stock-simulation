App.factory('stockService', function($http) {
	return {
		getStockSummary : function(symbol) {
			return $http.get('/api/mktdata/summary/' + symbol);
		}
	};
});

App.controller('stockController', function($scope, $http, $location,
		$routeParams, stockService, watchListService) {
	$scope.stock = {
		symbol : $routeParams.symbol
	};
	$scope.stockQuote = {};
	
	$scope.addStock = function(symbol) {
		watchListService.addStockToWatchList('1', symbol).success(handleAddStockSuccess);
	}
	
	$scope.removeStock = function(symbol) {
		watchListService.removeStockFromWatchList('1', symbol).success(handleRemoveStockSuccess);
	}

	var handleGetStockSummarySuccess = function(data, status) {
		$scope.stockQuote = data;
	};
	
	var handleAddStockSuccess = function(data, status) {
	};
	
	var handleRemoveStockSuccess = function(data, status) {
	};

	stockService.getStockSummary().success(handleGetStockSummarySuccess);
});