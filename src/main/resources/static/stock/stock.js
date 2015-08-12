App.factory('stockService', function($http) {
	return {
		getWatchList : function(id) {
			return $http.get('/api/watchlist/' + id);
		}
	};
});

App.controller('stockController', function($scope, $http, $location,
		$routeParams, stockService) {
	$scope.stock = {
		symbol : $routeParams.symbol
	};
});