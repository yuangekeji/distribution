angular.module('advance').controller('advanceAddCtrl',function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, $log, ConfirmModal, Notify) {
    title.setTitle('申请提现');
    $scope.user = $sessionStorage.currentUser;
    //定义提现model
    $scope.advance = {};
    //个人信息
    $scope.MemberInfo = {
        bankName    : '',
        bankUserName: '',
        cardNumber  : ''
    };


    //账户信息
    $scope.accountInfo = {};
    $scope.validateErrors ={};
    $scope.advanceValidateErrors={};


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
        $http.get(ctx + '/advance/getAccountInfo').success(function (resp) {

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

    /**
     * 页面初始化查询个人信息
     */
    $scope.getMemberInfo = function () {
       $scope.startLoading();
        $http.get(ctx + '/member/getMemberInfoById/'+$scope.user.id).success(function (resp) {
            if(resp.successful){
                $scope.MemberInfo = resp.data.member;
                $scope.banks = resp.data.list;
            }else{
                Notify.error("获取个人信息错误，请重新尝试");
            }
           $scope.stopLoading();
        }).error(function (error) {
            Notify.error(error);
            $scope.stopLoading();
        });
    }
    $scope.onInit=function () {
        $scope.getMemberInfo();
        $scope.getAccount();

        $scope.advance = {
            reqAmt      : 0.00,
            feeAmt      : 0.00,
            actAmt      : 0.00,
            payPassword : ''
        };
    }
    $scope.onInit();

    $scope.onInitParam=function () {
        $scope.advance.payPassword = '';
    }

    $scope.advanceCommit= function () {


        if( !$scope.advanceValidate()) {
            Notify.warning('请填写完整的提现信息');
            return false;
        }

        if($scope.advance.reqAmt < 100  ){
            Notify.warning('100元以上金额可以申请提现');
            return false;
        }

        if($scope.advance.reqAmt % 100 != 0  ){
            Notify.warning('提现金额应为100的整数倍');
            return false;
        }

        if($scope.advance.reqAmt >  $scope.accountInfo.bonusAmt){
            Notify.warning('提现金额不能大于账户总余额');
            return false;
        }

        ConfirmModal.show({
            text: '确定要申请提现吗？',
            isCancel:true //false alert ,true confirm
        }).then(function (sure) {
            if (!sure) {
                return;
            }

            $scope.startLoading();

            $http.post(ctx + '/advance/insertAdvance',
            {
                bankName    : $scope.MemberInfo.bankName,
                cardName    : $scope.MemberInfo.bankUserName,
                cardNo      : $scope.MemberInfo.cardNumber,
                reqAmt      : $scope.advance.reqAmt,
                feeAmt      : $scope.advance.feeAmt,
                actAmt      : $scope.advance.actAmt,
                payPassword : $scope.advance.payPassword
            }).success(function (resp) {
                //处理完成后重新获取账户信息，个人信息
                if(resp.successful) {
                    //$scope.msg = "";
                    if (resp.data.result == 'success') {
                        $scope.onInit();
                        Notify.success("提现成功");
                        $state.go("app.advance", {}, {reload: true});
                    } else if (resp.data.result == 'pwdWrong') {
                        $scope.onInitParam();
                        Notify.error("支付密码错误");
                    } else if (resp.data.result == 'fail') {
                        Notify.error("提现失败，请重新尝试");
                        $window.location.reload();
                    }
                    $scope.stopLoading();
                    //ConfirmModal.show({text: $scope.msg, isCancel: false});
                    //$scope.go("app.advance");

                }else{
                    Notify.error("提现失败，请稍后再试");
                    //失败后停止loading，刷新页面
                    $scope.stopLoading();
                    $window.location.reload();
                }
            });
        });
    }

    /**
     * 提现校验
     * @returns {*}
     */
    $scope.advanceValidate = function () {
        $scope.advanceValidateErrors={};

        for (conditionthis in $scope.advanceValidateConditionArray) {
            $scope.advanceValidateConditionArray[conditionthis]();
        }
        return $.isEmptyObject(this.advanceValidateErrors)
    }

    $scope.advanceValidateConditionArray = {
        bankNameError: function () {
            if (angular.isUndefined($scope.MemberInfo.bankName) ||
                $scope.MemberInfo.bankName.length <= 0) {
                $scope.advanceValidateErrors.bankNameError = true;
            }
        },
        bankUserNameError: function () {
            if (angular.isUndefined($scope.MemberInfo.bankUserName) ||
                $scope.MemberInfo.bankUserName.length <= 0) {
                $scope.advanceValidateErrors.bankUserNameError = true;
            }
        },
        cardNumberError: function () {
            if (angular.isUndefined($scope.MemberInfo.cardNumber) ||
                $scope.MemberInfo.cardNumber.length <= 0) {
                $scope.advanceValidateErrors.cardNumberError = true;
            }
        },
        advanceAmtError: function () {
            if (angular.isUndefined($scope.advance.reqAmt) || !(/^\+?[1-9][0-9]*$/.test($scope.advance.reqAmt))) {
                $scope.advanceValidateErrors.advanceAmtError = true;
            }
        },

        payPasswordError: function () {
            if (angular.isUndefined($scope.advance.payPassword) ||
                $scope.advance.payPassword.length <= 0) {
                $scope.advanceValidateErrors.payPasswordError = true;
            }
        }
    }

    $scope.changeReqAmt = function () {
        $scope.advance.feeAmt = ($scope.advance.reqAmt * 0.06).toFixed(2);
        $scope.advance.actAmt = $scope.advance.reqAmt - $scope.advance.feeAmt;
    }
});