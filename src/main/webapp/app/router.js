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
                 templateUrl: helper.basepath('member/html/member.html'),
                 resolve: helper.resolveFor('member'),
                 controller: 'memberCtrl'
         })
         .state('app.memberInfo',{
                url: '/memberInfo',
                templateUrl: helper.basepath('member/html/info.html'),
                resolve: helper.resolveFor('member.info'),
                controller: 'memberInfoCtrl'
         })
         .state('app.order',{
                url: '/order',
                templateUrl: helper.basepath('order/html/orderList.html'),
                resolve: helper.resolveFor('order'),
                controller: 'orderCtrl'
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
    }]);