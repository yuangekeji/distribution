angular.module('home').controller('homeCtrl',
    function ($scope, $http, title, $sessionStorage, $timeout, $state,$rootScope,ConfirmModal,settings ,$uibModal, $log) {
    title.setTitle('home');

    $scope.$on('$viewContentLoaded', function() {
        // initialize core components
        App.initAjax();

        // set default layout mode
        $rootScope.settings.layout.pageContentWhite = true;
        $rootScope.settings.layout.pageBodySolid = false;
        $rootScope.settings.layout.pageSidebarClosed = false;
    });

    $scope.currentUser = $sessionStorage.currentUser;
    console.log("$sessionStorage.currentUser："+$sessionStorage.currentUser);

    $scope.test =function () {
        ConfirmModal.show({
            text: '确定要转账给该用户3000.00吗？',
            isCancel:false //false alert ,true confirm
        }).then(function (sure) {
            if (!sure) {
                return;
            }
        })
    }
    /**
     * description 激活弹出窗
     * @author Bright
     * */
    $timeout(function(){
        if(!$scope.currentUser)
            $scope.currentUser = $sessionStorage.currentUser;
        if($scope.currentUser.moneyStatus=='Y' && $scope.currentUser.status=='N'){
            $http.get(ctx + "/member/getDictionary/bank_name").success(function (resp) {
                if(resp.successful){
                    $scope.dictionary = resp.data;
                    if($scope.dictionary){
                        $scope.currentUser.bankName = $scope.dictionary[0].dicCode;
                    }
                }else{
                    console.log(resp);
                }
            });
            $("#add").modal("show");
        }
    },1500);

    /**
     * description 激活账户
     * @author Bright
     * */
    $scope.activation = function () {
        if(!$scope.currentUser.idNumber||!$scope.currentUser.idNumber.trim()){
            alert("请输入身份证号码。");
        }else if(!/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/.test($scope.currentUser.idNumber.trim())){
            alert("身份证号码格式有误，请重新输入。")
        }else if(!$scope.currentUser.bankName){
            alert("请选择开户行。")
        }else if(!$scope.currentUser.bankUserName||!$scope.currentUser.bankUserName.trim()){
            alert("请输入开户人姓名。");
        }else if(!$scope.currentUser.cardNumber||!$scope.currentUser.cardNumber.trim()){
            alert("请输入银行卡号。")
        }else if(!/^[0-9]*$/.test($scope.currentUser.cardNumber)){
            alert("银行卡号只能为纯数字，请重新输入。")
        }else if(!$scope.currentUser.queryPassword||!$scope.currentUser.queryPassword.trim()){
            alert("请输入二级密码。")
        }else if(!$scope.currentUser.queryPasswordConfirm||!$scope.currentUser.queryPasswordConfirm.trim()){
            alert("请确认二级密码。")
        }else if($scope.currentUser.queryPassword!=$scope.currentUser.queryPasswordConfirm){
            alert("二级密码和二级密码确认不同，请重新输入。")
        }else if(!$scope.currentUser.payPassword||!$scope.currentUser.payPassword.trim()){
            alert("请输入三级密码。")
        }else if(!$scope.currentUser.payPasswordConfirm||!$scope.currentUser.payPasswordConfirm.trim()){
            alert("请确认三级密码。")
        }else if($scope.currentUser.payPassword!=$scope.currentUser.payPasswordConfirm){
            alert("三级密码和三级密码确认不同,请重新输入。")
        }else{
            $scope.currentUser.status='Y';
            $http.post(ctx + "/member/activation",$scope.currentUser).success(function (resp) {
                if(resp.successful){
                    $("#add").modal("hide");
                }
            }).error(function (resp) {
                console.log(resp);
            });
        }
    };

    /**
     * description alert和confirm代码demo
     * @author Bright
     * */
    //start
    $scope.info = function () {
        var txt=  "提示文字，提示文字，提示文字，提示文字，提示文字，提示文字";
        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info);
    };
    $scope.confirm = function () {
        var txt=  "提示文字，提示文字，提示文字，提示文字，提示文字，提示文字";
        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,{
            onOk:function(){
                window.wxc.xcConfirm("您选择了确认", window.wxc.xcConfirm.typeEnum.info);
            },
            onCancel:function(){
                window.wxc.xcConfirm("您选择了取消", window.wxc.xcConfirm.typeEnum.info);
            }
        });
    };
    $scope.warning = function () {
        var txt=  "提示文字，提示文字，提示文字，提示文字，提示文字，提示文字";
        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.warning);
    };
    $scope.error = function () {
        var txt=  "提示文字，提示文字，提示文字，提示文字，提示文字，提示文字";
        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
    };
    $scope.success = function () {
        var txt=  "提示文字，提示文字，提示文字，提示文字，提示文字，提示文字";
        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success);
    };
    $scope.input = function () {
        var txt=  "请输入";
        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.input,{
            onOk:function(v){
                console.log(v);
            }
        });
    };
    $scope.custom = function () {
        var txt=  "自定义呀";
        var option = {
            title: "自定义",
            btn: parseInt("0011",2),
            onOk: function(){
                console.log("确认啦");
            }
        };
        window.wxc.xcConfirm(txt, "custom", option);
    };
    //end

    /**
     * @param {number} opt_attributes
     * @return {undefined}
     */
    $scope.open = function(opt_attributes)
    {
        var out = $uibModal.open(
            {
                animation: $scope.animationsEnabled,
                backdrop: 'static',
                templateUrl: "myModalContent.html",
                controller: "ModalInstanceCtrl",
                size: opt_attributes,
                resolve:
                {
                    getMsg: function()
                    {
                        return "确定要转账给该用户吗？";
                    },
                    getType: function()
                    {
                        return "confirm";
                    }
                }
            });
        out.result.then(function(value)
        {
            console.info('确认');

        }, function()
        {
            console.info('取消');
        });
    };

});
angular.module('home').controller('ModalInstanceCtrl', function ($scope, $uibModalInstance, getMsg,getType) {
    $scope.msg = getMsg;
    $scope.type = getType;

    $scope.ok = function()
    {
        $uibModalInstance.close(true);
    };
    $scope.cancel = function()
    {
        $uibModalInstance.dismiss('cancel');
    };
});