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

    }]);