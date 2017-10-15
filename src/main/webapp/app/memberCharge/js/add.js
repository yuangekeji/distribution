angular.module('memberCharge').controller('memberChargeAddCtrl',function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, $log, ConfirmModal, Notify) {
    title.setTitle('充值申请');
    $scope.user = $sessionStorage.currentUser;
    //定义充值model
    $scope.memberCharge = {};

    //账户信息
    $scope.accountInfo = {};
    $scope.validateErrors ={};
    $scope.memberChargeValidateErrors={};


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

    /**
     * 页面初始化查询账户信息
     */
    $scope.getAccount =function () {
        $scope.startLoading();
        $http.get(ctx + '/memberCharge/getAccountInfo').success(function (resp) {

            if(resp.successful){
                $scope.accountInfo = resp.data.account;
            }else{
                Notify.error('获取账户信息错误，请重新尝试');
            }
            $scope.stopLoading();
        }).error(function (error) {
            Notify.error(error);
            $scope.stopLoading();
        });

    }
    $scope.onInit=function () {
        $scope.getAccount();

        $scope.memberCharge = {
            chargeAmt      : 0.00
        };
    }
    $scope.onInit();
    $scope.memberChargeCommit= function () {


        if( !$scope.memberChargeValidate()) {
            Notify.warning('请填写完整的充值申请信息');
            return false;
        }
        ConfirmModal.show({
            text: '确定要申请充值吗？',
            isCancel:true //false alert ,true confirm
        }).then(function (sure) {
            if (!sure) {
                return;
            }

            $scope.startLoading();

            $http.post(ctx + '/memberCharge/insertMemberCharge', $scope.memberCharge).success(function (resp) {
                if(resp.successful) {
                    //$scope.msg = "";
                    if (resp.data.result == 'success') {
                        Notify.success("充值申请成功");
                        $state.go("app.memberCharge", {}, {reload: true});
                    } else if (resp.data.result == 'fail') {
                        Notify.error("充值申请失败，请重新尝试");
                        $window.location.reload();
                    }
                    $scope.stopLoading();
                }else{
                    Notify.error("充值申请失败，请稍后再试");
                    //失败后停止loading，刷新页面
                    $scope.stopLoading();
                    $window.location.reload();
                }
            });
        });
    }

    /**
     * 充值申请校验
     * @returns {*}
     */
    $scope.memberChargeValidate = function () {
        $scope.memberChargeValidateErrors={};

        for (conditionthis in $scope.memberChargeValidateConditionArray) {
            $scope.memberChargeValidateConditionArray[conditionthis]();
        }
        return $.isEmptyObject(this.memberChargeValidateErrors)
    }

    $scope.memberChargeValidateConditionArray = {
        payMoneyTimeError: function () {
            if (angular.isUndefined($scope.memberCharge.payMoneyTime) ||
                $scope.memberCharge.payMoneyTime.length <= 0) {
                $scope.memberChargeValidateErrors.payMoneyTimeError = true;
            }
        },
        payMoneyTypeError: function () {
            if (angular.isUndefined($scope.memberCharge.payMoneyType) ||
                $scope.memberCharge.payMoneyType.length <= 0) {
                $scope.memberChargeValidateErrors.payMoneyTypeError = true;
            }
        },
        chargeAmtError: function () {
            if (angular.isUndefined($scope.memberCharge.chargeAmt) || !(/^\+?[1-9][0-9]*$/.test($scope.memberCharge.chargeAmt))) {
                $scope.memberChargeValidateErrors.chargeAmtError = true;
            }
        },
        applyInfoError: function () {
            if (angular.isUndefined($scope.memberCharge.applyInfo) ||
                $scope.memberCharge.applyInfo.length <= 0) {
                $scope.memberChargeValidateErrors.applyInfoError = true;
            }
        }
    }
});