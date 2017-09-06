angular.module('admOrder').controller('admOrderCtrl',function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, Notify) {
    title.setTitle('订单管理');
    $scope.myPage = {
        pageNo: 1,
        pageSize: 10,
        totalCount: 0,
        result: [],
        parameterMap: {}
    };

    /**
     * 查询订单列表
     */
    $scope.search = function(){
        $http.post(ctx + '/admOrder/list', $scope.myPage)
            .success(function (resp) {
                if (resp.successful) {
                    $scope.myPage = resp.data;
                    $scope.notData = false;
                    if (!$scope.myPage.result || $scope.myPage.result.length == 0) $scope.notData = true;
                } else {
                    Nodify.error("查询订单列表失败，请重新尝试");
                    console.log(resp.errorMessage);
                }
            }).error(function (error) {
            Nodify.error("查询订单列表失败，请重新尝试");
            console.error(error);
        });
    }

    /**确认收货*/
    $scope.confirmOrder = function (id, orderNo, orderStatues) {
        $.ajax({
            type: "POST",
            url: ctx + "/admOrder/confirmSendOrder",
            data: {
                id: id,
                orderNo: orderNo,
                orderStatues: orderStatues
            },

            dataType: "json",
            success: function (resp) {
                if (resp.successful) {
                    Nodify.success("确认发货成功");
                    $scope.search();
                } else{
                    Nodify.error("确认发货失败，请重新尝试");
                    console.log("error==", error);
                }
            },
            error: function (error) {
                Nodify.error("确认发货失败，请重新尝试");
                console.log("error==", error);
            }
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

angular.module('admOrder').filter("OrderStatuesFilter",function () {
    return function (input) {
        if(input=='1'){return '待支付'};
        if(input=='2'){return '待发货'};
        if(input=='3'){return '待收货'};
        if(input=='4'){return '订单完成'};
    }
});

angular.module('admOrder').filter("OrderCategoryFilter",function () {
    return function (input) {
        if(input=='1'){return '报单'};
        if(input=='2'){return '复投'};
        if(input=='3'){return '折扣单'};
    }
});

angular.module('admOrder').filter("MemberLevelFilter",function () {
    return function (input) {
        if(input=='member_level1'){return '普卡'};
        if(input=='member_level2'){return '铜卡'};
        if(input=='member_level3'){return '银卡'};
        if(input=='member_level4'){return '金卡'};
        if(input=='member_level5'){return '白金卡'};
        if(input=='member_level6'){return '黑金卡'};
    }
});