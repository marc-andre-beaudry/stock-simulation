App.factory('stockService', function($http) {
	return {
		getStockSummary : function(symbol) {
			return $http.get('/api/mktdata/summary/' + symbol);
		}
	};
});

App.controller('stockController', function($scope, $http, $location,
		$routeParams, stockService, watchListService, searchService,
		accountService) {

	if ($routeParams.symbol == undefined || $routeParams.symbol == '') {
		$location.path('/home');
	}

	$scope.stock = {
		symbol : $routeParams.symbol
	};
	$scope.stockQuote = {};
	$scope.stockInfo = {};
	$scope.buyQty = 100;
	$scope.sellQty = 100;

	$scope.buyStock = function() {
		var order = {
			symbol : $scope.stock.symbol,
			openQuantity : $scope.buyQty,
			orderType : "Market",
			side : "Buy"
		};
		accountService.addOrUpdateOrder('1', order);
	}

	$scope.sellStock = function() {
		var order = {
			symbol : $scope.stock.symbol,
			openQuantity : $scope.sellQty,
			orderType : "Market",
			side : "Sell"
		};
		accountService.addOrUpdateOrder('1', order);
	}

	var handleGetStockSummarySuccess = function(data, status) {
		$scope.stockQuote = data;
	};

	var handleGetCompanySuccess = function(data, status) {
		$scope.stockInfo = data;
	};

	stockService.getStockSummary($routeParams.symbol).success(
			handleGetStockSummarySuccess);
	searchService.getCompany($routeParams.symbol).success(
			handleGetCompanySuccess);
});