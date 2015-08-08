'use strict';

var appModule = angular.module(
		'openkp',
		[ 'ngRoute', "ngAnimate", 'ngResource', 'ui.bootstrap', 'ui.utils', 'ui.validate', 'ui.grid', 'ui.grid.resizeColumns', 'ui.grid.pinning',
				'ui.grid.selection', 'ui.calendar', 'ngWebSocket', 'openkp.kontekstAplikacji', 'openkp.wyplata', 'openkp.pracownicy', 'openkp.pracownikEdycja', 'openkp.pracownikNowy',
				'openkp.absencje', 'openkp.profil', 'openkp.dashboard' ]).config([ '$routeProvider', function($routeProvider) {
	$routeProvider.otherwise({
		redirectTo : '/'
	});
} ]).controller('NavController', function NavController($scope, $location) {
	$scope.matchesRoute = function(route) {
		var path = $location.path();
		return (path === ("/" + route) || path.indexOf("/" + route + "/") == 0);
	};
}).controller('ApplicationController', function ($scope, $location, kontekstAplikacji) {
	// dane kontekstowe dla całej aplikacji
	$scope.kontekstAplikacji = kontekstAplikacji;
});
