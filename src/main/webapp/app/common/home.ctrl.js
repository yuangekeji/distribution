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