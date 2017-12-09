/**
 * Created by lijingx on 8/24/2017.
 */
angular.module('admWarning').controller('admWarningCtrl',function ($q, title, $scope, $http,$uibModal, Notify, $state, $stateParams, $sessionStorage) {
    title.setTitle('job奖金发放管理');
    $scope.notData = false;
    $scope.myPage = {
        pageNo: 1,
        pageSize: 10,
        totalCount: 0,
        result: [],
        parameterMap: {
        }
    };
    $scope.titleData = {};

    var e1 = $('.full-view');
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
        $scope.startLoading();
        $http.post(ctx + '/admWarning/list', $scope.myPage)
            .success(function (resp) {
                if (resp.successful) {
                    $scope.myPage = resp.data;
                    $scope.notData = false;
                    if (!$scope.myPage.result || $scope.myPage.result.length == 0) $scope.notData = true;
                    $scope.stopLoading();
                } else {
                    $scope.stopLoading();
                    Notify.error(resp.errorMessage);
                }

            }).error(function (error) {
            $scope.stopLoading();
            Notify.error(error);
        });
    }
    $scope.getPlatformAccount = function(){
        $scope.startLoading();
        $http.post(ctx + '/admWarning/getPlatformAccount')
            .success(function (resp) {
                if (resp.successful) {
                    $scope.titleData = resp.data.platformAccount;
                    $scope.stopLoading();
                } else {
                    $scope.stopLoading();
                    Notify.error(resp.errorMessage);
                }

            }).error(function (error) {
            $scope.stopLoading();
            Notify.error(error);
        });
    }

    /**
     * 初始化
     */
    $scope.onInit = function () {

        $scope.search();
        $scope.getPlatformAccount();
    };

    $scope.onInit();

    $scope.bonusPool ={};

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

    $scope.bonusProc =function (poolType) {
        $http.post(ctx + '/admWarning/getBonusPool?poolType='+poolType)
            .success(function (resp) {
                if (resp.successful) {
                   $scope.open(resp.data,poolType);
                }else {
                    Notify.error(resp.errorMessage);
                }
            }).error(function (error) {
             Notify.error(error);
        });
    }

    $scope.open = function(data,poolType)
    {
        var out = $uibModal.open(
            {
                animation: true,
                backdrop: 'static',
                templateUrl: "bonusProc.html",
                controller: "bonusProcCtrl",
                resolve:
                {
                    getDatas: function()
                    {
                        return data;
                    },
                    getPoolType:function () {
                        return poolType;
                    },
                    startLoading:function () {
                        return $scope.startLoading;
                    },
                    stopLoading:function () {
                        return $scope.stopLoading;
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

    $scope.transferBonusProc =function (poolType) {
        $http.post(ctx + '/admWarning/getTransferBonusPool?poolType='+poolType)
            .success(function (resp) {
                if (resp.successful) {
                    $scope.open2(resp.data,poolType);
                }else {
                    Notify.error(resp.errorMessage);
                }
            }).error(function (error) {
            Notify.error(error);
        });
    }

    $scope.open2 = function(data,poolType)
    {
        var out = $uibModal.open(
            {
                animation: true,
                backdrop: 'static',
                templateUrl: "transferBonusProc.html",
                controller: "transferBonusProcCtrl",
                resolve:
                    {
                        getDatas: function()
                        {
                            return data;
                        },
                        getPoolType:function () {
                            return poolType;
                        },
                        startLoading:function () {
                            return $scope.startLoading;
                        },
                        stopLoading:function () {
                            return $scope.stopLoading;
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
    $scope.showReadme = false;
    $scope.showIntro = function () {
        $scope.showReadme = !$scope.showReadme;
    }
});

angular.module('bonus').controller('bonusProcCtrl', function ($scope, $uibModalInstance,getDatas,getPoolType,Notify,$http,startLoading,stopLoading) {

    $scope.datas = getDatas;
    $scope.poolType = getPoolType;
    $scope.payAmt = 0;


    $scope.ok = function()
    {
        if (angular.isUndefined($scope.payAmt) || !(/^\+?[1-9][0-9]*$/.test($scope.payAmt))) {
            Notify.warning('请输入正确的金额')
            return false;
        }
        if ( $scope.datas.bonusPoolAmt < $scope.payAmt ) {
            Notify.warning('资金池金额余额不足')
            return false;
        }
        startLoading();
        $http.post(ctx + '/admWarning/payAmtProc?poolType='+$scope.poolType+'&amount='+$scope.payAmt)
            .success(function (resp) {
                if (resp.successful) {
                    $uibModalInstance.close(true);
                    Notify.success('补发成功');

                }else {
                    Notify.error(resp.errorMessage);
                }
                stopLoading();
            }).error(function (error) {
            Notify.error(error);
            stopLoading();
        });
    };
    $scope.cancel = function()
    {
        $uibModalInstance.dismiss('cancel');
    };
});
angular.module('bonus').controller('transferBonusProcCtrl', function ($scope, $uibModalInstance,getDatas,getPoolType,Notify,$http,startLoading,stopLoading,$state) {

    $scope.datas = getDatas;
    $scope.poolType = getPoolType;
    $scope.payAmt = 0;

    $scope.ok = function()
    {
        if (angular.isUndefined($scope.payAmt) || !(/^\+?[1-9][0-9]*$/.test($scope.payAmt))) {
            Notify.warning('请输入正确的金额')
            return false;
        }
        if ( $scope.datas.accountAmount < $scope.payAmt ) {
            Notify.warning('公司账户资金余额不足')
            return false;
        }
        startLoading();
        $http.post(ctx + '/admWarning/transferPayAmtProc?poolType='+$scope.poolType+'&amount='+$scope.payAmt)
            .success(function (resp) {
                if (resp.successful) {
                    $uibModalInstance.close(true);
                    Notify.success('拨款成功');
                    stopLoading();
                    $state.go("app.admWarning", {}, {reload: true});

                }else {
                    Notify.error(resp.errorMessage);
                }
                stopLoading();
            }).error(function (error) {
            Notify.error(error);
            stopLoading();
        });
    };
    $scope.cancel = function()
    {
        $uibModalInstance.dismiss('cancel');
    };

    $scope.getPlatformDetail = function () {

    }
});

