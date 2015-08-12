App.factory('searchService', function($http) {
	return {
		getCompanies : function() {
			return $http.get('/api/mktdata/stocks');
		}
	};
});

App.controller('searchController', function($scope, $http, $location,
		$routeParams, searchService) {

	$scope.search = "";
	$scope.availableStocks = [];
	$scope.results = [];
	$scope.totalMatchs = 0;

	var handleGetCompaniesSuccess = function(data, status) {
		$scope.availableStocks = data;
		$scope.updateSearch();
	};

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
	searchService.getCompanies().success(handleGetCompaniesSuccess);
});