var App = angular.module('app', [
    'ngSanitize',
    'ui.router',
    'oc.lazyLoad',
    'app.lazyload',
    'app.routes',
    'ngStorage',
    //业务模块
    'home',
    'recommend',
    'member',
    'advance',
    'account',
    'bonus',
    'dividend',
    'graph',
    'order'
]);

angular.module('app.lazyload', []);
angular.module('app.routes', []);
angular.module('home', []);
angular.module('recommend', []);
angular.module('member', []);
angular.module('advance', []);
angular.module('account', []);
angular.module('bonus', []);
angular.module('dividend', []);
angular.module('graph', []);
angular.module('order', []);


App.controller('AppCtrl', function ($scope, $rootScope, $http, $state, $sessionStorage) {

    $scope.ctx = window['ctx'];

    // $http.get(ctx + '/member/getUserFromSession').success(function (res) {
    //         if (res.successful) {
    //         $sessionStorage.currentUser = res.data;
    //     }


    //
    // }).error(function (error) {
    //     alert('用户获取失败');
    // });


    /*Model中$watch函数影响变量用于记录当前页面是否改变过内容并没保存*/
        var _preventNavigation = false;
        /*_preventNavigationUrl记录当前url用于与将要跳转的url进行比较*/
        var _preventNavigationUrl = null;
        /**
         * 相应工具函数
         * allowNavigation：允许跳转
         * preventNavigation：拒绝跳转
         * checkNavigation：验证当前状态是否需要confirm。用于在拦截器无法生效的时候手动调用。
         */
        $rootScope.allowNavigation = function () {
            _preventNavigation = false;
        };
        $rootScope.checkNavigation = function () {
            return _preventNavigation;
        };
        $rootScope.preventNavigation = function () {
            _preventNavigation = true;
            _preventNavigationUrl = $scope.$state.current.url;
        };
        /*拦截器-检查菜单是否修改过 ConfirmModal ---start*/
        $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
            $scope.url = toState.url.replaceAll('/', '').replaceAll('-', '');
            if (_preventNavigationUrl != fromState.url || _preventNavigationUrl == null) {
                $rootScope.allowNavigation();
            }
            if (_preventNavigation) {
                $.modal({
                    title: '你确定要放弃当前编辑内容吗？',
                    buttons: [
                        {
                            text: '确定',
                            onClick: function () {
                                $rootScope.allowNavigation();
                                $state.go(toState.name, toParams);
                            }
                        },
                        {
                            text: '取消',
                            onClick: function () {
                                $rootScope.preventNavigation();
                            }
                        },
                    ]
                });
                event.preventDefault();
                return;
            } else {
                $rootScope.allowNavigation();
            }
        });
        $rootScope.$on('$stateChangeSuccess', function (event, toState, toParams, fromState, fromParams) {
            //  $scope.pageLoading = false;
        });
        /*拦截器-检查菜单是否修改过 ConfirmModal ---end*/
    });

/*metronic start*/
/* Configure ocLazyLoader(refer: https://github.com/ocombe/ocLazyLoad) */
App.config(['$ocLazyLoadProvider', function($ocLazyLoadProvider) {
    $ocLazyLoadProvider.config({
        // global configs go here
    });
}]);

//AngularJS v1.3.x workaround for old style controller declarition in HTML
App.config(['$controllerProvider', function($controllerProvider) {
    // this option might be handy for migrating old apps, but please don't use it
    // in new ones!
    $controllerProvider.allowGlobals();
}]);

/********************************************
 END: BREAKING CHANGE in AngularJS v1.3.x:
 *********************************************/

/* Setup global settings */
App.factory('settings', ['$rootScope', function($rootScope) {
    // supported languages
    var settings = {
        layout: {
            pageSidebarClosed: false, // sidebar menu state
            pageContentWhite: true, // set page content layout
            pageBodySolid: false, // solid body color state
            pageAutoScrollOnLoad: 1000 // auto scroll to top on page load
        },
        assetsPath: '../resources/metronic',
        globalPath: '../resources/metronic/global',
        layoutPath: '../resources/metronic/layouts/layout2',
    };

    $rootScope.settings = settings;

    return settings;
}]);


/* Setup Layout Part - Header */
App.controller('HeaderController', ['$scope', function($scope) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initHeader(); // init header
    });
}]);

/* Setup Layout Part - Sidebar */
App.controller('SidebarController', ['$state', '$scope','$rootScope','$http', function($state, $scope,$rootScope,$http) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initSidebar($state); // init sidebar
    });
    $scope.onInit = function () {

        $http.get(ctx + '/menu/getMenuByRoleId?roleId=1').success(function (res) {
            $rootScope.menu = res.data.menus;
            // console.info($rootScope.menu );
            $scope.firstMenu = [];

            angular.forEach($rootScope.menu,function (menu,index) {
                if(menu.parentMenu ==0){
                    $scope.firstMenu.push(menu);
                }
            })

            $scope.secondMenu =[];

            angular.forEach($rootScope.menu,function (menu,index) {
                if(menu.parentMenu > 0){
                    $scope.secondMenu.push(menu);
                }
            })
        })


        // console.info($scope.firstMenu);
        // console.info($scope.secondMenu);
    }

    $scope.onInit();
}]);
/* Setup Layout Part - Footer */
App.controller('FooterController', ['$scope', function($scope) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initFooter(); // init footer
    });
}]);
/*metronic end*/


String.prototype.replaceAll = function (s1, s2) {
    var temp = this;
    while (temp.indexOf(s1) != -1) {
        temp = temp.replace(s1, s2);
    }
    return temp;
}
/***
 GLobal Directives
 ***/

// Route State Load Spinner(used on page or content load)
App.directive('ngSpinnerBar', ['$rootScope', '$state',
    function($rootScope, $state) {
        return {
            link: function(scope, element, attrs) {
                // by defult hide the spinner bar
                element.addClass('hide'); // hide spinner bar by default

                // display the spinner bar whenever the route changes(the content part started loading)
                $rootScope.$on('$stateChangeStart', function() {
                    element.removeClass('hide'); // show spinner bar
                });

                // hide the spinner bar on rounte change success(after the content loaded)
                $rootScope.$on('$stateChangeSuccess', function(event) {
                    element.addClass('hide'); // hide spinner bar
                    $('body').removeClass('page-on-load'); // remove page loading indicator
                    Layout.setAngularJsSidebarMenuActiveLink('match', null, event.currentScope.$state); // activate selected link in the sidebar menu

                    // auto scorll to page top
                    setTimeout(function () {
                        App.scrollTop(); // scroll to the top on content load
                    }, $rootScope.settings.layout.pageAutoScrollOnLoad);
                });

                // handle errors
                $rootScope.$on('$stateNotFound', function() {
                    element.addClass('hide'); // hide spinner bar
                });

                // handle errors
                $rootScope.$on('$stateChangeError', function() {
                    element.addClass('hide'); // hide spinner bar
                });
            }
        };
    }
])

// Handle global LINK click
App.directive('a', function() {
    return {
        restrict: 'E',
        link: function(scope, elem, attrs) {
            if (attrs.ngClick || attrs.href === '' || attrs.href === '#') {
                elem.on('click', function(e) {
                    e.preventDefault(); // prevent link click for above criteria
                });
            }
        }
    };
});

// Handle Dropdown Hover Plugin Integration
App.directive('dropdownMenuHover', function () {
    return {
        link: function (scope, elem) {
            elem.dropdownHover();
        }
    };
});

/* Init global settings and run the app */
App .run(['$rootScope', '$state', '$stateParams', "settings",function ($rootScope, $state, $stateParams,settings) {
    $rootScope.$state = $state;
    $rootScope.$stateParams = $stateParams;
    $rootScope.$settings = settings; // state to be accessed from view//
}]);