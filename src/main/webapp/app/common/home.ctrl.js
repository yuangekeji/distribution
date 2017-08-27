angular.module('home').controller('homeCtrl', function ($scope, $http, title, $sessionStorage, $timeout, $state,$rootScope,settings ,$uibModal, $log) {
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

    $("#btn8").click(function(){
        var txt=  "默认";
        window.wxc.xcConfirm(txt);
    });
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
angular.module('home').controller('ModalInstanceCtrl', function ($scope, $uibModalInstance, getMsg,getType)
{
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