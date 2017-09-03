angular.module('bonus').controller('bonusCtrl',function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage,$uibModal) {
    title.setTitle('奖金明细');

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
            memberId: '',
            orderNo: '',
            orderStartDate: '',
            orderEndDate: ''
        }
    };

    $scope.search = function(){
        $http.post(ctx + '/bonus/list', $scope.myPage)
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
        $http.post(ctx + '/bonus/detail', $scope.myDetail)
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
                templateUrl: "bonusDetail.html",
                controller: "BonusDetailCtrl",
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

angular.module('bonus').controller('BonusDetailCtrl', function ($scope, $uibModalInstance,getDatas) {

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
angular.module('bonus').filter("StatusFilter",function () {
    return function (input) {
        if(input=='0'){return '销售奖'};
        if(input=='1'){return '一代奖'};
        if(input=='2'){return '二代奖'};
        if(input=='3'){return '分红包奖'};
        if(input=='4'){return '见点奖'};
        if(input=='5'){return '级差奖'};
        if(input=='6'){return '全国董事奖'};
        if(input=='7'){return '工作室奖'};
        if(input=='8'){return '运营中心奖'};
        if(input=='9'){return '运营中心扶持奖'};
    }
});