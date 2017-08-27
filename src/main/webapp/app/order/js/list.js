angular.module('order').controller('orderCtrl', function (title, $scope, $http, $state, $sessionStorage) {
    title.setTitle('我的订单');
    $scope.loadingFlag = true;
    $scope.notData = false;
    $scope.myPage = {
        pageNo: 1,
        pageSize: 10,
        totalCount: 0,
        result: [],
        parameterMap: {}
    };

    $scope.onInit = function () {
        $http.post(ctx + '/order/list', $scope.myPage).success(function (resp) {
            if (resp.successful) {
                $scope.myPage = resp.data;
                $scope.loadingFlag = false;
                $scope.notData = false;
                if (!$scope.dataList || $scope.dataList.length == 0) $scope.notData = true;
            } else {
                console.log(resp.errorMessage);
            }
        });
    };

   // $scope.param = {};
  //  $scope.dataList = [];
   /* $scope.onInit = function () {

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


    };*/
    $scope.onInit();

    $scope.gotoIndex = function () {
        $state.go('app.home');
    };

    /**查询*/
    $scope.search = function () {
        $scope.myPage.pageNo = 1;
        $scope.myPage.totalCount = 0;
        $http.post(ctx + '/order/list', $scope.myPage).success(function (resp) {
            if (resp.successful) {
                $scope.myPage = resp.data;
            } else {
                console.log(resp.errorMessage);
            }
        });
    };

    /**翻页*/
    $scope.pageChangeHandler = function(num) {
        console.log('going to page ooooo ' + num);
        $scope.myPage.pageNo = num;
        $scope.onInit();
    };

});

angular.module('order').filter("OrderStatuesFilter",function () {
    return function (input) {
        if(input=='01'){return '待支付'};
        if(input=='02'){return '待发货'};
        if(input=='03'){return '待收货'};
        if(input=='04'){return '订单完成'};
    }
});

angular.module('order').filter("OrderCategoryFilter",function () {
    return function (input) {
        if(input=='1'){return '报单'};
        if(input=='2'){return '复投'};
        if(input=='3'){return '线上下单'};
    }
});