App.controller('userController', function($scope, $http, $location,
		$routeParams, userService) {

	$scope.user = {};

	var handleGetCurrentUserSuccess = function(data, status) {
		$scope.user = data;
	}

	userService.getCurrentUser().success(handleGetCurrentUserSuccess);
});