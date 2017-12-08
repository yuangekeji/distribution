angular.module('account').controller('accountHistoryCtrl',
    function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, $log, ConfirmModal,Notify) {
        title.setTitle('账户明细');
        $scope.myPage = {
            pageNo: 1,
            pageSize: 10,
            totalCount: 0,
            result: [],
            parameterMap: {
                startTime: "",
                endTime: ""
            }
        };

        $scope.goMemberView = function () {
            $state.go('app.member.overview');
        }


        /**
         * 查询订单列表
         */
        $scope.search = function(){
            $http.post(ctx + '/member/accountHistory', $scope.myPage)
                .success(function (resp) {
                    if (resp.successful) {
                        $scope.myPage = resp.data;
                        $scope.notData = false;
                        if (!$scope.myPage.result || $scope.myPage.result.length == 0) $scope.notData = true;
                    } else {
                        Notify.error("账户明细查询失败，请重新尝试");
                        console.log(resp.errorMessage);
                    }
                }).error(function (error) {
                Notify.error("账户明细查询失败，请重新尝试");
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
            $scope.search();

        }


        /**
         * 查询按钮触发
         */
        $scope.searchByParam =function () {

            $scope.myPage.pageNo = 1;
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
