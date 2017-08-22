angular.module('app').directive('setFocus', function () {
    return function (scope, element, attr) {
        scope.$watch(attr.setFocus, function (newValue, oldValue) {
            console.log(newValue);
            if (newValue)
                element[0].focus();
        });
    };
});