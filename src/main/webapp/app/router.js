angular.module('app').config([
    '$stateProvider', '$urlRouterProvider', '$locationProvider', 'RouteHelpersProvider',
    function ($stateProvider, $urlRouterProvider, $locationProvider, helper) {
        // $locationProvider.hashPrefix('main');
        $urlRouterProvider.otherwise('/app/home');
        $stateProvider
            .state('app', {
                abstract: true,
                url: '/app',
                templateUrl: helper.basepath('common/layout.html')
            })
            .state('app.home', {
                url: '/home',
                templateUrl: helper.basepath('common/home.html'),
                resolve: helper.resolveFor('home'),
                controller: 'homeCtrl'
            })
          .state('app.recommend', {
                url: '/recommend-list',
                templateUrl: helper.basepath('recommend/html/list.html'),
                resolve: helper.resolveFor('recommend.list'),
                controller: 'recommendListCtrl'
         })
         .state('app.recommendAdd', {
                url: '/recommendAdd',
                templateUrl: helper.basepath('recommend/html/add.html'),
                resolve: helper.resolveFor('recommend.add'),
                controller: 'recommendAddCtrl'
         })
         .state('app.member',{
                 url: '/member',
                 abstract: true,
                 templateUrl: helper.basepath('member/html/member.html'),
                 resolve: helper.resolveFor('member'),
                 controller: 'memberCtrl'
         })
         .state('app.member.info',{
                url: '/member-info',
                templateUrl: helper.basepath('member/html/info.html'),
                resolve: helper.resolveFor('member.info'),
                controller: 'memberInfoCtrl'
         })
         .state('app.member.overview',{
                url: '/member-overview',
                templateUrl: helper.basepath('member/html/overview.html'),
                resolve: helper.resolveFor('member.overview'),
                controller: 'memberOverviewCtrl'
         })
         .state('app.account',{
                url: '/account',
                templateUrl: helper.basepath('account/html/list.html'),
                resolve: helper.resolveFor('account'),
                controller: 'accountListCtrl'
          })
         .state('app.advance',{
                url: '/advance',
                templateUrl: helper.basepath('advance/html/list.html'),
                resolve: helper.resolveFor('advance.list'),
                controller: 'advanceListCtrl'
          })
          .state('app.advanceAdd',{
                url: '/advance-add',
                templateUrl: helper.basepath('advance/html/add.html'),
                resolve: helper.resolveFor('advance.add'),
                controller: 'advanceAddCtrl'
          })
           .state('app.bonus',{
                url: '/bonus',
                templateUrl: helper.basepath('bonus/html/list.html'),
                resolve: helper.resolveFor('bonus'),
                controller: 'bonusCtrl'
            })
            .state('app.dividend',{
                url: '/dividend',
                templateUrl: helper.basepath('dividend/html/list.html'),
                resolve: helper.resolveFor('dividend'),
                controller: 'dividendCtrl'
            })
            .state('app.graph',{
                url: '/graph',
                templateUrl: helper.basepath('graph/html/graph.html'),
                resolve: helper.resolveFor('graph'),
                controller: 'graphCtrl'
            })
           .state('app.order',{
                url: '/order',
                templateUrl: helper.basepath('order/html/list.html'),
                resolve: helper.resolveFor('order'),
                controller: 'orderCtrl'
            })
            .state('app.admMember',{
                url: '/admMember',
                templateUrl: helper.basepath('admMember/html/list.html'),
                resolve: helper.resolveFor('admMember'),
                controller: 'admMemberCtrl'
            })
            .state('app.admOperator',{
                url: '/admOperator',
                templateUrl: helper.basepath('admOperator/html/list.html'),
                resolve: helper.resolveFor('admOperator'),
                controller: 'admOperatorCtrl'
            })
            .state('app.admDividend',{
                url: '/admDividend',
                templateUrl: helper.basepath('admDividend/html/list.html'),
                resolve: helper.resolveFor('admDividend'),
                controller: 'admDividendCtrl'
            })
            .state('app.admBonus',{
                url: '/admBonus',
                templateUrl: helper.basepath('admBonus/html/list.html'),
                resolve: helper.resolveFor('admBonus'),
                controller: 'admBonusCtrl'
            })
            .state('app.admRecommend',{
                url: '/admRecommend',
                templateUrl: helper.basepath('admRecommend/html/list.html'),
                resolve: helper.resolveFor('admRecommend'),
                controller: 'admRecommendCtrl'
            })
            .state('app.admOrder',{
                url: '/admOrder',
                templateUrl: helper.basepath('admOrder/html/list.html'),
                resolve: helper.resolveFor('admOrder'),
                controller: 'admOrderCtrl'
            })
            .state('app.admAdvance',{
                url: '/admAdvance',
                templateUrl: helper.basepath('admAdvance/html/list.html'),
                resolve: helper.resolveFor('admAdvance'),
                controller: 'admAdvanceCtrl'
            })
            .state('app.admProduct',{
                url: '/admProduct',
                templateUrl: helper.basepath('admProduct/html/list.html'),
                resolve: helper.resolveFor('admProduct'),
                controller: 'admProductCtrl'
            })
            .state('app.admBasicSetting',{
                url: '/admBasicSetting',
                templateUrl: helper.basepath('admBasicSetting/html/list.html'),
                resolve: helper.resolveFor('admBasicSetting'),
                controller: 'admBasicSettingCtrl'
            })
            .state('app.admPermission',{
                url: '/admPermission',
                templateUrl: helper.basepath('admPermission/html/list.html'),
                resolve: helper.resolveFor('admPermission'),
                controller: 'admPermissionCtrl'
            })
            .state('app.admin',{
                url: '/admin',
                templateUrl: helper.basepath('admin/html/list.html'),
                resolve: helper.resolveFor('admin'),
                controller: 'adminCtrl'
            })
    }]);