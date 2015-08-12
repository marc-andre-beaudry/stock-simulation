App.factory('watchListService', function($http) {
	return {
		getWatchList : function(id) {
			return $http.get('/api/watchlist/' + id);
		}
	};
});

App.controller('watchListController', function($scope, $http, $location, $routeParams, watchListService) {		
	$scope.watchList = [];
	
	var handleGetWatchListSuccess = function(data, status) {
		$scope.watchList = data;
	};
	
	watchListService.getWatchList('1').success(handleGetWatchListSuccess);
});