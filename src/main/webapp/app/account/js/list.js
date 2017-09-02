angular.module('account').controller('accountListCtrl',
    function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, $log,ConfirmModal) {
    title.setTitle('我的账户');
    //账户信息
    $scope.accountInfo = {};
    $scope.validateErrors ={};
    $scope.reOrdervalidateErrors={};

    //定义转账model
    $scope.transfer = {};
    //定义复投model
    $scope.reOrder = {};


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
        $http.get(ctx + '/transfer/getAccountInfo').success(function (resp) {

            if(resp.successful){
                 $scope.accountInfo = resp.data.account;
            }else{
                console.error('获取账户信息错误，请重新尝试');
            }
            $scope.stopLoading();
        });

    }
    $scope.onInit=function () {
        $scope.transfer = {
            transferAmt:0.00,
            receivePhone:'',
            receiveName:'',
            payPassword:''
        };
        $scope.getAccount();

        $scope.reOrder={
            reOrderCnt: 0,
            seedAmtMinus:0.00,
            bonusAmtMinus:0.00,
            totalAmtMinus:$scope.reOrder.reOrderCnt * 600
        }
    }
    $scope.onInit();
    $scope.commitTransfer= function () {

        //校验转入账户是否存在，询问用户，是否正确
        //转账处理，判断密码是否正确，若正确正常转账，否则提示密码错误
       if( !$scope.validate()) {
           ConfirmModal.show({text: '请填写完整的转账信息', isCancel:false });
           return false;
       }


        if($scope.transfer.transferAmt > $scope.accountInfo.bonusAmt ){
            ConfirmModal.show({text: '转账金额不能超过奖金币余额', isCancel:false });
            return false;
        }

        $scope.startLoading();

        $http.get(ctx + '/transfer/getMemberByPhone?phone='+$scope.transfer.receivePhone).success(function (resp) {

            if (resp.successful) {
                $scope.transfer.receiveName = resp.data.member.memberName;
                $scope.stopLoading();
                ConfirmModal.show({
                    text: '确定要转账给'+$scope.transfer.receiveName+'用户'+$scope.transfer.transferAmt+'吗？',
                    isCancel:true //false alert ,true confirm
                }).then(function (sure) {
                    if (!sure) {
                        return;
                    }
                    $scope.startLoading();

                    $http.post(ctx + '/transfer/insert',
                        {
                            transferAmt:$scope.transfer.transferAmt,
                            receivePhone:$scope.transfer.receivePhone,
                            receiveName:$scope.transfer.receiveName,
                            payPassword:$scope.transfer.payPassword
                        }).success(function (resp) {
                             $scope.stopLoading();
                             //处理完成后重新获取账户信息
                             $scope.onInit();
                            if(resp.successful) {
                                $scope.msg = "";
                                if (resp.data.result == 'success') {
                                    $scope.msg = "转账成功";

                                } else if (resp.data.result == 'pwdWrong') {
                                    $scope.msg = "支付密码错误";
                                } else if (resp.data.result == 'fail') {
                                    $scope.msg = "转账失败，请重新尝试";
                                }
                                ConfirmModal.show({text: $scope.msg, isCancel: false});

                            }else{
                                console.error("转账失败，请稍后再试");
                                //失败后停止loading，刷新页面
                                $scope.stopLoading();
                                $window.location.reload();
                            }

                    });

                })
            }
            else{

                $scope.stopLoading();
                ConfirmModal.show({text: '请确认收款账户信息是否正确', isCancel:false });

            }

        }).error(function (error) {
            console.info(error);
            $scope.stopLoading();
        });

    }
    $scope.validate = function () {
           $scope.validateErrors={};

            for (conditionthis in $scope.validateConditionArray) {
                    $scope.validateConditionArray[conditionthis]();
            }
            return $.isEmptyObject(this.validateErrors)
    }
    $scope.validateConditionArray = {

        transferAmtError: function () {
            if (angular.isUndefined($scope.transfer.transferAmt) ||
                $scope.transfer.transferAmt <= 0) {
                $scope.validateErrors.transferAmtError = true;
            }
        },
        receivePhoneError: function () {
            if (angular.isUndefined($scope.transfer.receivePhone) ||
                $scope.transfer.receivePhone.length < 1) {
                $scope.validateErrors.receivePhoneError = true;
            }
        },
        // receiveNameError: function () {
        //     if (angular.isUndefined($scope.transfer.receiveName) ||
        //         $scope.transfer.receiveName.length <= 0) {
        //         $scope.validateErrors.receiveNameError = true;
        //     }
        // },
        payPasswordError: function () {
            if (angular.isUndefined($scope.transfer.payPassword) ||
                $scope.transfer.payPassword.length <= 0) {
                $scope.validateErrors.payPasswordError = true;
            }
        }

    }

        /**
         * 复投校验
         * @returns {*}
         */
     $scope.reOrdervalidate = function () {
            $scope.reOrdervalidateErrors={};

            for (conditionthis in $scope.reOrdervalidateConditionArray) {
                $scope.reOrdervalidateConditionArray[conditionthis]();
            }
            return $.isEmptyObject(this.reOrdervalidateErrors)
        }

     $scope.reOrdervalidateConditionArray = {
            transferAmtError: function () {
                if (angular.isUndefined($scope.reOrder.reOrderCnt) ||
                    $scope.reOrder.reOrderCnt <= 0) {
                    $scope.reOrdervalidateErrors.reOrderCntError = true;
                }
            },
            receivePhoneError: function () {
                if (angular.isUndefined($scope.reOrder.seedAmtMinus) ||
                    $scope.reOrder.seedAmtMinus.length < 1) {
                    $scope.reOrdervalidateErrors.seedAmtMinusError = true;
                }
            },
            payPasswordError: function () {
                if (angular.isUndefined($scope.reOrder.bonusAmtMinus) ||
                    $scope.reOrder.bonusAmtMinus.length <= 0) {
                    $scope.reOrdervalidateErrors.bonusAmtMinusError = true;
                }
            }

        }

     $scope.reOrderCommit= function () {


         if( !$scope.reOrdervalidate()) {
             ConfirmModal.show({text: '请填写完整的复投信息', isCancel:false });
             return false;
         }

         if($scope.reOrder.totalAmtMinus != ( $scope.reOrder.seedAmtMinus +$scope.reOrder.bonusAmtMinus)  ){
             ConfirmModal.show({text: '请确认输入的金额和复投单金额是否匹配', isCancel:false });
             return false;
         }

     }

});
