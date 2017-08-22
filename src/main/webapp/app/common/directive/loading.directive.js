angular.module('app').directive('loading', function ($timeout) {
    return {
        restrict: 'E',
        replace: true,
        transclude: true,
        template: '<div>' +
        '<div ng-transclude></div>' +
        '<div id="loading_btn" class="loading" ng-class="{\'hidden\':!flag}">' +
        '<div class="loader-inner ball-beat">' +
        '<div></div>' +
        '<div></div>' +
        '<div></div>' +
        '</div>' +
        '</div>' +
        '</div>',
        link: function (scope, element, attr) {
            scope.$watch(attr.show, function (newValue, oldValue) {
                scope.flag = newValue;
                $timeout(function () {
                    scope.$apply(attr.show);
                });
            });
        }
    };
});