angular.module('app').filter('expDate', function ($filter) {
    return function (data) {
        var sub = new Date(data);
        sub.setMonth(sub.getMonth() + 2);
        return $filter('date')(sub, 'yyyy-MM-dd');
    }
});