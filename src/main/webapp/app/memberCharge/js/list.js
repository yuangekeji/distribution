angular.module('memberCharge').controller('memberChargeListCtrl',function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, $uibModal,Notify) {
    title.setTitle('充值管理');

    $scope.myPage = {
        pageNo: 1,
        pageSize: 10,
        totalCount: 0,
        result: [],
        parameterMap: {
            statue:'',
            startTime:'',
            endTime:''
        }
    };

    $scope.memberChargeRemarks = {
        remarks : ''
    }
    $scope.memberChargeApplyInfo = {
        applyInfo: ''
    }
    $scope.search = function(){
        $http.post(ctx + '/memberCharge/list', $scope.myPage)
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

    /**充值申请驳回备注*/
    $scope.getRemarks = function (remarks) {
        $scope.memberChargeRemarks.remarks = remarks;
        $scope.open(remarks);
    };

    $scope.open = function(opt_attributes)
    {
        var out = $uibModal.open(
            {
                animation: true,
                backdrop: 'static',
                templateUrl: "memberChargeRemarks.html",
                controller: "memberChargeRemarksCtrl",
                size: opt_attributes,
                resolve: {
                    getDatas: function()
                    {
                        return $scope.memberChargeRemarks;
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

    /**充值申请备注详情*/
    $scope.getApplyInfo = function (applyInfo) {
        $scope.memberChargeApplyInfo.applyInfo = applyInfo;
        $scope.openApplyInfo(applyInfo);
    };

    $scope.openApplyInfo = function(opt_attributes)
    {
        var out = $uibModal.open(
            {
                animation: true,
                backdrop: 'static',
                templateUrl: "memberChargeApplyInfo.html",
                controller: "memberChargeApplyInfoCtrl",
                size: opt_attributes,
                resolve: {
                    getDatas: function()
                    {
                        return $scope.memberChargeApplyInfo;
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
        $state.go("app.memberChargeAdd");
    };
});

angular.module('memberCharge').controller('memberChargeApplyInfoCtrl', function ($scope, $uibModalInstance,getDatas) {
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

angular.module('memberCharge').controller('memberChargeRemarksCtrl', function ($scope, $uibModalInstance,getDatas) {
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

angular.module('memberCharge').filter("MemberChargeStatusFilter",function () {
    return function (input) {
        if(input=='0'){return '审核中'};
        if(input=='1'){return '待充值'};
        if(input=='2'){return '审核驳回'};
        if(input=='3'){return '充值成功'};
    }
});

