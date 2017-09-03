angular.module('admDividend').controller('admDividendCtrl',function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage) {
    title.setTitle('分红包管理');

    $scope.notData = false;
    $scope.myPage = {
        pageNo: 1,
        pageSize: 10,
        totalCount: 0,
        result: [],
        parameterMap: {
            orderNo:'',
            dividendStatus:''
        }
    };
    $scope.search = function(){

        $http.post(ctx + '/adminDividend/list', $scope.myPage)
            .success(function (resp) {
                if (resp.successful) {
                    $scope.myPage = resp.data;
                    $scope.notData = false;
                    if (!$scope.myPage.result || $scope.myPage.result.length == 0) $scope.notData = true;

                } else {
                    console.error(resp.errorMessage);
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
    /**
     * 分红包明细查询
     * @param memberId
     * @param orderId
     */
    $scope.searchDividendDetails = function (memberId, orderNo) {
        $state.go('app.admDividend-detail', {memberId: memberId, orderNo: orderNo});
    }
});
angular.module('admDividend').filter("StatusFilter",function () {
    return function (input) {
        if(input=='1'){return '领取中'};
        if(input=='2'){return '领取完'};
    }
});