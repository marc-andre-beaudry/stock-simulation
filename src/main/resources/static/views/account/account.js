App.controller('accountController', function($scope, $http, $location,
		$routeParams, $route, accountService) {
	$scope.accounts = [];
	$scope.positions = [];
	$scope.orders = [];
	$scope.executions = [];
	
	var handleAddOrUpdateOrderSuccess = function(data, status) {
		$route.reload();
	};
	
	var handleAddOrUpdateOrderError = function(data, status) {
		$route.reload();
	};
	
	$scope.closePosition = function(position) {
		var order = {
				symbol : position.symbol,
				openQuantity : Math.abs(position.openQuantity	),
				orderType : "Market"
		};
		if(position.openQuantity > 0) {
			order.side = "Sell";
		} else if(position.openQuantity < 0) {
			order.side = "Buy";
		}
		accountService.addOrUpdateOrder('1', order).success(handleAddOrUpdateOrderSuccess).error(handleAddOrUpdateOrderError);
	};

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