angular.module('recommend').controller('recommendListCtrl', function (title, $scope, $http, $state, $sessionStorage) {
    title.setTitle('分销中心');
    $scope.loadingFlag = true;
    $scope.notData = false;
    $scope.param = {};
    $scope.dataList = [];
    $scope.onInit = function () {

        $scope.loadingFlag = true;


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


        $scope.dataList = [];
        // $http.post(ctx + '/recommend/list', $scope.param).success(function (resp) {
        //     if (resp.successful) {
        //         $scope.dataList = resp.data;
        //         $scope.loadingFlag = false;
        //         $scope.notData = false;
        //         if (!$scope.dataList || $scope.dataList.length == 0) $scope.notData = true;
        //     } else {
        //         console.log(resp.errorMessage);
        //     }
        // });


    };
    $scope.onInit();
    $scope.gotoAddPage = function () {
        $state.go("app.recommendAdd");
    };
    $scope.gotoIndex = function () {
        $state.go('app.home');
    };
    $scope.detail = function (id) {
        $state.go('app.recommendAdd', {id: id});
    };



});

angular.module('recommend').controller('OtherController', function ( $scope) {
    $scope.pageChangeHandler = function(num) {
        console.log('going to page ooooo ' + num);
    };
});
