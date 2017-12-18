angular.module('point').controller('pointCtrl', function (title, $scope, $http, $state, $sessionStorage, $uibModal, ConfirmModal, Notify) {
    title.setTitle('积分兑换');
    $scope.myPage = {
        pageNo: 1,
        pageSize: 10,
        totalCount: 0,
        result: [],
        parameterMap: {}
    };
    $scope.expressMessage = {
        expressNo: '',
        expressAddress: '',
        sendbypostyn: '',
        orderStatues: ''
    };
    var e1 = $('.portlet');
    $scope.startLoading=function () {
        App.blockUI({
            target: e1,
            animate: true,
            overlayColor: 'none'
        });
    }
    $scope.stopLoading=function () {
        App.unblockUI(e1);
    }

    $scope.search = function(){

        $http.post(ctx + '/point/list', $scope.myPage)
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
        ConfirmModal.show({
            text: '确定已经收到商品了吗？',
            isCancel:true //false alert ,true confirm
        }).then(function (sure) {
            if (!sure) {
                return;
            }
            $scope.startLoading();
            $http.post(ctx + "/point/confirmOrder", {id: id, orderStatues: statues}).success(function (resp) {
                if (resp.successful) {
                    Notify.success("确认收货完成。");
                    $scope.search();
                } else {
                    Notify.error(resp.error());
                }
                $scope.stopLoading();
            }).error(function (error) {
                Notify.error(error);
                $scope.stopLoading();
            });
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

    /**物流信息查询*/
    $scope.expressMessageSearch = function (expressNo, expressAddress, sendbypostyn, orderStatues) {
        $scope.expressMessage.expressNo = expressNo;
        $scope.expressMessage.expressAddress = expressAddress;
        $scope.expressMessage.sendbypostyn = sendbypostyn;
        $scope.expressMessage.orderStatues = orderStatues;
        $scope.open();

    };

    $scope.open = function(opt_attributes)
    {
        var out = $uibModal.open(
            {
                animation: true,
                backdrop: 'static',
                templateUrl: "expressMessage.html",
                controller: "expressMessageCtrl",
                size: opt_attributes,
                resolve: {
                    getDatas: function()
                    {
                        return $scope.expressMessage;
                    }
                }
            });
        out.result.then(function(value)
        {
            // console.info('确认');
        }, function()
        {
            // console.info('取消');
        });
    };

    /**
     * 分页触发
     * @param num
     */
    $scope.pageChangeHandler = function(num) {
        $scope.myPage.pageNo = num;
        $scope.search();
    };
});

angular.module('point').controller('expressMessageCtrl', function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, $uibModalInstance,getDatas) {

    var e1 = $('.portlet');
    $scope.startLoading=function () {
        App.blockUI({
            target: e1,
            animate: true,
            overlayColor: 'none'
        });
    }
    $scope.stopLoading=function () {
        App.unblockUI(e1);
    }

    $scope.datas = getDatas;

    $scope.close = function()
    {
        $uibModalInstance.close(true);
    };
    $scope.cancel = function()
    {
        $uibModalInstance.dismiss('cancel');
    };
});

angular.module('point').filter("PointOrderStatuesFilter",function () {
    return function (input) {
        if(input=='1'){return '待支付'};
        if(input=='2'){return '待发货'};
        if(input=='3'){return '待收货'};
        if(input=='4'){return '兑换单完成'};
    }
});

angular.module('point').filter("PointOrderCategoryFilter",function () {
    return function (input) {
        if(input=='1'){return '积分兑换'};
    }
});