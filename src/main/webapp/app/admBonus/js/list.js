angular.module('admBonus').controller('admBonusCtrl',function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, $uibModal) {
    title.setTitle('分销记录');

    $scope.notData = false;
    $scope.myPage = {
        pageNo: 1,
        pageSize: 10,
        totalCount: 0,
        result: [],
        parameterMap: {
            orderNo:'',
            chinaPresidentBonusYN: true
        }
    };
    $scope.myDetail = {
        pageNo: 1,
        pageSize: 10,
        totalCount: 0,
        result: [],
        parameterMap: {
            orderNo: '',
            orderStartDate: '',
            orderEndDate: ''
        }
    };

    $scope.search = function(){
        $http.post(ctx + '/adminBonus/list', $scope.myPage)
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

    $scope.searchBonusDetail = function(orderNo, orderStartDate, orderEndDate){
        $scope.myDetail.parameterMap.orderNo = orderNo;
        $scope.myDetail.parameterMap.orderStartDate = orderStartDate;
        $scope.myDetail.parameterMap.orderEndDate = orderEndDate;
        $http.post(ctx + '/adminBonus/detail', $scope.myDetail)
            .success(function (resp) {
                if (resp.successful) {
                    $scope.myDetail = resp.data;
                    $scope.open();
                } else {
                    console.log(resp.errorMessage);
                }

            }).error(function (error) {
            console.error(error);
        });
    }

    $scope.open = function(opt_attributes)
    {
        var out = $uibModal.open(
            {
                animation: true,
                backdrop: 'static',
                templateUrl: "admBonusDetail.html",
                controller: "admBonusDetailCtrl",
                size: opt_attributes,
                resolve:
                {
                    getDatas: function()
                    {
                        return $scope.myDetail.result;
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
});
angular.module('admBonus').controller('admBonusDetailCtrl', function ($scope, $uibModalInstance,getDatas) {

    $scope.datas = getDatas;

    $scope.ok = function()
    {
        $uibModalInstance.close(true);
    };
    $scope.cancel = function()
    {
        $uibModalInstance.dismiss('cancel');
    };
});
