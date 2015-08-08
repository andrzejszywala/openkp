'use strict';

angular.module('openkp.dashboard', [ 'ngRoute' ]).config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'presentation/dashboard/dashboard.html',
		controller : 'DashboardController'
	});
} ]).controller('DashboardController', function($scope) {

});