angular.module('admRecommend').controller('admRecommendCtrl',function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage) {
    title.setTitle('提现管理');

    $scope.onInit = function () {
        /*paging start*/
        $scope.currentPage = 1;
        $scope.pageSize = 10;
        $scope.meals = [];
        $scope.totalItems = 40;
        var dishes = [
            'noodles',
            'sausage',
            'beans on ',
            'chee',
            'battered  ',
            'crisp ',
            'yorkshire ',
            'wiener ',
            'sauerkraut ',
            'salad',
            'onion ',
            'bak ',
            'avacado '
        ];
        var sides = [
            'with',
            'a la',
            'drizzled',
            'with ',
            'on ',
            'with ',
            'on a ',
            'wrapped ',
            'on a',
            'in pitta'
        ];
        for (var i = 1; i <= 20; i++) {
            var dish = dishes[Math.floor(Math.random() * dishes.length)];
            var side = sides[Math.floor(Math.random() * sides.length)];
            $scope.meals.push('meal ' + i + ': ' + dish + ' ' + side);
        }

        /*paging end*/
    }

    $scope.onInit();
    $scope.detail = function (id) {
        $state.go('app.advanceAdd');
    };
});

angular.module('admRecommend').controller('OtherController', function ( $scope) {
    $scope.pageChangeHandler = function(num) {
        console.log('going to page ooooo ' + num);
    };
});
