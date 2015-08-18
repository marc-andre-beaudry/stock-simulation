App.controller('accountController', function($scope, $http, $location,
		$routeParams, accountService) {
	$scope.accounts = [];
	$scope.positions = [];
	$scope.orders = [];
	$scope.executions = [];

	var handleGetAccountsSuccess = function(data, status) {
		$scope.accounts = data;
		for (var i = 0; i < $scope.accounts.length; i++) {
			var account = $scope.accounts[i];
			accountService.getPositions(account.id).success(
					handleGetPositionsSuccess);
			accountService.getOrders(account.id)
					.success(handleGetOrdersSuccess);
			accountService.getExecutions(account.id).success(
					handleGetExecutionsSuccess);
		}
	};
	var handleGetPositionsSuccess = function(data, status) {
		for (var i = 0; i < data.length; i++) {
			$scope.positions.push(data[i]);
		}
	};
	var handleGetOrdersSuccess = function(data, status) {
		for (var i = 0; i < data.length; i++) {
			$scope.orders.push(data[i]);
		}
	};
	var handleGetExecutionsSuccess = function(data, status) {
		for (var i = 0; i < data.length; i++) {
			$scope.executions.push(data[i]);
		}
	};

	accountService.getAccounts().success(handleGetAccountsSuccess);
});