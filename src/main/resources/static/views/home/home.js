App.controller('homeController', function($scope, $http, $location,
		$routeParams, moversService) {

	$scope.priceGainers = [];
	$scope.priceLosers = [];
	$scope.marketCapGainers = [];
	$scope.marketCapLosers = [];

	var handleGetPriceGainers = function(data, status) {
		$scope.priceGainers = data;
	}
	var handleGetPriceLosers = function(data, status) {
		$scope.priceLosers = data;
	}
	var handleGetMarketCapGainers = function(data, status) {
		$scope.marketCapGainers = data;
	}
	var handleGetMarketCapLosers = function(data, status) {
		$scope.marketCapLosers = data;
	}

	moversService.getPriceGainers().success(handleGetPriceGainers);
	moversService.getPriceLosers().success(handleGetPriceLosers);
	moversService.getMarketCapGainers().success(handleGetMarketCapGainers);
	moversService.getMarketCapLosers().success(handleGetMarketCapLosers);
});