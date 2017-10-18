angular.module('advance').controller('advanceListCtrl',function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, $uibModal,Notify) {
    title.setTitle('我的提现');

    $scope.myPage = {
        pageNo: 1,
        pageSize: 10,
        totalCount: 0,
        result: [],
        parameterMap: {
            statues:'',
            startTime:'',
            endTime:''
        }
    };

    $scope.advanceRemark = {
        remark : ''
    }
    $scope.search = function(){
        $http.post(ctx + '/advance/list', $scope.myPage)
            .success(function (resp) {
                if (resp.successful) {
                    $scope.myPage = resp.data;
                    $scope.notData = false;
                    if (!$scope.myPage.result || $scope.myPage.result.length == 0) $scope.notData = true;
                } else {
                    Notify.error(resp);
                }
            }).error(function (error) {
                Notify.error(error);
            });

    };

    /**提现驳回备注*/
    $scope.getRemark = function (remark) {
        $scope.advanceRemark.remark = remark;
        $scope.open(remark);
    };

    $scope.open = function(opt_attributes, remark)
    {
        var out = $uibModal.open(
            {
                animation: true,
                backdrop: 'static',
                templateUrl: "advanceRemark.html",
                controller: "advanceRemarkCtrl",
                size: opt_attributes,
                resolve: {
                    getDatas: function()
                    {
                        return $scope.advanceRemark;
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

    $scope.gotoAddPage = function () {
        $state.go("app.advanceAdd");
    };
});

angular.module('advance').controller('advanceRemarkCtrl', function ($scope, $uibModalInstance,getDatas) {
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

angular.module('advance').filter("AdvanceStatuesFilter",function () {
    return function (input) {
        if(input=='1'){return '申请中'};
        if(input=='2'){return '已执行'};
        if(input=='3'){return '已驳回'};
    }
});

