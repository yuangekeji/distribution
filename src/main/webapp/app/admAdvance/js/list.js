angular.module('admAdvance').controller('admAdvanceCtrl',function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, $uibModal) {
    title.setTitle('提现管理');

    $scope.myPage = {
        pageNo: 1,
        pageSize: 10,
        totalCount: 0,
        result: [],
        parameterMap: {
            memberName:'',
            statues:'',
            startTime:'',
            endTime:''
        }
    };

    $scope.approvalRemark = {
            id: '',
            statues: '',
            remark: ''
    };

    $scope.search = function(){
        $http.post(ctx + '/admAdvance/list', $scope.myPage)
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

    /**提现批准*/
    $scope.confirmAdvance = function (id, statues) {
        $http.post(ctx + "/admAdvance/confirmAdvance",{id:id,statues:statues}).success(function (resp) {
            if(resp.successful){
                alert("提现审批完成。");
                $scope.search();
            }else{
                console.log(resp);
            }
        }).error(function (resp) {
            console.log(resp);
        })
    };

    /**提现驳回*/
    $scope.rejectAdvance = function (id, statues) {
        $scope.approvalRemark.id = id;
        $scope.approvalRemark.statues = statues;
        $scope.approvalRemark.remark = '';
        $scope.open();

    };

    $scope.open = function(opt_attributes)
    {
        var out = $uibModal.open(
            {
                animation: true,
                backdrop: 'static',
                templateUrl: "admAdvanceApproval.html",
                controller: "admAdvanceApprovalCtrl",
                size: opt_attributes,
                resolve: {
                    getDatas: function()
                    {
                        return $scope.approvalRemark;
                    }
                }
            });
        out.result.then(function(value)
        {
            console.info('确认');
        }, function()
        {
            console.info('取消');
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

    $scope.gotoAddPage = function () {
        $state.go("app.advanceAdd");
    };
});

angular.module('admAdvance').controller('admAdvanceApprovalCtrl', function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, $uibModalInstance,getDatas) {

    $scope.datas = getDatas;

    $scope.close = function()
    {
        $uibModalInstance.close(true);
    };
    $scope.cancel = function()
    {
        $uibModalInstance.dismiss('cancel');
    };

    /**提现驳回*/
    $scope.reject = function (id, statues, remark) {
        $http.post(ctx + "/admAdvance/confirmAdvance",{id:id,statues:statues,remark:remark}).success(function (resp) {
            if(resp.successful){
                alert("提现驳回完成。");
                $uibModalInstance.close(true);
                $scope.search();
            }else{
                console.log(resp);
            }
        }).error(function (resp) {
            console.log(resp);
        })
    };
});

angular.module('advance').filter("AdvanceStatuesFilter",function () {
    return function (input) {
        if(input=='1'){return '申请中'};
        if(input=='2'){return '已执行'};
        if(input=='3'){return '已驳回'};
    }
});
