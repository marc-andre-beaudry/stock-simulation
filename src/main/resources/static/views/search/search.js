App.controller('searchController', function($scope, $http, $location,
		$routeParams, searchService, watchListService) {

	$scope.search = "";
	$scope.watchList = [];
	$scope.availableStocks = [];
	$scope.results = [];
	$scope.totalMatchs = 0;
	$scope.isLoading = true;

	$scope.updateSearch = function() {
		$scope.results = [];
		$scope.totalMatchs = 0;

		var upperCaseSearch = $scope.search.toUpperCase();
		for (var i = 0; i < $scope.availableStocks.length; i++) {

			var stock = $scope.availableStocks[i];
			var nameUpperCase = stock.name.toUpperCase();
			var symbolUpperCase = stock.symbol.toUpperCase();

			if (symbolUpperCase.match(upperCaseSearch)
					|| nameUpperCase.match(upperCaseSearch)) {
				$scope.totalMatchs++;
				if ($scope.results.length < 100) {
					$scope.results.push(stock);
				}
			}
		}
	}

	$scope.addStock = function(symbol) {
		watchListService.addStockToWatchList('1', symbol).success(
				function(data, status) {
					$scope.watchList.stocks.push({
						"symbol" : symbol
					})
				});
	}

	$scope.removeStock = function(symbol) {
		watchListService.removeStockFromWatchList('1', symbol).success(
				function(data, status) {
					var indexToRemove = -1;
					for(var index = 0; index < $scope.watchList.stocks.length; index++){
						if ($scope.watchList.stocks[index].symbol == symbol) {
							indexToRemove = index;
							break;
						}
					}
					if(indexToRemove >= 0) {
						$scope.watchList.stocks.splice(indexToRemove, 1);
					}
				});
	}

	$scope.isInWatchList = function(symbol) {
		var isInWatchList = false;
		$scope.watchList.stocks.forEach(function(stock) {
			if (stock.symbol == symbol) {
				isInWatchList = true;
			}
		});
		return isInWatchList;
	}

	var handleGetCompaniesSuccess = function(data, status) {
		$scope.availableStocks = data;
		$scope.updateSearch();
		$scope.isLoading = false;
	};

	var handleGetWatchListSuccess = function(data, status) {
		$scope.watchList = data;
	};
	searchService.getCompanies().success(handleGetCompaniesSuccess).error(function() {
		$scope.isLoading = false;
	});
	watchListService.getWatchList('1').success(handleGetWatchListSuccess);
});