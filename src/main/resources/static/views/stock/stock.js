App.factory('stockService', function($http) {
	return {
		getStockSummary : function(symbol) {
			return $http.get('/api/mktdata/summary/' + symbol);
		},
		getEodHistoricalData : function(symbol) {
			return $http.get('/api/mktdata/eod/' + symbol);
		}
	};
});

App.controller('stockController', function($scope, $http, $location,
		$routeParams, $filter, stockService, watchListService, searchService,
		accountService) {

	if ($routeParams.symbol == undefined || $routeParams.symbol == '') {
		$location.path('/home');
	}

	$scope.stock = {
		symbol : $routeParams.symbol
	};
	$scope.stockQuote = {};
	$scope.stockInfo = {};
	$scope.buyQty = 100;
	$scope.sellQty = 100;

	$scope.buyStock = function() {
		var order = {
			symbol : $scope.stock.symbol,
			openQuantity : $scope.buyQty,
			orderType : "Market",
			side : "Buy"
		};
		accountService.addOrUpdateOrder('1', order);
	}

	$scope.sellStock = function() {
		var order = {
			symbol : $scope.stock.symbol,
			openQuantity : $scope.sellQty,
			orderType : "Market",
			side : "Sell"
		};
		accountService.addOrUpdateOrder('1', order);
	}

	var handleGetStockSummarySuccess = function(data, status) {
		$scope.stockQuote = data;
	};

	var handleGetCompanySuccess = function(data, status) {
		$scope.stockInfo = data;
	};

	stockService.getStockSummary($routeParams.symbol).success(
			handleGetStockSummarySuccess);
	searchService.getCompany($routeParams.symbol).success(
			handleGetCompanySuccess);

	var handleGetEodHistoricalDataSuccess = function(data) {
		var adjustedData = [];
		for (var i = 0; i < data.length; i++) {
			var eodData = data[i];
			var arr = [ eodData.date, $filter('formatPriceKeepNumber')(eodData.open),
					$filter('formatPriceKeepNumber')(eodData.high),
					$filter('formatPriceKeepNumber')(eodData.low),
					$filter('formatPriceKeepNumber')(eodData.close) ];
			adjustedData.push(arr);
		}
		// create the chart
		$('#container').highcharts('StockChart', {

			rangeSelector : {
				selected : 5
			},
			title : {
				text : $scope.stock.symbol + ' EOD'
			},
			series : [ {
				type : 'ohlc',
				name : $scope.stock.symbol,
				data : adjustedData,
				dataGrouping : {
					units : [ [ 'week', // unit name
					[ 1 ] // allowed multiples
					], [ 'month', [ 1, 2, 3, 4, 6 ] ] ]
				}
			} ]
		});
	};
	stockService.getEodHistoricalData($routeParams.symbol).success(
			handleGetEodHistoricalDataSuccess);
});