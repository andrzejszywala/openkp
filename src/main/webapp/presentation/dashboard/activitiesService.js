'use strict';

angular.module('openkp').factory('ActivitiesService', function($websocket) {
	// Open a WebSocket connection
	var dataStream = $websocket('ws://' + document.location.host + '/openkp/messages');

	var activities = [];

	dataStream.onMessage(function(message) {
		activities.unshift(JSON.parse(message.data));
	});

	var methods = {
		activities : activities,
		get : function() {
			dataStream.send(JSON.stringify({
				action : 'get'
			}));
		}
	};

	return methods;
})