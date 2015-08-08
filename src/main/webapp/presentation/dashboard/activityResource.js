'use strict';

angular.module('openkp').factory('ActivityResource', ['$resource', function($resource) {
	var resource = $resource('rest/activity/:id', {
		id : '@id'
	}, {
		'queryAll' : {
			method : 'GET',
			isArray : true
		},
		'query' : {
			method : 'GET',
			isArray : false
		},
		'update' : {
			method : 'PUT'
		}
	});
	return resource;
}]);