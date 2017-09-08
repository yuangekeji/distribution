angular.module('order').controller('orderCtrl', function (title, $scope, $http, $state, $sessionStorage,Notify) {
    title.setTitle('我的订单');
    $scope.myPage = {
        pageNo: 1,
        pageSize: 10,
        totalCount: 0,
        result: [],
        parameterMap: {}
    };
    $scope.search = function(){

        $http.post(ctx + '/order/list', $scope.myPage)
            .success(function (resp) {
                if (resp.successful) {
                    $scope.myPage = resp.data;
                    $scope.notData = false;
                    if (!$scope.myPage.result || $scope.myPage.result.length == 0) $scope.notData = true;

                } else {
                    console.log(resp.errorMessage);
                }

            }).error(function (error) {
            console.error(error);
        });
    }

    $scope.confirmOrder = function (id, statues) {
        $scope.startLoading();
        $http.post(ctx + "/order/confirmOrder",{id:id,orderStatues:statues}).success(function (resp) {
            if(resp.successful){
                Notify.success("确认收货完成。");
                $scope.search();
            }else{
                Notify.error(resp.error());
            }
            $scope.stopLoading();
        }).error(function (error) {
            Notify.error(error);
            $scope.stopLoading();
        });
    };


    /**
     * 初始化
     */
    $scope.onInit = function () {

        $scope.search();
    };


    $scope.onInit();

    /**
     * 查询按钮触发
     */
    $scope.searchByParam =function () {

        $scope.myPage.pageNo = 1;
        $scope.myPage.totalCount = 0;

        $scope.search();

    }

    /**
     * 分页触发
     * @param num
     */
    $scope.pageChangeHandler = function(num) {
        $scope.myPage.pageNo = num;
        $scope.search();
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
        if(input=='3'){return '折扣单'};
    }
});