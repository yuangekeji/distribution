angular.module('bonus').controller('bonusCtrl',function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage) {
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
            memberName: '',
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

    $scope.searchBonusDetail = function(memberId, memberName, orderNo, orderStartDate, orderEndDate){
        $scope.myDetail.parameterMap.memberId = memberId;
        $scope.myDetail.parameterMap.memberName = memberName;
        $scope.myDetail.parameterMap.orderNo = orderNo;
        $scope.myDetail.parameterMap.orderStartDate = orderStartDate;
        $scope.myDetail.parameterMap.orderEndDate = orderEndDate;
        $http.post(ctx + '/bonus/detail', $scope.myDetail)
            .success(function (resp) {
                if (resp.successful) {
                    $scope.myDetail = resp.data;
                } else {
                    console.log(resp.errorMessage);
                }

            }).error(function (error) {
            console.error(error);
        });
    }
});