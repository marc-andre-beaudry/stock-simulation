App.controller('loginController', function HeaderController($scope, $location) {
	$scope.isLoggedIn = false;
	$scope.user = "Marc-Andre Beaudry";

	$scope.login = function() {
		$scope.isLoggedIn = true;
	};
});