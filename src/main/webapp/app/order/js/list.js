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
                if (!$scope.myPage.result || $scope.myPage.result.length == 0) $scope.notData = true;
            } else {
                console.log(resp.errorMessage);
            }
        });
    };

    $scope.onInit();

  /*  $scope.gotoIndex = function () {
        $state.go('app.home');
    };
*/
    /**查询*/
    $scope.search = function () {
        $scope.myPage.pageNo = 1;
        $scope.myPage.totalCount = 0;
        $http.post(ctx + '/order/list', $scope.myPage).success(function (resp) {
            if (resp.successful) {
                $scope.myPage = resp.data;
                $scope.notData = false;
                if (!$scope.myPage.result || $scope.myPage.result.length == 0) $scope.notData = true;
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
        if(input=='1'){return '待支付'};
        if(input=='2'){return '待发货'};
        if(input=='3'){return '待收货'};
        if(input=='4'){return '订单完成'};
    }
});

angular.module('order').filter("OrderCategoryFilter",function () {
    return function (input) {
        if(input=='1'){return '报单'};
        if(input=='2'){return '复投'};
        if(input=='3'){return '线上下单'};
    }
});