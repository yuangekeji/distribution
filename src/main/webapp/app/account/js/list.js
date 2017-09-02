angular.module('account').controller('accountListCtrl',
    function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, $log,ConfirmModal) {
    title.setTitle('我的账户');

    //账户信息
    $scope.accountInfo = {};
    $scope.validateErrors ={};
    //定义转账model
    $scope.transfer = {};

    /**
     * 页面初始化查询账户信息
     */
    $scope.getAccount =function () {
        $http.get(ctx + '/transfer/getAccountInfo').success(function (resp) {
            if(resp.successful){
                 $scope.accountInfo = resp.data.account;
            }else{
                console.error('获取账户信息错误，请重新尝试');
            }
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
            ConfirmModal.show({text: '转账金额不能超过奖金币余额。', isCancel:false });
            return false;
        }

        $http.get(ctx + '/transfer/getMemberByPhone?phone='+$scope.transfer.receivePhone).success(function (resp) {

            if (resp.successful) {
                $scope.transfer.receiveName = resp.data.member.memberName;

                ConfirmModal.show({
                    text: '确定要转账给'+$scope.transfer.receiveName+'用户'+$scope.transfer.transferAmt+'吗？',
                    isCancel:true //false alert ,true confirm
                }).then(function (sure) {
                    if (!sure) {
                        return;
                    }
                    $http.post(ctx + '/transfer/insert',
                        {
                            transferAmt:$scope.transfer.transferAmt,
                            receivePhone:$scope.transfer.receivePhone,
                            receiveName:$scope.transfer.receiveName,
                            payPassword:$scope.transfer.payPassword
                        }).success(function (resp) {
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
                            console.error("转账失败，请稍后再试。")
                            $window.location.reload();
                        }
                        $scope.onInit();
                    });

                })
            }else{
                ConfirmModal.show({text: '请确认收款用户的手机号是否正确。', isCancel:false });
            }

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

});
