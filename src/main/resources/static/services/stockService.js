App.factory('stockService', function($http) {
	return {
		getStockSummary : function(symbol) {
			return $http.get('/api/mktdata/summary/' + symbol);
		},
		getEodHistoricalData : function(symbol) {
			return $http.get('/api/mktdata/eod/' + symbol);
		},
		getCountBySector : function() {
			return $http.get('/api/mktdata/sector?aggregation=count');
		},
		getMarketCapBySector : function() {
			return $http.get('/api/mktdata/sector?aggregation=marketCap');
		}
	};
});