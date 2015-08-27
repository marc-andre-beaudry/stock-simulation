'use strict';

var App = angular.module('StockSimulationApp', [ 'ngRoute' ]);

// Declare app level module which depends on filters, and services
App.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/home', {
		templateUrl : 'views/home/home.html',
		controller : 'homeController'
	});
	$routeProvider.when('/watchlist', {
		templateUrl : 'views/watchlist/watchlist.html',
		controller : 'watchListController'
	});
	$routeProvider.when('/search', {
		templateUrl : 'views/search/search.html',
		controller : 'searchController'
	});
	$routeProvider.when('/account', {
		templateUrl : 'views/account/account.html',
		controller : 'accountController'
	});
	$routeProvider.when('/stock', {
		templateUrl : 'views/stock/stock.html',
		controller : 'stockController'
	});
	$routeProvider.when('/stock/:symbol', {
		templateUrl : 'views/stock/stock.html',
		controller : 'stockController'
	});
	$routeProvider.when('/explorer', {
		templateUrl : 'views/explorer/explorer.html',
		controller : 'explorerController'
	});
	$routeProvider.when('/user', {
		templateUrl : 'views/user/user.html',
		controller : 'userController'
	});
	$routeProvider.otherwise({
		redirectTo : '/home'
	});
} ]);
