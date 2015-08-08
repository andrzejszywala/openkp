'use strict';

angular.module('openkp').directive("activityStream", function(ActivityResource, $interval, ActivitiesService) {
	return {
		link : function(scope, element, attrs) {
			scope.activities = ActivitiesService.activities;
		},
		restrict : "AE",
		templateUrl : "presentation/dashboard/activityStream.html",
		replace : true,
		scope : {}
	}
});