angular.module('account').controller('accountListCtrl',
    function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage, $log,ConfirmModal) {
    title.setTitle('我的账户');

    //账户信息
    $scope.accountInfo = {};

    //定义转账model
    $scope.transfer = {
        transferAmt:0.00,
        receviedPhone:'',
        receviedName:'',
        payPassword:''
    };

    /**
     * 页面初始化查询账户信息
     */
    $scope.getAccount =function () {
        $http.get(ctx + '/transfer/getAccountInfo').success(function (resp) {
            if(resp.successful){
                 $scope.accountInfo = resp.data.account;
            }
        });

    }

    $scope.onInit=function () {

        $scope.getAccount();
    }

    $scope.onInit();

    $scope.commitTransfer= function () {

        //校验转入账户是否存在，询问用户，是否正确
        //转账处理，判断密码是否正确，若正确正常转账，否则提示密码错误

        $http.get(ctx + '/transfer/getMemberByPhone?phone='+$scope.transfer.receviedPhone).success(function (resp) {

            $scope.transfer.receviedName = resp.data.member.memberName;

            ConfirmModal.show({
                text: '确定要转账给'+$scope.transfer.receviedName+'用户'+$scope.transfer.transferAmt+'吗？',
                isCancel:true //false alert ,true confirm
            }).then(function (sure) {
                if (!sure) {
                    return;
                }

                $http.post(ctx + '/transfer/transferProcess',$scope.transfer).success(function (resp) {
                    console.info(resp);
                   $scope.msg = "";

                   if(resp.data.result == 'success'){
                       $scope.msg = "转账成功";

                   }else if(resp.data.result == 'pwdWrong'){

                       $scope.msg = "支付密码错误";

                   }else if(resp.data.result == 'fail'){

                       $scope.msg = "转账失败，请重新尝试";
                   }

                    ConfirmModal.show({
                        text: $scope.msg,
                        isCancel:false //false alert ,true confirm
                    }).then(function (sure) {
                        if (!sure) {
                            return;
                        }
                    });
                });

            })
        });

    }

});
