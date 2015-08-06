App.controller('watchListController', function($scope, $http, $location, $routeParams) {		
	$scope.display = "Hello";
	
	$scope.stocks = [{symbol:"APPL", open:119.54, high:121.12, low:118.87, last:119.43}];
	
	$scope.generateData = function() {
		var aapl = {symbol:"APPL", open:119.54, high:121.12, low:118.87, last:119.43};
		var msft = {symbol:"MSFT", open:119.54, high:121.12, low:118.87, last:119.43};
		var ms = {symbol:"MS", open:119.54, high:121.12, low:118.87, last:119.43};
		var fb = {symbol:"FB", open:119.54, high:121.12, low:118.87, last:119.43};
		
		$scope.stocks.push(aapl);
		$scope.stocks.push(msft);
		$scope.stocks.push(ms);
		$scope.stocks.push(fb);
	}
	$scope.generateData();
	
	$scope.gridOptions = {
	    enableSorting: true,
	    data:'stocks'
	  };
});