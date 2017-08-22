angular.module('home').controller('homeCtrl', function ($scope, $http, title, $sessionStorage, $timeout, $state,$rootScope,settings) {
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
    if($scope.currentUser){
        $state.go("");
    }
    $scope.alertDemo = function () {
        $.alert('这是alert弹窗');
    };

    $scope.confirmDemo = function () {
        $.modal({
            title: '提示，这里是可变的',
            text: '这是提示说明，也是可变的，通常这个属性可以不设置，直接使用title就行',
            buttons: [
                {
                    text: '确定',
                    onClick: function () {
                        $.alert("您选择了确定操作，这里便可以放上您箱操作的代码");
                    }
                },
                {
                    text: '取消',
                    onClick: function () {
                        $.alert("您选择了取消操作，确认窗关闭，什么都不做，这里可以空着");
                    }
                }
            ]
        });
    };

    $scope.modalDemo = function () {
        // $.alert("这是点击模态框时调取JS的操作");
    };

});
