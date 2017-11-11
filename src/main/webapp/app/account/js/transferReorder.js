angular.module('account').controller('transferReorderCtrl',
    function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, $log,ConfirmModal,Notify) {
    title.setTitle('账户管理');
    //账户信息
    $scope.accountInfo = {};
    $scope.validateErrors ={};
    $scope.reOrdervalidateErrors={};
    $scope.user = $sessionStorage.currentUser;

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
            orderQty: 0,
            price:600,
            amt:0.00,
            orderAmt:0.00,
            payPassword:'',
            bonusType:'1',
            sendbypostyn : '1',
            receiveName: $scope.user.consignee,
            expressAddress:$scope.user.expressAddress,
            recevivePhone:$scope.user.linkmanPhone
        }
    }
    $scope.onInit();
    $scope.commitTransfer= function () {

        //校验转入账户是否存在，询问用户，是否正确
        //转账处理，判断密码是否正确，若正确正常转账，否则提示密码错误
       if( !$scope.validate()) {
           Notify.warning('请填写正确的转账信息');
           return false;
       }


        if($scope.transfer.transferAmt > $scope.accountInfo.bonusAmt ){
            Notify.warning('转账金额不能超过奖金币余额');
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
                            transferAmt:parseInt($scope.transfer.transferAmt),
                            receivePhone:$scope.transfer.receivePhone,
                            receiveName:$scope.transfer.receiveName,
                            payPassword:$scope.transfer.payPassword
                        }).success(function (resp) {
                             $scope.stopLoading();
                             //处理完成后重新获取账户信息

                            if(resp.successful) {
                                $scope.msg = "";
                                if (resp.data.result == 'success') {
                                    Notify.success('转账成功');
                                    $scope.onInit();

                                } else if (resp.data.result == 'pwdWrong') {
                                    // $scope.msg = "支付密码错误";
                                    Notify.error('支付密码错误');
                                } else if (resp.data.result == 'fail') {
                                    // $scope.msg = "转账失败，请重新尝试";
                                    Notify.error('转账失败，请重新尝试');
                                    $scope.onInit();
                                }


                            }else{

                                Notify.error('转账金额不能超过奖金币余额');
                                //失败后停止loading，刷新页面
                                $scope.stopLoading();
                                $window.location.reload();
                            }

                    });

                })
            }
            else{

                $scope.stopLoading();
                Notify.warning('请确认收款账户信息是否正确');

            }

        }).error(function (error) {

            Notify.error(error);
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

        /*转账金额非0,正整数*/
        transferAmtError: function () {
            if (angular.isUndefined($scope.transfer.transferAmt) ||
                $scope.transfer.transferAmt <= 0 || !(/^\+?[1-9][0-9]*$/.test($scope.transfer.transferAmt))) {
                $scope.validateErrors.transferAmtError = true;
            }
        },
        receivePhoneError: function () {
            if (angular.isUndefined($scope.transfer.receivePhone) ||
                $scope.transfer.receivePhone.length < 1 ||!(/^1[3|4|5|7|8][0-9]\d{4,8}$/.test($scope.transfer.receivePhone))) {
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
           /*非0,正整数*/
           orderQtyError: function () {
                if (angular.isUndefined($scope.reOrder.orderQty) || !(/^\+?[1-9][0-9]*$/.test($scope.reOrder.orderQty))) {
                    $scope.reOrdervalidateErrors.orderQtyError = true;
                }
            },
          /*金额0和正整数*/
           amtError: function () {
                if (angular.isUndefined($scope.reOrder.amt)  || !(/^\d+$/.test($scope.reOrder.amt)))  {
                    $scope.reOrdervalidateErrors.amtError = true;
                }
            },
          // /*金额0和正整数*/
          //  bonusAmtError: function () {
          //       if (angular.isUndefined($scope.reOrder.bonusAmt)  || !(/^\d+$/.test($scope.reOrder.bonusAmt))) {
          //           $scope.reOrdervalidateErrors.bonusAmtError = true;
          //       }
          //   },
          payPasswordError: function () {
             if (angular.isUndefined($scope.reOrder.payPassword) ||
                 $scope.reOrder.payPassword.length <= 0) {
                 $scope.reOrdervalidateErrors.payPasswordError = true;
             }
         },
         receiveNameError:function () {
             if($scope.reOrder.sendbypostyn == '2' && (!$scope.reOrder.receiveName||!$scope.reOrder.receiveName.trim())){
                 $scope.reOrdervalidateErrors.receiveNameError = true;
             }
         },
         expressAddressError:function () {
             if($scope.reOrder.sendbypostyn == '2' && (!$scope.reOrder.expressAddress||!$scope.reOrder.expressAddress.trim())){
                 $scope.reOrdervalidateErrors.expressAddressError = true;
             }
         },
         recevivePhoneError:function () {
             if($scope.reOrder.sendbypostyn == '2' && (!$scope.reOrder.recevivePhone||!$scope.reOrder.recevivePhone.trim())){
                 $scope.reOrdervalidateErrors.recevivePhoneError = true;
             }
         }

        }

     $scope.reOrderCommit= function () {

         if( !$scope.reOrdervalidate()) {
             Notify.warning('请填写完整的复投信息');
             return false;
         }

         if($scope.reOrder.orderQty > 50 || $scope.reOrder.amt > 30000){
             Notify.warning('每日投资额不能超过3万，请重新填写复投信息。');
             return false;
         }

         if($scope.reOrder.bonusType == 1 && $scope.reOrder.amt > $scope.accountInfo.seedAmt){
             Notify.warning('扣除的种子币金额不能大于账户种子币余额');
             return false;
         }

         if($scope.reOrder.bonusType == 2 && $scope.reOrder.amt > $scope.accountInfo.bonusAmt){
             Notify.warning('扣除的奖金币金额不能大于账户奖金币余额');
             return false;
         }

         $scope.reOrder.orderAmt = $scope.reOrder.orderQty * $scope.reOrder.price;
         if($scope.reOrder.orderAmt != $scope.reOrder.amt  ){
             Notify.warning('请确认输入的金额和复投单金额是否匹配');
             return false;
         }


         $scope.startLoading();

         $http.post(ctx + '/order/reOrder',
             {
                 orderQty:parseInt($scope.reOrder.orderQty),
                 orderAmt:parseInt($scope.reOrder.orderAmt),
                 bonusType:$scope.reOrder.bonusType,
                 amt:parseInt($scope.reOrder.amt),
                 payPassword:$scope.reOrder.payPassword,
                 sendbypostyn:$scope.reOrder.sendbypostyn,
                 receiveName:$scope.reOrder.receiveName,
                 expressAddress:$scope.reOrder.expressAddress,
                 recevivePhone:$scope.reOrder.recevivePhone

             }).success(function (resp) {

             $scope.stopLoading();
             //处理完成后重新获取账户信息

             if(resp.successful) {
                 $scope.msg = "";
                 if (resp.data.result == 'success') {
                     Notify.success('复投成功，已生成分红包。');
                     $scope.onInit();
                 } else if (resp.data.result == 'pwdWrong') {
                     Notify.error('支付密码错误');
                 } else if(resp.data.result =='reOrderLimit'){
                     Notify.error('当日投资额已超过3万，请明日操作。');
                 }else if (resp.data.result == 'fail') {
                     Notify.error('复投失败，请重新尝试');
                     $scope.onInit();
                 }

             }else{
                 Notify.error('复投失败，请重新尝试');
                 //失败后停止loading，刷新页面
                 $scope.stopLoading();
                 $window.location.reload();
             }

         });

     }

});
