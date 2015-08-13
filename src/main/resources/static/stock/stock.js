App.factory('stockService', function($http) {
	return {
		getStockSummary : function(symbol) {
			return $http.get('/api/mktdata/summary/' + symbol);
		}
	};
});

App.controller('stockController', function($scope, $http, $location,
		$routeParams, stockService) {
	$scope.stock = {
		symbol : $routeParams.symbol
	};
	$scope.stockQuote = {};

	var handleGetStockSummarySuccess = function(data, status) {
		$scope.stockQuote = data;
	};

	stockService.getStockSummary().success(handleGetStockSummarySuccess);
});