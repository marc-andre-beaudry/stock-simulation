App.controller('watchListController', function($scope, $http, $location,
		$routeParams, watchListService, stockService) {
	$scope.watchList = [];

	$scope.addStock = function(symbol) {
		watchListService.addStockToWatchList('1', symbol).success(
				handleAddStockSuccess);
	}

	$scope.removeStock = function(symbol) {
		watchListService.removeStockFromWatchList('1', symbol).success(
				handleRemoveStockSuccess);
	}

	var handleGetWatchListSuccess = function(data, status) {
		$scope.watchList = data;
		for (var i = 0; i < data.stocks.length; i++) {
			stockService.getStockSummary($scope.watchList.stocks[i].symbol)
					.success(handleGetStockSummarySuccess);
		}
	};

	var handleGetStockSummarySuccess = function(data, status) {
		for (var i = 0; i < $scope.watchList.stocks.length; i++) {
			if($scope.watchList.stocks[i].symbol == data.symbol) {
				$scope.watchList.stocks[i].stockQuote = data;
			}
			
		}
	}

	var handleAddStockSuccess = function(data, status) {
	};

	var handleRemoveStockSuccess = function(data, status) {
	};

	watchListService.getWatchList('1').success(handleGetWatchListSuccess);
});