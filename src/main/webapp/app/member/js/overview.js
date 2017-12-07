angular.module('member').controller('memberOverviewCtrl',
    function ($q, title, $scope, $http,  $state, $stateParams, $sessionStorage,$rootScope) {
        title.setTitle('个人中心');

        $scope.$on('$viewContentLoaded', function() {
            App.initAjax(); // initialize core components
            // Layout.setAngularJsSidebarMenuActiveLink('set', $('#sidebar_menu_link_profile'), $state); // set profile link active in sidebar menu
        });

        // set sidebar closed and body solid layout mode
        $rootScope.settings.layout.pageBodySolid = true;
        $rootScope.settings.layout.pageSidebarClosed = false;

        $scope.accountHistory = function (id) {
            $state.go('app.accountHistory',{'memberId':id});
        }
        $scope.recommend = function () {
            $state.go('app.recommend');
        }

    });