'use strict';

var AngularSpringApp = {};

var App = angular.module('StockSimulationApp', [ 'ngRoute', 'ui.grid']);

// Declare app level module which depends on filters, and services
App.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/home', {
		templateUrl : 'home/home.html',
		controller : 'homeController'
	});
	$routeProvider.when('/watchlist', {
		templateUrl : 'watchlist/watchlist.html',
		controller : 'watchListController'
	});
	$routeProvider.when('/search', {
		templateUrl : 'search/search.html',
		controller : 'searchController'
	});
	$routeProvider.when('/account', {
		templateUrl : 'account/account.html',
		controller : 'accountController'
	});

	$routeProvider.otherwise({
		redirectTo : '/home'
	});
} ]);
