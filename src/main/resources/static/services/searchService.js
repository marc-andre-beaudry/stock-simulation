App.factory('searchService', function($http) {
	return {
		getCompanies : function() {
			return $http.get('/api/mktdata/stocks');
		},
		getCompany : function(symbol) {
			return $http.get('/api/mktdata/stocks/' + symbol);
		}
	};
});